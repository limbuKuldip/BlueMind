package com.bluemind.bluemind.challenge_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bluemind.bluemind.R;
import com.bluemind.bluemind.challenge_activity.ChallengesList;

public class ReviewScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewscreen);

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ChallengesList.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
