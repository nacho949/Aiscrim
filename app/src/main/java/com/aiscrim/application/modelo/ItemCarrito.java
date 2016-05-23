package com.aiscrim.application.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 19/4/16.
 */
public class ItemCarrito {
    public Videojuego game;
    public int cantidad;


    public ItemCarrito(Videojuego game, int cantidad) {
        this.game = game;
        this.cantidad = cantidad;
    }

    public static List<Videojuego> GAMES = new ArrayList<Videojuego>();
    public static List<ItemCarrito> CARRITO = new ArrayList<ItemCarrito>();

    public static void remove() {
        CARRITO = new ArrayList<ItemCarrito>();
        GAMES = new ArrayList<Videojuego>();
    }

    public static void add(Videojuego game, int cantidad) {
        if(!exiteItem(game)) {
            CARRITO.add(new ItemCarrito(game,cantidad));
        }

    }

    public static boolean exiteItem(Videojuego game) {
        for (int i = 0; i<CARRITO.size(); i++) {
            if(CARRITO.get(i).game.getNombre().equals(game.getNombre()) && CARRITO.get(i).game.getPlataforma().equals(game.getPlataforma())){
                CARRITO.get(i).cantidad ++;
                return true;
            }
        }
        return false;
    }
}