package com.example.practica1;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView animacionFiesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textoBienvenida = findViewById(R.id.textView2);
        Button alegre = findViewById(R.id.button4);
        Button triste = findViewById(R.id.button5);
        Button estresado = findViewById(R.id.button3);
        ConstraintLayout rootLayout = findViewById(R.id.rootLayout);
        animacionFiesta = findViewById(R.id.animacionFiesta);
        animacionFiesta.setVisibility(View.GONE);

        String nombre = getIntent().getStringExtra("nombreUsuario");
        if (nombre != null) {
            textoBienvenida.setText("¡Hola " + nombre + "!");
        } else {
            textoBienvenida.setText("Hola!");
        }

        alegre.setOnClickListener(v -> {
            cambiarFondoProgresivo(rootLayout, Color.WHITE, Color.parseColor("#FFEB3B"));

            // Mostrar y reproducir la animación de Lottie
            animacionFiesta.setRepeatCount(0);
            animacionFiesta.setVisibility(View.VISIBLE);
            animacionFiesta.playAnimation();

            Toast.makeText(MainActivity.this, "¡Qué bien verte alegre, " + nombre + "!", Toast.LENGTH_SHORT).show();
        });

        triste.setOnClickListener(v -> {
            cambiarFondoProgresivo(rootLayout, Color.WHITE, Color.parseColor("#2196F3"));
            Toast.makeText(MainActivity.this, "Ánimo, " + nombre + ", ¡todo mejora!", Toast.LENGTH_SHORT).show();
        });

        estresado.setOnClickListener(v -> {
            cambiarFondoProgresivo(rootLayout, Color.WHITE, Color.parseColor("#F44336"));
            Toast.makeText(MainActivity.this, "Respira hondo, " + nombre + ", ¡tú puedes!", Toast.LENGTH_SHORT).show();
        });
    }

    private void cambiarFondoProgresivo(ConstraintLayout layout, int colorDesde, int colorHasta) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorDesde, colorHasta);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(animator -> layout.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnimation.start();
    }
}