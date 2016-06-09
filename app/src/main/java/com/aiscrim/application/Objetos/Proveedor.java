package com.aiscrim.application.Objetos;

/**
 * Created by macmini on 7/6/16.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by macmini on 19/4/16.
 */
public class Proveedor implements Serializable {
    public int ID;
    public String nombre;
    public String direccion;
    public String telefono;
    public String url;
    public String mail;


    public Proveedor(int ID, String nombre, String direccion,
                   String telefono, String url, String mail) {
        this.ID = ID;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.mail = mail;
    }

    public static List<Proveedor> PROVEEDORES = new ArrayList<Proveedor>();

    public static void remove() {
        PROVEEDORES = new ArrayList<Proveedor>();
    }

    public static void add(int ID, String nombre, String direccion,
                           String telefono, String url, String mail){
        PROVEEDORES.add(new Proveedor( ID,  nombre,  direccion,
                 telefono,  url,  mail));
    }
}

