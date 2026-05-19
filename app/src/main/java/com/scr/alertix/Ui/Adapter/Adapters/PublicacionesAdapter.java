package com.scr.alertix.Ui.Adapter.Adapters;

import android.content.Context;
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
        holder.tipo.setText(p.getCategoria()); // Usamos categoria de la API
        holder.lugar.setText(p.getBarrio());

        // Cargar Foto de Perfil con Picasso (ya que lo descargamos)
        if (p.getFotoPerfil() != null && !p.getFotoPerfil().isEmpty()) {
            Picasso.get().load(p.getFotoPerfil()).placeholder(R.drawable.alertix_logo).into(holder.avatar);
        } else {
            holder.avatar.setImageResource(R.drawable.alertix_logo);
        }

        // Cargar Imagen de la Publicación con Picasso
        if (p.getImagenPublicacion() != null && !p.getImagenPublicacion().isEmpty()) {
            holder.imagen.setVisibility(View.VISIBLE);
            Picasso.get().load(p.getImagenPublicacion()).into(holder.imagen);
        } else {
            holder.imagen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, fecha, tipo, lugar;
        ImageButton avatar;
        ImageView imagen;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombreUsuario);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            fecha = itemView.findViewById(R.id.txtFechaPublicacion);
            tipo = itemView.findViewById(R.id.txtTipoAlertaPublicacion);
            lugar = itemView.findViewById(R.id.txtLugarPublicacion);
            avatar = itemView.findViewById(R.id.imgUsuario);
            imagen = itemView.findViewById(R.id.imgPublicacion);
        }
    }
}
