package com.plter.photo360;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {


    private SensorManager systemService;
    private Sensor sensor;
    private ImageView iv;
    private int screenWidth = 1280;
    private int minX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemService = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = systemService.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        iv = (ImageView) findViewById(R.id.iv);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;

        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        layoutParams.height = displayMetrics.heightPixels;
        layoutParams.width = (int) (((double) layoutParams.height) / 768 * 2100);
        iv.setLayoutParams(layoutParams);

        minX = screenWidth - layoutParams.width;
    }


    @Override
    protected void onResume() {
        super.onResume();

        systemService.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();

        systemService.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0.1) {
            System.out.println("left");

            iv.setX(iv.getX() + 1);

            if (iv.getX() > 0) {
                iv.setX(0);
            }

        } else if (event.values[0] < -0.1) {
            System.out.println("right");

            iv.setX(iv.getX() - 1);

            if (iv.getX() < minX) {
                iv.setX(minX);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
