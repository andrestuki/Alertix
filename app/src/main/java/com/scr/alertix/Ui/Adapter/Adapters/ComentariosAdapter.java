package com.scr.alertix.Ui.Adapter.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scr.alertix.Data.Model.DTO.ComentarioAgrupado;
import com.scr.alertix.Data.Model.DTO.ComentariosDTO;
import com.scr.alertix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {
    private ArrayList<ComentarioAgrupado> comentariosAgrupados;
    private OnResponderClickListener responderClickListener;

    public interface OnResponderClickListener {
        void onResponderClick(ComentariosDTO comentarioPadre);
    }

    public ComentariosAdapter(ArrayList<ComentarioAgrupado> comentariosAgrupados, OnResponderClickListener listener) {
        this.comentariosAgrupados = comentariosAgrupados;
        this.responderClickListener = listener;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentarios, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        ComentarioAgrupado grupo = comentariosAgrupados.get(position);
        ComentariosDTO padre = grupo.getPadre();

        holder.nombre.setText(padre.getNombreUsuario());
        holder.contenido.setText(padre.getComentario());
        holder.fecha.setText(padre.getFechaComentario());
        holder.likesCount.setText(String.valueOf(padre.getContadorLikesLocal()));
        cargarImagenPerfil(padre.getFotoPerfil(), holder.imgPerfil);

        holder.btnResponder.setOnClickListener(v -> {
            if (responderClickListener != null) {
                responderClickListener.onResponderClick(padre);
            }
        });

        actualizarColorLike(holder.btnLike, padre.isLikedLocal());
        holder.btnLike.setOnClickListener(v -> {
            boolean nuevoEstado = !padre.isLikedLocal();
            padre.setLikedLocal(nuevoEstado);
            padre.setContadorLikesLocal(padre.getContadorLikesLocal() + (nuevoEstado ? 1 : -1));
            holder.likesCount.setText(String.valueOf(padre.getContadorLikesLocal()));
            actualizarColorLike(holder.btnLike, nuevoEstado);
        });

        if (holder.containerRespuestas != null) {
            holder.containerRespuestas.removeAllViews();

            if (!grupo.getHijos().isEmpty()) {
                holder.containerRespuestas.setVisibility(View.VISIBLE);
                LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());

                for (ComentariosDTO hijo : grupo.getHijos()) {
                    View vistaHijo = inflater.inflate(R.layout.item_comentario_hijo, holder.containerRespuestas, false);

                    ImageView imgHijo = vistaHijo.findViewById(R.id.imgPerfilComentarioHijo);
                    TextView txtNombreHijo = vistaHijo.findViewById(R.id.txtNombreUsuarioComentarioHijo);
                    TextView txtFechaHijo = vistaHijo.findViewById(R.id.txtFechaComentarioHijo);
                    TextView txtContenidoHijo = vistaHijo.findViewById(R.id.txtContenidoComentarioHijo);
                    TextView txtLikesHijo = vistaHijo.findViewById(R.id.txtLikesCountHijo);
                    ImageButton btnLikeHijo = vistaHijo.findViewById(R.id.btnLikeComentarioHijo);

                    txtNombreHijo.setText(hijo.getNombreUsuario());
                    txtContenidoHijo.setText(hijo.getComentario());
                    txtFechaHijo.setText(hijo.getFechaComentario());
                    txtLikesHijo.setText(String.valueOf(hijo.getContadorLikesLocal()));
                    cargarImagenPerfil(hijo.getFotoPerfil(), imgHijo);

                    actualizarColorLike(btnLikeHijo, hijo.isLikedLocal());
                    btnLikeHijo.setOnClickListener(v -> {
                        boolean nuevoEstado = !hijo.isLikedLocal();
                        hijo.setLikedLocal(nuevoEstado);
                        hijo.setContadorLikesLocal(hijo.getContadorLikesLocal() + (nuevoEstado ? 1 : -1));
                        txtLikesHijo.setText(String.valueOf(hijo.getContadorLikesLocal()));
                        actualizarColorLike(btnLikeHijo, nuevoEstado);
                    });

                    holder.containerRespuestas.addView(vistaHijo);
                }
            } else {
                holder.containerRespuestas.setVisibility(View.GONE);
            }
        }
    }

    private void cargarImagenPerfil(String url, ImageView imageView) {
        if (url != null && !url.isEmpty() && !url.equals("null")) {
            Picasso.get().load(url).placeholder(R.drawable.alertix_logo).error(R.drawable.alertix_logo).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.alertix_logo);
        }
    }

    private void actualizarColorLike(ImageButton btn, boolean isLiked) {
        btn.setColorFilter(isLiked ? Color.RED : Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return comentariosAgrupados.size();
    }

    public static class ComentarioViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPerfil;
        TextView nombre, contenido, fecha, likesCount, btnResponder;
        ImageButton btnLike;
        LinearLayout containerRespuestas;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerfil = itemView.findViewById(R.id.imgPerfilComentario);
            nombre = itemView.findViewById(R.id.txtNombreUsuarioComentario);
            contenido = itemView.findViewById(R.id.txtContenidoComentario);
            fecha = itemView.findViewById(R.id.txtFechaComentario);
            likesCount = itemView.findViewById(R.id.txtLikesCount);
            btnLike = itemView.findViewById(R.id.btnLikeComentario);
            btnResponder = itemView.findViewById(R.id.btnResponder);
            containerRespuestas = itemView.findViewById(R.id.containerRespuestas);
        }
    }
}