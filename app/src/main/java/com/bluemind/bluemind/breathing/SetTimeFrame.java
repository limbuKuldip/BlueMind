package com.bluemind.bluemind.breathing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.MySingleton;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.breathing.BreathingActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SetTimeFrame extends AppCompatActivity {
    private EditText inhaleSeconds, exhaleSeconds;
    private Button startButton, newsFeed;
    int inhaleTime, exhaleTime;
    private static final String STATUS = "status";
    private static final String USERNAME = "userName";
    private static final String INHALESECONDS = "inhaleSeconds";
    private static final String EXHALESECONDS = "exhaleSeconds";
    private static final String link = "http://www.limbukuldip.com/breathingExerciseCount.php";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_activity_set_time_frame);

        inhaleSeconds = (EditText) findViewById(R.id.inhaleSeconds);
        exhaleSeconds = (EditText) findViewById(R.id.exhaleSeconds);

        startButton = (Button) findViewById(R.id.breathing_SetTime_Start_Button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = inhaleSeconds.getText().toString();
                inhaleTime = Integer.parseInt(value1);

                String value2 = exhaleSeconds.getText().toString();
                exhaleTime = Integer.parseInt(value2);

                Intent intent = new Intent(getApplicationContext(), BreathingExercise.class);
                intent.putExtra("inhaleSeconds", inhaleTime);
                intent.putExtra("exhaleSeconds", exhaleTime);
                startActivity(intent);

                breathingExerciseCount();
            }
        });

        newsFeed = (Button) findViewById(R.id.set_timeFrame_news_feed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void breathingExerciseCount(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userName = preferences.getString("userId", null);
        JSONObject request = new JSONObject();
        try{
            request.put(USERNAME, userName);
            request.put(INHALESECONDS, inhaleTime);
            request.put(EXHALESECONDS, exhaleTime);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getInt(STATUS) == 0){

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
