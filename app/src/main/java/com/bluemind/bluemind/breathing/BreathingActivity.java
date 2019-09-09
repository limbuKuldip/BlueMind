package com.bluemind.bluemind.breathing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;

public class BreathingActivity extends AppCompatActivity {
    private Button startButton, newsFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_activity);

        startButton = (Button) findViewById(R.id.brething_activity_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetTimeFrame.class);
                startActivity(intent);
            }
        });

        newsFeed = (Button) findViewById(R.id.breathing_activity_news_feed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
