package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bluemind.bluemind.breathing.BreathingActivity;
import com.bluemind.bluemind.breathing.BreathingExercise;
import com.bluemind.bluemind.breathing.SetTimeFrame;
import com.bluemind.bluemind.depression_workshop.DepressionWorkshop;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;
import com.bluemind.bluemind.meditation.Meditation;
import com.bluemind.bluemind.online_yoga.OnlineYogaMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityList extends AppCompatActivity {
    private ImageButton challenges, depressionWorkshop, meditationCenter, breathingExercise, onlineYoga, expertHelp;
    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        challenges = (ImageButton) findViewById(R.id.challengesButton);
        depressionWorkshop = (ImageButton) findViewById(R.id.depressionWorkshopButton);
        meditationCenter = (ImageButton) findViewById(R.id.meditationCenterButton);
        breathingExercise = (ImageButton) findViewById(R.id.breathingExercise_button);
        onlineYoga = (ImageButton) findViewById(R.id.onlineYogaButton);
        expertHelp = (ImageButton) findViewById(R.id.expert_help_button);

        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent challengeIntent = new Intent(getApplicationContext(), ChallengesList.class);
                startActivity(challengeIntent);
            }
        });

        depressionWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depressionWorkshopIntent = new Intent(getApplicationContext(), DepressionWorkshop.class);
                startActivity(depressionWorkshopIntent);
            }
        });

        meditationCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meditationCenterIntent = new Intent(getApplicationContext(), Meditation.class);
                startActivity(meditationCenterIntent);
            }
        });

        breathingExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breathingExerciseIntent = new Intent(getApplicationContext(), BreathingActivity.class);
                startActivity(breathingExerciseIntent);
            }
        });

        onlineYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onlineYogaIntent = new Intent(getApplicationContext(), OnlineYogaMain.class);
                startActivity(onlineYogaIntent);
            }
        });

        expertHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expertHelpIntent = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(expertHelpIntent);
            }
        });
    }
}
