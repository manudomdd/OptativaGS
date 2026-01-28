package com.example.sensorproximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView sensorText;
    private ImageView imageView;
    private ConstraintLayout fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorText = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        fondo = findViewById(R.id.main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

        if (proximitySensor == null) {
            sensorText.setText("El dispositivo no tiene sensor de proximidad");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];

        if (proximitySensor != null) {
            if (distance < proximitySensor.getMaximumRange()) {
                sensorText.setText("¡OBJETO CERCA!");
                imageView.setImageResource(R.drawable.contenedor_verde);
                fondo.setBackgroundColor(Color.GREEN);

            } else {
                sensorText.setText("¡OBJETO LEJOS!");
                imageView.setImageResource(R.drawable.contenedor_amarillo);
                fondo.setBackgroundColor(Color.YELLOW);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}