package com.scr.alertix.Ui.Main.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.Request.RegisterRequest;
import com.scr.alertix.Data.Repository.UsuarioRepository;
import com.scr.alertix.Pojo.Usuario;
import com.scr.alertix.Ui.Main.MenuPrincipal;
import com.scr.alertix.R;
import com.scr.alertix.Utils.SessionManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarCuartaPagina extends AppCompatActivity {

    EditText edtContrasenia, edtConfirmarContrasenia, edtEmail,edtUsuarioName;

    String nombre, apellido, genero, fechaNacimiento;

    Long idDirecion;

    Button btnContinuar;

    RegisterRequest request;

    UsuarioRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_cuarta_pagina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtContrasenia = findViewById(R.id.edtContrasenia);
        edtConfirmarContrasenia = findViewById(R.id.edtConfirmarContrasenia);
        btnContinuar = findViewById(R.id.btnRegistrar3Continuar);
        edtUsuarioName = findViewById(R.id.edtUsuarioName);


        repository = new UsuarioRepository();
        request= new RegisterRequest();

        Intent intentRecibido = getIntent();
        nombre = intentRecibido.getStringExtra("nombre");
        apellido = intentRecibido.getStringExtra("apellido");
        genero = intentRecibido.getStringExtra("genero");
        idDirecion = intentRecibido.getLongExtra("idDireccion", -1L);
        fechaNacimiento=intentRecibido.getStringExtra("fechaNacimiento");


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String finalEmail = edtEmail.getText().toString().trim();
                final String finalContrasenia = edtContrasenia.getText().toString();
                String confirmarContrasenia = edtConfirmarContrasenia.getText().toString();

                if (!finalEmail.isEmpty() && !finalContrasenia.isEmpty() && !confirmarContrasenia.isEmpty()) {
                    if (finalContrasenia.equals(confirmarContrasenia)) {

                        request.setUsuarioNombre(edtUsuarioName.getText().toString());
                        request.setNombre(nombre);
                        request.setApellido(apellido);
                        request.setGeneroUsuario(genero);
                        request.setCorreoUsuario(finalEmail);
                        request.setContraseniaUsuario(finalContrasenia);
                        request.setIdProfile(2);
                        request.setIdDireccion(new RegisterRequest.DireccionId(idDirecion));
                        request.setImgPerfil(null);
                        request.setFechaNacimiento(fechaNacimiento);

                        repository.registrarUsuario(request, new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.isSuccessful() && response.body() != null) {

                                    Toast.makeText(RegistrarCuartaPagina.this, "¡Cuenta creada con éxito!", Toast.LENGTH_SHORT).show();

                                    Integer idUsuario=response.body();
                                    Long idUsuarioLong = idUsuario.longValue();
                                    SessionManager sessionManager = new SessionManager(RegistrarCuartaPagina.this);
                                    sessionManager.saveSession(idUsuarioLong,2,idDirecion);

                                    Intent intent = new Intent(RegistrarCuartaPagina.this, MenuPrincipal.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("idUsuario", sessionManager.getUserId());
                                    intent.putExtra("idProfile", 2);
                                    intent.putExtra("idDireccion",sessionManager.getDireccionId());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    if(response.code()==400){
                                        Toast.makeText(RegistrarCuartaPagina.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Toast.makeText(RegistrarCuartaPagina.this,
                                            "Error al registrar: " + response.code(), Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Toast.makeText(RegistrarCuartaPagina.this, "Falla de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("Error de red: " + t.getMessage(), t.getMessage());

                            }
                        });
                    } else {
                        edtConfirmarContrasenia.setError("Las contraseñas no coinciden");
                    }
                } else {
                    if (finalEmail.isEmpty()) edtEmail.setError("Obligatorio");
                    if (finalContrasenia.isEmpty()) edtContrasenia.setError("Obligatorio");
                    if (confirmarContrasenia.isEmpty()) edtConfirmarContrasenia.setError("Obligatorio");
                }
            }
        });
    }
}
