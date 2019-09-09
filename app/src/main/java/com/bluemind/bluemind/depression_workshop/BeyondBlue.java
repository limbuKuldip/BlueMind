package com.bluemind.bluemind.depression_workshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.bluemind.bluemind.R;

public class BeyondBlue extends AppCompatActivity {
    private Button call, chat, browse;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depression_workshop_beyond_blue);

        WebView webView = (WebView) findViewById(R.id.beyondBlueWebView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("https://www.beyondblue.org.au/");

        call = (Button) findViewById(R.id.beyondBlueCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:1300 22 4636"));
                startActivity(callIntent);
            }
        });

        chat = (Button) findViewById(R.id.beyondBlueChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://online.beyondblue.org.au/#/chat/questions1";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        browse = (Button) findViewById(R.id.beyondBlueBrowse);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlIntent = new Intent(getApplicationContext(), DepressionWorkshop.class);
                startActivity(urlIntent);
            }
        });
    }
}
