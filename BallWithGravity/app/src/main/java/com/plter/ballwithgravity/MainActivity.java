package com.plter.ballwithgravity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor sensor;
    private float speedX = 0, speedY = 0;
    private ImageView iv;
    private FrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (FrameLayout) findViewById(R.id.rootView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        iv = (ImageView) findViewById(R.id.iv);
    }


    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        iv.setX(iv.getX() + speedX);
        iv.setY(iv.getY() + speedY);

        speedX -= event.values[0] / 500;
        speedY += event.values[1] / 500;

        if (iv.getX() < 0) {
            speedX = Math.abs(speedX);
        }
        if (iv.getX() > rootView.getWidth() - iv.getWidth()) {
            speedX = -Math.abs(speedX);
        }
        if (iv.getY() < 0) {
            speedY = Math.abs(speedY);
        }
        if (iv.getY() > rootView.getHeight() - iv.getHeight()) {
            speedY = -Math.abs(speedY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
