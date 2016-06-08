package com.aiscrim.application.Administrador;

import android.content.Intent;
import android.os.Bundle;
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
        adaptador = new AdaptadorProductos(getContext());

        reciclador.setAdapter(adaptador);
        ItemTouchHelper.Callback callback = new MovieTouchHelperProductos(adaptador,getContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(reciclador);


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

