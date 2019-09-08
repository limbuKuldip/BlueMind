package com.bluemind.bluemind.expert_Consultation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluemind.bluemind.R;

public class ExpertsDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experts_details);

        Bundle values = getIntent().getExtras();

        TextView name = (TextView) findViewById(R.id.experts_details_name);
        name.setText(values.getString("ExpertName"));
        TextView expertise = (TextView) findViewById(R.id.experts_details_title);
        ImageView profilePic = (ImageView) findViewById(R.id.experts_details_profile_picture);

        byte[] byteArray = getIntent().getByteArrayExtra("ExpertProfilePic");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        expertise.setText(values.getString("ExpertExpertise"));
        profilePic.setImageBitmap(bitmap);
    }
}
