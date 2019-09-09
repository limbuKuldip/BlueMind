package com.bluemind.bluemind.breathing;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;

public class BreathingExercise extends AppCompatActivity {
    private EditText inhaleET, exhaleET;
    private Button playButton, pauseButton, newsFeed;
    CountDownTimer timer, timer2;
    private boolean timerIsRunning = false;
    private boolean timer2IsRunning = false;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_exercise);

        Bundle values = getIntent().getExtras();
        final int inhaleSeconds = values.getInt("inhaleSeconds");
        int totalInhaleSeconds = inhaleSeconds * 1000;
        timer = new CountDownTimer(totalInhaleSeconds, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                inhaleET.setText(Long.toString(millisUntilFinished/1000));
                timerIsRunning = true;
            }

            @Override
            public void onFinish() {
                inhaleET.setText("");
                timer2.start();
                timerIsRunning = false;
            }
        };

        int exhaleSeconds = values.getInt("exhaleSeconds");
        int totalExhaleSeconds = exhaleSeconds * 1000;
        timer2 = new CountDownTimer(totalExhaleSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                exhaleET.setText(Long.toString(millisUntilFinished/1000));
                timer2IsRunning = true;
            }

            @Override
            public void onFinish() {
                exhaleET.setText("");
                timer.start();
                timer2IsRunning = false;
            }
        };

        inhaleET = (EditText) findViewById(R.id.inhaleSeconds_breathingExercise);
        exhaleET = (EditText) findViewById(R.id.exhaleSeconds_breathingExercise);

        playButton = (Button) findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerIsRunning == true){
                    timer.cancel();
                } else if(timer2IsRunning == true){
                    timer2.cancel();
                }
            }
        });

        newsFeed = (Button) findViewById(R.id.breathing_exercise_news_feed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
