package com.example.sensorluz;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tvLuxInfo;
    private View rootLayout;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLuxInfo = findViewById(R.id.tvLuxInfo);
        rootLayout = findViewById(R.id.rootLayout);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        if (lightSensor == null) {
            tvLuxInfo.setText("No hay sensor de luz en este dispositivo");
            Toast.makeText(this, "Sensor de luz no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI);
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
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float luxValue = event.values[0];

            if (luxValue < 150) {
                rootLayout.setBackgroundColor(Color.BLACK);
                tvLuxInfo.setTextColor(Color.WHITE);
                tvLuxInfo.setText("Modo Oscuro\n" + luxValue + " lx");
            } else if (luxValue > 200) {
                rootLayout.setBackgroundColor(Color.WHITE);
                tvLuxInfo.setTextColor(Color.BLACK);
                tvLuxInfo.setText("Modo Claro\n" + luxValue + " lx");
            } else {
                tvLuxInfo.setText("Iluminación\n" + luxValue + " lx");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}