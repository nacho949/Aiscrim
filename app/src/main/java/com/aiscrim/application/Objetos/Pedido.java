package com.aiscrim.application.Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    public int num;
    public String estado;


    public Pedido(int num, String estado) {
        this.num = num;
        this.estado = estado;

    }

    public static List<Pedido> PEDIDOS  = new ArrayList<Pedido>();


    public static void remove() {
        PEDIDOS = new ArrayList<Pedido>();
    }

    public static void add(int num, String estado){
        PEDIDOS.add(new Pedido(num, estado));
    }
}
