package com.example.conversorviajero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        conversion();

    }

    public void conversion() {
        TextView text = findViewById(R.id.textView2);

        int euros = getIntent().getIntExtra("euros", 0);
        double factor = 1.08;
        double result = euros * factor;

        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
        String resultFormatted = formatoMoneda.format(result);

        text.setText("La conversion a dolares es " + resultFormatted);

    }
}