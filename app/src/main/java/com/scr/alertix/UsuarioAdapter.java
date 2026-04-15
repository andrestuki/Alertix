package com.scr.alertix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarios;

    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return usuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_usuario, viewGroup, false);
        }
        Usuario u = usuarios.get(i);

        TextView nombre = view.findViewById(R.id.txtNombreItem);
        TextView correo = view.findViewById(R.id.txtCorreoItem);
        TextView tipo = view.findViewById(R.id.txtTipoItem);

        nombre.setText(u.getNombre());
        correo.setText(u.getCorreo());
        tipo.setText(u.getTipo());

        return view;
    }
}
