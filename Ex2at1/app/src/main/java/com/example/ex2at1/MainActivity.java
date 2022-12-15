package com.example.ex2at1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor light;
    TextView lightValue;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightValue = (TextView)findViewById(R.id.light);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sensorManager = (SensorManager)
                    getSystemService(Context.SENSOR_SERVICE);
        }
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null)
        {
            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightValue.setText("Light sensor not supported");
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            lightValue.setText("Light Intensity: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}