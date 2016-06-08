package com.aiscrim.application.Administrador;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class DialogoCrearProducto extends DialogFragment{
    Operaciones op;
    EditText nombre, precio1,precio2, stock, nombre_imagen;
    Spinner plataforma, tipo, proveedor;
    Button cancelar,aceptar, seleccionar;
    Bitmap imagenSeleccionada;

    private static int SELECT_PICTURE = 2;

    static DialogoCrearProducto newInstance() {
        return new DialogoCrearProducto();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Seleccionar y mostrar el layout a mostrar
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.crear_producto, null);
        alertDialogBuilder.setView(view);
        //Seteamos el título
        alertDialogBuilder.setTitle("Crear nuevo producto");
        op = new Operaciones(getContext());

        ArrayList<String> a = null;
        try {
            a = op.consultarListaProveedores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, a);

        plataforma = (Spinner)view.findViewById(R.id.sp_plataforma_producto);
        tipo = (Spinner)view.findViewById(R.id.sp_tipo_producto);
        proveedor = (Spinner)view.findViewById(R.id.sp_proveedor_producto);

        nombre = (EditText)view.findViewById(R.id.et_nombre_producto);
        precio1 = (EditText)view.findViewById(R.id.et_precio_producto);
        precio2 = (EditText)view.findViewById(R.id.et_precio2_producto);
        stock = (EditText)view.findViewById(R.id.et_stock_producto);
        nombre_imagen = (EditText)view.findViewById(R.id.imagen_producto);

        //El botón cancelar
        cancelar = (Button) view.findViewById(R.id.cancelar_producto);
        aceptar = (Button) view.findViewById(R.id.aceptar_producto);
        seleccionar = (Button) view.findViewById(R.id.btn_selecion_imagen);

        proveedor.setAdapter(adapter);

        seleccionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                int code = SELECT_PICTURE;

            startActivityForResult(intent, code);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarImagen(nombre_imagen.getText().toString());
                Guardar();
            }
        });
        return alertDialogBuilder.create();
    }

    private String guardarImagen(String nombre){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aiscrim/Images";
        File dir = new File(path,nombre + ".jpg");
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(dir);
            imagenSeleccionada.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return dir.getAbsolutePath();
    }

    public void Guardar() {

        try {
            op.GuardarProducto(nombre.getText().toString(), plataforma.getSelectedItem().toString(), stock.getText().toString(), proveedor.getSelectedItem().toString(),
                    precio1.getText().toString() + "." + precio2.getText().toString(), tipo.getSelectedItem().toString(), nombre_imagen.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "Se guardó correctamente",
                Toast.LENGTH_SHORT).show();
        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
        dismiss();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_PICTURE){
            Uri selectedImage = data.getData();
            InputStream is;
            try {
                is = getContext().getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                imagenSeleccionada = BitmapFactory.decodeStream(bis);
            } catch (FileNotFoundException e) {

            }
        }
    }




}
