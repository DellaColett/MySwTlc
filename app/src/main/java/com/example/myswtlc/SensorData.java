package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SensorData extends AppCompatActivity implements SensorEventListener {
    TextView tvlist;
    SensorManager sensorManager;
    private Sensor sensorLight;
    private Sensor sensorTemperature;
    private Sensor sensorGravity;
    private float lastX;
    private float lastY;
    private float lastZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tvlist = findViewById(R.id.sensor_list);
        tvlist.setMovementMethod(new ScrollingMovementMethod());

        for (int i = 1; i < mList.size(); i++) {
            tvlist.setVisibility(View.VISIBLE);
            tvlist.append("Sensor number: " + String.valueOf(i) + "\n"
                    + "Sensor name: " + mList.get(i).getName() + "\n"
                    + "Sensor type: " + mList.get(i).getStringType() + "\n"
                    + "Sensor vendor: " + mList.get(i).getVendor() + "\n"
                    + "Sensor version: "+ mList.get(i).getVersion()+ "\n" + "\n");
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType()==Sensor.TYPE_LIGHT) {
            TextView tvlight = findViewById(R.id.light_sensor);
            tvlight.setText("Light: " + event.values[0] + " lx");
        } else if (event.sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE) {
            TextView tvtemperature = findViewById(R.id.temp_sensor);
            tvtemperature.setText("Ambient air temperature: " + event.values[0] + " °C");
            GradientDrawable drawable = (GradientDrawable) tvtemperature.getBackground();
            if(event.values[0] >= 15.0){
                tvtemperature.setTextColor(Color.RED);
                drawable.setStroke(2, getColor(R.color.red));
            }
            else{
                tvtemperature.setTextColor(Color.BLUE);
                drawable.setStroke(2, getColor(R.color.blue));
            }
        } else if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            final float alpha = 0.8f;
            final float[] gravity = new float[3];
            final float[] linear_acceleration = new float[3];
            final float gravity_magnitude;
            final float acc_magnitude;
            gravity[0] = alpha * lastX + (1 - alpha) * event.values[0];
            gravity[1] = alpha * lastY + (1 - alpha) * event.values[1];
            gravity[2] = alpha * lastZ + (1 - alpha) * event.values[2];
            gravity_magnitude= (float) Math.sqrt(gravity[0] * gravity[0] + gravity[1] * gravity[1] + gravity[2] * gravity[2]);
            TextView tvgravity = findViewById(R.id.grav_sensor);
            tvgravity.setText("Acceleration incl. gravity: " + "\n"
                    + "x-axis: " + gravity[0] + " m/s^2" + "\n"
                    + "y-axis: " + gravity[1] + " m/s^2"+ "\n"
                    + "z-axis: " + gravity[2] + " m/s^2"+ "\n"
                    + "magnitude: " + gravity_magnitude + " m/s^2");
            linear_acceleration[0] = event.values[0] - lastX;
            linear_acceleration[1] = event.values[1] - lastY;
            linear_acceleration[2] = event.values[2] - lastZ;
            acc_magnitude=(float)Math.sqrt(linear_acceleration[0] * linear_acceleration[0] +
                    linear_acceleration[1] * linear_acceleration[1] +
                    linear_acceleration[2] * linear_acceleration[2]);
            TextView tvaccelleration= findViewById(R.id.linear_sensor);
            tvaccelleration.setText("Acceleration: " + "\n"
                    + "x-axis: " + linear_acceleration[0] + " m/s^2"+ "\n"
                    + "y-axis: " + linear_acceleration[1] + " m/s^2"+ "\n"
                    + "z-axis: " + linear_acceleration[2] + " m/s^2"+"\n"
                    + "magnitude: " + acc_magnitude + " m/s^2");
            lastX = event.values[0];
            lastY = event.values[1];
            lastZ = event.values[2];
        }
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}