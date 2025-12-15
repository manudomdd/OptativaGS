package com.example.repasoexamen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Map;

public class Resultado extends AppCompatActivity {

    public MainActivity main;
    private TextView txtResultado;
    private EditText txtComentario;
    private Button btnGuardar;
    private TextView comentario;
    private TextView valoraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponents();
        mostrarPrecio();
        obtenerUltimaValoracion();
        guardarComentario();
        calcularMedia();
    }

    private void initComponents() {
        txtResultado = findViewById(R.id.textView3);
        txtComentario = findViewById(R.id.editTextTextMultiLine);
        btnGuardar = findViewById(R.id.button2);
        comentario = findViewById(R.id.textView5);
        valoraciones = findViewById(R.id.textView6);
    }

    private void mostrarPrecio() {
        double precio = getIntent().getDoubleExtra("precioFinal", 0);
        String precioFormatted = NumberFormat.getCurrencyInstance().format(precio);
        txtResultado.setText("El precio final es de " + precioFormatted);
    }

    private void guardarComentario() {
        btnGuardar.setOnClickListener(v -> {
            int valoracion = Integer.parseInt(txtComentario.getText().toString());

            SharedPreferences prefs = getSharedPreferences("valoraciones", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("ultima_valoracion", valoracion);

            String claveUnica = "valoracion_" + System.currentTimeMillis();
            editor.putInt(claveUnica, valoracion);

            editor.apply();


        });
    }

    private void obtenerUltimaValoracion() {
        SharedPreferences prefs = getSharedPreferences("valoraciones", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("ultima_")) {
                int value = (int) entry.getValue();
                comentario.setText("La ultima valoracion es de: " + value);
            }
        }
    }

    private void calcularMedia () {
        SharedPreferences prefs = getSharedPreferences("valoraciones", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        int suma = 0;
        int contador = 0;
        double resultado = 0;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("valoracion_")) {
                Object value = (Integer) entry.getValue();
                if (value instanceof Integer) {
                    suma += (int) value;
                    contador++;
                }
            }
        }

        resultado = suma / contador;

        valoraciones.setText("La media de las valoraciones es de " + resultado);
    }
}