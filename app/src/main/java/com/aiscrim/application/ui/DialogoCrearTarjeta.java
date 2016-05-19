package com.aiscrim.application.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.BaseDeDatos;

import java.sql.SQLException;

/*
 *  To use android.app.DialogFragment,
 *  android:minSdkVersion="11" is needed to be specified in AndroidManifest.xml
 */
public class DialogoCrearTarjeta extends DialogFragment{
    BaseDeDatos admin;
    Spinner dia, year,tipo;
    EditText NumTarjeta,TitularTarjeta;

    static DialogoCrearTarjeta newInstance() {
        return new DialogoCrearTarjeta();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.crear_tarjeta, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Crear nueva tarjeta");
        admin = new BaseDeDatos(getContext());
        try {
            admin.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dia = (Spinner)view.findViewById(R.id.dia_tarjeta);
        year = (Spinner)view.findViewById(R.id.year_tarjeta);
        tipo = (Spinner)view.findViewById(R.id.tipo_tarjeta);

        NumTarjeta = (EditText)view.findViewById(R.id.numero_tarjeta);
        TitularTarjeta = (EditText)view.findViewById(R.id.titular_tarjeta);

        //El botón cancelar
        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //El botón aceptar
        alertDialogBuilder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Guardar();
                getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
            }
        });
        return alertDialogBuilder.create();
    }

    public void Guardar() {
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numeroTarjeta = NumTarjeta.getText().toString();
        String TitularTarjeta1 = TitularTarjeta.getText().toString();
        String Fecha = dia.getSelectedItem().toString() + "/" + year.getSelectedItem().toString();
        String Tipo = tipo.getSelectedItem().toString();
        ContentValues registro = new ContentValues();
        registro.put("NumeroTarjeta", numeroTarjeta);
        registro.put("TitularTarjeta", TitularTarjeta1);
        registro.put("TipoTarjeta", Tipo);
        registro.put("Fecha_vecimiento", Fecha);
        bd.insert("Tarjetas", null, registro);
        bd.close();

        Toast.makeText(getContext(), "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }



}
