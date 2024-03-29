package com.bluemind.bluemind.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.R;

public class Meditation extends AppCompatActivity {
    private Button browse, newsFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation);

        browse = (Button) findViewById(R.id.meditationBrowse);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(getApplicationContext(), MeditationPosts.class);
                startActivity(browse);
            }
        });

        newsFeed = (Button) findViewById(R.id.meditation_newsFeed);
        newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsFeedIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newsFeedIntent);
            }
        });
    }
}
