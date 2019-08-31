package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetTimeFrame extends AppCompatActivity {
    private TextView setTimeFrameTextView, exerciseTimeFrame, setTimeFrameInhaleAndExhale;
    private EditText minutesFirst, minutesSecond, secondsFirst, secondsSecond, inhaleMinutesFirst, inhaleMinutesSecond,
    inhaleSecondsFirst, inhaleSecondsSecond;
    private Button setTimeFrameButton, setInhaleExhaleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_activity_set_time_frame);

        setTimeFrameTextView = (TextView) findViewById(R.id.time_frame);
        minutesFirst = (EditText) findViewById(R.id.minutesFirst);
        minutesSecond = (EditText) findViewById(R.id.minutesSecond);
        secondsFirst = (EditText) findViewById(R.id.secondsFirst);
        secondsSecond = (EditText) findViewById(R.id.secondsSecond);

        exerciseTimeFrame = (TextView) findViewById(R.id.exercise_time_frame);
        exerciseTimeFrame.setVisibility(View.INVISIBLE);

        setTimeFrameInhaleAndExhale = (TextView) findViewById(R.id.inhaleExhale);
        setTimeFrameInhaleAndExhale.setVisibility(View.INVISIBLE);

        inhaleMinutesFirst = (EditText) findViewById(R.id.InhaleMinutesFirst);
        inhaleMinutesFirst.setVisibility(View.INVISIBLE);

        inhaleMinutesSecond = (EditText) findViewById(R.id.InhaleMinutesSecond);
        inhaleMinutesSecond.setVisibility(View.INVISIBLE);

        inhaleSecondsFirst = (EditText) findViewById(R.id.InhaleSecondsFirst);
        inhaleSecondsFirst.setVisibility(View.INVISIBLE);

        inhaleSecondsSecond = (EditText) findViewById(R.id.InhaleSecondsSecond);
        inhaleSecondsSecond.setVisibility(View.INVISIBLE);

        setInhaleExhaleButton = (Button) findViewById(R.id.inhaleAndExhaleContinue);
        setInhaleExhaleButton.setVisibility(View.INVISIBLE);
        setInhaleExhaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breathingActivity = new Intent(getApplicationContext(), BreathingActivity.class);
                breathingActivity.putExtra("minutesFirst", minutesFirst.getText().toString());
                breathingActivity.putExtra("minutesSecond", minutesSecond.getText().toString());
                breathingActivity.putExtra("secondsFirst", secondsFirst.getText().toString());
                breathingActivity.putExtra("secondsSecond", secondsSecond.getText().toString());
                breathingActivity.putExtra("inhaleMinutesFirst", inhaleMinutesFirst.getText().toString());
                breathingActivity.putExtra("inhaleMinutesSecond", inhaleMinutesSecond.getText().toString());
                breathingActivity.putExtra("inhaleSecondsFirst", inhaleSecondsFirst.getText().toString());
                breathingActivity.putExtra("inhaleSecondsSecond", inhaleSecondsSecond.getText().toString());
                startActivity(breathingActivity);
            }
        });

        setTimeFrameButton = (Button) findViewById(R.id.breathing_activity_continue);
        setTimeFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeFrameTextView.setVisibility(View.INVISIBLE);
                exerciseTimeFrame.setVisibility(View.VISIBLE);
                setTimeFrameButton.setVisibility(View.INVISIBLE);
                setTimeFrameInhaleAndExhale.setVisibility(View.VISIBLE);
                inhaleMinutesFirst.setVisibility(View.VISIBLE);
                inhaleMinutesFirst.requestFocus();
                inhaleMinutesSecond.setVisibility(View.VISIBLE);
                inhaleSecondsFirst.setVisibility(View.VISIBLE);
                inhaleSecondsSecond.setVisibility(View.VISIBLE);
                setInhaleExhaleButton.setVisibility(View.VISIBLE);
            }
        });
    }
}
