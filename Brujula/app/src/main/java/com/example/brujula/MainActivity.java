package com.example.brujula;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imgFlecha;
    private TextView tvGrados;
    private SensorManager sensorManager;
    private Sensor acelerometro, magnetometro;

    private float[] lecturasAcelerometro = new float[3];
    private float[] lecturasMagnetometro = new float[3];
    private boolean acelerometroListo = false;
    private boolean magnetometroListo = false;

    private float anguloActual = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFlecha = findViewById(R.id.imgFlecha);
        tvGrados = findViewById(R.id.tvGrados);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometro, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, lecturasAcelerometro, 0, event.values.length);
            acelerometroListo = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, lecturasMagnetometro, 0, event.values.length);
            magnetometroListo = true;
        }

        if (acelerometroListo && magnetometroListo) {
            actualizarOrientacion();
        }
    }

    private void actualizarOrientacion() {
        float[] R = new float[9];
        float[] I = new float[9];

        boolean exito = SensorManager.getRotationMatrix(R, I, lecturasAcelerometro, lecturasMagnetometro);

        if (exito) {
            float[] orientacion = new float[3];
            SensorManager.getOrientation(R, orientacion);

            float azimuthEnRadianes = orientacion[0];
            float azimuthEnGrados = (float) Math.toDegrees(azimuthEnRadianes);

            float anguloDestino = -azimuthEnGrados;

            RotateAnimation ra = new RotateAnimation(
                    anguloActual,
                    anguloDestino,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);

            ra.setDuration(210);
            ra.setFillAfter(true);

            imgFlecha.startAnimation(ra);
            anguloActual = anguloDestino;

            int gradosMostrar = (int) ((azimuthEnGrados + 360) % 360);
            tvGrados.setText(gradosMostrar + "° N");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}