package com.aiscrim.application.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aiscrim.application.Objetos.ItemCarrito;
import com.aiscrim.application.R;

import java.sql.SQLException;

public class CestaCompraActivity extends AppCompatActivity {
    Button tramitar;
    static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_grupo_items_carrito);
        agregarToolbar();
        final RecyclerView reciclador = (RecyclerView)findViewById(R.id.reciclador);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorCarrito adaptador = new AdaptadorCarrito(this);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(this));
        reciclador.setAdapter(adaptador);
        ItemTouchHelper.Callback callback = new MovieTouchHelperCarrito(adaptador,this);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(reciclador);
        tramitar = (Button)findViewById(R.id.tramitar_pedido);
        tramitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(v.getContext(), "Pulsado boton añadir", Toast.LENGTH_SHORT).show();
                tramitarPedido();

            }
        });

    }

    public void tramitarPedido() {
        if(ItemCarrito.CARRITO.size() > 0) {
            Intent intent = new Intent(getApplicationContext(),TramitarPedido.class);
            startActivityForResult(intent, PICK_CONTACT_REQUEST);
        }else{
            Toast.makeText(this, "La cesta de la compra está vacia", Toast.LENGTH_SHORT).show();
        }
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                finish();
            }
        }
    }
}
