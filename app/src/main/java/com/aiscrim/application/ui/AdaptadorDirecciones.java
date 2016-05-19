package com.aiscrim.application.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Direccion;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorDirecciones
        extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView direccion;
        public TextView codigo_postal;
        public TextView ciudad;

        public ViewHolder(View v) {
            super(v);
            direccion = (TextView) v.findViewById(R.id.texto_direccion);
            codigo_postal = (TextView) v.findViewById(R.id.texto_codigo_postal);
            ciudad = (TextView) v.findViewById(R.id.texto_ciudad);
        }
    }


    public AdaptadorDirecciones() {
    }

    @Override
    public int getItemCount() {
        return Direccion.DIRECCIONES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_lista_direccion, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
                Direccion item = Direccion.DIRECCIONES.get(i);
                viewHolder.direccion.setText(item.direccion);
                viewHolder.codigo_postal.setText(item.codigo_postal);
                viewHolder.ciudad.setText(item.ciudad);
    }

}