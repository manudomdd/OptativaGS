package com.example.centrorelajacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modoSonido();
        modoVideo();
        mostrarUltimoModo();
    }

    public void modoSonido() {
        Button boton = findViewById(R.id.button);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModoSonido.class);
            startActivity(intent);
        });
    }

    public void modoVideo() {
        Button boton = findViewById(R.id.button2);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModoVideo.class);
            startActivity(intent);
        });
    }

    public void mostrarUltimoModo() {
        SharedPreferences prefs = getSharedPreferences("RelaxPrefs", MODE_PRIVATE);
        String text = prefs.getString("ultimo_modo", "Aún no has usado la applicación");
        Toast.makeText(this, "Tu ultimo modo fue: " + text, Toast.LENGTH_SHORT).show();
    }
}