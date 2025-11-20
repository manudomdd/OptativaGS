package com.example.miasistentepersonal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Calendar;

public class Saludo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);
        mostrarSaludo();
    }

    public void mostrarSaludo() {
        TextView texto = findViewById(R.id.textView2);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Invitado");

        Calendar calendario = Calendar.getInstance();

        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        if (hora >= 6 && hora < 12) {
            texto.setText("Hola " + nombre + " buenos días.");
        } else if (hora >= 12 && hora < 18) {
            texto.setText("Hola " + nombre + " buenas tardes.");
        } else {
            texto.setText("Hola " + nombre + " buenas noches.");
        }
    }
}