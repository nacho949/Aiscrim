package com.aiscrim.application.Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Videojuego implements Serializable{
    private int ID;
    private int stock;
    private float precio;
    private String plataforma;
    private String proveedor;
    private String nombre;
    private int descuento;
    private String imagen;
    private String tipo;

    public Videojuego(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        this.ID = ID;
        this.stock = stock;
        this.tipo = tipo;
        this.precio = precio;
        this.plataforma = plataforma;
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descuento = descuento;
    }

    public static  List<Videojuego> XBOX_ONE = new ArrayList<Videojuego>();
    public static  List<Videojuego> XBOX_ONE_SEMINUEVOS = new ArrayList<>();
    public static  List<Videojuego> PS4_SEMINUEVOS = new ArrayList<>();
    public static  List<Videojuego> PS4 = new ArrayList<>();

    public static void removePS4() {
        PS4 = new ArrayList<>();
    }

    public static void removeXBOX() {
        XBOX_ONE = new ArrayList<>();
    }

    public static void removePS4Seminuevos() {
        PS4_SEMINUEVOS = new ArrayList<>();
    }

    public static void removeXBOXSeminuevos() {
        XBOX_ONE_SEMINUEVOS = new ArrayList<>();
    }

    public static void addPS4(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        PS4.add(new Videojuego( ID, nombre,  plataforma,  stock, proveedor,   imagen,  precio,  tipo,  descuento));
    }

    public static void addXBOX(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        XBOX_ONE.add(new Videojuego( ID, nombre,  plataforma,  stock, proveedor,  imagen,  precio,  tipo,  descuento));
    }

    public static void addPS4Seminuevo(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        PS4_SEMINUEVOS.add(new Videojuego( ID, nombre,  plataforma,  stock, proveedor,  imagen,  precio,  tipo,  descuento));
    }

    public static void addXBOXSeminuevo(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        XBOX_ONE_SEMINUEVOS.add(new Videojuego( ID, nombre,  plataforma,  stock, proveedor,  imagen,  precio,  tipo,  descuento));
    }


    public int getID() {
        return ID;
    }

    public int getStock() {
        return stock;
    }

    public float getPrecio() {
        return precio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDescuento() {
        return descuento;
    }


    public String getImagen() {
        return imagen;
    }

    public String getTipo() {
        return tipo;
    }

}
