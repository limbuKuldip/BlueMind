package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.HttpJsonParser;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.newsfeed.AppController;
import com.bluemind.bluemind.newsfeed.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpertConsultation extends AppCompatActivity {

    private ListView listView;
    private static final String TAG = ExpertConsultation.class.getSimpleName();
    private ExpertsListAdapter listAdapter;
    private List<ExpertsList> expertsLists;
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_EXPERT_NAME = "expert_name";
    private static final String KEY_EXPERT_TITLE = "expert_title";
    private static final String url  = "https://bluemind-bucket.s3-ap-southeast-2.amazonaws.com/expertsList.json";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_consultation_page);

        listView = (ListView) findViewById(R.id.expert_consultation_listview);
        expertsLists = new ArrayList<ExpertsList>();

        listAdapter = new ExpertsListAdapter(this, expertsLists);
        listView.setAdapter(listAdapter);

            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonExpertList(response);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView expertise = (TextView) v.findViewById(R.id.expertise);
                ImageView profilepic = (ImageView) v.findViewById(R.id.profilePic);

                profilepic.buildDrawingCache();
                Bitmap bitmap = profilepic.getDrawingCache();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream );
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(), ExpertsDetails.class);
                intent.putExtra("ExpertName", name.getText().toString());
                intent.putExtra("ExpertExpertise", expertise.getText().toString());
                intent.putExtra("ExpertProfilePic", byteArray);
                startActivity(intent);
            }
        });
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     */
    private void parseJsonExpertList(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("list");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                ExpertsList item = new ExpertsList();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));
                item.setProfilePicture(feedObj.getString("profilePicture"));
                item.setExpertise(feedObj.getString("expertise"));

                expertsLists.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
