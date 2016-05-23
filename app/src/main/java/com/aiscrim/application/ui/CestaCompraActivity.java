package com.aiscrim.application.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aiscrim.application.R;

public class CestaCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_grupo_items_carrito);
        agregarToolbar();
        final RecyclerView reciclador = (RecyclerView)findViewById(R.id.reciclador);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorCarrito adaptador = new AdaptadorCarrito();
        reciclador.setAdapter(adaptador);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Carrito");
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
