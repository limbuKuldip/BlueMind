package com.bluemind.bluemind.depression_workshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.MySingleton;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;

import org.json.JSONException;
import org.json.JSONObject;

public class DepressionWorkshopList extends AppCompatActivity {
    private ImageButton beyondBlueButton, healthDirectButton, bluemindBlog;
    private Button newsFeed, help;
    private static final String BEYONDBLUE = "beyondBlue";
    private static final String USERNAME = "userName";
    private static final String link = "http://www.limbukuldip.com/updateDepressionActivity.php";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    String userName;
    String beyondBlue = "1";
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depression_workshop_page_2);

        beyondBlueButton = (ImageButton) findViewById(R.id.beyondBlueButton);
        beyondBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                userName = preferences.getString("userId", null);
                RecordClick();
                Intent intent = new Intent(getApplicationContext(), BeyondBlue.class);
                startActivity(intent);
            }
        });

        healthDirectButton = (ImageButton) findViewById(R.id.healthDirectButton);
        healthDirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                userName = preferences.getString("userId", null);
                Intent healthDirect = new Intent(getApplicationContext(), HealthDirect.class);
                startActivity(healthDirect);
            }
        });

        bluemindBlog = (ImageButton) findViewById(R.id.blueMindBlogButton);
        bluemindBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                userName = preferences.getString("userId", null);
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

    private void RecordClick(){
        final JSONObject request = new JSONObject();
        try{
            request.put(USERNAME, userName);
            request.put(BEYONDBLUE, beyondBlue);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getInt(STATUS) == 0){

                    } else if(response.getInt(STATUS) == 1){
                        Toast.makeText(getApplicationContext(), response.getString(MESSAGE), Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getApplicationContext(), response.getString(MESSAGE), Toast.LENGTH_LONG).show();
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
