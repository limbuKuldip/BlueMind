package com.bluemind.bluemind;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpertConsultation extends AppCompatActivity {

    private ListView listView;
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_EXPERT_NAME = "expert_name";
    private static final String KEY_EXPERT_TITLE = "expert_title";
    private static final String url  = "http://www.limbukuldip.com/expertsList.php";
    private ArrayList<HashMap<String, String>> expertsList;
    private ImageView profilePicture;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_consultation_page);
        listView = (ListView) findViewById(R.id.expert_consultation_listview);
        new FetchExpertsNameAsyncTask().execute();

        profilePicture = (ImageView) findViewById(R.id.expert_profile);
    }

    private class FetchExpertsNameAsyncTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params){
            HttpJsonParser parser = new HttpJsonParser();
            JSONObject jsonObject = parser.makeHttpRequest(url , "GET", null);

            try{
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONArray expertsName;
                if(success == 1){
                    expertsList = new ArrayList<>();
                    expertsName = jsonObject.getJSONArray(KEY_DATA);
                    for(int i = 0; i <expertsName.length(); i++){
                        JSONObject experts = expertsName.getJSONObject(i);
                        String experts_name = experts.getString(KEY_EXPERT_NAME);
                        String experts_title = experts.getString(KEY_EXPERT_TITLE);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_EXPERT_NAME, experts_name);
                        map.put(KEY_EXPERT_TITLE, experts_title);
                        expertsList.add(map);
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                populateExpertsListView();
                }
            });
        }
    }

    private void populateExpertsListView(){
        ListAdapter adapter = new SimpleAdapter(
                ExpertConsultation.this, expertsList,
                R.layout.expert_consultation, new String[]{KEY_EXPERT_NAME, KEY_EXPERT_TITLE},
                new int[]{R.id.expert_userName, R.id.expert_title});

        listView.setAdapter(adapter);
    }
}
