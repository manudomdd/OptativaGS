package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicial);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText name = findViewById(R.id.editText);
        Button aceptar = findViewById(R.id.button);



        aceptar.setOnClickListener(v -> {
            String nombreUsuario = name.getText().toString().trim();

            if (!nombreUsuario.isEmpty()) {
                Intent intent = new Intent(ActivityInicial.this, MainActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                startActivity(intent);
            } else {
                Toast.makeText(ActivityInicial.this, "Introduce tu nombre", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
