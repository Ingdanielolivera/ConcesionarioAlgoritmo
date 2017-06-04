package com.example.ingdaniel.concesionarioalgoritmo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ING DANIEL on 13/05/2017.
 */

public class AutosSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql = "CREATE TABLE Autos(uuid text, urlfoto text, placa text, kilometraje text, color text, marca text, idfoto text)";
    private static int version=1;
    public AutosSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory){
        super(contexto, name, factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Autos");
        db.execSQL(sql);
    }
}
