package com.example.ex2at2;

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
    private Sensor temperature;
    TextView temperatureValue;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperatureValue = (TextView)findViewById(R.id.temperature);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sensorManager = (SensorManager)
                    getSystemService(Context.SENSOR_SERVICE);
        }
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temperature != null)
        {
            sensorManager.registerListener(MainActivity.this, temperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            temperatureValue.setText("temperature sensor not supported");
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            temperatureValue.setText("Ambient Temperature: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}