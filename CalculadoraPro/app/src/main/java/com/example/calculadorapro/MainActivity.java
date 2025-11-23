package com.example.calculadorapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText fText, sText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fText = findViewById(R.id.editTextTextPersonName);
        sText = findViewById(R.id.editTextTextPersonName2);

        calcular();
        ultimaPreferencia();
    }

    public void calcular() {
        Button botonSuma = findViewById(R.id.button2);
        Button botonResta = findViewById(R.id.button3);
        Button botonMulti = findViewById(R.id.button4);
        Button botonDiv = findViewById(R.id.button5);

        botonSuma.setOnClickListener(v -> realizarOperacion("+"));
        botonResta.setOnClickListener(v -> realizarOperacion("-"));
        botonMulti.setOnClickListener(v -> realizarOperacion("*"));
        botonDiv.setOnClickListener(v -> realizarOperacion("/"));
    }

    private boolean obtenerNumeros(float[] numeros) {
        String fStr = fText.getText().toString();
        String sStr = sText.getText().toString();

        if (fStr.isEmpty() || sStr.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben de estar rellenos", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            numeros[0] = Float.parseFloat(fStr);
            numeros[1] = Float.parseFloat(sStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduce números válidos", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void realizarOperacion(String tipo) {
        float[] numeros = new float[2];
        if (!obtenerNumeros(numeros)) return;

        float fNum = numeros[0];
        float sNum = numeros[1];
        float result = 0;

        if (tipo.equals("+")) {
            result = fNum + sNum;
        } else if (tipo.equals("-")) {
            result = fNum - sNum;
        } else if (tipo.equals("*")) {
            result = fNum * sNum;
        } else if (tipo.equals("/")) {
            if (sNum == 0) {
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                return;
            }
            result = fNum / sNum;
        }

        SharedPreferences prefs = getSharedPreferences("lastResult", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("lastResult", result);
        editor.apply();
        
        Intent intent = new Intent(MainActivity.this, Resultado.class);
        intent.putExtra("resultado", result);
        startActivity(intent);
    }

    public void ultimaPreferencia() {
        SharedPreferences prefs = getSharedPreferences("lastResult", MODE_PRIVATE);
        float lastResult = prefs.getFloat("lastResult", 0);
        Toast.makeText(this, "El ultimo resultado fue: " + lastResult, Toast.LENGTH_SHORT).show();
    }
}
