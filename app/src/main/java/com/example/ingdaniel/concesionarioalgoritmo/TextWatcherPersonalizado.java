package com.example.ingdaniel.concesionarioalgoritmo;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by ING DANIEL on 04/06/2017.
 */

public abstract class TextWatcherPersonalizado implements TextWatcher {

    private TextInputLayout icaja;
    private String textError;

    public TextWatcherPersonalizado(TextInputLayout icaja, String textError) {
        this.icaja = icaja;
        this.textError = textError;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(estavacio(s))icaja.setError(textError);
        else if (icaja.isErrorEnabled()){
            icaja.setError(null);
        }
    }

    public abstract boolean estavacio(Editable s);

}
