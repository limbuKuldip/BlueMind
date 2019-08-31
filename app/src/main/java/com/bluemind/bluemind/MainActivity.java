package com.bluemind.bluemind;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.bluemind.bluemind.groupchannel.GroupChannelActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton activityButton, chatForumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();

        activityButton = (ImageButton) findViewById(R.id.activitiesButton);
        chatForumButton = (ImageButton) findViewById(R.id.chatForumButton);

        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(activityIntent);
            }
        });

        chatForumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatForumIntent = new Intent(getApplicationContext(), GroupChannelActivity.class);
                startActivity(chatForumIntent);
            }
        });
    }
}
