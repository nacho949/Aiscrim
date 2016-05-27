package com.aiscrim.application.modelo;

/**
 * Created by macmini on 27/5/16.
 */
public class Usuario {
    public static String Nick;
    public static String Nombre;
    public static String Apellidos;
    public static String mail;
    public static String telefono;

    public Usuario(String nick, String nombre, String apellidos, String mail, String telefono) {
        this.Nick = nick;
        this.Nombre = nombre;
        this.Apellidos = apellidos;
        this.mail = mail;
        this.telefono = telefono;
    }

    public static String getTelefono() {
        return telefono;
    }

    public static void setTelefono(String telefono) {
        Usuario.telefono = telefono;
    }

    public static String getApellidos() {
        return Apellidos;
    }

    public static void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        Usuario.mail = mail;
    }

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String nombre) {
        Nombre = nombre;
    }

    public static String getNick() {
        return Nick;
    }

    public static void setNick(String nick) {
        Nick = nick;
    }
}
