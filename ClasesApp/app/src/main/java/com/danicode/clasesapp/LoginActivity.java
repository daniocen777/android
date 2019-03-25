package com.danicode.clasesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_usuario;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Match
        et_usuario = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
    }

    // Método para ir al home
    public void goHome (View view) {
        if((et_usuario.getText().toString().equalsIgnoreCase("daniocean")) &&
                (et_password.getText().toString().equalsIgnoreCase("hawking777"))) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if ((et_usuario.getText().toString().equalsIgnoreCase("")) &&
                (et_password.getText().toString().equalsIgnoreCase(""))){
            Toast.makeText(this, "Debe ingresar sus datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No tiene permiso de ingresar", Toast.LENGTH_SHORT).show();
        }
    }
}
