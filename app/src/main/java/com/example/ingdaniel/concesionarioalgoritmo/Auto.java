package com.example.ingdaniel.concesionarioalgoritmo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by ING DANIEL on 03/06/2017.
 */

public class Auto {
    private String uuid;
    private String urlfoto;
    private String idfoto;
    private String placa;
    private String kilometraje;
    private String color;
    private String marca;

    public Auto(){

    }

    public Auto(String urlfoto, String placa, String kilometraje, String color, String marca,String idfoto) {
        this.uuid= UUID.randomUUID().toString();
        this.urlfoto = urlfoto;
        this.placa=placa;
        this.kilometraje = kilometraje;
        this.color = color;
        this.marca = marca;
        this.idfoto=idfoto;
    }

    public Auto(String uuid, String urlfoto, String marca, String kilometraje, String color, String placa,String idfoto) {
        this.uuid = uuid;
        this.urlfoto = urlfoto;
        this.placa= placa;
        this.kilometraje = kilometraje;
        this.color = color;
        this.marca = marca;
        this.idfoto = idfoto;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(String idfoto) {
        this.idfoto = idfoto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }



    public  void guardar(Context contexto){
        guardarLocal(contexto);
    }



    public void guardarLocal(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        AutosSQLiteOpenHelper aux = new AutosSQLiteOpenHelper(contexto,"DBAutos",null);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "INSERT INTO Autos values('"
                +this.getUuid()+"','"
                +this.getUrlfoto()+"','"
                +this.getPlaca()+"','"
                +this.getKilometraje()+"','"
                +this.getColor()+"','"
                +this.getMarca()+"','"
                +this.getIdfoto()+"')";
        db.execSQL(sql);
        db.close();

    }



}
