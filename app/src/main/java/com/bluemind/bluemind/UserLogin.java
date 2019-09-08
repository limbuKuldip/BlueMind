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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.sendbird.android.ConnectionManager;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
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
    private static final String KEY_STATUS= "status";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "userPassword";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_EMPTY = "";
    private static String userID = null;
    private static String link = "http://www.limbukuldip.com/login.php";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        userNameET = (EditText) findViewById(R.id.userNameEditText);
        userPasswordET = (EditText) findViewById(R.id.userPasswordEditText);

        loginIn = (Button) findViewById(R.id.loginButton);
        loginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if(validateInputs()){
                    login();
                }
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

    public void getData(){
        userName = userNameET.getText().toString();
        nickName = userPasswordET.getText().toString();
        userPassword = userPasswordET.getText().toString();
    }

    private void login(){
        final JSONObject request = new JSONObject();
        try{
            request.put(KEY_USERNAME, userName);
            request.put(KEY_PASSWORD, userPassword);
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getInt(KEY_STATUS) == 0){
                        response.getString("email");
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userID", userName);
                        editor.putString("userEmail", response.getString("email"));
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), IdentifyMe.class);
                        startActivity(intent);
                    } else{
                        Toast.makeText(getApplicationContext(), response.getString(KEY_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private Boolean validateInputs(){
        if(KEY_EMPTY.equals(userName)){
            userNameET.setError("Username cannot be empty");
        }
        if(KEY_EMPTY.equals(userPassword)){
            userPasswordET.setError("Password cannot be empty");
        }
        return true;
    }
}
