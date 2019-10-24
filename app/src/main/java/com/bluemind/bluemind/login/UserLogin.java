package com.bluemind.bluemind.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluemind.bluemind.identify_me.IdentifyMe;
import com.bluemind.bluemind.MainActivity;
import com.bluemind.bluemind.MySingleton;
import com.bluemind.bluemind.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLogin extends AppCompatActivity {

    private Button loginIn, createAccount;
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
    private static String link_identifyMe = "http://www.limbukuldip.com/checkUserName.php";
    private static final String LOGGEDIN = "loggedIn";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean hasLoggedIn = preferences.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

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
                /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);*/
            }
        });

        createAccount = (Button) findViewById(R.id.createAccount);
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
        nickName = userNameET.getText().toString();
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
                        editor.putString("userId", userName);
                        editor.putString("nickName", userName);
                        editor.putString("userEmail", response.getString("email"));
                        editor.putString("UserId", response.getString("userName"));
                        editor.apply();

                        SharedPreferences.Editor editor1 = getSharedPreferences("sendbird", MODE_PRIVATE).edit();
                        editor1.putString("userId", userName);
                        editor1.putString("nickName", userName);
                        editor1.apply();

                        SharedPreferences.Editor editor_second = preferences.edit();
                        editor_second.putBoolean("hasLoggedIn", true);
                        editor_second.commit();

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
