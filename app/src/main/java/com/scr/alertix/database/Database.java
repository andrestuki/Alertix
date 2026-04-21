package com.scr.alertix.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Alertix.db";
    private static final int DATABASE_VERSION = 3;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí creas tus tablas
        db.execSQL("CREATE TABLE usuarios (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, nombreUsuario varchar(100),apellidoUsuario varchar(100)," +
                "generoUsuario varchar(100), contraseniaUsuario varchar(100), correoUsuario varchar(100), idProfile int)");

        db.execSQL("CREATE TABLE publicaciones (idPublicacion INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario varchar(100), descripcion varchar(100), " +
                "fecha varchar(100), tipo varchar(100), lugar varchar(100),img varchar(300)," +
                " constraint fk_idUsuario foreign key (idUsuario) references usuarios(idUsuario))");

        // --- CUENTA ADMIN PRE-CREADA ---
        db.execSQL("INSERT INTO usuarios (nombreUsuario, apellidoUsuario, generoUsuario, contraseniaUsuario, correoUsuario, idProfile) " +
                "VALUES ('Admin', 'Alertix', 'N/A', 'admin123', 'admin@alertix.com', 1)");
        db.execSQL("INSERT INTO publicaciones (idUsuario, descripcion, fecha, tipo, lugar, img) " +
                "VALUES (1, '¡Bienvenidos a Alertix! Nuestra comunidad está para protegerte.', '22/04/2024', 'NOTICIA', 'Central', 'alertix_logo')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si subimos la versión de la DB, borramos y recreamos (solo para desarrollo)
        db.execSQL("DROP TABLE IF EXISTS publicaciones");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
