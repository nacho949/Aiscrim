package com.aiscrim.application.Usuario;

import android.app.ActionBar;
import android.content.ClipData;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aiscrim.application.Objetos.ItemCarrito;
import com.aiscrim.application.R;

import java.text.DecimalFormat;

public class TramitarPedido extends AppCompatActivity {
    TableLayout table;
    TableRow row;
    TextView producto,precio;
    TextView tx1, tx2;
    float subtotal;
    DecimalFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramitar_pedido);
        agregarToolbar();

        table = (TableLayout) findViewById(R.id.table_layout_carrito);
        df = new DecimalFormat("0.00");
        rellenarTabla();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Tramitar pedido");
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

    public void rellenarTabla() {

        DecimalFormat f = new DecimalFormat("0.00");
        for(int i = 0; i< ItemCarrito.CARRITO.size(); i++) {
            ItemCarrito item = ItemCarrito.CARRITO.get(i);
            row = new TableRow(this);
            producto = new TextView(this);
            precio = new TextView(this);
            producto.setText(item.game.getNombre() + " x " + item.cantidad);
            if(item.game.getDescuento() == 0){
                subtotal += item.cantidad*item.game.getPrecio();
                precio.setText(df.format(item.cantidad*item.game.getPrecio()) + " €");
            }else{
                float p = item.game.getPrecio();
                float desc = item.game.getDescuento();
                precio.setText(df.format(item.cantidad*(p * (1 - (desc / 100)))) + " €");
                subtotal += item.cantidad*(p * (1 - (desc / 100)));
            }

            producto.setLayoutParams(new TableRow.LayoutParams(0));
            precio.setLayoutParams(new TableRow.LayoutParams(2));
            precio.setGravity(Gravity.RIGHT);
            row.addView(producto);
            row.addView(precio);
            table.addView(row);
        }

        View v = new View(this);
        v.setBackgroundColor(Color.DKGRAY);
        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
        table.addView(v);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Subtotal");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText(df.format(subtotal) + " €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Coste envío");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText( "4,99 €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Total");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText(df.format(subtotal+4.99f) + " €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

    }
}
