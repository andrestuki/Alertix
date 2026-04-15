package com.scr.alertix;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuariosActivity extends AppCompatActivity {

    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.listview_prueba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView ListViewUsuarios = findViewById(R.id.lvUsuarios);
        listaUsuarios = new ArrayList<>();

        listaUsuarios = (List<Usuario>) getIntent().getSerializableExtra("lista_usuarios");

        if (listaUsuarios == null) {
            listaUsuarios = new ArrayList<>();
        }


        UsuarioAdapter adapter = new UsuarioAdapter(this, listaUsuarios);
        ListViewUsuarios.setAdapter(adapter);
    }
}
