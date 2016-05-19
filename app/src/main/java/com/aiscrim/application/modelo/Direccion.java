package com.aiscrim.application.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 19/4/16.
 */
public class Direccion {
    public String direccion;
    public String codigo_postal;
    public String ciudad;


    public Direccion(String direccion, String codigo_postal,
                     String ciudad) {
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
    }

    public static List<Direccion> DIRECCIONES = new ArrayList<Direccion>();

    public static void remove() {
        DIRECCIONES = new ArrayList<Direccion>();
    }

    public static void add(String direccion, String codigo_postal, String ciudad){
        DIRECCIONES.add(new Direccion(direccion, codigo_postal,
                ciudad));
    }
}
