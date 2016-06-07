package com.aiscrim.application.Usuario;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Pedido;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.Objetos.Videojuego;
import com.aiscrim.application.R;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Fragmento que contiene otros fragmentos anidados para representar las categorías
 * de comidas
 */
public class FragmentoPedidos extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager linearLayout;
    private AdaptadorPedidos adaptador;
    private View.OnClickListener listener;
    private Operaciones operacion;

    public FragmentoPedidos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        operacion = new Operaciones(getContext());
        try {
            operacion.consultarPedidos(Usuario.getNick());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("++++++", Pedido.PEDIDOS + "");

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);
        adaptador = new AdaptadorPedidos(getContext());

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzaractividad(reciclador.getChildAdapterPosition(v));
                //Toast.makeText(v.getContext(), "Pulsado el elemento " + getArguments().getInt(INDICE_SECCION), Toast.LENGTH_SHORT).show();
            }
        });

        reciclador.setAdapter(adaptador);

        return view;
    }

    public void lanzaractividad(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), SeleccionPedido.class);
        //intent.putExtra(EXTRA_NOMBRE,"diablo3.jpg")
        intent.putExtra("parametro", (Serializable) Pedido.PEDIDOS.get(position));
        startActivity(intent);
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


}
