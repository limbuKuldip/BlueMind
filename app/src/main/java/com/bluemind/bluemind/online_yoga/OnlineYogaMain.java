package com.bluemind.bluemind.online_yoga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bluemind.bluemind.R;

import java.util.Vector;

public class OnlineYogaMain extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<OnlineYoga> onlineYogas = new Vector<OnlineYoga>();
    View childView;
    int RecyclerViewItemPosition;
    LinearLayout mainLayout, subLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_yoga);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        subLayout = (LinearLayout) findViewById(R.id.subLayout);

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
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(OnlineYogaMain.this, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                childView = rv.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && gestureDetector.onTouchEvent(e)){
                    RecyclerViewItemPosition = rv.getChildAdapterPosition(childView);
                    //Toast.makeText(getApplicationContext(), rv.getAdapter().toString(), Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
