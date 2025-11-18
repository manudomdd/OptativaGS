package com.example.practicapreferencias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonPreferencias();
        botonDialogo();
    }

    public void botonPreferencias() {
        Button boton = findViewById(R.id.button);

        boton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Preferencias.class);
            startActivity(intent);
        });
    }

    public void botonDialogo() {
        Button boton = findViewById(R.id.button2);


        boton.setOnClickListener(v -> {
            CharSequence[] opciones = {"Turno de mañana", "Turno de tarde"};
            AlertDialog.Builder constructor = new AlertDialog.Builder(this);
            constructor
                    .setTitle("SELECCIONA TU CITA")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMultiChoiceItems(opciones, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        }
                    });
            AlertDialog dialogo = constructor.create();
            dialogo.show();
        });
    }
}