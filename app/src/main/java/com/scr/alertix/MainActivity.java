package com.scr.alertix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String tipo;
    ArrayList<Usuario> usuarios;
    EditText edtNombre,edtContraseña,edtCorreo,edtConfirmarContraseña;
    RadioButton rbAutoridad;
    RadioButton rbCiudadano;
    RadioButton rbLider;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarios = new ArrayList<>();

        edtNombre=findViewById(R.id.edtNombre);

        edtCorreo=findViewById(R.id.edtEmail);

        edtContraseña=findViewById(R.id.edtContraseña);

        edtConfirmarContraseña=findViewById(R.id.edtConfirmarContraseña);

        rbCiudadano=findViewById(R.id.rbCiudadano);

        rbAutoridad=findViewById(R.id.rbAutoridad);

        rbLider=findViewById(R.id.rbLider);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNombre.getText().toString().isEmpty() && !edtCorreo.getText().toString().isEmpty() && !edtContraseña.getText().toString().isEmpty() &&
                        !edtConfirmarContraseña.getText().toString().isEmpty())
                {
                    if(edtContraseña.getText().toString().equals(edtConfirmarContraseña.getText().toString())) {
                        if(rbCiudadano.isChecked())
                        {
                            tipo = "Ciudadano";
                        }else if(rbAutoridad.isChecked())
                        {
                            tipo = "Autoridad";
                        }else if(rbLider.isChecked())
                        {
                            tipo = "Lider";
                        }
                        String nombre = edtNombre.getText().toString();
                        String correo = edtCorreo.getText().toString();
                        String contraseña = edtContraseña.getText().toString();
                        usuarios.add(new Usuario(nombre, correo, contraseña,tipo));
                        Toast.makeText(MainActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}








