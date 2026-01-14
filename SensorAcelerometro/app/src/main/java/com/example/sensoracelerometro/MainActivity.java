package com.example.sensoracelerometro;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView tx, ty, tz, tvStatus;
    private View rootLayout;

    private long lastShakeTime = 0;
    private static final float SHAKE_THRESHOLD = 12.0f;
    private static final int SHAKE_WAIT_TIME_MS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);
        tx = findViewById(R.id.tx);
        ty = findViewById(R.id.ty);
        tz = findViewById(R.id.tz);
        tvStatus = findViewById(R.id.tvStatus);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            tx.setText(String.format("X: %.2f", x));
            ty.setText(String.format("Y: %.2f", y));
            tz.setText(String.format("Z: %.2f", z));

            detectShake(x, y, z);
        }
    }

    private void detectShake(float x, float y, float z) {
        double magnitude = Math.sqrt(x * x + y * y + z * z);
        long now = System.currentTimeMillis();

        if (magnitude > SHAKE_THRESHOLD && (now - lastShakeTime) > SHAKE_WAIT_TIME_MS) {
            lastShakeTime = now;
            cambiarColorFondo();
            tvStatus.setText("¡SHAKE DETECTADO!");
            Toast.makeText(this, "Shake detectado", Toast.LENGTH_SHORT).show();
        }
    }

    private void cambiarColorFondo() {
        Random random = new Random();
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        rootLayout.setBackgroundColor(color);
    }
}