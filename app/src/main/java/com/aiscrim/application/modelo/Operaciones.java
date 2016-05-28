package com.aiscrim.application.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;


public class Operaciones extends BaseDeDatos {

    public Operaciones(Context context) {
        super(context);
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
                Tarjeta.add(fila.getString(2), fila.getString(1), fila.getString(4), fila.getString(3));

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

    public void consultarDirecciones(String usuario) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Direcciones where Usuario='" + usuario + "'", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Direccion.remove();
            do {
                Direccion.add(fila.getString(1), fila.getString(2), fila.getString(3));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void removeTarjeta(Tarjeta t) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        int l = bd.delete("Tarjetas", "Titular = '" + t.titular + "' AND Usuario='" + Usuario.getNick() + "'", null);
        Log.e("ERROR", "SQLITE: " + "DELETE FROM Tarjetas WHERE TitularTarjeta='" + t.titular + "'");
        Log.e("ERROR", l + "");

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
}
