package com.aiscrim.application.Administrador;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
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

public class DialogoEditarStockProducto extends DialogFragment{
    Operaciones op;
    EditText stock;
    Button cancelar,aceptar;
    Producto p;

    static DialogoEditarStockProducto newInstance() {
        return new DialogoEditarStockProducto();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.editar_stock_producto, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Editar Stock");

        p = (Producto)getArguments().getSerializable("parametro");

        op = new Operaciones(getContext());


        stock = (EditText)view.findViewById(R.id.et_stock);

        //El botón cancelar
        cancelar = (Button) view.findViewById(R.id.cancelar_editar_stock);
        aceptar = (Button) view.findViewById(R.id.aceptar_editar_stock);

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
            op.UpdateStockProducto(p.getID(), Integer.parseInt(stock.getText().toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "Se guardó correctamente",
                Toast.LENGTH_SHORT).show();
        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
        dismiss();

    }

    public void rellenarDialogo() {
        stock.setText(p.getStock() + "");

    }




}
