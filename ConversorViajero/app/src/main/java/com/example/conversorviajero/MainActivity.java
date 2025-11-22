package com.example.conversorviajero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pulsarBoton();
    }

    public void pulsarBoton() {
        Button boton = findViewById(R.id.button);
        EditText text = findViewById(R.id.editTextTextPersonName);

        boton.setOnClickListener(v -> {

            String textoStr = text.getText().toString();

            if (textoStr.isEmpty()) {
                Toast.makeText(this, "Introduce un valor", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int numero = Integer.parseInt(textoStr);

                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                intent.putExtra("euros", numero);
                startActivity(intent);

            } catch (NumberFormatException ex) {
                Toast.makeText(this, "El dato introducido debe ser un entero positivo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
