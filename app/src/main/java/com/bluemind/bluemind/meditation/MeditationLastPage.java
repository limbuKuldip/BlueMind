package com.bluemind.bluemind.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;

public class MeditationLastPage extends AppCompatActivity {
    private Button newsfeed, profesionalHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_last_page);

        profesionalHelp = (Button) findViewById(R.id.meditation_last_professional_help);
        profesionalHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(intent);
            }
        });

        newsfeed = (Button) findViewById(R.id.meditation_Last_newsFeed);
        newsfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsFeedIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newsFeedIntent);
            }
        });
    }
}
