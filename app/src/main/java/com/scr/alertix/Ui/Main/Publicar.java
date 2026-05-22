package com.scr.alertix.Ui.Main;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.PublicacionDTO;
import com.scr.alertix.Data.Repository.PublicacionRepository;
import com.scr.alertix.R;
import com.scr.alertix.database.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Publicar extends AppCompatActivity {
    AutoCompleteTextView comboTipoAlerta;
    EditText edtDescripcion;
    Button btnPublicar;
    ArrayList<String> lista;
    Database dbHelper;
    SQLiteDatabase db;
    int idUsuario;
    PublicacionRepository repository;

    Intent i;
    String tipoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publicar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comboTipoAlerta = findViewById(R.id.comboTipoAlerta);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnPublicar=findViewById(R.id.btnPublicar);
        dbHelper = new Database(this);
        db = dbHelper.getWritableDatabase();
        repository = new PublicacionRepository();
        i=getIntent();
        lista = new ArrayList<>();
        lista.add("robo");
        lista.add("incendio");
        lista.add("lluvia");
        lista.add("apagon");
        lista.add("inundacion");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        comboTipoAlerta.setAdapter(adapter);


        comboTipoAlerta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                tipoSeleccionado = adapterView.getItemAtPosition(position).toString();
            }
        });

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // En Publicar.java
                idUsuario = i.getIntExtra("idUsuario", 0);

                    if (!edtDescripcion.getText().toString().isEmpty() && !tipoSeleccionado.isEmpty()) {
                        idUsuario = i.getIntExtra("idUsuario", 0);
                        ContentValues values = new ContentValues();
                        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(System.currentTimeMillis());

                        values.put("idUsuario", idUsuario);
                        values.put("descripcion", edtDescripcion.getText().toString());
                        values.put("fecha", fecha);
                        values.put("tipo", tipoSeleccionado);
                        values.put("lugar","");
                        values.put("img", "url");
                        
                        // Preparar objeto para la API
                        PublicacionDTO nuevaP = new PublicacionDTO();
                        nuevaP.setDescripcion(edtDescripcion.getText().toString());
                        nuevaP.setCategoria(tipoSeleccionado);
                        nuevaP.setFecha(fecha);
                        nuevaP.setBarrio("Santa marta, cbn");
                        nuevaP.setImagenPublicacion("url");
                        
                        repository.crearPublicacion(nuevaP, new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    db.insert("publicaciones", null, values);
                                    Toast.makeText(Publicar.this, "Alerta publicada en la red", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(Publicar.this, "Error en API: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Si falla internet, guardamos en local de todos modos
                                db.insert("publicaciones", null, values);
                                Toast.makeText(Publicar.this, "Guardado local (sin conexión)", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(Publicar.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    }

            }
        });


    }
}
