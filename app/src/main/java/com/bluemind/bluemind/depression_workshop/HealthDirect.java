package com.bluemind.bluemind.depression_workshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.bluemind.bluemind.R;

public class HealthDirect extends AppCompatActivity {
    private Button call, symptomChecker, browse;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depression_workshop_health_direct);

        WebView webView = (WebView) findViewById(R.id.HealthDirectWebView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("https://www.healthdirect.gov.au/");

        call = (Button) findViewById(R.id.healthDirectCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:1800 022 222"));
                startActivity(callIntent);
            }
        });

        symptomChecker = (Button) findViewById(R.id.healthDirectSymptomChecker);
        symptomChecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.healthdirect.gov.au/symptom-checker";

                Intent chatIntent = new Intent(Intent.ACTION_VIEW);
                chatIntent.setData(Uri.parse(url));
                startActivity(chatIntent);
            }
        });

        browse = (Button) findViewById(R.id.healthDirectBrowse);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseIntent = new Intent(getApplicationContext(), DepressionWorkshop.class);
                startActivity(browseIntent);
            }
        });
    }
}
