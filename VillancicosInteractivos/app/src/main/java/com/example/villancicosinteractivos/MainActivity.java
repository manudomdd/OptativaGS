package com.example.villancicosinteractivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonArbol();
        botonReno();
        botonCampana();
    }

    private void botonArbol() {
        Button boton = findViewById(R.id.button);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Villancico.class);
            intent.putExtra("seleccion", "arbol");
            startActivity(intent);
        });
    }

    private void botonReno() {
        Button boton = findViewById(R.id.button2);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Villancico.class);
            intent.putExtra("seleccion", "reno");
            startActivity(intent);
        });
    }

    private void botonCampana() {
        Button boton = findViewById(R.id.button3);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Villancico.class);
            intent.putExtra("seleccion", "campana");
            startActivity(intent);
        });
    }
}