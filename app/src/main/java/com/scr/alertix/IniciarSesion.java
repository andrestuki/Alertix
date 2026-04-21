package com.scr.alertix;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.database.Database;
import com.scr.alertix.registrar.RegistrarPrimeraPagina;

import java.util.ArrayList;

public class IniciarSesion extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnIniciarSesion, btnCrearCuenta;
    Database dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciar_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        dbHelper = new Database(this);
        db = dbHelper.getReadableDatabase();

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()) {
                    // Consultar si el usuario existe
                    Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE correoUsuario=? AND contraseniaUsuario=?", new String[]{email, pass});

                    if (cursor.moveToFirst()) {
                        // Usuario encontrado
                        Toast.makeText(IniciarSesion.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        int idIndex = cursor.getColumnIndexOrThrow("idUsuario");
                        int idUsuario = cursor.getInt(idIndex);
                        Intent intent = new Intent(IniciarSesion.this, MenuPrincipal.class);
                        intent.putExtra("idUsuario", idUsuario);

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(IniciarSesion.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                } else {
                    Toast.makeText(IniciarSesion.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, RegistrarPrimeraPagina.class);
                startActivity(intent);
            }
        });
    }
}
