package com.scr.alertix;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class IniciarSesion extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnIniciarSesion, btnCrearCuenta;

    Intent login;

    String email, password;

    List<Usuario> listaUsuarios;

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
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Admin", "admin@correo.com", "1234", "Administrador"));
        listaUsuarios.add(new Usuario("Jonathan", "jonathansie42@gmail.com", "abc", "Usuario"));

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    email = edtEmail.getText().toString();
                    password = edtPassword.getText().toString();

                    boolean encontrado = false;

                    for (Usuario u : listaUsuarios){
                        if (u.getCorreo().equals(email) && u.getContraseña().equals(password)){
                            encontrado = true;
                            String nombre = u.getNombre();
                            Toast.makeText(IniciarSesion.this, "Bienvenido " + nombre, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    if (encontrado) {
                        Intent login = new Intent(IniciarSesion.this, MainActivity.class);
                        login.putExtra("lista_usuarios", (java.io.Serializable) listaUsuarios);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(IniciarSesion.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception error){

                }
            }
        });
    }
}