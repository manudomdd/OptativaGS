package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class StatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        inicializarTexto();
        botonAlegre();
        botonTriste();
        botonEstresado();
    }

    protected void inicializarTexto() {
        TextView texto = findViewById(R.id.textView2);
        String nombre = getIntent().getStringExtra("nombre");

        if (nombre == null) {
            nombre = "amigo";
        }

        int hora = determinarFecha();
        String saludo;

        if (hora >= 6 && hora < 12) {
            saludo = "buenos días";
        } else if (hora >= 12 && hora < 20) {
            saludo = "buenas tardes";
        } else {
            saludo = "buenas noches";
        }

        texto.setText("Hola " + nombre + ", " + saludo);
    }

    protected void botonAlegre() {
        Button botonAlegre = findViewById(R.id.button2);
        botonAlegre.setOnClickListener(v -> {
            Toast.makeText(this, "¡Me alegro mucho de que estés feliz!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(StatesActivity.this, AlegreActivity.class);
            startActivity(intent);
        });
    }

    protected void botonTriste() {
        Button botonTriste = findViewById(R.id.button3);
        botonTriste.setOnClickListener(v -> {
            Toast.makeText(this, "¡Animo! ¡Aqui te dejo una canción para que te motives!", Toast.LENGTH_SHORT).show();
            //meter una canción motivadora.
        });
    }

    protected void botonEstresado() {
        Button botonEstresado = findViewById(R.id.button4);
        botonEstresado.setOnClickListener(v -> {
            Toast.makeText(this, "Tranquilo, te dejo con musica relajante, dale al play", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(StatesActivity.this, EstresadoActivity.class);
            startActivity(intent);
        });
    }

    private int determinarFecha() {
        Calendar calendario = Calendar.getInstance();
        return calendario.get(Calendar.HOUR_OF_DAY);
    }
}