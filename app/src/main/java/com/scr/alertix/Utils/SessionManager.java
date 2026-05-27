package com.scr.alertix.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.scr.alertix.Ui.Adapter.Adapters.PublicacionesAdapter;

public class SessionManager {
    private static final String PREF_NAME = "AlertixSession";
    private static final String KEY_USER_ID = "idUsuario";

    private static final String  id_Profile="idProfile";

    private static final String KEY_DIRECCION_ID="idDireccion";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        if (context != null) {
            pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            editor = pref.edit();
        }
    }

    public SessionManager(PublicacionesAdapter publicacionesAdapter) {

    }

    // Guardar sesión
    public void saveSession(Long idUsuario, int idProfile, Long idDireccion) {
        if (editor != null) {
            editor.putLong(KEY_USER_ID, idUsuario);
            editor.putInt(id_Profile, idProfile);
            editor.putLong(KEY_DIRECCION_ID, idDireccion);
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.apply();
        }
    }



    // Obtener el ID guardado
    public Long getUserId() {
        if (pref == null) return 0L;
        try {
            return pref.getLong(KEY_USER_ID, 0L);
        } catch (ClassCastException e) {
            // Manejar caso donde se guardó como Integer en versiones anteriores
            return (long) pref.getInt(KEY_USER_ID, 0);
        }
    }
    public Long getDireccionId() {
        if (pref == null) return 0L;
        try {
            return pref.getLong("idDireccion", 0L);
        } catch (ClassCastException e) {
            // Si antes era Integer, lo recuperamos así y lo convertimos
            return (long) pref.getInt("idDireccion", 0);
        }
    }

    // Saber si hay una sesión activa
    public boolean isLoggedIn() {
        if (pref == null) return false;
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Cerrar sesión
    public void logout() {
        if (editor != null) {
            editor.clear();
            editor.apply();
        }
    }
}