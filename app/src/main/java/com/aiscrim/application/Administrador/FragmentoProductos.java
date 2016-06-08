package com.aiscrim.application.Administrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Pedido;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.R;
import com.aiscrim.application.Usuario.AdaptadorPedidos;
import com.aiscrim.application.Usuario.DecoracionLineaDivisoria;
import com.aiscrim.application.Usuario.MovieTouchHelperDirecciones;
import com.aiscrim.application.Usuario.SeleccionPedido;

import java.io.Serializable;
import java.sql.SQLException;




public class FragmentoProductos extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayout;
    private AdaptadorProductos adaptador;
    private View.OnClickListener listener;
    private Operaciones operacion;
    FloatingActionButton add;

    public FragmentoProductos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        operacion = new Operaciones(getContext());
        try {
            operacion.consultarProductos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
        adaptador = new AdaptadorProductos(getContext(), this, getFragmentManager());

        reciclador.setAdapter(adaptador);
        ItemTouchHelper.Callback callback = new MovieTouchHelperProductos(adaptador,getContext());
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showDialog() {
        DialogFragment a = DialogoCrearProducto.newInstance();
        a.setTargetFragment(this, 0);
        a.show(getFragmentManager(), "dialog");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        operacion.consultarProductos();
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

