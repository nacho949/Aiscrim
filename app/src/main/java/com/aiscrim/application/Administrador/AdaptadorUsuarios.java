package com.aiscrim.application.Administrador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Cliente;
import com.aiscrim.application.Objetos.Direccion;
import com.aiscrim.application.Objetos.Proveedor;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.R;

import java.sql.SQLException;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.Direccion;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorUsuarios
        extends RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nick;
        public TextView nombre_apellidos;
        public TextView tlf;
        public TextView mail;

        public ViewHolder(View v) {
            super(v);
            nick = (TextView) v.findViewById(R.id.nick_cliente);
            nombre_apellidos = (TextView) v.findViewById(R.id.nombre_apellidos_cliente);
            tlf = (TextView) v.findViewById(R.id.telefono_cliente);
            mail = (TextView) v.findViewById(R.id.mail_cliente);
        }
    }


    public AdaptadorUsuarios(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return Cliente.CLIENTES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_clientes, viewGroup, false);
        v.setOnLongClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Cliente item = Cliente.CLIENTES.get(i);
        viewHolder.nick.setText(item.getNick());
        viewHolder.nombre_apellidos.setText(item.getNombre() + " " + item.getApellidos());
        viewHolder.tlf.setText(item.getTelefono());
        viewHolder.mail.setText(item.getMail());

    }

    public void remove(final int position) {

        AlertDialog opciones = new AlertDialog.Builder(
                context).setMessage("¿desea eliminar este usuario?")
                .setTitle("Confirmacion")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarCliente(position);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                }).create();

        opciones.show();
    }

    public void eliminarCliente(int pos) {
        Operaciones op = new Operaciones(context);
        try {
            op.removeCliente(Cliente.CLIENTES.get(pos));

            Cliente.CLIENTES.remove(pos);
            op.consultarClientes();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(listener != null)
            listener.onLongClick(v);
        return true;
    }

}
