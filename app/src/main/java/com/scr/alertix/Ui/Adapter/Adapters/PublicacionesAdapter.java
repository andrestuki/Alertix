package com.scr.alertix.Ui.Adapter.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scr.alertix.Data.Model.PublicacionDTO;
import com.scr.alertix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.PublicacionViewHolder> {
    private ArrayList<PublicacionDTO> publicaciones;

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

        // 3. Lógica del Like (Corazón)
        actualizarColorLike(holder.btnLike, p.isLiked());

        holder.btnLike.setOnClickListener(v -> {
            boolean nuevoEstado = !p.isLiked();
            p.setLiked(nuevoEstado);
            
            // Actualizar contador localmente
            if (nuevoEstado) {
                p.setLikes(p.getLikes() + 1);
            } else {
                p.setLikes(p.getLikes() - 1);
            }
            holder.countLikes.setText(String.valueOf(p.getLikes()));

            actualizarColorLike(holder.btnLike, nuevoEstado);
        });
    }

    private void actualizarColorLike(ImageButton btn, boolean isLiked) {
        if (isLiked) {
            btn.setImageResource(R.drawable.ic_heart_red);
        } else {
            btn.setImageResource(R.drawable.ic_heart);
        }
        // Quitamos cualquier filtro de color previo para que se vean los colores originales del vector
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
