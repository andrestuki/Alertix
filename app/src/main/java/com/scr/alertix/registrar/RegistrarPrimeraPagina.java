package com.scr.alertix.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.R;

public class RegistrarPrimeraPagina extends AppCompatActivity {

    EditText edtNombre, edtApellido;
    Button btnContinuar;

    String nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_primera_pagina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        btnContinuar = findViewById(R.id.btnRegistrar1Continuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNombre.getText().toString().isEmpty() && !edtApellido.getText().toString().isEmpty()){
                    nombre = edtNombre.getText().toString();
                    apellido = edtApellido.getText().toString();

                    Intent primera = new Intent(RegistrarPrimeraPagina.this, RegistrarSegundaPagina.class);

                    primera.putExtra("nombre", nombre);
                    primera.putExtra("apellido", apellido);
                    startActivity(primera);
                    finish();


                }
                else {
                    edtNombre.setError("Este campo es obligatorio");
                    edtApellido.setError("Este campo es obligatorio");
                }
            }
        });
    }
}