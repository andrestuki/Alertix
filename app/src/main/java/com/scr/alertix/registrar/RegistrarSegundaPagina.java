package com.scr.alertix.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.R;

public class RegistrarSegundaPagina extends AppCompatActivity {
    RadioGroup rgGenero;
    String genero;
    Button btnContinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_segunda_pagina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgGenero = findViewById(R.id.rgGenero);
        btnContinuar = findViewById(R.id.btnRegistro2Continuar);

        rgGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {

                if(i == R.id.rbMasculino)
                {
                    genero = "masculino";
                }
                else if(i == R.id.rbFemenino)
                {
                    genero="femenino";
                }
                else if(i == R.id.rbOtro)
                {
                    genero="otro";
                }

            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genero == null)
                {
                    Toast.makeText(RegistrarSegundaPagina.this, "Selecciona un genero", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent segunda = new Intent(RegistrarSegundaPagina.this, RegistrarTerceraPagina.class);
                    segunda.putExtra("genero", genero);
                    startActivity(segunda);
                }
            }
        });
    }
}