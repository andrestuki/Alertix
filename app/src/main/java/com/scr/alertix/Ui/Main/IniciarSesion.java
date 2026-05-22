package com.scr.alertix.Ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.LoginResponse;
import com.scr.alertix.Data.Repository.UsuarioRepository;
import com.scr.alertix.Utils.SessionManager;
import com.scr.alertix.R;
import com.scr.alertix.Ui.Main.registrar.RegistrarPrimeraPagina;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IniciarSesion extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnIniciarSesion, btnCrearCuenta;
    UsuarioRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MenuPrincipal.class));
            finish();
            return;
        }
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

        repository = new UsuarioRepository();

        btnIniciarSesion.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String pass = edtPassword.getText().toString();

            if (!email.isEmpty() && !pass.isEmpty()) {
                ejecutarLogin(email, pass);
            } else {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
            }
        });

        btnCrearCuenta.setOnClickListener(v -> {
            startActivity(new Intent(IniciarSesion.this, RegistrarPrimeraPagina.class));
        });
    }

    private void ejecutarLogin(String email, String pass) {
        repository.iniciarSesion(email, pass, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse res = response.body();

                    if (res.getIdUsuario() > 0) {
                        SessionManager sessionManager = new SessionManager(IniciarSesion.this);
                        sessionManager.saveSession(res.getIdUsuario(),res.getIdProfile());
                        Intent intent = new Intent(IniciarSesion.this, MenuPrincipal.class);
                        intent.putExtra("idUsuario", res.getIdUsuario());
                        intent.putExtra("idProfile", res.getIdProfile());


                        startActivity(intent);
                        finish();
                    }
                } else if (response.code() == 401) {
                // AQUÍ manejas las credenciales incorrectas (Código 401)
                Toast.makeText(IniciarSesion.this, "Usuario/contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
                else {
                    String errorMsg = "Error en el servidor: " + response.code();
                    Toast.makeText(IniciarSesion.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Falla de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
