package com.aiscrim.application.Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 27/5/16.
 */
public class Usuario {
    public static String Nick;
    public static String password;
    public static String Nombre;
    public static String Apellidos;
    public static String mail;
    public static String telefono;

    public Usuario(String nick, String password, String nombre, String apellidos, String mail, String telefono) {
        this.Nick = nick;
        this.password = password;
        this.Nombre = nombre;
        this.Apellidos = apellidos;
        this.mail = mail;
        this.telefono = telefono;
    }

    public static List<Usuario> USUARIO = new ArrayList<Usuario>();
    public static void remove() {
        USUARIO = new ArrayList<Usuario>();
    }


    public static String getNick() {
        return USUARIO.get(0).Nick;
    }

    public static String getPassword() {
        return USUARIO.get(0).password;
    }

    public static String getNombre() {
        return USUARIO.get(0).Nombre;
    }

    public static String getApellidos() {
        return USUARIO.get(0).Apellidos;
    }

    public static String getMail() {
        return USUARIO.get(0).mail;
    }

    public static String getTelefono() {
        return USUARIO.get(0).telefono;
    }

}
