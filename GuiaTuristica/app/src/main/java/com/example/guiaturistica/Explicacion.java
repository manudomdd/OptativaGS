package com.example.guiaturistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Explicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion);
        mostrarResultado();

    }

    public void mostrarResultado() {
        String seleccion = getIntent().getStringExtra("selection");
        TextView texto = findViewById(R.id.textView);
        ImageView imagen = findViewById(R.id.imageView2);
        Button reproducir = findViewById(R.id.button4);
        Button volver = findViewById(R.id.button5);

        if (seleccion.equalsIgnoreCase("sevilla")) {
            imagen.setImageResource(R.drawable.sevilla);
            texto.setText("La giralda de Sevilla es un histórico campanario de la Catedral");
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audiosevilla);
            reproducir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                }
            });

            volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer.release();

                    Intent intent = new Intent(Explicacion.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else if (seleccion.equalsIgnoreCase("granada")) {
            imagen.setImageResource(R.drawable.granada);
            texto.setText("La Alhambra de Granada es un majestuoso palacio y fortaleza islámica");
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audiogranada);
            reproducir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                }
            });
            volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer.release();

                    Intent intent = new Intent(Explicacion.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            imagen.setImageResource(R.drawable.cordoba);
            texto.setText("La Mezquita de Córdoba es un impresionante templo histórico");
        }
    }
}