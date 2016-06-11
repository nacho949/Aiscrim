package com.aiscrim.application.Administrador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Cliente;
import com.aiscrim.application.Objetos.Direccion;
import com.aiscrim.application.Objetos.Producto;
import com.aiscrim.application.Objetos.Proveedor;
import com.aiscrim.application.Objetos.Tarjeta;
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
import java.text.DecimalFormat;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorProductos
        extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;
    Fragment targetFragment;
    FragmentManager fragmentManager;
    DecimalFormat df = new DecimalFormat("0.00");

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


    public AdaptadorProductos(Context context, Fragment targetFragment, FragmentManager fragmentManager) {
        this.context = context;
        this.targetFragment = targetFragment;
        this.fragmentManager = fragmentManager;
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
        viewHolder.precio.setText("Precio: " + df.format(item.getPrecio()));
        viewHolder.tipo.setText("Tipo: " + item.getTipo());
        viewHolder.descuento.setText("Descuento: " + item.getDescuento() + " %");

    }

    public void remove(final int position) {

        AlertDialog opciones = new AlertDialog.Builder(
                context).setMessage("¿desea eliminar este producto?")
                .setTitle("Confirmacion")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarProducto(position);
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

    public void eliminarProducto(int pos) {
        Operaciones op = new Operaciones(context);
        try {
            op.removeProducto(Producto.PRODUCTOS.get(pos));

            Producto.PRODUCTOS.remove(pos);
            op.consultarProductos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStock(int pos) {
        DialogFragment a = DialogoEditarStockProducto.newInstance();
        Bundle args = new Bundle();
        args.putSerializable("parametro", Producto.PRODUCTOS.get(pos));
        a.setArguments(args);
        a.setTargetFragment(targetFragment, 0);
        a.show(fragmentManager, "dialog");
    }


    public void updatePrecio(int pos) {
        DialogFragment a = DialogoEditarPrecioProducto.newInstance();
        Bundle args = new Bundle();
        args.putSerializable("parametro", Producto.PRODUCTOS.get(pos));
        a.setArguments(args);
        a.setTargetFragment(targetFragment, 0);
        a.show(fragmentManager, "dialog");
    }

    public void updateDescuento(int pos) {
        DialogFragment a = DialogoEditarDescuentoProducto.newInstance();
        Bundle args = new Bundle();
        args.putSerializable("parametro", Producto.PRODUCTOS.get(pos));
        a.setArguments(args);
        a.setTargetFragment(targetFragment, 0);
        a.show(fragmentManager, "dialog");
    }

    public void editar(final int position) {
        String[] opc = new String[]{"Editar Stock", "Editar Precio", "Editar Descuento"};


        AlertDialog opciones = new AlertDialog.Builder(
                context).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        }).setItems(opc,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int selected) {
                        if (selected == 0) {
                            updateStock(position);
                        } else if (selected == 1) {
                            updatePrecio(position);
                        } else {
                            updateDescuento(position);
                        }
                    }
                }).create();
        opciones.show();
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
