package com.aiscrim.application.Usuario;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

/*
 *  To use android.app.DialogFragment,
 *  android:minSdkVersion="11" is needed to be specified in AndroidManifest.xml
 */
public class DialogoCrearDireccion extends DialogFragment{
    Operaciones op;
    EditText direccion,codigo_postal,ciudad;
    Button cancelar,aceptar;

    static DialogoCrearDireccion newInstance() {
        return new DialogoCrearDireccion();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.crear_direccion, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Crear nueva dirección");
        op = new Operaciones(getContext());

        direccion = (EditText)view.findViewById(R.id.direccion);
        codigo_postal = (EditText)view.findViewById(R.id.codigo_postal);
        ciudad = (EditText)view.findViewById(R.id.ciudad);

        cancelar = (Button) view.findViewById(R.id.cancelar_direccion);
        aceptar = (Button) view.findViewById(R.id.aceptar_direccion);

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
                op.GuardarDireccion(Usuario.getNick(),direccion.getText().toString(),codigo_postal.getText().toString(),ciudad.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "Se guardó correctamente",
                    Toast.LENGTH_SHORT).show();
            getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
            dismiss();
        }

    }

    public boolean validate() {
        boolean valid = true;

        String cod_postal = codigo_postal.getText().toString();

        if (cod_postal.isEmpty() || cod_postal.length() < 5 || cod_postal.length() > 5) {
            codigo_postal.setError("Introduzca un código postal válido");
            valid = false;
        } else {
            codigo_postal.setError(null);
        }

        return valid;
    }

    public void onSaveFailed() {
        Toast.makeText(getContext(),
                "Error al guardar", Toast.LENGTH_LONG).show();
    }



}
