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
        guardarRemoto(contexto);
    }
    public void guardarRemoto(final Context contexto){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                HttpURLConnection conexion = null;

                try {
                    URL url = new URL("http://54.237.241.125/guardarau.php");
                    conexion =(HttpURLConnection)url.openConnection();
                    conexion.setConnectTimeout(30000);
                    conexion.setReadTimeout(30000);

                    //Configuracion de envío de datos via POST
                    conexion.setRequestMethod("POST");
                    conexion.setDoOutput(true);
                    conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                    //Crear consulta con los datos

                    StringBuilder builder = new StringBuilder();
                    builder.append("id");
                    builder.append("=");
                    builder.append(URLEncoder.encode(uuid,"UTF-8"));
                    builder.append("&");
                    builder.append("foto");
                    builder.append("=");
                    builder.append(URLEncoder.encode(urlfoto,"UTF-8"));
                    builder.append("&");
                    builder.append("placa");
                    builder.append("=");
                    builder.append(URLEncoder.encode(placa,"UTF-8"));
                    builder.append("&");
                    builder.append("kilometraje");
                    builder.append("=");
                    builder.append(URLEncoder.encode(kilometraje,"UTF-8"));
                    builder.append("&");
                    builder.append("color");
                    builder.append("=");
                    builder.append(URLEncoder.encode(color,"UTF-8"));
                    builder.append("&");
                    builder.append("marca");
                    builder.append("=");
                    builder.append(URLEncoder.encode(color,"UTF-8"));
                    builder.append("&");
                    builder.append("idfoto");
                    builder.append("=");
                    builder.append(URLEncoder.encode(idfoto,"UTF-8"));

                    String query = builder.toString();

                    conexion.setFixedLengthStreamingMode(query.getBytes("UTF-8").length);

                    OutputStream outputStream = conexion.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(query);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    //Conectar
                    conexion.connect();

                    //Leer Respuesta del servidor

                    InputStream inputStream = conexion.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder datos = new StringBuilder();
                    String linea;
                    while((linea =bufferedReader.readLine())!=null){
                        datos.append(linea);
                    }
                    bufferedReader.close();
                    return datos.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        urlfoto = jsonObject.getString("foto");
                        guardarLocal(contexto);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
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
