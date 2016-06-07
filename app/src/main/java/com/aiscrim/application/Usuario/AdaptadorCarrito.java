package com.aiscrim.application.Usuario;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.ItemCarrito;

import java.text.DecimalFormat;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorCarrito
        extends RecyclerView.Adapter<AdaptadorCarrito.ViewHolder> {

    Context context;
    DecimalFormat df = new DecimalFormat("0.00");
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView name;
        public TextView plataforma;
        public TextView autor;
        public TextView precio;
        public TextView cantidad;
        public TextView tipo_item;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            plataforma = (TextView) v.findViewById(R.id.carrito_palataforma);
            autor = (TextView) v.findViewById(R.id.autor);
            precio = (TextView) v.findViewById(R.id.price);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
            tipo_item = (TextView) v.findViewById(R.id.tipo_item);
            imagen = (ImageView) v.findViewById(R.id.image);
        }
    }


    public AdaptadorCarrito(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return ItemCarrito.CARRITO.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cesta_compra, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        ItemCarrito item = ItemCarrito.CARRITO.get(i);
        viewHolder.name.setText(item.game.getNombre());
        viewHolder.plataforma.setText(item.game.getPlataforma());
        viewHolder.autor.setText(item.game.getProveedor());
        viewHolder.tipo_item.setText(item.game.getTipo());
        if(item.game.getDescuento() == 0) {
            viewHolder.precio.setText(df.format(item.game.getPrecio()) + " €");
        }else{
            float p = item.game.getPrecio();
            float desc = item.game.getDescuento();
            viewHolder.precio.setText(df.format((p * (1 - (desc / 100)))) + " €");
        }
        viewHolder.imagen.setImageResource(context.getResources().getIdentifier(item.game.getImagen(),"drawable",context.getPackageName()));
        viewHolder.cantidad.setText("Cantidad: " + item.cantidad);
    }

    public void remove(int position) {
        ItemCarrito.CARRITO.remove(position);
        notifyItemRemoved(position);
    }

}