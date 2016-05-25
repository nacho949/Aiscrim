package com.aiscrim.application.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Direccion;
import com.aiscrim.application.modelo.ItemCarrito;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorCarrito
        extends RecyclerView.Adapter<AdaptadorCarrito.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView name;
        public TextView plataforma;
        public TextView autor;
        public TextView precio;
        public TextView cantidad;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            plataforma = (TextView) v.findViewById(R.id.carrito_palataforma);
            autor = (TextView) v.findViewById(R.id.autor);
            precio = (TextView) v.findViewById(R.id.price);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
            imagen = (ImageView) v.findViewById(R.id.image);
        }
    }


    public AdaptadorCarrito() {
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
        viewHolder.autor.setText(item.game.getDesarrollador());
        viewHolder.precio.setText(item.game.getPrecio() + " €");
        viewHolder.imagen.setImageResource(item.game.getIdDrawable());
        viewHolder.cantidad.setText("Cantidad: " + item.cantidad);
    }

    public void remove(int position) {
        ItemCarrito.CARRITO.remove(position);
        notifyItemRemoved(position);
    }

}