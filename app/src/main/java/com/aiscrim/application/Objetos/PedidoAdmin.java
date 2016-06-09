package com.aiscrim.application.Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoAdmin implements Serializable {
    public int num;
    public String estado;


    public PedidoAdmin(int num, String estado) {
        this.num = num;
        this.estado = estado;

    }

    public static List<PedidoAdmin> PEDIDOS_ADMIN  = new ArrayList<PedidoAdmin>();


    public static void remove() {
        PEDIDOS_ADMIN = new ArrayList<PedidoAdmin>();
    }

    public static void add(int num, String estado){
        PEDIDOS_ADMIN.add(new PedidoAdmin(num, estado));
    }
}
