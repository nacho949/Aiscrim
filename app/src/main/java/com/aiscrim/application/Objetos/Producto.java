package com.aiscrim.application.Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Producto implements Serializable{
    private int ID;
    private int stock;
    private float precio;
    private String plataforma;
    private String proveedor;
    private String nombre;
    private int descuento;
    private String imagen;
    private String tipo;

    public Producto(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
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

    public static  List<Producto> PRODUCTOS = new ArrayList<Producto>();

    public static void remove() {
        PRODUCTOS = new ArrayList<Producto>();
    }

    public static void add(int ID,String nombre, String plataforma, int stock,String proveedor, String imagen, float precio, String tipo, int descuento) {
        PRODUCTOS.add(new Producto( ID, nombre,  plataforma,  stock, proveedor,   imagen,  precio,  tipo,  descuento));
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
