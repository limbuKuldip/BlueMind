package com.bluemind.bluemind;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class BreathingExercise extends AppCompatActivity {
    private TextView startingTime, inhaleAndExhalTextView;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_exercise);

        startingTime = (TextView) findViewById(R.id.startingTime);
        inhaleAndExhalTextView = (TextView) findViewById(R.id.inhaleandexhale);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startingTime.setText("Starting In " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                startingTime.setVisibility(View.INVISIBLE);
            }
        }.start();

        Bundle values = getIntent().getExtras();

        int firstMinutes = Integer.parseInt(values.getString("minutesFirst"));
        int secondMinutes = Integer.parseInt(values.getString("minutesSecond"));
        int firstSeconds = Integer.parseInt(values.getString("secondsFirst"));
        int secondSeconds = Integer.parseInt(values.getString("secondsSecond"));

        int firstInhaleSeconds = Integer.parseInt(values.getString("inhaleMinutesFirst"));
        int secondInhaleSeconds = Integer.parseInt(values.getString("inhaleMinutesSecond"));
        int firstExhaleSeconds = Integer.parseInt(values.getString("inhaleSecondsFirst"));
        int secondExhaleSeconds = Integer.parseInt(values.getString("inhaleSecondsSecond"));

        long minutesToMilliSeconds = (firstMinutes + secondMinutes) * 60000;
        long secondsToMilliSeconds = (firstSeconds + secondSeconds) * 1000;

        final long TotalsecondsToMilliSecondsInhale = (firstInhaleSeconds + secondInhaleSeconds) * 1000;
        final long TotalsecondsToMilliSecondsExhale = (firstExhaleSeconds + secondExhaleSeconds) * 1000;

        long TotalMilliSeconds = minutesToMilliSeconds + secondsToMilliSeconds;

        new CountDownTimer(TotalMilliSeconds, 3000){
            @Override
            public void onTick(long millisUntilFinished){
                new CountDownTimer(8000, 6000){
                    @Override
                    public void onTick(long millisUntilFinished){
                        inhaleAndExhalTextView.setText("Inhale");
                    }

                    public void onFinish(){
                        inhaleAndExhalTextView.setText("Exhale");
                    }
                }.start();
            }

            public void onFinish(){

            }
        }.start();
    }
}
