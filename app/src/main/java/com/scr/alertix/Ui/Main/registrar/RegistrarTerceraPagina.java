package com.scr.alertix.Ui.Main.registrar;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scr.alertix.Data.Model.Request.DireccionesRequest;
import com.scr.alertix.Data.Model.Response.UbicacionResponse;
import com.scr.alertix.Data.Repository.DireccionesRepository;
import com.scr.alertix.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarTerceraPagina extends AppCompatActivity {

    private Spinner spinnerPais, spinnerDepartamento, spinnerCiudad;
    private EditText edtBarrio, edtDireccion, edtCodigoPostal;
    private Button btnRegistrar, btnOmitir;

    private DireccionesRepository dirRepository;
    private List<UbicacionResponse> paisesList;
    private List<UbicacionResponse.Departamento> departamentosActuales;
    private List<String> ciudadesActuales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tercera_pagina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        spinnerPais = findViewById(R.id.spinnerPais);
        spinnerDepartamento = findViewById(R.id.spinnerDepartamento);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        edtBarrio = findViewById(R.id.edtBarrio);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtCodigoPostal = findViewById(R.id.edtCodigoPostal);
        btnRegistrar = findViewById(R.id.btnRegistrarDireccion);
        btnOmitir = findViewById(R.id.btnOmitirDireccion);

        dirRepository = new DireccionesRepository();

        cargarUbicaciones();
        configurarSpinners();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarYRegistrar();
            }
        });

        btnOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarSiguientePagina(null);
            }
        });
    }

    private void cargarUbicaciones() {
        try {
            InputStream is = getResources().openRawResource(R.raw.ubicaciones);
            InputStreamReader reader = new InputStreamReader(is);
            Type listType = new TypeToken<List<UbicacionResponse>>() {}.getType();
            paisesList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
            paisesList = new ArrayList<>();
            Toast.makeText(this, "Error al cargar datos de ubicación", Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarSpinners() {
        List<String> nombresPaises = new ArrayList<>();
        for (UbicacionResponse p : paisesList) {
            nombresPaises.add(p.getPais());
        }

        ArrayAdapter<String> paisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresPaises);
        paisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPais.setAdapter(paisAdapter);

        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departamentosActuales = paisesList.get(position).getDepartamentos();
                List<String> nombresDeptos = new ArrayList<>();
                for (UbicacionResponse.Departamento d : departamentosActuales) {
                    nombresDeptos.add(d.getNombre());
                }
                
                ArrayAdapter<String> deptoAdapter = new ArrayAdapter<>(RegistrarTerceraPagina.this, android.R.layout.simple_spinner_item, nombresDeptos);
                deptoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDepartamento.setAdapter(deptoAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciudadesActuales = departamentosActuales.get(position).getCiudades();
                ArrayAdapter<String> ciudadAdapter = new ArrayAdapter<>(RegistrarTerceraPagina.this, android.R.layout.simple_spinner_item, ciudadesActuales);
                ciudadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCiudad.setAdapter(ciudadAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void validarYRegistrar() {
        String pais = spinnerPais.getSelectedItem() != null ? spinnerPais.getSelectedItem().toString() : "";
        String departamento = spinnerDepartamento.getSelectedItem() != null ? spinnerDepartamento.getSelectedItem().toString() : "";
        String ciudad = spinnerCiudad.getSelectedItem() != null ? spinnerCiudad.getSelectedItem().toString() : "";
        String barrio = edtBarrio.getText().toString().trim();
        String direccion = edtDireccion.getText().toString().trim();
        String codigoPostal = edtCodigoPostal.getText().toString().trim();

        if (barrio.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Por favor completa el barrio y la dirección", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pais.isEmpty() || departamento.isEmpty() || ciudad.isEmpty()){
            Toast.makeText(this, "Por favor selecione el pais/departamento/ciudad", Toast.LENGTH_SHORT).show();
            return;
        }

        registrarDirecciones(barrio, direccion, pais, ciudad, departamento, codigoPostal);
    }

    public void registrarDirecciones(String barrio, String direccion, String pais, String ciudad,
                                     String departamento, String codigoPostal) {

        String direccionCompleta = direccion + ", " + barrio + ", " + ciudad + ", " + pais;

        double latitud = 0.0;
        double longitud = 0.0;

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(direccionCompleta, 1);
            if (addresses != null && !addresses.isEmpty()) {
                latitud = addresses.get(0).getLatitude();
                longitud = addresses.get(0).getLongitude();
            } else {
                // Si no encuentra nada, avisamos y NO seguimos
                Toast.makeText(this, "No se pudieron obtener coordenadas para esta dirección", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Si hay error de red, avisamos y NO seguimos
            Toast.makeText(this, "Error de conexión al buscar coordenadas", Toast.LENGTH_SHORT).show();
            return;
        }

        DireccionesRequest direccionesRequest = new DireccionesRequest();
        direccionesRequest.setBarrio(barrio);
        direccionesRequest.setDireccion(direccion);
        direccionesRequest.setPais(pais);
        direccionesRequest.setCiudad(ciudad);
        direccionesRequest.setDepartamento(departamento);
        direccionesRequest.setCodigoPostal(codigoPostal);
        direccionesRequest.setLatitud(latitud);
        direccionesRequest.setLongitud(longitud);

        if (dirRepository == null) dirRepository = new DireccionesRepository();

        dirRepository.registrarDireccion(direccionesRequest, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {

                    enviarSiguientePagina(response.body());
                } else {
                    Toast.makeText(RegistrarTerceraPagina.this, "Error al guardar dirección", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(RegistrarTerceraPagina.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error de red: " + t.getMessage(), t.getMessage());
            }
        });
    }

    private void enviarSiguientePagina(Integer idDireccion) {
        Intent intentRecibido = getIntent();
        String nombre = intentRecibido.getStringExtra("nombre");
        String apellido = intentRecibido.getStringExtra("apellido");
        String genero = intentRecibido.getStringExtra("genero");
        String fechaSeleccionada= intentRecibido.getStringExtra("fechaNacimiento");

        Intent intent = new Intent(RegistrarTerceraPagina.this, RegistrarCuartaPagina.class);

        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("genero", genero);
        intent.putExtra("fechaNacimiento", fechaSeleccionada);
        if (idDireccion != null) {
            intent.putExtra("idDireccion", idDireccion.longValue());
        }
        startActivity(intent);
    }
}
