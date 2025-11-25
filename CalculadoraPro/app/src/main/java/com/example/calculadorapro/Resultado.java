package com.example.calculadorapro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        mostrarResultado();
    }

    private void mostrarResultado() {
        TextView text = findViewById(R.id.textView3);
        float num = getIntent().getFloatExtra("resultado", 0);
        text.setText("El resultado es: " + num);

        SharedPreferences prefs = getSharedPreferences("lastResult", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("lastResult", num);
        editor.apply();
    }
}