package com.example.ingdaniel.concesionarioalgoritmo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ING DANIEL on 03/06/2017.
 */

public class Datos {
    public static ArrayList<Auto> traerAutos(Context contexto) {
        ArrayList<Auto> autos= new ArrayList<>();

        //Declarar Variables
        SQLiteDatabase db;
        String sql, uuid, urlfoto,placa, kilometraje, color,idfoto;
        Auto a;
        //Abrir conexción
        AutosSQLiteOpenHelper aux = new AutosSQLiteOpenHelper(contexto,"DBAutos",null);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Autos";
        Cursor c =db.rawQuery(sql,null);

        //Recorido del cursor
        if(c.moveToFirst()){
            do{
                uuid=c.getString(0);
                urlfoto = c.getString(1);
                placa=c.getString(2);
                kilometraje=c.getString(3);
                color=c.getString(4);
                idfoto=c.getString(5);
                a = new Auto (uuid, urlfoto, placa, kilometraje, color,idfoto);
                autos.add(a);
            } while (c.moveToNext());
        }
        //Cierro la base de datos y retorno personas
        db.close();
        return autos;
    }

    public static Auto buscarAuto(Context contexto, String ced){
        //Declarar Variables
        SQLiteDatabase db;
        String sql, uuid, urlfoto,placa, kilometraje, color,idfoto;
        Auto a=null;
        //Abrir conexción
        AutosSQLiteOpenHelper aux = new AutosSQLiteOpenHelper(contexto,"DBAutos",null);
        db = aux.getReadableDatabase();
        //Cursor
        sql ="select * from Personas where placa ='"+ced+"'";
        Cursor c =db.rawQuery(sql,null);
        //Recorido del cursor
        if(c.moveToFirst()){
            uuid=c.getString(0);
            urlfoto = c.getString(1);
            placa=c.getString(2);
            kilometraje=c.getString(3);
            color=c.getString(4);
            idfoto=c.getString(5);
            a = new Auto (uuid, urlfoto, placa, kilometraje, color,idfoto);
        }
        //Cierro la base de datos y retorno personas
        db.close();
        return a;
    }

}
