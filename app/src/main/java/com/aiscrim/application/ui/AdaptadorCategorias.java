package com.aiscrim.application.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Videojuego;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias
        extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> implements View.OnClickListener  {



    private View.OnClickListener listener;

    private final List<Videojuego> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre_videojuego;
        public TextView desarrollador;
        public TextView plataforma;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);

            nombre_videojuego = (TextView) v.findViewById(R.id.nombre_videojuego);
            desarrollador = (TextView) v.findViewById(R.id.desarrollador);
            plataforma = (TextView) v.findViewById(R.id.plataforma);
            imagen = (ImageView) v.findViewById(R.id.imagen_videojuego);
        }
    }


    public AdaptadorCategorias(List<Videojuego> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Videojuego item = items.get(i);

        viewHolder.imagen.setImageResource(item.getIdDrawable());
        viewHolder.nombre_videojuego.setText(item.getNombre());
        viewHolder.plataforma.setText(item.getPlataforma());
        viewHolder.desarrollador.setText(item.getDesarrollador());

    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


}