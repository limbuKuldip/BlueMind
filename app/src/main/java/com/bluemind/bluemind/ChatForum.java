package com.bluemind.bluemind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.sendbird.android.GroupChannel;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class ChatForum extends AppCompatActivity {

    public static final String APP_ID = "90A0A694-F349-4249-BE71-875F6A2EE5F5";
    public static String USER_ID = null;
    public boolean IS_DISTINCT = true;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatforum);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String getUserID = preferences.getString("userID", "");

        USER_ID = getUserID;

        SendBird.init(APP_ID, getApplicationContext());
        SendBird.connect(USER_ID, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if(e != null){
                    return;
                }
            }
        });
    }
}
