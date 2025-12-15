package com.example.repasoexamen;

import android.content.Intent;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private EditText txtPrecio;
    private EditText txtDescuento;
    private TextView txtFecha;

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

        initComponents();
        calcularPrecio();
        actualizarFechaHora();
    }

    private void initComponents() {
        btnCalcular = findViewById(R.id.button);
        txtPrecio = findViewById(R.id.editTextNumber2);
        txtDescuento = findViewById(R.id.editTextNumber3);
        txtFecha = findViewById(R.id.textView7);
    }

    private void actualizarFechaHora() {
        Date ahora = new Date();
        DateFormat formateador = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
        String fechaAuto = formateador.format(ahora);
        txtFecha.setText(fechaAuto);
    }

    private void calcularPrecio() {
        btnCalcular.setOnClickListener(v -> {
            try {
                String precioStr = txtPrecio.getText().toString();
                String descuentoStr = txtDescuento.getText().toString();

                if (precioStr.isEmpty() || descuentoStr.isEmpty()) {
                    Toast.makeText(this, "Debe de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int precio = Integer.parseInt(txtPrecio.getText().toString());
                int descuento = Integer.parseInt(txtDescuento.getText().toString());

                if (descuento < 0 || descuento > 100) {
                    Toast.makeText(this, "El descuento no debe ser inferior a 0 ni superior a 100", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double precioDesc = precio - (precio * descuento / 100);
                    Intent intent = new Intent(MainActivity.this, Resultado.class);
                    intent.putExtra("precioFinal", precioDesc);
                    startActivity(intent);
                }
                actualizarFechaHora();
            } catch (NumberFormatException ex) {
                Toast.makeText(this, "Debe de introducir un numero valido.", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarFechaHora();
    }
}