package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class AlegreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alegre);
        inicializarVideo();
    }

    private void inicializarVideo() {
        VideoView video = findViewById(R.id.videoView);

        MediaController controller = new MediaController(this);
        controller.setAnchorView(video);
        video.setMediaController(controller);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pocoyo);
        video.setVideoURI(videoUri);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

                int orientacion = getResources().getConfiguration().orientation;

                if (orientacion == Configuration.ORIENTATION_LANDSCAPE) {
                    // MODO HORIZONTAL
                    // En horizontal hay menos altura, subimos menos el video (-50f)
                    // Quizás necesites más zoom si quieres llenar el ancho
                    video.setScaleX(1.6f);
                    video.setScaleY(1.6f);
                    video.setTranslationY(-50f);
                } else {
                    // MODO VERTICAL (Tu configuración original)
                    video.setScaleX(1.6f);
                    video.setScaleY(1.6f);
                    video.setTranslationY(-200f);
                }

                video.start();
            }
        });
    }
}