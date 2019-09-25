package com.bluemind.bluemind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.breathing.BreathingActivity;
import com.bluemind.bluemind.chat.GroupChannelActivity;
import com.bluemind.bluemind.chat.PreferenceUtils;
import com.bluemind.bluemind.depression_workshop.DepressionWorkshop;
import com.bluemind.bluemind.meditation.Meditation;
import com.bluemind.bluemind.newsfeed.AppController;
import com.bluemind.bluemind.newsfeed.CreatePost;
import com.bluemind.bluemind.newsfeed.FeedItem;
import com.bluemind.bluemind.newsfeed.FeedListAdapter;
import com.bluemind.bluemind.online_yoga.OnlineYoga;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton activityButton, chatForumButton, postButton, aboutButton;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter feedListAdapter;
    private List<FeedItem> feedItemList;
    private String feedURL = "http://www.limbukuldip.com/feed.json";
    private static final String APP_ID = "90A0A694-F349-4249-BE71-875F6A2EE5F5";
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize the SendBird SDK.
        PreferenceUtils.init(getApplicationContext());
        SendBird.init(APP_ID, this.getApplicationContext());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final String userId = preferences.getString("userId", null);
        final String nickName = preferences.getString("nickName", null);
        final String userEmail = preferences.getString("userEmail", null);

        View v = navigationView.getHeaderView(0);
        TextView userName = (TextView) v.findViewById(R.id.nav_userName);
        userName.setText(userId);
        TextView email = (TextView) v.findViewById(R.id.nav_user_email);
        email.setText(userEmail);

        activityButton = (ImageButton) findViewById(R.id.activitiesButton);
        chatForumButton = (ImageButton) findViewById(R.id.chatForumButton);
        postButton = (ImageButton) findViewById(R.id.postButton);
        aboutButton = (ImageButton) findViewById(R.id.infoButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createPost = new Intent(getApplicationContext(), CreatePost.class);
                startActivity(createPost);
                overridePendingTransition(R.anim.slide_up, R.anim.stay);
            }
        });

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
                PreferenceUtils.setUserId(userId);
                PreferenceUtils.setNickname(nickName);
                connectToSendBird(userId, nickName);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.newFeedListView);
        feedItemList = new ArrayList<FeedItem>();

        feedListAdapter = new FeedListAdapter(this, feedItemList);
        listView.setAdapter(feedListAdapter);

        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(feedURL);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    feedURL, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItemList.add(item);
            }

            // notify data changes to list adapater
            feedListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to connect a user to SendBird.
     * @param userId    The unique ID of the user.
     * @param userNickname  The user's nickname, which will be displayed in chats.
     */
    private void connectToSendBird(final String userId, final String userNickname) {

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {

                if (e != null) {
                    // Error!
                    Toast.makeText(
                            MainActivity.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                Intent chatForumIntent = new Intent(getApplicationContext(), GroupChannelActivity.class);
                startActivity(chatForumIntent);
                finish();
            }
        });
    }

    /**
     * Updates the user's nickname.
     * @param userNickname  The new nickname of the user.
     */
    private void updateCurrentUserInfo(String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            MainActivity.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        menuItem.setChecked(true);

        if (id == R.id.nav_home) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.challengeCenter) {
            Intent intent = new Intent(getApplicationContext(), ChallengesList.class);
            startActivity(intent);

        } else if (id == R.id.depressionWorkshop) {
            Intent intent = new Intent(getApplicationContext(), DepressionWorkshop.class);
            startActivity(intent);

        } else if (id == R.id.onlineYoga) {
            Intent intent = new Intent(getApplicationContext(), OnlineYoga.class);
            startActivity(intent);
        } else if (id == R.id.breathingExercise) {
            Intent intent = new Intent(getApplicationContext(), BreathingActivity.class);
            startActivity(intent);
        } else if (id == R.id.meditationCenter) {
            Intent intent = new Intent(getApplicationContext(), Meditation.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
