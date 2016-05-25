package com.aiscrim.application.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Operaciones;
import com.aiscrim.application.modelo.Tarjeta;

import java.sql.SQLException;

/**
 * Fragmento para la pestaña "TARJETAS" de la sección "Mi Cuenta"
 */
public class FragmentoTarjetas extends android.support.v4.app.Fragment  {
    FloatingActionButton add;
    private LinearLayoutManager linearLayout;
    private Operaciones operacion;
    AdaptadorTarjetas adaptador;

    public FragmentoTarjetas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view;
        operacion = new Operaciones(getContext());
        try {
            operacion.consultarTarjetas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);
            final RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
            linearLayout = new LinearLayoutManager(getActivity());
            reciclador.setLayoutManager(linearLayout);

            adaptador = new AdaptadorTarjetas(getContext());
            reciclador.setAdapter(adaptador);
            ItemTouchHelper.Callback callback = new MovieTouchHelperTarjetas(adaptador);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(reciclador);
            add = (FloatingActionButton) view.findViewById(R.id.fab);
            add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Toast.makeText(v.getContext(), "Pulsado boton añadir", Toast.LENGTH_SHORT).show();
                    showDialog();
                }
            });

        return view;
    }

    private void showDialog() {
        DialogFragment a = DialogoCrearTarjeta.newInstance();
        a.setTargetFragment(this, 0);
        a.show(getFragmentManager(), "dialog");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        operacion.consultarTarjetas();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adaptador.notifyDataSetChanged();
                } else if (resultCode == Activity.RESULT_CANCELED){
                    // After Cancel code.
                }
                break;
        }
    }

}


