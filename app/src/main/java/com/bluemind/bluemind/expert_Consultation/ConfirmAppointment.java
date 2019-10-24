package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.MySingleton;
import com.bluemind.bluemind.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmAppointment extends AppCompatActivity {
    private TextView fullName, email, selectedDate, selectedTime;
    String fullNames, emails, am_pm, appointedExpert, date, time;
    int dayofMonth, month, year, minutes, hour;
    Button confirm;
    Bundle values;
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String link = "http://www.limbukuldip.com/makeAppointments.php";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_appointment);

        values = getIntent().getExtras();

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.confirmAppointmentEmail);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        selectedTime = (TextView) findViewById(R.id.selectedTime);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        dayofMonth = values.getInt("dayOfMonth");
        month = values.getInt("month");
        year = values.getInt("year");
        minutes = values.getInt("minutes");
        hour = values.getInt("hour");
        am_pm = preferences.getString("am_pm", null);
        fullNames = preferences.getString("userId", null);
        emails = preferences.getString("userEmail", null);

        fullName.setText(fullNames);
        email.setText(emails);
        selectedDate.setText(dayofMonth + " " + month + " " + year);
        date = String.valueOf(dayofMonth + "/" + month + "/" + year);
        selectedTime.setText(hour + " " + minutes + " " + am_pm);
        time = String.valueOf(hour + ":" + minutes + " " + am_pm);

        confirm = (Button) findViewById(R.id.confirmButtonAppointment);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                recordAppointment();
            }
        });
    }

    private void getData(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        fullNames = preferences.getString("userId", null);
        emails = preferences.getString("userEmail", null);

        appointedExpert = values.getString("expert_name");
    }

    private void recordAppointment(){
        final JSONObject request = new JSONObject();
        try{
            request.put("userName", fullNames);
            request.put("appointmentDate", date);
            request.put("appointmentTime", time);
            request.put("appointmentExpert", appointedExpert);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        Intent intent = new Intent(getApplicationContext(), ConfirmationSplashScreen.class);
                        startActivity(intent);
                    } else {
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
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
