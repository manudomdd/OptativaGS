package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        inicializarTexto();
    }

    protected void inicializarTexto() {
        TextView texto = findViewById(R.id.textView2);
        String nombre = getIntent().getStringExtra("nombre");
        texto.setText("Hola " + nombre);
    }

    protected void botonAlegre() {
        Button botonAlegre = findViewById(R.id.button2);
        botonAlegre.setOnClickListener(v -> {
            Toast.makeText(this, "¡Me alegro mucho de que estés feliz!", Toast.LENGTH_SHORT).show();
            //abrir un dialogo con un gif de un gato bailando. hay que importar librerias.
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
            Toast.makeText(this, "Tranquilo, te paso con un video para que te relajes", Toast.LENGTH_SHORT).show();
            //poner un vídeo relajante tipo ASMR.
        });
    }
}