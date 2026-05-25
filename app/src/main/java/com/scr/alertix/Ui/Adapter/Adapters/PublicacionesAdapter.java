package com.scr.alertix.Ui.Adapter.Adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.scr.alertix.Ui.Main.ComentariosModal;
import com.scr.alertix.Data.Model.Request.LikeRequest;
import com.scr.alertix.Data.Model.Response.LikesResponse;
import com.scr.alertix.Data.Model.DTO.PublicacionDTO;
import com.scr.alertix.Data.Repository.LikesRepository;
import com.scr.alertix.R;
import com.scr.alertix.Utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.PublicacionViewHolder> {
    private ArrayList<PublicacionDTO> publicaciones;
    SessionManager sessionManager;


    public PublicacionesAdapter(ArrayList<PublicacionDTO> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicaciones, parent, false);
        return new PublicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        PublicacionDTO p = publicaciones.get(position);

        holder.nombre.setText(p.getNombreUsuario());
        holder.descripcion.setText(p.getDescripcion());
        holder.fecha.setText(p.getFecha());
        holder.tipo.setText(p.getCategoria());
        holder.lugar.setText(p.getBarrio());

        // Mostrar contadores
        holder.countLikes.setText(String.valueOf(p.getLikes()));
        holder.countComments.setText(String.valueOf(p.getComentarios()));

        // 1. Cargar Foto de Perfil
        String urlPerfil = p.getFotoPerfil();
        if (urlPerfil != null && !urlPerfil.trim().isEmpty() && !urlPerfil.equalsIgnoreCase("null")) {
            Picasso.get()
                    .load(urlPerfil)
                    .placeholder(R.drawable.alertix_logo)
                    .into(holder.avatar);
        } else {
            holder.avatar.setImageResource(R.drawable.alertix_logo);
        }


        String imgPublicacion = p.getImagenPublicacion();
        if (imgPublicacion != null && !imgPublicacion.trim().isEmpty() && !imgPublicacion.equalsIgnoreCase("null")) {
            holder.imagen.setVisibility(View.VISIBLE);

            // Si empieza con http, significa que es una URL tradicional (usamos Picasso)
            if (imgPublicacion.startsWith("http://") || imgPublicacion.startsWith("https://")) {
                Picasso.get()
                        .load(imgPublicacion)
                        .into(holder.imagen);
            }
            // Si no es una URL, asumimos que es el String en Base64 que guardaste de la galería
            else {
                try {
                    // Por si acaso el servidor le concatenó algún prefijo de Data URI
                    if (imgPublicacion.contains(",")) {
                        imgPublicacion = imgPublicacion.split(",")[1];
                    }

                    // Decodificamos el string Base64 a bytes
                    byte[] decodedString = android.util.Base64.decode(imgPublicacion, android.util.Base64.DEFAULT);
                    // Convertimos los bytes en un Bitmap que ImageView sí puede pintar
                    android.graphics.Bitmap decodedByte = android.graphics.BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    if (decodedByte != null) {
                        holder.imagen.setImageBitmap(decodedByte);
                    } else {
                        // Si da null es porque el String llegó corrupto o incompleto de la DB
                        holder.imagen.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    holder.imagen.setVisibility(View.GONE);
                }
            }
        } else {
            holder.imagen.setVisibility(View.GONE);
        }


        actualizarColorLike(holder.btnLike, p.isLiked());

        android.content.Context context = holder.itemView.getContext();


        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }
        LikesRepository likesRepo = new LikesRepository();

        holder.btnLike.setOnClickListener(v -> {
            Long idUsuarioActual = sessionManager.getUserId();

            LikeRequest request = new LikeRequest();
            request.setIdPublicacion(new LikeRequest.PublicacionId(p.getId()));
            request.setIdUsuario(new LikeRequest.UsuarioId(idUsuarioActual));

            likesRepo.darLike(request, new Callback<LikesResponse>() {
                @Override
                public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LikesResponse res = response.body();

                        p.setLiked(res.getIsLiked());
                        p.setLikes(res.getCantidadLikes());

                        holder.countLikes.setText(String.valueOf(res.getCantidadLikes()));
                        actualizarColorLike(holder.btnLike, p.isLiked());
                    }
                }

                @Override
                public void onFailure(Call<LikesResponse> call, Throwable t) {
                    Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("API_DEBUG", "Error de conexión: " + t.getMessage());
                }
            });
        });

        holder.btnComment.setOnClickListener(v -> {
            ComentariosModal bottomSheet = new ComentariosModal();

            Bundle args = new Bundle();

            args.putLong("idPublicacion", p.getId());

            bottomSheet.setArguments(args);
            bottomSheet.show(((AppCompatActivity)context).getSupportFragmentManager(), "TAG");
        });


    }

    private void actualizarColorLike(ImageButton btn, boolean isLiked) {
        if (isLiked) {
            btn.setImageResource(R.drawable.ic_heart_red);
        } else {
            btn.setImageResource(R.drawable.ic_heart);
        }
        btn.setColorFilter(null);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, fecha, tipo, lugar, countLikes, countComments;
        ImageButton avatar, btnLike, btnComment, btnShare;
        ImageView imagen;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombreUsuario);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            fecha = itemView.findViewById(R.id.txtFechaPublicacion);
            tipo = itemView.findViewById(R.id.txtTipoAlertaPublicacion);
            lugar = itemView.findViewById(R.id.txtLugarPublicacion);
            countLikes = itemView.findViewById(R.id.txtCountLikes);
            countComments = itemView.findViewById(R.id.txtCountComments);
            avatar = itemView.findViewById(R.id.imgUsuario);
            imagen = itemView.findViewById(R.id.imgPublicacion);
            
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
            btnShare = itemView.findViewById(R.id.btnShare);
        }
    }
}
