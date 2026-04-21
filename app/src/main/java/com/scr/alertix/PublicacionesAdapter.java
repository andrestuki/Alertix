package com.scr.alertix;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.PublicacionViewHolder> {
    private ArrayList<Publicaciones> publicaciones;

    public PublicacionesAdapter(ArrayList<Publicaciones> publicaciones) {
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
        Publicaciones p = publicaciones.get(position);

        holder.nombre.setText(p.getNombreUsuario());
        holder.descripcion.setText(p.getDescripcion());
        holder.fecha.setText(p.getFecha());
        holder.tipo.setText(p.getTipo());
        holder.lugar.setText(p.getLugar());
        holder.avatar.setImageResource(R.drawable.alertix_logo);
        

        if (p.getImg() != null && !p.getImg().isEmpty()) {
            Context context = holder.itemView.getContext();
            int idRes = context.getResources().getIdentifier(p.getImg(), "drawable", context.getPackageName());
            if (idRes != 0) {
                holder.imagen.setImageResource(idRes);
                holder.imagen.setVisibility(View.VISIBLE);
            } else {
                holder.imagen.setVisibility(View.GONE);
            }
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
