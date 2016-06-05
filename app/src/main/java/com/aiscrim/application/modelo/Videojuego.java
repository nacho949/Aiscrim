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
    private int descuento;
    private int idDrawable;

    public Videojuego(float precio, String nombre, String plataforma, String dessarrollador, int idDrawable, int descuento) {
        this.precio = precio;
        this.plataforma = plataforma;
        this.desarrollador = dessarrollador;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.descuento = descuento;
    }

    public static final List<Videojuego> COMIDAS_POPULARES = new ArrayList<Videojuego>();
    public static final List<Videojuego> BEBIDAS = new ArrayList<>();
    public static final List<Videojuego> POSTRES = new ArrayList<>();
    public static final List<Videojuego> PS4 = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Videojuego(5, "Camarones Tismados","PS4", "EA", R.drawable.camarones, 0));
        COMIDAS_POPULARES.add(new Videojuego(3.2f, "Rosca Herbárea","PS4", "EA", R.drawable.rosca, 0));
        COMIDAS_POPULARES.add(new Videojuego(12f, "Sushi Extremo","PS4", "EA", R.drawable.sushi, 0));
        COMIDAS_POPULARES.add(new Videojuego(9, "Sandwich Deli","PS4", "EA", R.drawable.sandwich, 0));
        COMIDAS_POPULARES.add(new Videojuego(34f, "Lomo De Cerdo Austral","PS4", "EA", R.drawable.lomo_cerdo, 0));

        PS4.add(new Videojuego(5, "Diablo3", "PS4", "EA", R.drawable.diablo3, 30));
        PS4.add(new Videojuego(3.2f, "Far cry 4","PS4", "EA", R.drawable.farcry4, 0));
        PS4.add(new Videojuego(12f, "Farming simulator","PS4", "EA", R.drawable.farming, 20));
        PS4.add(new Videojuego(9, "Ufc","PS4", "EA", R.drawable.ufc, 0));
        PS4.add(new Videojuego(34f, "Fallout 4","PS4", "EA", R.drawable.fallout4, 0));

        BEBIDAS.add(new Videojuego(3, "Taza de Café","PS4", "EA", R.drawable.cafe, 0));
        BEBIDAS.add(new Videojuego(12, "Coctel Tronchatoro","PS4", "EA", R.drawable.coctel, 0));
        BEBIDAS.add(new Videojuego(5, "Jugo Natural","PS4", "EA", R.drawable.jugo_natural, 0));
        BEBIDAS.add(new Videojuego(24, "Coctel Jordano","PS4", "EA", R.drawable.coctel_jordano, 0));
        BEBIDAS.add(new Videojuego(30, "Botella Vino Tinto Darius","PS4", "EA", R.drawable.vino_tinto, 0));

        POSTRES.add(new Videojuego(2, "Postre De Vainilla", "PS4", "EA",R.drawable.postre_vainilla, 0));
        POSTRES.add(new Videojuego(3, "Flan Celestial", "PS4", "EA",R.drawable.flan_celestial, 0));
        POSTRES.add(new Videojuego(2.5f, "Cupcake Festival","PS4", "EA", R.drawable.cupcakes_festival, 0));
        POSTRES.add(new Videojuego(4, "Pastel De Fresa","PS4", "EA", R.drawable.pastel_fresa, 0));
        POSTRES.add(new Videojuego(5, "Muffin Amoroso","PS4", "EA", R.drawable.muffin_amoroso, 0));
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

    public int getDescuento() {
        return descuento;
    }

}
