package com.bluemind.bluemind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class IdentifyMe2 extends AppCompatActivity {

    private Button back, next;
    private String clinicallyDiagonised;
    private CheckBox yes, no, notSure;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyme_2);

        yes = (CheckBox)findViewById(R.id.ClinicallyDiagonizedYes);
        no = (CheckBox) findViewById(R.id.ClinicallyDiagonizedNo);
        notSure = (CheckBox) findViewById(R.id.ClinicallyDiagonizedNotSure);

        back = (Button) findViewById(R.id.backIdentifyMe2) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent identifyMe = new Intent(getApplicationContext(), IdentifyMe.class);
                startActivity(identifyMe);
            }
        });

        next = (Button) findViewById(R.id.nextIdentifyMe2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent identifyMe3 = new Intent(getApplicationContext(), IdentifyMe3.class);
                startActivity(identifyMe3);
            }
        });
    }

    public void RecordClinicallyDiagonised(){
        if(yes.isChecked()){
            clinicallyDiagonised = yes.getText().toString();
        }else if(no.isChecked()){
            clinicallyDiagonised = no.getText().toString();
        }else if(notSure.isChecked()){
            clinicallyDiagonised = notSure.getText().toString();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clinicallyDiagonised", clinicallyDiagonised);
        editor.apply();
    }
}
