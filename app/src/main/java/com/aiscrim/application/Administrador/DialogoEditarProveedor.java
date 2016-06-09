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

import com.aiscrim.application.Objetos.Proveedor;
import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

public class DialogoEditarProveedor extends DialogFragment{
    Operaciones op;
    EditText nombre, direccion, tlf, url, mail;
    Button cancelar,aceptar;
    Proveedor p;

    static DialogoEditarProveedor newInstance() {
        return new DialogoEditarProveedor();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.crear_proveedor, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Editar proveedor");

        p = (Proveedor)getArguments().getSerializable("parametro");

        op = new Operaciones(getContext());


        nombre = (EditText)view.findViewById(R.id.et_nombre_proveedor);
        direccion = (EditText)view.findViewById(R.id.et_direccion_proveedor);
        tlf = (EditText)view.findViewById(R.id.et_tlf_proveedor);
        url = (EditText)view.findViewById(R.id.et_url_proveedor);
        mail = (EditText)view.findViewById(R.id.et_mail_proveedor);

        //El botón cancelar
        cancelar = (Button) view.findViewById(R.id.cancelar_proveedor);
        aceptar = (Button) view.findViewById(R.id.aceptar_proveedor);

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
            op.UpdateProveedor(p.ID, nombre.getText().toString(), direccion.getText().toString(), tlf.getText().toString(), url.getText().toString(), mail.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "Se guardó correctamente",
                Toast.LENGTH_SHORT).show();
        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
        dismiss();

    }

    public void rellenarDialogo() {
        nombre.setText(p.nombre);
        direccion.setText(p.direccion);
        tlf.setText(p.telefono);
        url.setText(p.url);
        mail.setText(p.mail);
    }




}
