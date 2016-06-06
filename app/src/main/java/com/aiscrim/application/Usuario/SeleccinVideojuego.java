package com.aiscrim.application.Usuario;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.ItemCarrito;
import com.aiscrim.application.Objetos.Videojuego;

/**
 * Actividad para la configuración de preferencias
 */

public class SeleccinVideojuego extends AppCompatActivity {

    TextView nombre;
    TextView plataforma;
    TextView precio, precio_sin;
    TextView descripcion;
    TextView autor;
    TextView precio_anterior;
    TextView descuento;
    RelativeLayout des, precio_ant, prec, prec_sin;
    ImageView img;
    FloatingActionButton add;
    Videojuego videojuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccin_videojuego);

        nombre = (TextView)findViewById(R.id.detail_name);
        plataforma =  (TextView) findViewById(R.id.detail_plataform);
        precio = (TextView) findViewById(R.id.detail_price);
        precio_sin = (TextView) findViewById(R.id.precio);
        descripcion = (TextView) findViewById(R.id.detail_description);
        autor  = (TextView) findViewById(R.id.detail_author);
        precio_anterior = (TextView) findViewById(R.id.precio_anterior);
        descuento = (TextView) findViewById(R.id.descuento);
        des = (RelativeLayout) findViewById(R.id.des_layout);
        precio_ant = (RelativeLayout) findViewById(R.id.precio_ant_layout);
        prec = (RelativeLayout) findViewById(R.id.precio_layout);
        prec_sin = (RelativeLayout) findViewById(R.id.precio_sin_layout);

        img = (ImageView)findViewById(R.id.detail_image);
        add = (FloatingActionButton) findViewById(R.id.fab_carrito);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(v.getContext(), "Pulsado boton añadir", Toast.LENGTH_SHORT).show();
                agregarItemCarrito();
            }
        });

        //Obteniendo la instancia del Intent
        videojuego = (Videojuego)getIntent().getExtras().getSerializable("parametro");
        String name = videojuego.getNombre();
        int n = getResources().getIdentifier(videojuego.getImagen(),"drawable",getPackageName());


        //Seteando el valor del extra en el TextView
        nombre.setText(name);
        plataforma.setText(videojuego.getPlataforma());

        descripcion.setText("Es un juego muy completo lleno de aventura e intriga que hara que el jugador" +
                " disfrute como nunca antes lo habia hecho");
        autor.setText(videojuego.getProveedor());
        Log.e("++++++++++", videojuego.getDescuento() + "");
        if(videojuego.getDescuento() == 0) {
            precio_sin.setText(videojuego.getPrecio() + " €");

        }else {
            des.setVisibility(View.VISIBLE);
            precio_ant.setVisibility(View.VISIBLE);
            prec.setVisibility(View.VISIBLE);
            prec_sin.setVisibility(View.GONE);
            float p = videojuego.getPrecio();
            float desc = videojuego.getDescuento();
            precio.setText((p * (1 - (desc / 100))) + " €");
            precio_anterior.setText(videojuego.getPrecio() + " €");
            precio_anterior.setPaintFlags(precio_anterior.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            descuento.setText(videojuego.getDescuento() + " %");
        }
        img.setImageResource(n);
        agregarToolbar();
    }

    private void agregarItemCarrito() {
        ItemCarrito.add(videojuego,1);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(nombre.getText());
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

