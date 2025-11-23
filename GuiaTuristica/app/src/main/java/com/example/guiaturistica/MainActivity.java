package com.example.guiaturistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abrirActivity();
    }

    public void abrirActivity() {
        Button botonSevilla = findViewById(R.id.button);
        Button botonGranada = findViewById(R.id.button2);
        Button botonCordoba = findViewById(R.id.button3);

        botonSevilla.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Explicacion.class);
            intent.putExtra("selection", "sevilla");
            startActivity(intent);
        });

        botonGranada.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Explicacion.class);
            intent.putExtra("selection", "granada");
            startActivity(intent);
        });

        botonCordoba.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Explicacion.class);
            intent.putExtra("selection", "cordoba");
            startActivity(intent);
        });
    }
}