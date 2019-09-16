package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bluemind.bluemind.R;

public class SetAppointmentDT extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;
    Button next;
    String am_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_picker);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        final int dayofMonth = datePicker.getDayOfMonth();
        final int month = datePicker.getMonth();
        final int year = datePicker.getYear();

        final int minutes = timePicker.getMinute();

        next = (Button) findViewById(R.id.appointmentNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour_twentyFour = timePicker.getHour();
                if(hour_twentyFour > 12){
                    am_pm = "PM";
                    hour_twentyFour = hour_twentyFour - 12;
                }else{
                    am_pm = "AM";
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("dayOfMonth", dayofMonth);
                editor.putInt("month", month);
                editor.putInt("year", year);
                editor.putInt("minutes", minutes);
                editor.putInt("hour", hour_twentyFour);
                editor.putString("am_pm", am_pm);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), ConfirmAppointment.class);
                startActivity(intent);
            }
        });
    }
}
