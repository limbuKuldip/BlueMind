package com.bluemind.bluemind.challenge_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bluemind.bluemind.R;
import com.bluemind.bluemind.challenge_activity.ChallengesList;

public class Splash extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ChallengesList.class);
                startActivity(intent);
            }
        }, secondsDelayed * 1000);
        finish();
    }
}