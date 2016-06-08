package com.aiscrim.application.Objetos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String Nick;
    private String Nombre;
    private String Apellidos;
    private String mail;
    private String telefono;

    public Cliente(String nick, String nombre, String apellidos, String mail, String telefono) {
        this.Nick = nick;
        this.Nombre = nombre;
        this.Apellidos = apellidos;
        this.mail = mail;
        this.telefono = telefono;
    }

    public static List<Cliente> CLIENTES = new ArrayList<Cliente>();

    public static void remove() {
        CLIENTES = new ArrayList<Cliente>();
    }

    public static void add(String nick, String nombre, String apellidos, String mail, String telefono){
        CLIENTES.add(new Cliente( nick,  nombre,  apellidos,  mail,  telefono));
    }


    public String getNick() {
        return Nick;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

}
