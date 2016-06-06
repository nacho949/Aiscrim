package com.aiscrim.application.Usuario;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;


/**
 * Fragmento para la pestaña "DIRECCIONES" De la sección "Mi Cuenta"
 */
public class FragmentoDirecciones extends Fragment {

    private LinearLayoutManager linearLayout;
    private FloatingActionButton add;
    private Operaciones operacion;
    private AdaptadorDirecciones adaptador;


    public FragmentoDirecciones() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        operacion = new Operaciones(getContext());
        try {
            operacion.consultarDirecciones(Usuario.getNick());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);
        final RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        adaptador = new AdaptadorDirecciones(getContext());
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                String[] opc = new String[]{"Editar", "Marcar como predeterminada"};

                //Toast.makeText(getContext(),
                        //"pos: " + reciclador.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();


                AlertDialog opciones = new AlertDialog.Builder(
                        getActivity())
                        .setItems(opc,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int selected) {
                                        if (selected == 0) {
                                            //acciones para editar
                                        } else if (selected == 1) {
                                            //acciones para copiar
                                        }
                                    }
                                }).create();
                opciones.show();
                return false;
            }
        });
        reciclador.setAdapter(adaptador);
        ItemTouchHelper.Callback callback = new MovieTouchHelperDirecciones(adaptador);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(reciclador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
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
        DialogFragment a = DialogoCrearDireccion.newInstance();
        a.setTargetFragment(this, 0);
        a.show(getFragmentManager(), "dialog");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                if (resultCode == Activity.RESULT_OK) {
                    try {
                        operacion.consultarDirecciones(Usuario.getNick());
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

