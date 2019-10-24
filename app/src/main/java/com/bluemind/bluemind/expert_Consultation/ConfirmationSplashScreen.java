package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.bluemind.bluemind.R;

public class ConfirmationSplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_confirm_splash);

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(intent);
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
