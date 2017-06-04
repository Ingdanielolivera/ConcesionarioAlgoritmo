package com.example.ingdaniel.concesionarioalgoritmo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AgregarAuto extends AppCompatActivity {
    private EditText cajaPlaca;
    private EditText cajaKilometraje;
    private EditText cajaColor;
    private EditText cajaMarca;

    private TextInputLayout icajaPlaca;
    private TextInputLayout icajakilometraje;
    private TextInputLayout icajaColor;
    private TextInputLayout icajaMarca;

    private boolean guardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_auto);

        cajaPlaca =(EditText)findViewById(R.id.txtPlaca);
        cajaKilometraje=(EditText)findViewById(R.id.txtKilometraje);
        cajaColor=(EditText)findViewById(R.id.txtColor);
        cajaMarca=(EditText)findViewById(R.id.txtMarca);

        icajaPlaca=(TextInputLayout)findViewById(R.id.placaAuto);
        icajakilometraje=(TextInputLayout)findViewById(R.id.kilometrajeAuto);
        icajaColor=(TextInputLayout)findViewById(R.id.colorAuto);
        icajaMarca=(TextInputLayout)findViewById(R.id.marcaAuto);

        guardado=false;
        cajaPlaca.addTextChangedListener(new TextWatcherPersonalizado(icajaPlaca,"Diguite la placa") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });

        cajaKilometraje.addTextChangedListener(new TextWatcherPersonalizado(icajakilometraje,"Diguite el Kilometraje") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

        cajaColor.addTextChangedListener(new TextWatcherPersonalizado(icajaColor,"Diguite el color") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

        cajaMarca.addTextChangedListener(new TextWatcherPersonalizado(icajaMarca,"Diguite la marca") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

    }

    public int fotoAleatoria() {
        int fotos[] = {R.drawable.images, R.drawable.images2, R.drawable.images3};
        int numero = (int) (Math.random() * 3);
        return fotos[numero];
    }

    public boolean validartodo() {
        if (cajaPlaca.getText().toString().isEmpty()) {
            icajaPlaca.setError("Digite la placa");
            cajaPlaca.requestFocus();
            return false;
        }
        if (cajaKilometraje.getText().toString().isEmpty()) {
            icajakilometraje.setError("Diguite el kilometraje");
            cajaKilometraje.requestFocus();
            return false;
        }
        if (cajaColor.getText().toString().isEmpty()) {
            icajaColor.setError(("Diguite el Color"));
            cajaColor.requestFocus();
            return false;
        }
        if (cajaMarca.getText().toString().isEmpty()) {
            icajaMarca.setError(("Diguite la Marca"));
            cajaMarca.requestFocus();
            return false;
        }
        return true;
    }


    public void guardar(View v) throws IOException {
        String urlfoto, placa, kilometraje, color, marca,idfoto;
        Auto a;
        if (validartodo()) {

            placa = cajaPlaca.getText().toString();
            kilometraje = cajaKilometraje.getText().toString();
            color = cajaColor.getText().toString();
            marca =cajaMarca.getText().toString();
            idfoto=String.valueOf(fotoAleatoria());

            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),Integer.parseInt(idfoto));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[]imagenBytes = baos.toByteArray();
            urlfoto= Base64.encodeToString(imagenBytes,Base64.DEFAULT);

            baos.close();

            a = new Auto(urlfoto, placa, kilometraje, color, marca,idfoto);
            a.guardar(getApplicationContext());

            //Ocultar teclado
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(cajaPlaca.getWindowToken(), 0);

            Snackbar.make(v,"Auto Guardado Exitosamente!",Snackbar.LENGTH_SHORT).show();
            guardado=true;
            limpiar();
        }
    }

    public void limpiar() {
        cajaPlaca.setText("");
        cajaKilometraje.setText("");
        cajaColor.setText("");
        cajaMarca.setText("");
        guardado=false;
        cajaPlaca.requestFocus();
    }

    public void limpia(View v) {
        cajaPlaca.setText("");
        cajaKilometraje.setText("");
        cajaColor.setText("");
        cajaMarca.setText("");
        guardado=false;
        cajaPlaca.requestFocus();
    }
    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarAuto.this,Principal.class);
        startActivity(i);
    }




}
