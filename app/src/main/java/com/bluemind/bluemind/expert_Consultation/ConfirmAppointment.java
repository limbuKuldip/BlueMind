package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluemind.bluemind.R;

public class ConfirmAppointment extends AppCompatActivity {
    private TextView fullName, email, selectedDate, selectedTime;
    String fullNames, emails, am_pm;
    int dayofMonth, month, year, minutes, hour;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_appointment);

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.confirmAppointmentEmail);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        selectedTime = (TextView) findViewById(R.id.selectedTime);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        dayofMonth = preferences.getInt("dayOfMonth", -1);
        month = preferences.getInt("month", -1);
        year = preferences.getInt("year", -1);
        minutes = preferences.getInt("minutes", -1);
        hour = preferences.getInt("hour", -1);
        am_pm = preferences.getString("am_pm", null);
        fullNames = preferences.getString("userId", null);
        emails = preferences.getString("userEmail", null);

        fullName.setText(fullNames);
        email.setText(emails);
        selectedDate.setText(dayofMonth + " " + month + " " + year);
        selectedTime.setText(hour + " " + minutes + " " + am_pm);

        confirm = (Button) findViewById(R.id.confirmButtonAppointment);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(intent);
            }
        });
    }
}
