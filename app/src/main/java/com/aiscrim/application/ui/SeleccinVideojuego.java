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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Actividad para la configuración de preferencias
 */

public class SeleccinVideojuego extends AppCompatActivity {

    TextView nombre;
    TextView plataforma;
    TextView precio;
    TextView descripcion;
    TextView autor;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccin_videojuego);
        agregarToolbar();

        nombre = (TextView)findViewById(R.id.detail_name);
        plataforma =  (TextView) findViewById(R.id.detail_plataform);
        precio = (TextView) findViewById(R.id.detail_price);
        descripcion = (TextView) findViewById(R.id.detail_description);
        autor  = (TextView) findViewById(R.id.detail_author);

        img = (ImageView)findViewById(R.id.detail_image);

        //Obteniendo la instancia del Intent
        Videojuego videojuego = (Videojuego)getIntent().getExtras().getSerializable("parametro");
        String name = videojuego.getNombre();
        int n = videojuego.getIdDrawable();


        //Seteando el valor del extra en el TextView
        nombre.setText(name);
        plataforma.setText(videojuego.getPlataforma());
        precio.setText(videojuego.getPrecio() + "");
        descripcion.setText("Es un juego muy completo lleno de aventura e intriga que hara que el jugador" +
                " disfrute como nunca antes o habia Hecho");
        autor.setText(videojuego.getDesarrollador());
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

