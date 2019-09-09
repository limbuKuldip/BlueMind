package com.bluemind.bluemind.depression_workshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;

public class DepressionWorkshop extends AppCompatActivity {
    private Button newsFeed, browse;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depression_workshop);

        newsFeed = (Button) findViewById(R.id.depression_workshop_newsFeed);
        browse = (Button) findViewById(R.id.browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepressionWorkshopList.class);
                startActivity(intent);
            }
        });

        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsFeed = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newsFeed);
            }
        });
    }
}
