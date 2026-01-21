package com.example.acelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView; // <--- Importante importar esto

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor gyroscope;

    private ImageView image;
    // 1. Declaramos las variables para los textos (NUEVO)
    private TextView tvX, tvY, tvZ;

    private float rotation = 0f;
    private long lastTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);

        // 2. Enlazamos las variables con el XML (NUEVO)
        tvX = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME);
            lastTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Valores crudos del sensor (rad/s)
        float rx = event.values[0];
        float ry = event.values[1];
        float rz = event.values[2];

        // 3. Actualizamos los textos en pantalla (NUEVO)
        // Usamos String.format para limitar a 2 decimales
        tvX.setText(String.format("%.2f", rx));
        tvY.setText(String.format("%.2f", ry));
        tvZ.setText(String.format("%.2f", rz));

        // Lógica de rotación de la imagen
        long now = System.currentTimeMillis();
        // Evitamos saltos grandes si lastTime es 0
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        float dt = (now - lastTime) / 1000f;
        lastTime = now;

        // Actualizar ángulo con velocidad de giro en eje Z (rad/s -> grados)
        rotation += rz * dt * (180f / (float)Math.PI);

        // Invertimos el signo (-) para que gire natural con el movimiento del móvil
        image.setRotation(-rotation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No es necesario implementar nada aquí por ahora
    }
}