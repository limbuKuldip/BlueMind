package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluemind.bluemind.R;
import com.bluemind.bluemind.chat.ChatActivity;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class ExpertsDetails extends AppCompatActivity {
    private String expert_name;
    private Button chat, makeAnAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experts_details);

        Bundle values = getIntent().getExtras();

        final TextView name = (TextView) findViewById(R.id.experts_details_name);
        name.setText(values.getString("ExpertName"));
        expert_name = values.getString("ExpertName");
        TextView expertise = (TextView) findViewById(R.id.experts_details_title);
        ImageView profilePic = (ImageView) findViewById(R.id.experts_details_profile_picture);

        byte[] byteArray = getIntent().getByteArrayExtra("ExpertProfilePic");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        expertise.setText(values.getString("ExpertExpertise"));
        profilePic.setImageBitmap(bitmap);

        chat = (Button) findViewById(R.id.experts_details_chat_button);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userId = preferences.getString("userId", null);
                String nickName = preferences.getString("userId", null);
                connectToSendBird(userId, nickName);
            }
        });
        makeAnAppointment = (Button) findViewById(R.id.expert_booking);
        makeAnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetAppointmentDT.class);
                intent.putExtra("appoint_expert", expert_name);
                startActivity(intent);
            }
        });
    }

    private void connectToSendBird(final String userId, final String userNickname) {

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {

                if (e != null) {
                    // Error!
                    Toast.makeText(
                            getApplicationContext(), "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);

                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCurrentUserInfo(String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            getApplicationContext(), "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

            }
        });
    }
}
