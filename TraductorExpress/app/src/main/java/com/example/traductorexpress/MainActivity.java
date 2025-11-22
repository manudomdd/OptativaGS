package com.example.traductorexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final Map<String, String> diccionario = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarDiccionario();
        traducir();
    }

    public void inicializarDiccionario() {
        diccionario.put("colegio", "School");
        diccionario.put("programador", "Developer");
    }

    public void traducir() {
        EditText texto = findViewById(R.id.editTextTextPersonName);
        Button boton = findViewById(R.id.button);

        boton.setOnClickListener(v -> {
            String textoStr = texto.getText().toString().trim().toLowerCase();
            if (!diccionario.containsKey(textoStr)) {
                Toast.makeText(this, "El diccionario no contiene esa palabra", Toast.LENGTH_SHORT).show();
                return;
            } else {
                String traduccion = diccionario.get(textoStr);

                Intent intent = new Intent(MainActivity.this, PantallaTraduccion.class);
                intent.putExtra("palabraTraducida", traduccion);
                startActivity(intent);
            }
        });
    }
}