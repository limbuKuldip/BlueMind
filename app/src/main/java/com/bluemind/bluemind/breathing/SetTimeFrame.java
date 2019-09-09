package com.bluemind.bluemind.breathing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.breathing.BreathingActivity;

public class SetTimeFrame extends AppCompatActivity {
    private EditText inhaleSeconds, exhaleSeconds;
    private Button startButton, newsFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_activity_set_time_frame);

        inhaleSeconds = (EditText) findViewById(R.id.inhaleSeconds);
        exhaleSeconds = (EditText) findViewById(R.id.exhaleSeconds);

        startButton = (Button) findViewById(R.id.breathing_SetTime_Start_Button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = inhaleSeconds.getText().toString();
                final int inhaleTime = Integer.parseInt(value1);

                String value2 = exhaleSeconds.getText().toString();
                final int exhaleTime = Integer.parseInt(value2);

                Intent intent = new Intent(getApplicationContext(), BreathingExercise.class);
                intent.putExtra("inhaleSeconds", inhaleTime);
                intent.putExtra("exhaleSeconds", exhaleTime);
                startActivity(intent);
            }
        });

        newsFeed = (Button) findViewById(R.id.set_timeFrame_news_feed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
