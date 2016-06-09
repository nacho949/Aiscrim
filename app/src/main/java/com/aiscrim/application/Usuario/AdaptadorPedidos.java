package com.aiscrim.application.Usuario;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiscrim.application.Objetos.Pedido;
import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.Videojuego;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorPedidos
        extends RecyclerView.Adapter<AdaptadorPedidos.ViewHolder> implements View.OnClickListener  {



    private View.OnClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView numero_pedido;
        public TextView estado;

        public ViewHolder(View v) {
            super(v);

            numero_pedido = (TextView) v.findViewById(R.id.numero_pedido);
            estado = (TextView) v.findViewById(R.id.estado_pedido);
        }
    }


    public AdaptadorPedidos(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Pedido.PEDIDOS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_pedidos, viewGroup, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Pedido item = Pedido.PEDIDOS.get(i);

        viewHolder.numero_pedido.setText(item.num + "");
        viewHolder.estado.setText(item.estado);
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