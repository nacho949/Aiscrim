package com.aiscrim.application.Administrador;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aiscrim.application.Objetos.Producto;
import com.aiscrim.application.Objetos.Proveedor;
import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

public class DialogoEditarPrecioProducto extends DialogFragment{
    Operaciones op;
    EditText precio, precio2;
    Button cancelar,aceptar;
    Producto p;

    static DialogoEditarPrecioProducto newInstance() {
        return new DialogoEditarPrecioProducto();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.editar_precio_producto, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Editar Precio");

        p = (Producto)getArguments().getSerializable("parametro");

        op = new Operaciones(getContext());


        precio = (EditText)view.findViewById(R.id.et_precio_editar_precio_producto);
        precio2 = (EditText)view.findViewById(R.id.et_precio2_editar_precio_producto);

        //El botón cancelar
        cancelar = (Button) view.findViewById(R.id.cancelar_editar_precio);
        aceptar = (Button) view.findViewById(R.id.aceptar_editar_precio);

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Guardar();
            }
        });

        rellenarDialogo();
        return alertDialogBuilder.create();
    }

    public void Guardar() {

        try {
            op.UpdatePrecioProducto(p.getID(), Float.parseFloat(precio.getText().toString() + "." + precio2.getText().toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "Se guardó correctamente",
                Toast.LENGTH_SHORT).show();
        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
        dismiss();

    }

    public void rellenarDialogo() {
        Log.e("++++++", String.valueOf(p.getPrecio()));
        String precio3 = String.valueOf(p.getPrecio());
        String[] palabrasSeparadas = precio3.split("\\.");
        precio.setText(palabrasSeparadas[0]);
        precio2.setText(palabrasSeparadas[1]);

    }




}
