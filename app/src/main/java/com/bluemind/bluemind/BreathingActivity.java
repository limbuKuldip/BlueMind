package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BreathingActivity extends AppCompatActivity {
    private TextView minutesFirst, minutesSecond, secondsFirst, secondsSecond, inhaleMinutesFirst, inhaleMinutesSecond,
            inhaleSecondsFirst, inhaleSecondsSecond;
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_activity);

        Bundle values = getIntent().getExtras();

        minutesFirst = (TextView) findViewById(R.id.breathing_activity_minutes_first);
        minutesFirst.setText(values.getString("minutesFirst"));
        minutesSecond = (TextView) findViewById(R.id.breathing_activity_minutes_second);
        secondsFirst = (TextView) findViewById(R.id.breathing_activity_seconds_first);
        secondsSecond = (TextView) findViewById(R.id.breathing_activity_seconds_second);
        inhaleMinutesFirst = (TextView) findViewById(R.id.breathing_activity_inhale_minutes_first);
        inhaleMinutesSecond = (TextView) findViewById(R.id.breathing_activity_inhale_minutes_second);
        inhaleSecondsFirst = (TextView) findViewById(R.id.breathing_activity_inhale_seconds_first);
        inhaleSecondsSecond = (TextView) findViewById(R.id.breathing_activity_inhale_seconds_second);

        minutesSecond.setText(values.getString("minutesSecond"));
        secondsFirst.setText(values.getString("secondsFirst"));
        secondsSecond.setText(values.getString("secondsSecond"));
        inhaleMinutesFirst.setText(values.getString("inhaleMinutesFirst"));
        inhaleMinutesSecond.setText(values.getString("inhaleMinutesSecond"));
        inhaleSecondsFirst.setText(values.getString("inhaleSecondsFirst"));
        inhaleSecondsSecond.setText(values.getString("inhaleSecondsSecond"));

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breathingExercise = new Intent(getApplicationContext(), BreathingExercise.class);
                breathingExercise.putExtra("minutesFirst", minutesFirst.getText().toString());
                breathingExercise.putExtra("minutesSecond", minutesSecond.getText().toString());
                breathingExercise.putExtra("secondsFirst", secondsFirst.getText().toString());
                breathingExercise.putExtra("secondsSecond", secondsSecond.getText().toString());
                breathingExercise.putExtra("inhaleMinutesFirst", inhaleMinutesFirst.getText().toString());
                breathingExercise.putExtra("inhaleMinutesSecond", inhaleMinutesSecond.getText().toString());
                breathingExercise.putExtra("inhaleSecondsFirst", inhaleSecondsFirst.getText().toString());
                breathingExercise.putExtra("inhaleSecondsSecond", inhaleSecondsSecond.getText().toString());
                startActivity(breathingExercise);
            }
        });
    }
}
