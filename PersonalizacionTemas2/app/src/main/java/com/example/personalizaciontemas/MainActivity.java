package com.example.personalizaciontemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seleccionarTema();
        obtenerPreferencias();
    }

    public void seleccionarTema() {
        RadioButton claro = findViewById(R.id.radioButton);
        RadioButton oscuro = findViewById(R.id.radioButton2);
        Button boton = findViewById(R.id.button);

        if (!claro.isChecked() && !oscuro.isChecked()) {
            Toast.makeText(this, "Debe de seleccionar una de las opciones", Toast.LENGTH_SHORT).show();
            return; 
        }

        boton.setOnClickListener(v -> {
            if (claro.isChecked()) {
                String temaSlt = "claro";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "Tema cambiado a modo claro ☀️", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("tema", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("tema", temaSlt);
                editor.apply();
            } else if (oscuro.isChecked()) {
                String temaSlt = "oscuro";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "Tema cambiado a modo oscuro \uD83C\uDF19️", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("tema", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("tema", temaSlt);
                editor.apply();
            }
        });
    }

    public void obtenerPreferencias() {
        SharedPreferences prefs = getSharedPreferences("tema", MODE_PRIVATE);
        String texto = prefs.getString("tema", "");
        Toast.makeText(this, "El ultimo tema seleccionado fue: " + texto, Toast.LENGTH_SHORT).show();
    }
}