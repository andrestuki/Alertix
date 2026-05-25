package com.scr.alertix.Ui.Main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scr.alertix.Data.Model.Request.PublicacionRequest;
import com.scr.alertix.Data.Model.DTO.TipoCategoriasDTO;
import com.scr.alertix.Data.Repository.CategoriasRepository;
import com.scr.alertix.Data.Repository.PublicacionRepository;
import com.scr.alertix.R;
import com.scr.alertix.Utils.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Publicar extends AppCompatActivity {
    AutoCompleteTextView comboTipoAlerta;
    EditText edtDescripcion;
    Button btnPublicar, btnSeleccionarImagen;
    ImageView imgPreview;

    SessionManager sessionManager;
    Long idUsuario;
    PublicacionRepository repository;
    CategoriasRepository catRepository;
    private List<TipoCategoriasDTO> listaCategoriasGlobal;
    private int idCategoriaDetectado = 0;

    Intent i;
    String tipoSeleccionado = "";
    String base64Image = "";

    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    imgPreview.setImageURI(uri);
                    base64Image = uriToBase64(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publicar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);

        comboTipoAlerta = findViewById(R.id.comboTipoAlerta);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnPublicar = findViewById(R.id.btnPublicar);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        imgPreview = findViewById(R.id.imgPreview);

        repository = new PublicacionRepository();
        catRepository = new CategoriasRepository();
        i = getIntent();

        cargarCategorias();

        btnSeleccionarImagen.setOnClickListener(v -> mGetContent.launch("image/*"));

        // CORRECCIÓN: Obtener el objeto seleccionado correctamente incluso con filtros
        comboTipoAlerta.setOnItemClickListener((adapterView, view, position, id) -> {
            TipoCategoriasDTO seleccionado = (TipoCategoriasDTO) adapterView.getItemAtPosition(position);
            if (seleccionado != null) {
                Integer idCat = seleccionado.getIdCategoria();
                idCategoriaDetectado = (idCat != null) ? idCat : 0;
                tipoSeleccionado = seleccionado.getNombreCategoria();

                Log.d("API_CAT",
                        "ID: " + seleccionado.getIdCategoria()
                                + " Nombre: " + seleccionado.getNombreCategoria());
            }
        });

        btnPublicar.setOnClickListener(view -> {
            idUsuario = sessionManager.getUserId();
            String descripcion = edtDescripcion.getText().toString().trim();
            String textoTipoAlerta = comboTipoAlerta.getText().toString().trim();

            // RESPALDO: Si el usuario escribió y no hizo clic, buscamos el ID por texto
            if (idCategoriaDetectado == 0 && !textoTipoAlerta.isEmpty() && listaCategoriasGlobal != null) {
                for (TipoCategoriasDTO cat : listaCategoriasGlobal) {
                    if (cat.getNombreCategoria() != null && cat.getNombreCategoria().equalsIgnoreCase(textoTipoAlerta)) {
                        Integer idCat = cat.getIdCategoria();
                        idCategoriaDetectado = (idCat != null) ? idCat : 0;
                        tipoSeleccionado = cat.getNombreCategoria();
                        Log.d("API_CAT", "Encontrado por texto: " + idCategoriaDetectado);
                        break;
                    }
                }
            }

            if (descripcion.isEmpty()) {
                Toast.makeText(Publicar.this, "Escribe una descripción para el reporte", Toast.LENGTH_SHORT).show();
            } else if (idCategoriaDetectado == 0) {
                Toast.makeText(Publicar.this, "Selecciona un tipo de alerta", Toast.LENGTH_SHORT).show();
            } else {
                PublicacionRequest nuevaP = new PublicacionRequest();
                nuevaP.setDescripcion(descripcion);
                nuevaP.setImg(base64Image);
                nuevaP.setIdUsuarios(new PublicacionRequest.UsuarioId(idUsuario));
                nuevaP.setCategorias(new PublicacionRequest.CategoriaId(idCategoriaDetectado));
                nuevaP.setIdDireccion(new PublicacionRequest.DireccionId(1));

                repository.crearPublicacion(nuevaP, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Publicar.this, "¡Alerta publicada!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Publicar.this, "Error del servidor: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Publicar.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cargarCategorias() {
        catRepository.obtenerFeed(new Callback<List<TipoCategoriasDTO>>() {
            @Override
            public void onResponse(Call<List<TipoCategoriasDTO>> call, Response<List<TipoCategoriasDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaCategoriasGlobal = response.body();
                    Log.d("API_DEBUG", "JSON Recibido: " + new com.google.gson.Gson().toJson(listaCategoriasGlobal));

                    ArrayAdapter<TipoCategoriasDTO> adapter = new ArrayAdapter<>(
                            Publicar.this,
                            android.R.layout.simple_list_item_1,
                            listaCategoriasGlobal
                    );
                    comboTipoAlerta.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TipoCategoriasDTO>> call, Throwable t) {
                Toast.makeText(Publicar.this, "Error al cargar categorías", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String uriToBase64(Uri uri) {
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            if (bitmap == null) return "";

            // Redimensionar a max 800px para ahorrar espacio
            int maxSize = 800;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > maxSize || height > maxSize) {
                float ratio = (float) width / (float) height;
                if (ratio > 1) {
                    width = maxSize;
                    height = (int) (maxSize / ratio);
                } else {
                    height = maxSize;
                    width = (int) (maxSize * ratio);
                }
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); // Compresión al 50%
            return Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
        } catch (Exception e) {
            return "";
        }
    }
}
