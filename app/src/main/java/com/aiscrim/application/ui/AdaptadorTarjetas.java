package com.aiscrim.application.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.modelo.Operaciones;
import com.aiscrim.application.modelo.Tarjeta;

import java.sql.SQLException;

/**
 * Created by macmini on 18/4/16.
 */
public class AdaptadorTarjetas
        extends RecyclerView.Adapter<AdaptadorTarjetas.ViewHolder> {

    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titular;
        public TextView numero;
        public TextView fecha;
        public TextView tipo;

        public ViewHolder(View v) {
            super(v);
            titular = (TextView) v.findViewById(R.id.titular);
            numero = (TextView) v.findViewById(R.id.numero_tarjeta);
            fecha = (TextView) v.findViewById(R.id.fecha);
            tipo = (TextView) v.findViewById(R.id.tipo);
        }
    }


    public AdaptadorTarjetas(Context context) {
           this.context = context;
    }

    @Override
    public int getItemCount() {
        return Tarjeta.TARJETAS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragmento_tarjetas, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Tarjeta item = Tarjeta.TARJETAS.get(i);
        viewHolder.titular.setText(item.titular);
        viewHolder.numero.setText(item.numero);
        viewHolder.fecha.setText(item.fecha);
        viewHolder.tipo.setText(item.tipo);
    }

    public void remove(int position) {
        Log.e("ERROR","position: " + position);
        Operaciones op = new Operaciones(context);
        try {
            op.removeTarjeta(Tarjeta.TARJETAS.get(position));
            op.consultarTarjetas();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }
}