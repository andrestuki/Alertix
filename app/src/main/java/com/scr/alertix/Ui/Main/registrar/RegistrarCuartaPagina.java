package com.scr.alertix.Ui.Main.registrar;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.DragAndDropPermissionsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.Request.DireccionesRequest;
import com.scr.alertix.Data.Repository.DireccionesRepository;
import com.scr.alertix.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarCuartaPagina extends AppCompatActivity {

    private EditText edtPais, edtDepartamento, edtMunicipio, edtCiudad, edtBarrio, edtDireccion, edtCodigoPostal;
    private Button btnRegistrar, btnOmitir;

    private DireccionesRepository dirRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuarta_pagina);

        // Configuración de insets para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Inicializar componentes
        edtPais = findViewById(R.id.edtPais);
        edtDepartamento = findViewById(R.id.edtDepartamento);
        edtMunicipio = findViewById(R.id.edtMunicipio);
        edtCiudad = findViewById(R.id.edtCiudad);
        edtBarrio = findViewById(R.id.edtBarrio);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtCodigoPostal = findViewById(R.id.edtCodigoPostal);
        btnRegistrar = findViewById(R.id.btnRegistrarDireccion);
        btnOmitir = findViewById(R.id.btnOmitirDireccion);

        Intent intentRecibido = getIntent();
        String nombre = intentRecibido.getStringExtra("nombre");
        String apellido = intentRecibido.getStringExtra("apellido");
        String genero = intentRecibido.getStringExtra("genero");




    }
    public void registrarDirecciones(String barrio, String direccion, String pais, String ciudad,
                                     String departamento, String municipio, String codigoPostal){

        String direccionCompleta = direccion + ", " + barrio + ", " + ciudad + ", " + pais;


        double latitud = 0.0;
        double longitud = 0.0;

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(direccionCompleta, 1);
            if (addresses != null && !addresses.isEmpty()) {
                latitud = addresses.get(0).getLatitude();
                longitud = addresses.get(0).getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al obtener coordenadas, se usará 0.0", Toast.LENGTH_SHORT).show();
        }
        DireccionesRequest direccionesRequest = new DireccionesRequest();
        direccionesRequest.setBarrio(barrio);
        direccionesRequest.setDireccion(direccion);
        direccionesRequest.setPais(pais);
        direccionesRequest.setCiudad(ciudad);
        direccionesRequest.setDepartamento(departamento);
        direccionesRequest.setMunicipio(municipio);
        direccionesRequest.setCodigoPostal(codigoPostal);
        direccionesRequest.setLatitud(latitud);
        direccionesRequest.setLongitud(longitud);
        dirRepository.registrarDireccion(direccionesRequest, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer idDireccion = response.body();
                    enviarSiguientePagina(idDireccion);
                } else {
                    Toast.makeText(RegistrarCuartaPagina.this, "Error al guardar dirección", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(RegistrarCuartaPagina.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void enviarSiguientePagina(Integer idDireccion) {
        Intent intent = new Intent(this, RegistrarTerceraPagina.class);
        Intent intentActual = getIntent();
        String nombre = intentActual.getStringExtra("nombre");
        String apellido = intentActual.getStringExtra("apellido");
        String genero = intentActual.getStringExtra("genero");

        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("genero", genero);
        if (idDireccion != null) {
            intent.putExtra("idDireccion", idDireccion);
        }
        startActivity(intent);
    }
}
