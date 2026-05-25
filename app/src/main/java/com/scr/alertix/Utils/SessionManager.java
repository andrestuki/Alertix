package com.scr.alertix.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.scr.alertix.Ui.Adapter.Adapters.PublicacionesAdapter;

public class SessionManager {
    private static final String PREF_NAME = "AlertixSession";
    private static final String KEY_USER_ID = "idUsuario";

    private static final String  id_Profile="idProfile";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public SessionManager(PublicacionesAdapter publicacionesAdapter) {

    }

    // Guardar sesión
    public void saveSession(Long idUsuario,int idProfile) {
        editor.putLong(KEY_USER_ID, idUsuario);
        editor.putInt(id_Profile, idProfile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply(); // Guarda los cambios asíncronamente
    }

    // Obtener el ID guardado
    public Long getUserId() {
        return pref.getLong(KEY_USER_ID, 0);
    }

    // Saber si hay una sesión activa
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Cerrar sesión
    public void logout() {
        editor.clear();
        editor.apply();
    }
}