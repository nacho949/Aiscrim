package com.aiscrim.application.Usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.R;

import java.sql.SQLException;

public class CambiarPasswordActivity extends AppCompatActivity {
    EditText password, nuevo_password, repetir_nuevo_password;
    Button cancelar, guardar;
    private Operaciones operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);
        agregarToolbar();
        operacion = new Operaciones(this);

        password = (EditText) findViewById(R.id.actual_password);
        nuevo_password = (EditText) findViewById(R.id.nuevo_password);
        repetir_nuevo_password = (EditText) findViewById(R.id.repetir_nuevo_password);

        cancelar = (Button) findViewById(R.id.cancelar_password);
        guardar = (Button) findViewById(R.id.guardar_password);
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardar();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Cambiar contraseña");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardar() {
        if (!validate()) {
            return;
        }
        try {
            operacion.updatePassword(Usuario.getNick(), nuevo_password.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    public boolean validate() {
        boolean valid = true;
        String repetir_password = repetir_nuevo_password.getText().toString();
        String password_nuevo = nuevo_password.getText().toString();
        String password_actual = password.getText().toString();

        if (!password_actual.equals(Usuario.getPassword())) {
            password.setError("la contraseña es errónea");
            valid = false;
        } else {
            password.setError(null);
        }

        if (!repetir_password.equals(password_nuevo)) {
            repetir_nuevo_password.setError("Las contraseñas no son iguales");
            valid = false;
        } else {
            repetir_nuevo_password.setError(null);
        }

        return valid;
    }

}
