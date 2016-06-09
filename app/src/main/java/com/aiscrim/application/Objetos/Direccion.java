package com.aiscrim.application.Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 19/4/16.
 */
public class Direccion {
    public int ID;
    public String direccion;
    public String codigo_postal;
    public String ciudad;
    public int predeterminada;


    public Direccion(int ID, String direccion, String codigo_postal,
                     String ciudad, int predeterminada) {
        this.ID = ID;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.predeterminada = predeterminada;
    }

    public static List<Direccion> DIRECCIONES = new ArrayList<Direccion>();
    public static List<Direccion> DIRECCION_PREDETERMINADA = new ArrayList<Direccion>();

    public static void remove() {
        DIRECCIONES = new ArrayList<Direccion>();
        DIRECCION_PREDETERMINADA = new ArrayList<Direccion>();
    }

    public static void add(int ID, String direccion, String codigo_postal, String ciudad,int predeterminada){
        if(predeterminada == 1) {
            DIRECCION_PREDETERMINADA.add(new Direccion(ID, direccion, codigo_postal,
                    ciudad,predeterminada));
        }
        DIRECCIONES.add(new Direccion(ID, direccion, codigo_postal,
                ciudad,predeterminada));
    }
}
