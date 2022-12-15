package com.example.ex1at3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final Integer THRESHOLD = 20;

    private EditText editTextX;
    private EditText editTextY;
    private EditText editTextZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextX = findViewById(R.id.editTextTextPersonName);
        editTextY = findViewById(R.id.editTextTextPersonName2);
        editTextZ = findViewById(R.id.editTextTextPersonName3);


        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener se = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Handle accuracy changes
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                editTextX.setText(String.valueOf(x));
                editTextY.setText(String.valueOf(y));
                editTextZ.setText(String.valueOf(z));

                if (Math.abs(x) > THRESHOLD || Math.abs(y) > THRESHOLD || Math.abs(z) > THRESHOLD) {
                    // Switch to a new activity
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        };

    }
}