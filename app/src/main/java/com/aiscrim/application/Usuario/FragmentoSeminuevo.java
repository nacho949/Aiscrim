package com.aiscrim.application.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiscrim.application.R;

/**
 * Fragmento para la sección de "Inicio"
 */
import android.support.v7.widget.GridLayoutManager;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.Objetos.Videojuego;

import java.sql.SQLException;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoSeminuevo extends Fragment {

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;
    private View.OnClickListener listener;
    private Operaciones operacion;

    public static FragmentoSeminuevo nuevaInstancia(int indiceSeccion) {
        FragmentoSeminuevo fragment = new FragmentoSeminuevo();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmento_grupo_videojuegos, container, false);

        operacion = new Operaciones(getContext());
        try {
            operacion.consultarDirecciones(Usuario.getNick());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        reciclador.setLayoutManager(layoutManager);

        final int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                try {
                    operacion.consultarVideojuegosPS4Seminuevo(Usuario.getNick());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adaptador = new AdaptadorCategorias(Videojuego.PS4_SEMINUEVOS, getContext());
                break;
            case 1:
                try {
                    operacion.consultarVideojuegosXBOXSeminuevos(Usuario.getNick());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adaptador = new AdaptadorCategorias(Videojuego.XBOX_ONE_SEMINUEVOS, getContext());
                break;
        }

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzaractividad(reciclador.getChildAdapterPosition(v), getArguments().getInt(INDICE_SECCION));
                //Toast.makeText(v.getContext(), "Pulsado el elemento " + getArguments().getInt(INDICE_SECCION), Toast.LENGTH_SHORT).show();
            }
        });

        reciclador.setAdapter(adaptador);

        return view;
    }

    public void lanzaractividad(int position, int tipo) {
        Intent intent = new Intent(getActivity().getApplicationContext(), SeleccinVideojuego.class);
        //intent.putExtra(EXTRA_NOMBRE,"diablo3.jpg");
        if(tipo == 0) {
            intent.putExtra("parametro", Videojuego.PS4_SEMINUEVOS.get(position));
        }else{
            intent.putExtra("parametro", Videojuego.XBOX_ONE_SEMINUEVOS.get(position));
        }

        startActivity(intent);
    }
}

/*public class FragmentoTienda extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;

    private ListView list;
    String[] sistemas ={
            "Diablo 3",
            "Far Cry 4",
            "Farming Simulator",
            "Ufc",
            "Fallout 4",
            "Battlefield 4",
            "Just Dance",
            "Preject Cars"
    };
    Integer[] imgid={
            R.drawable.diablo3,
            R.drawable.farcry4,
            R.drawable.farming,
            R.drawable.ufc,
            R.drawable.fallout4,
            R.drawable.battlefield4,
            R.drawable.just_dance_,
            R.drawable.projectcars,
    };

    public FragmentoTienda() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_tienda, container, false);


        CustomListAdapter adapter=new CustomListAdapter(getActivity(), sistemas, imgid);
        list = (ListView)view.findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub


                String Slecteditem = sistemas[+position];
                Toast.makeText(view.getContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    package com.example.macmini.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SeleccionVideojuego extends AppCompatActivity {
    TextView list;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_videojuego);

        list = (TextView)findViewById(R.id.image_name);
        img = (ImageView)findViewById(R.id.imageView);

//Obteniendo la instancia del Intent
        Intent intent = getIntent();
        Bundle extras = new Bundle();
        extras = intent.getExtras();

        String name = extras.getString("nombre");
        int n = extras.getInt("imagen");

//Seteando el valor del extra en el TextView
        list.setText(name);
        img.setImageResource(n);
    }
}
    public void lanzaractividad(int position) {
        Intent intent = new Intent(this,SeleccionVideojuego.class);
        Bundle extras = new Bundle();
        extras.putString("nombre", "Diablo 3");
        extras.putInt("imagen", imgid[+position]);
        //intent.putExtra(EXTRA_NOMBRE,"diablo3.jpg");
        intent.putExtras(extras);
        startActivity(intent);
    }

}*/
