package com.bluemind.bluemind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class IdentifyMe3 extends AppCompatActivity {

    private Button back, next;
    private String intenseDepression;
    private CheckBox yes, no, notSure;
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
                Intent splashIdentifyMe = new Intent(getApplicationContext(), IdentifyMeSplashScreen.class);
                startActivity(splashIdentifyMe);
            }
        });
    }

    public void RecordIntenseDepression(){
        if(yes.isChecked()){
            intenseDepression = yes.getText().toString();
        }else if(no.isChecked()){
            intenseDepression = no.getText().toString();
        }else if(notSure.isChecked()){
            intenseDepression = notSure.getText().toString();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IntenseDepression", intenseDepression);
        editor.apply();
    }
}
