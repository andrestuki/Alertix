package com.scr.alertix.registrar;

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

import com.scr.alertix.MenuPrincipal;
import com.scr.alertix.R;
import com.scr.alertix.database.Database;

public class RegistrarTerceraPagina extends AppCompatActivity {

    EditText edtContrasenia, edtConfirmarContrasenia, edtEmail;

    String email,contrasenia,nombre,apellido,genero,idprofile;

    Button btnContinuar;
    Database dbHelper;
    SQLiteDatabase db;

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


        dbHelper = new Database(this);
        db = dbHelper.getWritableDatabase();


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String contrasenia = edtContrasenia.getText().toString();
                String confirmarContrasenia = edtConfirmarContrasenia.getText().toString();

                if (!email.isEmpty() && !contrasenia.isEmpty() && !confirmarContrasenia.isEmpty()) {
                    if (contrasenia.equals(confirmarContrasenia)) {

                        i=getIntent();
                        nombre=i.getStringExtra("nombre");
                        apellido=i.getStringExtra("apellido");
                        genero=i.getStringExtra("genero");
                        contrasenia=edtContrasenia.getText().toString();
                        email=edtEmail.getText().toString();

                        ContentValues values = new ContentValues();

                        values.put("nombreUsuario", nombre);
                        values.put("apellidoUsuario", apellido);
                        values.put("generoUsuario", genero);
                        values.put("contraseniaUsuario", contrasenia);
                        values.put("correoUsuario", email);
                        values.put("idProfile", 2);
                        long resultado = db.insert("usuarios", null, values);
                        android.widget.Toast.makeText(RegistrarTerceraPagina.this,
                                "¡Cuenta creada con éxito!", android.widget.Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrarTerceraPagina.this, MenuPrincipal.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();




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
