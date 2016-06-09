package com.aiscrim.application.Usuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiscrim.application.Objetos.Producto;
import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.Direccion;
import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;

import java.sql.SQLException;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorDirecciones
        extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> implements View.OnLongClickListener{

    private View.OnLongClickListener listener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView direccion;
        public TextView codigo_postal;
        public TextView ciudad;
        public TextView predet;

        public ViewHolder(View v) {
            super(v);
            direccion = (TextView) v.findViewById(R.id.texto_direccion);
            codigo_postal = (TextView) v.findViewById(R.id.texto_codigo_postal);
            ciudad = (TextView) v.findViewById(R.id.texto_ciudad);
            predet = (TextView) v.findViewById(R.id.predeterminada_direccion);
        }
    }


    public AdaptadorDirecciones(Context context) {
        this.context = context;
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
        v.setOnLongClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
                Direccion item = Direccion.DIRECCIONES.get(i);
                viewHolder.direccion.setText(item.direccion);
                viewHolder.codigo_postal.setText(item.codigo_postal);
                viewHolder.ciudad.setText(item.ciudad);
                if(item.predeterminada == 1) {
                    viewHolder.predet.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.predet.setVisibility(View.INVISIBLE);
                }
    }

    public void remove(final int position) {

        AlertDialog opciones = new AlertDialog.Builder(
                context).setMessage("¿desea eliminar esta direccion?")
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
            op.removeDireccion(Direccion.DIRECCIONES.get(pos));

            Direccion.DIRECCIONES.remove(pos);
            op.consultarDirecciones(Usuario.getNick());

        } catch (SQLException e) {
            e.printStackTrace();
        }
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