package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bluemind.bluemind.breathing.BreathingActivity;
import com.bluemind.bluemind.breathing.SetTimeFrame;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;
import com.bluemind.bluemind.meditation.Meditation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    private ListView listView;
    String[] activityListString = new String[]{"Breathing Center", "Challenges", "Depression Workshop", "Meditation Center", "Online Yoga", "Expert Consultation"};

    int[] activityListImages = new int[]{R.drawable.breathable, R.drawable.challenges, R.drawable.depression, R.drawable.meditation, R.drawable.yoga, R.drawable.consulting};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivitypage);

        final String UID = getIntent().getStringExtra("userID");

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < 6; i++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("list_activityListString", activityListString[i]);
            hm.put("list_activityListImages", Integer.toString(activityListImages[i]));
            list.add(hm);
        }

        String[] from = {"list_activityListImages", "list_activityListString"};
        int[] to = {R.id.activitylistImage, R.id.activitylistLabel};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), list, R.layout.activitylistview, from, to);

        listView = (ListView) findViewById(R.id.activitylist);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent breathingIntent = new Intent(getApplicationContext(), BreathingActivity.class);
                    startActivity(breathingIntent);
                } else if(position == 1){
                    Intent challengesintent = new Intent(getApplicationContext(), Splash.class);
                    challengesintent.putExtra("userID", UID);
                    startActivity(challengesintent);
                } else if(position == 3){
                    Intent meditationIntent = new Intent(getApplicationContext(), Meditation.class);
                    startActivity(meditationIntent);
                } else if (position == 5){
                    Intent expertConsultation = new Intent(getApplicationContext(), ExpertConsultation.class);
                    startActivity(expertConsultation);
                }
            }
        });
    }
}
