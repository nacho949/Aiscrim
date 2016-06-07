package com.aiscrim.application.Administrador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.BaseDeDatos.Operaciones;
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
public class AdaptadorProveedores
        extends RecyclerView.Adapter<AdaptadorProveedores.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView direccion;
        public TextView tlf;
        public TextView url;
        public TextView mail;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_proveedor);
            direccion = (TextView) v.findViewById(R.id.direccion_proveedor);
            tlf = (TextView) v.findViewById(R.id.telefono_proveedor);
            url = (TextView) v.findViewById(R.id.url_proveedor);
            mail = (TextView) v.findViewById(R.id.mail_proveedor);
        }
    }


    public AdaptadorProveedores(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return Proveedor.PROVEEDORES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_proveedores, viewGroup, false);
        v.setOnLongClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Proveedor item = Proveedor.PROVEEDORES.get(i);
        viewHolder.nombre.setText(item.nombre);
        viewHolder.direccion.setText(item.direccion);
        viewHolder.tlf.setText(item.telefono);
        viewHolder.url.setText(item.url);
        viewHolder.mail.setText(item.mail);

    }

    public void remove(int position) {
        Log.e("ERROR", "position: " + position);
        Log.e("ERROR", "Tamaño: " + Proveedor.PROVEEDORES.get(position));
        Operaciones op = new Operaciones(context);
        try {
            op.removeProveedor(Proveedor.PROVEEDORES.get(position));
            Log.e("ERROR", "Tamaño1: " + Direccion.DIRECCIONES);
            Proveedor.PROVEEDORES.remove(position);
            op.consultarProveedores();
            Log.e("ERROR", "Tamaño2: " + Direccion.DIRECCIONES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void editar(int position) {
        //notifyDataSetChanged();
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
