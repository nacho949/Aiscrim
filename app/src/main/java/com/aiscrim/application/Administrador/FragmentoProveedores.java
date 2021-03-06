package com.aiscrim.application.Administrador;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
import com.aiscrim.application.Objetos.Proveedor;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.R;
import com.aiscrim.application.Usuario.AdaptadorPedidos;
import com.aiscrim.application.Usuario.DecoracionLineaDivisoria;
import com.aiscrim.application.Usuario.DialogoCrearTarjeta;
import com.aiscrim.application.Usuario.MovieTouchHelperDirecciones;
import com.aiscrim.application.Usuario.SeleccionPedido;

import java.io.Serializable;
import java.sql.SQLException;




public class FragmentoProveedores extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayout;
    private AdaptadorProveedores adaptador;
    private View.OnClickListener listener;
    private Operaciones operacion;
    FloatingActionButton add;
    private Paint p = new Paint();

    public FragmentoProveedores() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        operacion = new Operaciones(getContext());
        try {
            operacion.consultarProveedores();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
        adaptador = new AdaptadorProveedores(getContext(), this, getFragmentManager());
        ItemTouchHelper.Callback callback = new MovieTouchHelperProveedores(adaptador,getContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(reciclador);
        reciclador.setAdapter(adaptador);
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
        DialogFragment a = DialogoCrearProveedor.newInstance();
        a.setTargetFragment(this, 0);
        a.show(getFragmentManager(), "dialog");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        operacion.consultarProveedores();
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

