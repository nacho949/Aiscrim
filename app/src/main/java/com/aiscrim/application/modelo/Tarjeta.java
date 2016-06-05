package com.aiscrim.application.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 19/4/16.
 */
public class Tarjeta {
    public int ID;
    public String titular;
    public String numero;
    public String fecha;
    public String tipo;
    public int predeterminada;


    public Tarjeta(int ID, String titular, String numero,
                   String fecha, String tipo, int predeterminada) {
        this.ID = ID;
        this.titular = titular;
        this.numero = numero;
        this.fecha = fecha;
        this.tipo = tipo;
        this.predeterminada = predeterminada;
    }

    public static List<Tarjeta> TARJETAS = new ArrayList<Tarjeta>();
    public static List<Tarjeta> TARJETA_PREDETERMINADA = new ArrayList<Tarjeta>();


    public static void remove() {
        TARJETAS = new ArrayList<Tarjeta>();
        TARJETA_PREDETERMINADA = new ArrayList<Tarjeta>();
    }

    public static void add(int ID, String titular, String numero,
                    String fecha, String tipo, int predeterminada){
        if(predeterminada == 1) {
            TARJETA_PREDETERMINADA.add(new Tarjeta(ID, titular, numero,
                    fecha, tipo,predeterminada));
        }
        TARJETAS.add(new Tarjeta(ID, titular, numero,
                fecha, tipo,predeterminada));
    }
}
