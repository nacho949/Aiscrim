package com.aiscrim.application.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aiscrim.application.Objetos.DetallePedido;
import com.aiscrim.application.Objetos.Direccion;
import com.aiscrim.application.Objetos.Pedido;
import com.aiscrim.application.Objetos.Tarjeta;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.Objetos.Videojuego;

import java.sql.SQLException;


public class Operaciones extends BaseDeDatos {

    public Operaciones(Context context) {
        super(context);
    }

    public void consultarPedidos(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Pedidos where Usuario='" + usuario + "'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Pedido.remove();
            do {
                Pedido.add(fila.getInt(0),fila.getString(2));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarDetallePedido(int numero_pedido) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Detalle_Pedido where Num_pedido=" + numero_pedido, null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            DetallePedido.remove();
            do {
                DetallePedido.add(fila.getString(1),fila.getString(2),fila.getInt(3),fila.getFloat(4),fila.getFloat(5),fila.getString(6));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarTarjetas(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Tarjetas where Usuario='" + usuario + "'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Tarjeta.remove();
            do {
                Tarjeta.add(fila.getInt(0),fila.getString(3), fila.getString(2), fila.getString(5), fila.getString(4), fila.getInt(6));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void GuardarTarjeta(String usuario, String numero, String titular, String dia, String year, String tipo) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("Usuario", usuario);
        registro.put("Numero", numero);
        registro.put("Titular", titular);
        registro.put("Tipo", tipo);
        registro.put("Fecha_vencimiento", dia + "/" + year);
        Log.e("------------------", registro.toString());
        bd.insert("Tarjetas", null, registro);
        bd.close();
    }

    public void GuardarDireccion(String usuario, String direccion, String codigo_postal, String ciudad) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("Usuario", usuario);
        registro.put("Direccion", direccion);
        registro.put("codigo_postal", codigo_postal);
        registro.put("ciudad", ciudad);
        Log.e("------------------", registro.toString());
        bd.insert("Direcciones", null, registro);
        bd.close();
    }

    public void consultarDirecciones(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Direcciones where Usuario='" + usuario + "'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Direccion.remove();
            do {
                Direccion.add(fila.getInt(0), fila.getString(2), fila.getString(3), fila.getString(4), fila.getInt(5));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void removeTarjeta(Tarjeta t) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        int l = bd.delete("Tarjetas", "ID = " + t.ID + " AND Usuario='" + Usuario.getNick() + "'", null);

        bd.close();
        close();
    }

    public void removeDireccion(Direccion d) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        int l = bd.delete("Direcciones", "ID = " + d.ID + " AND Usuario='" + Usuario.getNick() + "'", null);
        bd.close();
        close();
    }

    public void marcarTarjetaPredeterminada(Tarjeta t, Tarjeta t1) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("predeterminada",0);
        ContentValues valores1 = new ContentValues();
        valores1.put("predeterminada",1);

        bd.update("Tarjetas", valores, "ID = " + t.ID + " AND Usuario='" + Usuario.getNick() + "'", null);
        bd.update("Tarjetas", valores1, "ID = " + t1.ID + " AND Usuario='" + Usuario.getNick() + "'", null);
        bd.close();
        close();
    }

    public int checkLogin(String usuario, String password) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor c = bd.rawQuery("select tipo from Usuarios where Nick='" + usuario + "' AND password='" + password + "'", null);

        int tipo = -1;

        if(c.moveToFirst()) {
            do{
                tipo = c.getInt(0);
            }while(c.moveToNext());
        }
        bd.close();
        close();
        return tipo;
    }

    public void getUsuarioLogeado(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Usuarios where Nick='" + usuario + "'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){

            do {
                Usuario a = new Usuario(fila.getString(0), fila.getString(2), fila.getString(3), fila.getString(4),fila.getString(5));
            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarVideojuegosPS4(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Productos where Plataforma='PS4' AND Tipo='Nuevo'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Videojuego.removePS4();
            do {
                Videojuego.addPS4(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getInt(3),fila.getString(4),fila.getString(5),fila.getFloat(6),fila.getString(7),fila.getInt(8));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarVideojuegosXBOX(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Productos where Plataforma='XBOX_ONE' AND Tipo='Nuevo'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Videojuego.removeXBOX();
            do {
                Videojuego.addXBOX(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getInt(3), fila.getString(4), fila.getString(5), fila.getFloat(6), fila.getString(7), fila.getInt(8));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarVideojuegosXBOXSeminuevos(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Productos where Plataforma='XBOX_ONE' AND Tipo='SemiNuevo'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Videojuego.removeXBOXSeminuevos();
            do {
                Videojuego.addXBOXSeminuevo(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getInt(3), fila.getString(4), fila.getString(5), fila.getFloat(6), fila.getString(7), fila.getInt(8));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void consultarVideojuegosPS4Seminuevo(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Productos where Plataforma='PS4' AND Tipo='SemiNuevo'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Videojuego.removePS4Seminuevos();
            do {
                Videojuego.addPS4Seminuevo(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getInt(3), fila.getString(4), fila.getString(5), fila.getFloat(6), fila.getString(7), fila.getInt(8));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }
}
