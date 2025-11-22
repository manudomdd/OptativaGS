package com.example.centrorelajacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModoSonido extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_sonido);
        reproductor();
    }

    public void reproductor() {
        Button botonPlay = findViewById(R.id.btnPlay);
        Button botonPause = findViewById(R.id.btnPause);
        Button botonStop = findViewById(R.id.btnStop);

        mediaPlayer = MediaPlayer.create(this, R.raw.audio_relajante);

        SharedPreferences prefs = getSharedPreferences("RelaxPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ultimo_modo", "Audio");
        editor.apply();

        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        });

        botonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });

        botonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer = MediaPlayer.create(ModoSonido.this, R.raw.audio_relajante);
                }
            }
        });
    }
}