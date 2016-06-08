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
import com.aiscrim.application.Objetos.Producto;
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
public class AdaptadorProductos
        extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView plataforma;
        public TextView stock;
        public TextView proveedor;
        public TextView precio;
        public TextView tipo;
        public TextView descuento;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_producto);
            plataforma = (TextView) v.findViewById(R.id.plataforma_producto);
            stock = (TextView) v.findViewById(R.id.stock_producto);
            proveedor = (TextView) v.findViewById(R.id.proveedor_producto);
            precio = (TextView) v.findViewById(R.id.precio_producto);
            tipo = (TextView) v.findViewById(R.id.tipo_producto);
            descuento = (TextView) v.findViewById(R.id.descuento_producto);
        }
    }


    public AdaptadorProductos(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return Producto.PRODUCTOS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_productos, viewGroup, false);
        v.setOnLongClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Producto item = Producto.PRODUCTOS.get(i);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.plataforma.setText(item.getPlataforma());
        viewHolder.stock.setText("Stock: " + item.getStock());
        viewHolder.proveedor.setText(item.getProveedor());
        viewHolder.precio.setText("Precio: " + item.getPrecio());
        viewHolder.tipo.setText("Tipo: " + item.getTipo());
        viewHolder.descuento.setText("Descuento: " + item.getDescuento() + " %");

    }

    public void remove(int position) {
        Log.e("ERROR", "position: " + position);
        Log.e("ERROR", "Tamaño: " + Producto.PRODUCTOS.get(position));
        Operaciones op = new Operaciones(context);
        try {
            op.removeProducto(Producto.PRODUCTOS.get(position));
            Log.e("ERROR", "Tamaño1: " + Producto.PRODUCTOS);
            Producto.PRODUCTOS.remove(position);
            op.consultarProveedores();
            Log.e("ERROR", "Tamaño2: " + Producto.PRODUCTOS);
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
