package com.aiscrim.application.Usuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aiscrim.application.Administrador.ActividadPrincipalAdmin;
import com.aiscrim.application.R;
import com.aiscrim.application.BaseDeDatos.Operaciones;

import java.sql.SQLException;

import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int USUARIO = 1;
    private static final int ADMINISTRADOR = 0;

    EditText _usuario;
    EditText _password;
    Button _login;
    TextView _registro;
    Operaciones op;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        op = new Operaciones(this);
        _usuario = (EditText)findViewById(R.id.text_usuario);
        _password = (EditText) findViewById(R.id.text_password);
        _login = (Button) findViewById(R.id.btn_login);
        _registro = (TextView) findViewById(R.id.link_signup);

        _login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _registro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                //Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _login.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        final String usuario = _usuario.getText().toString();
        String password = _password.getText().toString();

        int tipo = -1;
        try {
            tipo = op.checkLogin(usuario,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final int finalTipo = tipo;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (finalTipo == USUARIO) {
                            onLoginSuccessUsuario();
                            try {
                                op.getUsuarioLogeado(usuario);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else if (finalTipo == ADMINISTRADOR) {
                            onLoginSuccessAdministrador();
                            try {
                                op.getUsuarioLogeado(usuario);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            onLoginFailed();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccessUsuario() {
        _login.setEnabled(true);

        finish();
        Intent intent = new Intent(getApplicationContext(), ActividadPrincipal.class);
        startActivity(intent);
    }

    public void onLoginSuccessAdministrador() {
        _login.setEnabled(true);

        finish();
        Intent intent = new Intent(getApplicationContext(), ActividadPrincipalAdmin.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "\n" +
                "Error de inicio de sesion", Toast.LENGTH_LONG).show();

        _login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String usuario = _usuario.getText().toString();
        String password = _password.getText().toString();

        if (usuario.isEmpty()) {
            _usuario.setError("Introduzca una dirección de correo electrónico válida");
            valid = false;
        } else {
            _usuario.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _password.setError("entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            _password.setError(null);
        }

        return valid;
    }
}

