package com.bluemind.bluemind.identify_me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.MySingleton;
import com.bluemind.bluemind.R;

import org.json.JSONException;
import org.json.JSONObject;

public class IdentifyMe3 extends AppCompatActivity {

    private Button back, next;
    private String userName, purpose, clinicallyDiagonised, intenseDepression;
    private CheckBox yes, no, notSure;
    private static final String USER_ID = "userName";
    private static final String PURPOSE = "purpose";
    private static final String CLINICALLY_DIAGONISED = "clinicallyDiagonised";
    private static final String INTENSE_DEPRESSION = "intenseDepression";
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String link = "http://www.limbukuldip.com/identifyMe.php";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyme_3);

        yes = (CheckBox) findViewById(R.id.IntenseDepressionYes);
        no = (CheckBox) findViewById(R.id.IntenseDepressionNo);
        notSure = (CheckBox) findViewById(R.id.IntenseDepressionNotSure);

        back = (Button)findViewById(R.id.backIdentifyMe3);
        next = (Button)findViewById(R.id.nextIdentifyMe3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent identifyMe2 = new Intent(getApplicationContext(), IdentifyMe2.class);
                startActivity(identifyMe2);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserID();
                RecordData();
            }
        });
    }

    private void getUserID(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userName = preferences.getString("userId", null);
        purpose = preferences.getString("purpose", null);
        clinicallyDiagonised = preferences.getString("clinicallyDiagonised", null);
    }

    public void RecordData(){
        if(yes.isChecked()){
            intenseDepression = yes.getText().toString();
        }else if(no.isChecked()){
            intenseDepression = no.getText().toString();
        }else if(notSure.isChecked()){
            intenseDepression = notSure.getText().toString();
        }

        final JSONObject request = new JSONObject();
        try{
            request.put(USER_ID, userName);
            request.put(PURPOSE, purpose);
            request.put(CLINICALLY_DIAGONISED, clinicallyDiagonised);
            request.put(INTENSE_DEPRESSION, intenseDepression);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        Intent intent = new Intent(getApplicationContext(), IdentifyMeSplashScreen.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), response.getString(KEY_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
