package com.bluemind.bluemind.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

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
    private static final String TAG = MeditationMediaPlayer.class.getSimpleName();
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MeditationPlayerList> listHeader;
    HashMap<List<MeditationPlayerList>, List<Integer>> childList;
    private static final String url = "https://bluemind.s3-ap-southeast-2.amazonaws.com/AudioList.json";
    private Button newsfeed, professionalHelp;
    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_media_player);

        newsfeed = (Button) findViewById(R.id.meditation_media_player_newsFeed);
        professionalHelp = (Button) findViewById(R.id.meditation_media_player_guided);

        expandableListView = (ExpandableListView) findViewById(R.id.meditation_audio_expandable_listview);
        expandableListAdapter = new ExpandableListAdapter(this, listHeader, childList);

        expandableListView.setAdapter(expandableListAdapter);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonMeditationPlayerList(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonMeditationPlayerList(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

        newsfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsFeed = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newsFeed);
            }
        });

        professionalHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MeditationLastPage.class);
                startActivity(intent);
            }
        });
    }

    private void parseJsonMeditationPlayerList(JSONObject response){
        childList = new HashMap<List<MeditationPlayerList>, List<Integer>>();
        try{
            JSONArray audioListArray = response.getJSONArray("AudioList");

            for(int i = 0; i < audioListArray.length(); i++){
                JSONObject audioListObject = (JSONObject) audioListArray.get(i);

                MeditationPlayerList meditationPlayerList = new MeditationPlayerList();
                meditationPlayerList.setName(audioListObject.getString("name"));
                meditationPlayerList.setCoverImage(audioListObject.getString("coverImage"));

                List<Integer> mediaControls = new ArrayList<Integer>();
                mediaControls.add(R.drawable.playbutton);
                mediaControls.add(R.drawable.pausebutton);
                mediaControls.add(R.drawable.nextbutton);
                mediaControls.add(R.drawable.previousbutton);

                listHeader.add(meditationPlayerList);
                childList.put(listHeader, mediaControls);
                expandableListAdapter.notifyDataSetChanged();
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
