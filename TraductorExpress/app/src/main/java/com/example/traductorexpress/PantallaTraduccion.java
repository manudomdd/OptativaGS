package com.example.traductorexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PantallaTraduccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_traduccion);
        traduccion();
    }

    public void traduccion() {
        TextView texto = findViewById(R.id.textView2);
        String traduccion = getIntent().getStringExtra("palabraTraducida");

        texto.setText("La tradcuccion en ingles es: " + traduccion);
    }
}