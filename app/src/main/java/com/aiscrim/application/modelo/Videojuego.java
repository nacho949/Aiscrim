package com.aiscrim.application.modelo;

import com.aiscrim.application.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Videojuego implements Serializable{
    private float precio;
    private String plataforma;
    private String desarrollador;
    private String nombre;
    private int idDrawable;

    public Videojuego(float precio, String nombre, String plataforma, String dessarrollador, int idDrawable) {
        this.precio = precio;
        this.plataforma = plataforma;
        this.desarrollador = dessarrollador;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static final List<Videojuego> COMIDAS_POPULARES = new ArrayList<Videojuego>();
    public static final List<Videojuego> BEBIDAS = new ArrayList<>();
    public static final List<Videojuego> POSTRES = new ArrayList<>();
    public static final List<Videojuego> PS4 = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Videojuego(5, "Camarones Tismados","PS4", "EA", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Videojuego(3.2f, "Rosca Herbárea","PS4", "EA", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Videojuego(12f, "Sushi Extremo","PS4", "EA", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Videojuego(9, "Sandwich Deli","PS4", "EA", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Videojuego(34f, "Lomo De Cerdo Austral","PS4", "EA", R.drawable.lomo_cerdo));

        PS4.add(new Videojuego(5, "Diablo3", "PS4", "EA", R.drawable.diablo3));
        PS4.add(new Videojuego(3.2f, "Far cry 4","PS4", "EA", R.drawable.farcry4));
        PS4.add(new Videojuego(12f, "Farming simulator","PS4", "EA", R.drawable.farming));
        PS4.add(new Videojuego(9, "Ufc","PS4", "EA", R.drawable.ufc));
        PS4.add(new Videojuego(34f, "Fallout 4","PS4", "EA", R.drawable.fallout4));

        BEBIDAS.add(new Videojuego(3, "Taza de Café","PS4", "EA", R.drawable.cafe));
        BEBIDAS.add(new Videojuego(12, "Coctel Tronchatoro","PS4", "EA", R.drawable.coctel));
        BEBIDAS.add(new Videojuego(5, "Jugo Natural","PS4", "EA", R.drawable.jugo_natural));
        BEBIDAS.add(new Videojuego(24, "Coctel Jordano","PS4", "EA", R.drawable.coctel_jordano));
        BEBIDAS.add(new Videojuego(30, "Botella Vino Tinto Darius","PS4", "EA", R.drawable.vino_tinto));

        POSTRES.add(new Videojuego(2, "Postre De Vainilla", "PS4", "EA",R.drawable.postre_vainilla));
        POSTRES.add(new Videojuego(3, "Flan Celestial", "PS4", "EA",R.drawable.flan_celestial));
        POSTRES.add(new Videojuego(2.5f, "Cupcake Festival","PS4", "EA", R.drawable.cupcakes_festival));
        POSTRES.add(new Videojuego(4, "Pastel De Fresa","PS4", "EA", R.drawable.pastel_fresa));
        POSTRES.add(new Videojuego(5, "Muffin Amoroso","PS4", "EA", R.drawable.muffin_amoroso));
    }

    public float getPrecio() {
        return precio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }


}
