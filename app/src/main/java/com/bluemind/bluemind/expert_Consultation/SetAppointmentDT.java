package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bluemind.bluemind.R;

public class SetAppointmentDT extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;
    Button next;
    String am_pm, expertName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_picker);

        Bundle vales = getIntent().getExtras();
        expertName = vales.getString("appoint_expert");
        Log.d("EXPERT NAME ", expertName);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        next = (Button) findViewById(R.id.appointmentNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int dayofMonth = datePicker.getDayOfMonth();
                final int month = datePicker.getMonth()+1;
                final int year = datePicker.getYear();

                final int minutes = timePicker.getMinute();
                int hour_twentyFour = timePicker.getHour();
                if(hour_twentyFour > 12){
                    am_pm = "PM";
                    hour_twentyFour = hour_twentyFour - 12;
                }else{
                    am_pm = "AM";
                }

                Intent intent = new Intent(getApplicationContext(), ConfirmAppointment.class);
                intent.putExtra("expert_name", expertName);
                intent.putExtra("dayOfMonth", dayofMonth);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("minutes", minutes);
                intent.putExtra("hour", hour_twentyFour);
                intent.putExtra("am_pm", am_pm);
                startActivity(intent);
            }
        });
    }
}
