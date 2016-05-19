package com.aiscrim.application.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Videojuego;

import java.util.List;

/**
 * Actividad para la configuración de preferencias
 */

public class SeleccinVideojuego extends AppCompatActivity {

    TextView list;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccin_videojuego);
        agregarToolbar();
        list = (TextView)findViewById(R.id.textView);
        img = (ImageView)findViewById(R.id.imageView4);

//Obteniendo la instancia del Intent
        Videojuego videojuego = (Videojuego)getIntent().getExtras().getSerializable("parametro");
        String name = videojuego.getNombre();
        int n = videojuego.getIdDrawable();


//Seteando el valor del extra en el TextView
        list.setText(name);
        img.setImageResource(n);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

