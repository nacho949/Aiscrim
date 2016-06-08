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
import com.aiscrim.application.Objetos.PedidoAdmin;
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
public class AdaptadorPedidosAdmin
        extends RecyclerView.Adapter<AdaptadorPedidosAdmin.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView numero;
        public TextView estado;

        public ViewHolder(View v) {
            super(v);
            numero = (TextView) v.findViewById(R.id.numero_pedido_admin);
            estado = (TextView) v.findViewById(R.id.estado_pedido_admin);
        }
    }


    public AdaptadorPedidosAdmin(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return PedidoAdmin.PEDIDOS_ADMIN.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pedidos_admin, viewGroup, false);
        v.setOnLongClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PedidoAdmin item = PedidoAdmin.PEDIDOS_ADMIN.get(i);
        viewHolder.numero.setText("Número pedido: " + item.num);
        viewHolder.estado.setText("Estado: " +item.estado);
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
