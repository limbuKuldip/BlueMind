package com.bluemind.bluemind.meditation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.newsfeed.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MeditationMediaPlayer extends AppCompatActivity {
    private Button newsFeed, browse;
    private ImageButton play, pause,stop;
    private MediaPlayer mediaPlayer;
    private ListView listView;
    String[] audioNames = new String[]{"Quiet Time", "Deep Meditation", "Healing Water", "An Ambient Day", "Tranquality", "Elven Forest"};
    int[] audioCoverImages = new int[]{R.drawable.quiet_time_cover_image, R.drawable.deep_meditation_cover_image, R.drawable.healing_water_cover_image, R.drawable.quiet_time_cover_image, R.drawable.tranquility_cover_image, R.drawable.elven_forest_cover_image};
    private boolean isQuieTime = false;
    private boolean isDeepMeditation = false;
    private boolean isHealingWater = false;
    private boolean isAnAmbientDay = false;
    private boolean isTranquality = false;
    private boolean isElvenFores = false;
    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_media_player);

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i<6; i++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("list_audioNames", audioNames[i]);
            hm.put("list_audioCoverImages", Integer.toString(audioCoverImages[i]));
            list.add(hm);
        }

        String[] from = {"list_audioCoverImages", "list_audioNames"};
        int[] to = {R.id.audioImageView, R.id.audioTextView};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), list, R.layout.meditation_media_player_listview, from, to);

        listView = (ListView) findViewById(R.id.meditation_media_player_listView);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.quiet_time);
                    mediaPlayer.start();
                    isQuieTime = true;
                } else if(position == 1){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.deep_meditation);
                    mediaPlayer.start();
                    isDeepMeditation = true;
                } else if(position == 2){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.healing_water);
                    mediaPlayer.start();
                    isHealingWater = true;
                } else if(position == 3){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.an_ambient_day);
                    mediaPlayer.start();
                    isAnAmbientDay = true;
                } else if(position == 4){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tranqality);
                    mediaPlayer.start();
                    isTranquality = true;
                } else if(position == 5){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.elven_forest);
                    mediaPlayer.start();
                    isElvenFores = true;
                }
            }
        });

        newsFeed = (Button) findViewById(R.id.meditation_media_player_newsFeed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        browse = (Button) findViewById(R.id.meditation_media_player_guided);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MeditationLastPage.class);
                startActivity(intent);
            }
        });

        play = (ImageButton) findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.quiet_time);
                mediaPlayer.start();
            }
        });

        pause = (ImageButton) findViewById(R.id.pauseButton);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isQuieTime || isDeepMeditation || isHealingWater || isAnAmbientDay || isTranquality == true){
                    mediaPlayer.pause();
                }
            }
        });

        stop = (ImageButton) findViewById(R.id.stopButton);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isQuieTime || isDeepMeditation || isHealingWater || isAnAmbientDay || isTranquality == true){
                    mediaPlayer.stop();
                }
            }
        });
    }
}
