package com.bluemind.bluemind.online_yoga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bluemind.bluemind.R;

import java.util.Vector;

public class OnlineYogaMain extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<OnlineYoga> onlineYogas = new Vector<OnlineYoga>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_yoga);

        recyclerView = (RecyclerView) findViewById(R.id.online_yogaRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v7AYKMP6rOE\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/H4dVbaLqg84\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/x_9g8ftbVYU\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pEFVxxNyFKA\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/J-05m7bboK0\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/X3-gKPNyrTA\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Ci3na6ThUJc\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jOfshreyu4w\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jsLAc-2y0bE\" frameborder=\"0\" allowfullscreen></iframe>"));
        onlineYogas.add(new OnlineYoga("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BPobdbmzY9o\" frameborder=\"0\" allowfullscreen></iframe>"));

        OnlineYogaAdapter adapter = new OnlineYogaAdapter(onlineYogas);
        recyclerView.setAdapter(adapter);
    }
}
