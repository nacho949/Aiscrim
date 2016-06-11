package com.aiscrim.application.Login;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.R;

import java.sql.SQLException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Registro extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    Button _signupButton;
    TextView _loginLink;
    EditText nick, password, repetir_password, nombre, apellidos, mail, telefono;
    Operaciones op;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.inject(this);

        op = new Operaciones(this);

        nick = (EditText) findViewById(R.id.et_nick_usuario);
        password = (EditText) findViewById(R.id.pass_crear_usuario);
        repetir_password = (EditText) findViewById(R.id.repetir_pass_crear_usuario);
        nombre = (EditText) findViewById(R.id.et_nombre_crear_usuario);
        apellidos = (EditText) findViewById(R.id.et_apellidos_crear_usuario);
        mail = (EditText) findViewById(R.id.et_mail_crear_usuario);
        telefono = (EditText) findViewById(R.id.et_tlf_crear_usuario);

        _signupButton = (Button) findViewById(R.id.btn_crear_usuario);
        _loginLink = (TextView) findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Registro.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creando Cuenta...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        try {
            op.crearUsuario(nick.getText().toString(),password.getText().toString(),nombre.getText().toString(),apellidos.getText().toString(),mail.getText().toString(),telefono.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(), "\n" +
                "Registro completado con éxito", Toast.LENGTH_LONG).show();
        finish();
    }


    public boolean validate() {
        boolean valid = true;

        String _nick = nick.getText().toString();
        String _password_repetir = password.getText().toString();
        String _password = repetir_password.getText().toString();
        String mai = mail.getText().toString();

        try {
            if (op.comprobarNick(_nick)) {
                nick.setError("Ya existe el usuario");
                valid = false;
            } else {
                nick.setError(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (mai.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mai).matches()) {
            mail.setError("Ingresa un correo válido");
            valid = false;
        } else {
            mail.setError(null);
        }

        if (!_password.equals(_password_repetir)) {
            repetir_password.setError("Las contraseñas no coinciden");
            valid = false;
        } else {
            repetir_password.setError(null);
        }

        return valid;
    }
}
