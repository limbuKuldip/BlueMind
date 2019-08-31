package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChallengesList extends AppCompatActivity {

    private Button completeButton, viewButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challengeslist);

        final String UID = getIntent().getStringExtra("userID");

        completeButton = (Button) findViewById(R.id.rideComplete);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent completeIntent = new Intent(getApplicationContext(), CompleteButton.class);
                startActivity(completeIntent);
                completeIntent.putExtra("userId", UID);
            }
        });

        viewButton = (Button) findViewById(R.id.activityoneViewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(getApplicationContext(), ViewButton.class);
                startActivity(viewIntent);
            }
        });
    }
}
