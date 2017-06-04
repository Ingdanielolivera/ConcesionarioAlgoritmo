package com.example.ingdaniel.concesionarioalgoritmo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by ING DANIEL on 03/06/2017.
 */

public class Principal extends AppCompatActivity implements AdaptadorAuto.OnAutoClickListener{
    private RecyclerView listado;
    private ArrayList<Auto> autos;
    private AdaptadorAuto adapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listado=(RecyclerView)findViewById(R.id.lstopciones);
        autos =Datos.traerAutos(getApplicationContext());
        adapter =new AdaptadorAuto(autos,this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);
    }


    public void agregar(View v){
        finish();
        Intent i = new Intent(Principal.this,AgregarAuto.class);
        startActivity(i);
    }

   @Override
    public void OnAutoClick(Auto a) {
       finish();
       Intent i = new Intent(Principal.this,DetalleAuto.class);
       Bundle b = new Bundle();
       b.putString("placa",a.getPlaca());
       i.putExtra("datos",b);
       startActivity(i);
       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
