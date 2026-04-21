package com.scr.alertix;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scr.alertix.database.Database;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity {

    RecyclerView rvPublicacion;
    ArrayList<Publicaciones> publicaciones;
    FloatingActionButton fabAgregar;
    Database dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        rvPublicacion = findViewById(R.id.rvPublicaciones);
        fabAgregar = findViewById(R.id.fabAgregar);
        rvPublicacion.setLayoutManager(new LinearLayoutManager(this));
        
        dbHelper = new Database(this);
        db = dbHelper.getReadableDatabase();
        
        publicaciones = new ArrayList<>();
        cargarPublicaciones();

        PublicacionesAdapter adapter = new PublicacionesAdapter(publicaciones);
        rvPublicacion.setAdapter(adapter);

        fabAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, Publicar.class);
            // Pasamos el ID del usuario que inició sesión para que pueda publicar
            intent.putExtra("idUsuario", getIntent().getIntExtra("idUsuario", 0));
            startActivity(intent);
        });
    }

    private void cargarPublicaciones() {
        publicaciones.clear();
        String query = "SELECT p.*, u.nombreUsuario " +
                      "FROM publicaciones p " +
                      "INNER JOIN usuarios u ON p.idUsuario = u.idUsuario " +
                      "ORDER BY p.idPublicacion DESC";

        Cursor cursor = db.rawQuery(query, null);
        
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("idPublicacion"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                String nombreReal = cursor.getString(cursor.getColumnIndexOrThrow("nombreUsuario"));
                String img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
                String tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
                String lugar = cursor.getString(cursor.getColumnIndexOrThrow("lugar"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                
                publicaciones.add(new Publicaciones(id, desc, nombreReal, img, tipo, lugar, fecha));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPublicaciones();
        if (rvPublicacion.getAdapter() != null) {
            rvPublicacion.getAdapter().notifyDataSetChanged();
        }
    }
}
