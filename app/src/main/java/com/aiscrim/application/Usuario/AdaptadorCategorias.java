package com.aiscrim.application.Usuario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiscrim.application.R;
import com.aiscrim.application.Objetos.Videojuego;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.FileHandler;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias
        extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> implements View.OnClickListener  {



    private View.OnClickListener listener;
    Context context;
    private final List<Videojuego> items;
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aiscrim/Images/";

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


    public AdaptadorCategorias(List<Videojuego> items, Context context) {
        this.items = items;
        this.context = context;
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
        File imgFile = new  File(path + item.getImagen() + ".jpg");
        Log.e("+++++++", path + item.getImagen() + ".jpg");
        if (imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            viewHolder.imagen.setImageBitmap(myBitmap);
        }
        viewHolder.nombre_videojuego.setText(item.getNombre());
        viewHolder.plataforma.setText(item.getPlataforma());
        viewHolder.desarrollador.setText(item.getProveedor());

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