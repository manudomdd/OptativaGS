package com.example.proyectooptativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAceptar();
        mostrarFechaYMoneda();
    }

    protected void botonAceptar() {
        Button botonAceptar = findViewById(R.id.button);
        EditText texto = findViewById(R.id.editTextTextPersonName);

        botonAceptar.setOnClickListener(v -> {
            String nombre = texto.getText().toString();
            if (nombre == null || nombre.isEmpty()) {
                Toast.makeText(this, "Debe de introducir un nombre.", Toast.LENGTH_SHORT).show();
                return;
            } else if (!nombre.matches("^[a-zA-Z]+$")) {
                Toast.makeText(this, "El nombre solo puede contener letras", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "¡Bienvenido " + nombre + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StatesActivity.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
            }
        });
    }

    private void mostrarFechaYMoneda() {
        TextView text = findViewById(R.id.textView5);
        Calendar calendar = Calendar.getInstance();
        double cantidad = 2500.50;

        DateFormat formatterFecha = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        NumberFormat formatterNumero = NumberFormat.getCurrencyInstance();

        String fechaStr = formatterFecha.format(calendar.getTime());
        String moneda = formatterNumero.format(cantidad);

        text.setText("Fecha: " + fechaStr + "\n Moneda: " + moneda);

    }
}