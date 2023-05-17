package com.inhatc.proximitysensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager objSMG;

    Sensor sensor_Proximity;

    ImageView imageView;

    private ImageSwitcher objIMG;

    Vibrator objVIB;

    @Override
    protected void onResume() {
        super.onResume();

        objSMG.registerListener(this, sensor_Proximity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        objSMG.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objSMG = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor_Proximity = objSMG.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        imageView = imageView.findViewById(R.id.imageView);
        objVIB = (Vibrator)getSystemService(VIBRATOR_SERVICE);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
                if(sensorEvent.values[0]<=5){
                    Animation objANI = AnimationUtils.loadAnimation(this, R.anim.jumping);
                    objIMG.setImageResource(R.drawable.emoticon2);
                    objIMG.startAnimation(objANI);
                    objVIB.vibrate(1000);
                }
                else{
                    objIMG.setImageResource(R.drawable.emoticon1);
                }
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}