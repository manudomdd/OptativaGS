package com.example.miasistentepersonal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAceptar();
    }

    public void botonAceptar() {
        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> {
            obtenerPreferencias();
            Intent intent = new Intent(MainActivity.this, Saludo.class);
            startActivity(intent);
        });
    }

    public void obtenerPreferencias() {
        EditText texto = findViewById(R.id.editTextTextPersonName);
        String nombreUsuario = texto.getText().toString();

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre", nombreUsuario);
        editor.apply();
    }
}