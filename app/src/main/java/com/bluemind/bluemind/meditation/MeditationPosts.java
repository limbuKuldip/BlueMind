package com.bluemind.bluemind.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.bluemind.bluemind.R;
import com.bluemind.bluemind.expert_Consultation.ExpertConsultation;

public class MeditationPosts extends AppCompatActivity {
    private Button professional_help, meditate;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_post);

        WebView webView = (WebView) findViewById(R.id.meditation_webView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("https://melbournemeditationcentre.com.au/articles");

        professional_help = (Button) findViewById(R.id.meditation_professional_help);
        professional_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(getApplicationContext(), ExpertConsultation.class);
                startActivity(helpIntent);
            }
        });

        meditate = (Button) findViewById(R.id.meditate);
        meditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MeditationMediaPlayer.class);
                startActivity(intent);
            }
        });
    }
}
