package com.scr.alertix.Ui.Adapter.Main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scr.alertix.Data.Model.PublicacionDTO;
import com.scr.alertix.Data.Repository.PublicacionRepository;
import com.scr.alertix.Pojo.Publicaciones;
import com.scr.alertix.R;
import com.scr.alertix.Ui.Adapter.Adapters.PublicacionesAdapter;
import com.scr.alertix.database.Database;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView rvPublicacion;
    ArrayList<PublicacionDTO> listaPublicaciones;
    PublicacionesAdapter adapter;
    FloatingActionButton fabAgregar;
    BottomNavigationView bottomNavigationView;

    PublicacionRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        rvPublicacion = findViewById(R.id.rvPublicaciones);
        fabAgregar = findViewById(R.id.fabAgregar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        rvPublicacion.setLayoutManager(new LinearLayoutManager(this));

        listaPublicaciones = new ArrayList<>();
        repository = new PublicacionRepository();

        adapter = new PublicacionesAdapter(listaPublicaciones);
        rvPublicacion.setAdapter(adapter);

        obtenerDatosDeApi();

        fabAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, Publicar.class);
            intent.putExtra("idUsuario", getIntent().getIntExtra("idUsuario", 0));
            startActivity(intent);
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.mapa) {
                startActivity(new Intent(this, SectorizacionActivity.class));
                return true;
            } else if (id == R.id.profile) {
                // Navegar a perfil si existiera
                return true;
            } else if (id == R.id.home) {
                return true;
            }
            return false;
        });
    }

    private void obtenerDatosDeApi() {
        // Usamos el repositorio que creamos en data/repository
        repository.obtenerFeed(new Callback<List<PublicacionDTO>>() {
            @Override
            public void onResponse(Call<List<PublicacionDTO>> call, Response<List<PublicacionDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPublicaciones.clear();
                    listaPublicaciones.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MenuPrincipal.this, "Error al cargar publicaciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PublicacionDTO>> call, Throwable t) {
                Toast.makeText(MenuPrincipal.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refrescamos los datos de la API cada vez que volvemos a la pantalla
        obtenerDatosDeApi();
    }
}
