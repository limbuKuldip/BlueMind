package com.bluemind.bluemind.depression_workshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;

public class DepressionWorkshopList extends AppCompatActivity {
    private ImageButton beyondBlueButton, healthDirectButton, bluemindBlog;
    private Button newsFeed, help;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depression_workshop_page_2);

        beyondBlueButton = (ImageButton) findViewById(R.id.beyondBlueButton);
        beyondBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BeyondBlue.class);
                startActivity(intent);
            }
        });

        healthDirectButton = (ImageButton) findViewById(R.id.healthDirectButton);
        healthDirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent healthDirect = new Intent(getApplicationContext(), HealthDirect.class);
                startActivity(healthDirect);
            }
        });

        bluemindBlog = (ImageButton) findViewById(R.id.blueMindBlogButton);
        bluemindBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blueMindBlog = new Intent(getApplicationContext(), BlueMindBlog.class);
                startActivity(blueMindBlog);
            }
        });

        newsFeed = (Button) findViewById(R.id.depression_workshop_page_2_newsFeed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsFeed = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newsFeed);
            }
        });

        help = (Button) findViewById(R.id.depression_workshop_page_2_help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent professionalHelp = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(professionalHelp);
            }
        });
    }
}
