package com.scr.alertix.Ui.Main.registrar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.RegisterRequest;
import com.scr.alertix.Data.Repository.UsuarioRepository;
import com.scr.alertix.Pojo.Usuario;
import com.scr.alertix.Ui.Main.MenuPrincipal;
import com.scr.alertix.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarTerceraPagina extends AppCompatActivity {

    EditText edtContrasenia, edtConfirmarContrasenia, edtEmail;

    String email,contrasenia,nombre,apellido,genero,idprofile;

    Button btnContinuar;

    SQLiteDatabase db;
    UsuarioRepository repository;

    Intent i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_tercera_pagina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtContrasenia = findViewById(R.id.edtContrasenia);
        edtConfirmarContrasenia = findViewById(R.id.edtConfirmarContrasenia);
        btnContinuar = findViewById(R.id.btnRegistrar3Continuar);

        repository = new UsuarioRepository();


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String finalEmail = edtEmail.getText().toString();
                final String finalContrasenia = edtContrasenia.getText().toString();
                String confirmarContrasenia = edtConfirmarContrasenia.getText().toString();

                if (!finalEmail.isEmpty() && !finalContrasenia.isEmpty() && !confirmarContrasenia.isEmpty()) {
                    if (finalContrasenia.equals(confirmarContrasenia)) {

                        i=getIntent();
                        nombre=i.getStringExtra("nombre");
                        apellido=i.getStringExtra("apellido");
                        genero=i.getStringExtra("genero");

                        RegisterRequest request = new RegisterRequest(nombre, apellido, genero, finalEmail, finalContrasenia, 2);

                        repository.registrarUsuario(request, new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if (response.isSuccessful()) {
                                    // Guardar en SQLite opcionalmente o solo navegar
                                    ContentValues values = new ContentValues();
                                    values.put("nombreUsuario", nombre);
                                    values.put("apellidoUsuario", apellido);
                                    values.put("generoUsuario", genero);
                                    values.put("contraseniaUsuario", finalContrasenia);
                                    values.put("correoUsuario", finalEmail);
                                    values.put("idProfile", 2);
                                    db.insert("usuarios", null, values);

                                    android.widget.Toast.makeText(RegistrarTerceraPagina.this,
                                            "¡Cuenta creada con éxito!", android.widget.Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(RegistrarTerceraPagina.this, MenuPrincipal.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    android.widget.Toast.makeText(RegistrarTerceraPagina.this,
                                            "Error al registrar: " + response.code(), android.widget.Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                android.widget.Toast.makeText(RegistrarTerceraPagina.this,
                                        "Falla de red: " + t.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
                            }
                        });




                    } else {
                        edtConfirmarContrasenia.setError("Las contraseñas no coinciden");
                    }
                } else {
                    if (email.isEmpty()) edtEmail.setError("Obligatorio");
                    if (contrasenia.isEmpty()) edtContrasenia.setError("Obligatorio");
                    if (confirmarContrasenia.isEmpty()) edtConfirmarContrasenia.setError("Obligatorio");
                }
            }
        });
    }
}
