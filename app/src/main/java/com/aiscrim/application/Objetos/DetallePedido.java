package com.aiscrim.application.Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 7/6/16.
 */
public class DetallePedido {
    private String nombreProducto;
    private String plataforma;
    private int cantidad;
    private float precioU;
    private float precioTotal;
    private String tipo;

    public DetallePedido(String nombreProducto, String plataforma, int cantidad,float precioU, float precioTotal, String tipo) {
        this.nombreProducto = nombreProducto;
        this.plataforma = plataforma;
        this.cantidad = cantidad;
        this.precioU = precioU;
        this.precioTotal = precioTotal;
        this.tipo = tipo;
    }

    public static List<DetallePedido> PEDIDOS_DETALLE  = new ArrayList<DetallePedido>();
    public static void remove() {
        PEDIDOS_DETALLE = new ArrayList<DetallePedido>();
    }

    public static void add(String NombreProducto, String plataforma, int cantidad,float precioU, float precioTotal, String tipo){
        PEDIDOS_DETALLE.add(new DetallePedido( NombreProducto,  plataforma,  cantidad, precioU,  precioTotal,  tipo));
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioU() {
        return precioU;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String getTipo() {
        return tipo;
    }

}
