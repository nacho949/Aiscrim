package com.aiscrim.application.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

/**
 * Fragmento para la pestaña "PERFIL" De la sección "Mi Cuenta"
 */
public class FragmentoPerfil extends Fragment {
    ImageButton editar;
    ImageButton guardar;
    CardView card;
    EditText nombreApellidos;
    TextView mail;
    TextView tlf;
    private Operaciones operacion;

    public FragmentoPerfil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmento_perfil, container, false);

        operacion = new Operaciones(getContext());


        nombreApellidos = (EditText) view.findViewById(R.id.texto_nombre);
        mail = (TextView) view.findViewById(R.id.texto_email);
        tlf = (TextView) view.findViewById(R.id.texto_telefono);

        nombreApellidos.setText(Usuario.getNombre() + " " + Usuario.getApellidos());
        mail.setText(Usuario.getMail());
        tlf.setText(Usuario.getTelefono());

        editar = (ImageButton) view.findViewById(R.id.boton_editar);
        editar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pulsado boton editar", Toast.LENGTH_SHORT).show();
                nombreApellidos.setEnabled(true);
                nombreApellidos.setSelection(nombreApellidos.getText().length());
                mail.setEnabled(true);
                tlf.setEnabled(true);
                guardar.setVisibility(View.VISIBLE);
                editar.setVisibility(View.INVISIBLE);
            }
        });

        guardar = (ImageButton) view.findViewById(R.id.boton_guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pulsado boton guardar", Toast.LENGTH_SHORT).show();
                nombreApellidos.setEnabled(false);
                mail.setEnabled(false);
                tlf.setEnabled(false);
                guardar.setVisibility(View.INVISIBLE);
                editar.setVisibility(View.VISIBLE);
                updateDatos();
            }
        });

        card = (CardView) view.findViewById(R.id.imagebutton2);
        card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pulsado boton contraseña", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CambiarPasswordActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void updateDatos() {
        String[] palabrasSeparadas = nombreApellidos.getText().toString().split(" ");
        try {
            operacion.updateDatosUsuario(Usuario.getNick(),palabrasSeparadas[0],palabrasSeparadas[1] + " " + palabrasSeparadas[2], mail.getText().toString(), tlf.getText().toString());
            operacion.getUsuarioLogeado(Usuario.getNick());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 }
