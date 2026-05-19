package com.scr.alertix.Ui.Adapter.Main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.scr.alertix.Data.Model.LoginResponse;
import com.scr.alertix.Data.Repository.UsuarioRepository;
import com.scr.alertix.Utils.Url;
import com.scr.alertix.R;
import com.scr.alertix.database.Database;
import com.scr.alertix.Ui.Adapter.Main.registrar.RegistrarPrimeraPagina;

import org.json.JSONException;
import org.json.JSONObject;

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
                        Intent intent = new Intent(IniciarSesion.this, MenuPrincipal.class);
                        intent.putExtra("idUsuario", res.getIdUsuario());
                        intent.putExtra("idProfile", res.getIdProfile());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(IniciarSesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(IniciarSesion.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Falla de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
