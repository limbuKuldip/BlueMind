package com.bluemind.bluemind.identify_me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.bluemind.bluemind.R;

public class IdentifyMe extends AppCompatActivity {

    private Button next;
    private String purpose;
    private CheckBox relax, overcome, notSure;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyme);

        relax = (CheckBox) findViewById(R.id.relax);
        overcome = (CheckBox) findViewById(R.id.overcome);
        notSure = (CheckBox) findViewById(R.id.notSureIdentifyMe);
        next = (Button) findViewById(R.id.nextIdentifyMe);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordPurpose();
                Intent intent = new Intent(getApplicationContext(), IdentifyMe2.class);
                startActivity(intent);
            }
        });

    }

    public void RecordPurpose(){
        if(relax.isChecked()){
            purpose = relax.getText().toString();
        }else if(overcome.isChecked()){
            purpose = overcome.getText().toString();
        }else if(notSure.isChecked()){
            purpose = notSure.getText().toString();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("purpose", purpose);
        editor.apply();
    }
}
