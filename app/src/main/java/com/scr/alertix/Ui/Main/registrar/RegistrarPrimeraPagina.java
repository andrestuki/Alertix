package com.scr.alertix.Ui.Main.registrar;

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

    EditText edtNombre, edtApellido,edtFechaNacimiento;

    Button btnContinuar;
    String fechaSeleccionada = "";
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
        edtFechaNacimiento = findViewById(R.id.edtFechaNacimiento);
        edtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCalendario();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNombre.getText().toString().isEmpty() && !edtApellido.getText().toString().isEmpty()){
                    nombre = edtNombre.getText().toString();
                    apellido = edtApellido.getText().toString();

                    Intent primera = new Intent(RegistrarPrimeraPagina.this, RegistrarSegundaPagina.class);

                    primera.putExtra("nombre", nombre);
                    primera.putExtra("apellido", apellido);
                    primera.putExtra("fechaNacimiento", fechaSeleccionada);
                    startActivity(primera);
                }
                else {
                    edtNombre.setError("Este campo es obligatorio");
                    edtApellido.setError("Este campo es obligatorio");
                }
            }
        });
    }
    private void mostrarCalendario() {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(java.util.Calendar.YEAR);
        int month = c.get(java.util.Calendar.MONTH);
        int day = c.get(java.util.Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Formatear para que siempre tenga 2 dígitos (ej: 05 en vez de 5)
                    String diaStr = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                    String mesStr = ((monthOfYear + 1) < 10) ? "0" + (monthOfYear + 1) : String.valueOf(monthOfYear + 1);

                    fechaSeleccionada = year1 + "-" + mesStr + "-" + diaStr;
                    edtFechaNacimiento.setText(fechaSeleccionada);
                }, year, month, day);
        datePickerDialog.show();
    }
}