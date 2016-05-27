package com.aiscrim.application.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Usuario;

import org.w3c.dom.Text;

/**
 * Fragmento para la pestaña "PERFIL" De la sección "Mi Cuenta"
 */
public class FragmentoPerfil extends Fragment {
    ImageButton editar;
    CardView card;
    TextView nombreApellidos;
    TextView mail;
    TextView tlf;

    public FragmentoPerfil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmento_perfil, container, false);

        nombreApellidos = (TextView) view.findViewById(R.id.texto_nombre);
        mail = (TextView) view.findViewById(R.id.texto_email);
        tlf = (TextView) view.findViewById(R.id.texto_telefono);

        nombreApellidos.setText(Usuario.getNombre() + " " + Usuario.getApellidos());
        mail.setText(Usuario.getMail());
        tlf.setText(Usuario.getTelefono());

        editar = (ImageButton) view.findViewById(R.id.boton_editar);
        editar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pulsado boton editar", Toast.LENGTH_SHORT).show();

            }
        });

        card = (CardView) view.findViewById(R.id.imagebutton2);
        card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pulsado boton contraseña", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
