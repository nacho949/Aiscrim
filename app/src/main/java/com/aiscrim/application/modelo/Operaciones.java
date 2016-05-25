package com.aiscrim.application.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;


public class Operaciones extends BaseDeDatos {

    public Operaciones(Context context) {
        super(context);
    }

    public void consultarTarjetas() throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from Tarjetas", null);
        fila.moveToFirst();
        if(fila.getCount() > 0){
            Tarjeta.remove();
            do {
                Tarjeta.add(fila.getString(1), fila.getString(0), fila.getString(3), fila.getString(2));

            } while (fila.moveToNext());
        }

        fila.close();
        bd.close();
        close();
    }

    public void removeTarjeta(Tarjeta t) throws SQLException {
        open();
        SQLiteDatabase bd = getWritableDatabase();

        bd.delete("Tarjetas", "TitularTarjeta = '" + t.titular + "'", null);
        Log.e("ERROR", "SQLITE: " + "DELETE FROM Tarjetas WHERE TitularTarjeta='" + t.titular + "'");

        bd.close();
        close();
    }
}
