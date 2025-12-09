package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class TristeActivity extends AppCompatActivity {

    String[] frases = {
            "Esto también pasará.",
            "Eres más fuerte de lo que crees.",
            "No llueve eternamente.",
            "Mañana será un día mejor.",
            "Date permiso para estar mal, pero no te quedes ahí.",
            "Cree en ti y todo será posible.",
            "Sigue nadando, sigue nadando..."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triste);

        TextView textoFrase = findViewById(R.id.textView3);
        Button botonNuevaFrase = findViewById(R.id.button8);

        textoFrase.setText("");

        botonNuevaFrase.setOnClickListener(v -> {
            cambiarFrase(textoFrase);
        });
    }

    private void cambiarFrase(TextView texto) {
        Random random = new Random();
        int indice = random.nextInt(frases.length);
        texto.setText(frases[indice]);
    }
}