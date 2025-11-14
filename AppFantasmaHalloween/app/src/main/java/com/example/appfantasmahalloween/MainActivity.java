package com.example.appfantasmahalloween;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "!Has liberado al programador fantasma!", Toast.LENGTH_SHORT).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.grito);
            mediaPlayer.start();
        });
    }
}
