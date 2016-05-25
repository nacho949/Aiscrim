package com.aiscrim.application.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 19/4/16.
 */
public class Tarjeta {
    public String titular;
    public String numero;
    public String fecha;
    public String tipo;


    public Tarjeta(String titular, String numero,
                   String fecha, String tipo) {
        this.titular = titular;
        this.numero = numero;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public static List<Tarjeta> TARJETAS = new ArrayList<Tarjeta>();

    public static void remove() {
        TARJETAS = new ArrayList<Tarjeta>();
    }

    public static void add(String titular, String numero,
                    String fecha, String tipo){
        TARJETAS.add(new Tarjeta(titular, numero,
                fecha, tipo));
    }
}
