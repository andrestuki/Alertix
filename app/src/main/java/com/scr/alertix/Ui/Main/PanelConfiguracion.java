package com.scr.alertix.Ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.scr.alertix.R;
import com.scr.alertix.Ui.Main.IniciarSesion;
import com.scr.alertix.Utils.SessionManager;

public class PanelConfiguracion extends AppCompatActivity {

    private ImageButton btnBack;
    private LinearLayout optionEditProfile, optionAddAddress, optionManageUsers, optionSystemReports, optionLogout;
    private TextView txtAdminTitle;
    private LinearLayout layoutAdmin;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_panel_configuracion);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);

        // Inicializar vistas
        btnBack = findViewById(R.id.btnBack);
        optionEditProfile = findViewById(R.id.optionEditProfile);
        optionAddAddress = findViewById(R.id.optionAddAddress);
        optionManageUsers = findViewById(R.id.optionManageUsers);
        optionLogout = findViewById(R.id.optionLogout);
        txtAdminTitle = findViewById(R.id.txtAdminTitle);
        layoutAdmin = findViewById(R.id.layoutAdmin);


        int idProfile = getSharedPreferences("AlertixSession", MODE_PRIVATE).getInt("idProfile", 0);
        if (idProfile != 1) {
            if (layoutAdmin != null) {
                layoutAdmin.setVisibility(View.GONE);
            }
        }

        btnBack.setOnClickListener(v -> finish());

        optionEditProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Editar Perfil próximamente", Toast.LENGTH_SHORT).show();
        });

        optionAddAddress.setOnClickListener(v -> {
            Toast.makeText(this, "Funcionalidad de agregar dirección", Toast.LENGTH_SHORT).show();
        });

        optionManageUsers.setOnClickListener(v -> {
            Toast.makeText(this, "Panel de gestión de usuarios", Toast.LENGTH_SHORT).show();
        });

        optionLogout.setOnClickListener(v -> {
            cerrarSesion();
        });
    }

    private void cerrarSesion() {
        sessionManager.logout();
        Intent intent = new Intent(PanelConfiguracion.this, com.scr.alertix.Ui.Main.IniciarSesion.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}