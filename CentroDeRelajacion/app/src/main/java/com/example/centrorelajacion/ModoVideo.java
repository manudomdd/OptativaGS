package com.example.centrorelajacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ModoVideo extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_video);
        reproducirVideo();
    }

    public void reproducirVideo() {
        videoView = findViewById(R.id.videoView);

        SharedPreferences prefs = getSharedPreferences("RelaxPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ultimo_modo", "Video");
        editor.apply();

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_relajante);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }
}