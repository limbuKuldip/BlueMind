package com.bluemind.bluemind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                //RecordPurpose();
                Intent intent = new Intent(getApplicationContext(), IdentifyMe2.class);
                startActivity(intent);
            }
        });

    }

    public void RecordPurose(){
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
