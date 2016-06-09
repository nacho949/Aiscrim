package com.aiscrim.application.Usuario;

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

import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

public class DialogoCrearTarjeta extends DialogFragment{
    Operaciones op;
    Spinner dia, year,tipo;
    EditText NumTarjeta,TitularTarjeta;
    Button cancelar,aceptar;

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
        op = new Operaciones(getContext());

        dia = (Spinner)view.findViewById(R.id.dia_tarjeta);
        year = (Spinner)view.findViewById(R.id.year_tarjeta);
        tipo = (Spinner)view.findViewById(R.id.tipo_tarjeta);

        NumTarjeta = (EditText)view.findViewById(R.id.numero_tarjeta);
        TitularTarjeta = (EditText)view.findViewById(R.id.titular_tarjeta);

        //El botón cancelar
        cancelar = (Button) view.findViewById(R.id.cancelar_tarjeta);
        aceptar = (Button) view.findViewById(R.id.aceptar_tarjeta);

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
        return alertDialogBuilder.create();
    }

    public void Guardar() {

        if (!validate()) {
            onSaveFailed();
            return;
        }else {
            try {
                op.GuardarTarjeta(Usuario.getNick(), NumTarjeta.getText().toString(), TitularTarjeta.getText().toString(), dia.getSelectedItem().toString(), year.getSelectedItem().toString(),
                        tipo.getSelectedItem().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "Se guardó la tarjeta correctamente",
                    Toast.LENGTH_SHORT).show();
            getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
            dismiss();
        }
    }

    public boolean validate() {
        boolean valid = true;

        String numero = NumTarjeta.getText().toString();

        if (numero.isEmpty() || numero.length() < 16 || numero.length() > 16) {
            NumTarjeta.setError("Introduzca un número válido");
            valid = false;
        } else {
            NumTarjeta.setError(null);
        }

        return valid;
    }

    public void onSaveFailed() {
        Toast.makeText(getContext(),
                "Error al guardar", Toast.LENGTH_LONG).show();
    }



}
