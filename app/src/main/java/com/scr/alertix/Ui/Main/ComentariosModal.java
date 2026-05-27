package com.scr.alertix.Ui.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scr.alertix.Data.Model.DTO.ComentarioAgrupado;
import com.scr.alertix.Data.Model.DTO.ComentariosDTO;
import com.scr.alertix.Data.Model.Request.ComentariosRequest;
import com.scr.alertix.Data.Repository.ComentariosRepository;
import com.scr.alertix.R;
import com.scr.alertix.Ui.Adapter.Adapters.ComentariosAdapter;
import com.scr.alertix.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentariosModal extends BottomSheetDialogFragment {

    RecyclerView rvComentario;
    EditText etNuevoComentario;
    ImageButton btnEnviarComentario;

    ArrayList<ComentarioAgrupado> comentarios;
    ComentariosAdapter comAdapter;
    ComentariosRepository comRepository;
    private Long idPublicacion;
    SessionManager sessionManager;

    // Variable para saber si estamos respondiendo a alguien
    ComentariosDTO padreSeleccionado = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPublicacion = getArguments().getLong("idPublicacion");
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {

            getDialog().getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_comentarios, container, false);

        rvComentario = v.findViewById(R.id.rvComentarios);
        etNuevoComentario = v.findViewById(R.id.etNuevoComentario);
        btnEnviarComentario = v.findViewById(R.id.btnEnviarComentario);

        sessionManager = new SessionManager(requireContext());
        comentarios = new ArrayList<>();
        comRepository = new ComentariosRepository();

        // 1. INICIALIZAR EL ADAPTADOR CON EL LISTENER (SOLO UNA VEZ)
        comAdapter = new ComentariosAdapter(comentarios, padre -> {
            padreSeleccionado = padre;
            etNuevoComentario.setHint("Respondiendo a " + padre.getNombreUsuario() + "...");
            etNuevoComentario.requestFocus();
        });


        rvComentario.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComentario.setAdapter(comAdapter);

        obtenerComentarios();

        // 2. LÓGICA DEL BOTÓN ENVIAR
        btnEnviarComentario.setOnClickListener(view -> {
            String texto = etNuevoComentario.getText().toString().trim();
            if (!texto.isEmpty()) {
                procesarEnvio(texto);
            } else {
                Toast.makeText(getContext(), "Escribe un comentario", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void procesarEnvio(String texto) {
        ComentariosRequest request = new ComentariosRequest();
        request.setComentario(texto);
        request.setIdUsuario(new ComentariosRequest.UsuarioId(sessionManager.getUserId()));
        request.setIdPublicacion(new ComentariosRequest.PublicacionId(idPublicacion));
        request.setIdComentarioHijo(padreSeleccionado != null ? padreSeleccionado.getIdComentarios() : null);

        Callback<Void> callback = new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    etNuevoComentario.setText("");
                    etNuevoComentario.setHint("Escribe un comentario...");
                    padreSeleccionado = null; // Resetear
                    obtenerComentarios();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        };

        if (padreSeleccionado == null) {
            comRepository.crearComentario(callback, request);
        } else {
            request.setComentario("@" + padreSeleccionado.getNombreUsuario() + " " + texto);
            Log.d("DEBUG_ENVIO", "Respondiendo al comentario ID: " + padreSeleccionado.getIdComentarios());
            comRepository.responderComentario(request, callback);
        }
    }

    public void obtenerComentarios() {
        comRepository.obtenerComentarios(new Callback<List<ComentariosDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<ComentariosDTO>> call, @NonNull Response<List<ComentariosDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    comentarios.clear();
                    comentarios.addAll(agruparComentarios(new ArrayList<>(response.body())));
                    comAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ComentariosDTO>> call, @NonNull Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
            }
        }, idPublicacion);
    }

    private ArrayList<ComentarioAgrupado> agruparComentarios(ArrayList<ComentariosDTO> listaApi) {
        ArrayList<ComentarioAgrupado> listaAgrupada = new ArrayList<>();
        for (ComentariosDTO c : listaApi) {
            if (c.getIdComentarioHijo() == null) {
                listaAgrupada.add(new ComentarioAgrupado(c));
            }
        }
        for (ComentariosDTO c : listaApi) {
            if (c.getIdComentarioHijo() != null) {
                for (ComentarioAgrupado grupo : listaAgrupada) {
                    if (grupo.getPadre().getIdComentarios() == c.getIdComentarioHijo().intValue()) {
                        grupo.addHijo(c);
                        break;
                    }
                }
            }
        }
        return listaAgrupada;
    }
}
