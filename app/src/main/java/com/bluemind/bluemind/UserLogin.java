package com.bluemind.bluemind;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogin extends AppCompatActivity {

    private Button loginIn;
    private TextView createAccount;
    private EditText userNameET, userPasswordET;
    String userName, userPassword, nickName;
    JSONParser parser = new JSONParser();
    private static final String KEY_STATUS= "status";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "userPassword";
    private static final String KEY_MESSAGE = "message";
    private static String userID = null;
    private static String link = "http://www.limbukuldip.com/authenticateUser.php";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        userNameET = (EditText) findViewById(R.id.userNameEditText);
        userPasswordET = (EditText) findViewById(R.id.userPasswordEditText);

        userName = userNameET.getText().toString();
        nickName = userPasswordET.getText().toString();
        userPassword = userPasswordET.getText().toString();

        loginIn = (Button) findViewById(R.id.loginButton);
        loginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchUserDetailsAsyncTask().execute();
            }
        });

        createAccount = (TextView) findViewById(R.id.createAccount);
        createAccount.setTextColor(Color.BLUE);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(createAccountIntent);
            }
        });
    }

    private class FetchUserDetailsAsyncTask extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute(){
            userName = userNameET.getText().toString();
        }
        @Override
        protected String doInBackground(String... params){
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(link, "GET", null);

            try{
                int success = jsonObject.getInt(KEY_SUCCESS);
                if(success == 1){
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userID", userName);
                    editor.putString("NickName", userName);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), IdentifyMe.class);
                    startActivity(intent);
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    /*private void login(){

        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", userName);
        params.put("userPassword", userPassword);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userID", userNameET.getText().toString());
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), IdentifyMe.class);
        startActivity(intent);

        CustomRequest customRequest = new CustomRequest(Request.Method.POST, link, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        Intent intent = new Intent(getApplicationContext(), IdentifyMe.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login UnSuccessful", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(customRequest);
    }*/
}
