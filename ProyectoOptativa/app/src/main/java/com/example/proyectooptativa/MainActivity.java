package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAceptar();
    }

    protected void botonAceptar() {
        Button botonAceptar = findViewById(R.id.button);
        EditText texto = findViewById(R.id.editTextTextPersonName);

        botonAceptar.setOnClickListener(v -> {
            String nombre = texto.getText().toString();
            if (nombre == null || nombre.isEmpty()) {
                Toast.makeText(this, "Debe de introducir un nombre.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "¡Bienvenido " + nombre + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StatesActivity.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);

            }
        });
    }
}