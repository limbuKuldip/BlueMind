package com.bluemind.bluemind;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class CreateAccount extends AppCompatActivity {

    private static String link = "http://www.limbukuldip.com/createAccount.php";
    private EditText firstNameEditText, lastNameEditText, userNameEditText, userPasswordEditText, userConfirmPasswordET, emailEditext, phoneNumberEditText;
    String firstName, lastName, userName, userPassword, userConfirmPassword, email, phoneNumber;
    private Button createButton;
    private TextView confirmMessage;
    private static final String KEY_STATUS = "status";
    private static final String KEY_EMPTY = "";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "userPassword";
    private static final String KEY_CONFIRMPASSWORD = "confirmPassword";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONENUMBER = "phoneNumber";
    private static final String KEY_MESSAGE = "message";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
        userNameEditText = (EditText) findViewById(R.id.userName);
        userPasswordEditText = (EditText) findViewById(R.id.userPassword);
        userConfirmPasswordET = (EditText) findViewById(R.id.confirmPassword);
        confirmMessage = (TextView) findViewById(R.id.passwordConfirmMessage);
        confirmMessage.setVisibility(View.GONE);
        emailEditext = (EditText) findViewById(R.id.email);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);

        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if(validateInputs()){
                    register();
                }
                //InsertData(userID, firstName, lastName, userName, userPassword, email, phoneNumber);
            }
        });
    }

    public void getData(){
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        userName = userNameEditText.getText().toString();
        userPassword = userPasswordEditText.getText().toString();
        userConfirmPassword = userConfirmPasswordET.getText().toString();
        email = emailEditext.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();
    }

    private Boolean validateInputs(){
        if(KEY_EMPTY.equals(firstName)){
            firstNameEditText.setError("First name cannot be empty");
            return false;
        }
        if(KEY_EMPTY.equals(lastName)){
            lastNameEditText.setError("Last name cannot be empty");
            return false;
        }
        if(KEY_EMPTY.equals(userName)) {
            userNameEditText.setError("Username cannot be empty");
            return false;
        }
        if(KEY_EMPTY.equals(userPassword)){
            userPasswordEditText.setError("User Password cannot be empty ");
            return false;
        }
        if(KEY_EMPTY.equals(userConfirmPassword)){
            userConfirmPasswordET.setError("Please confirm password");
            return false;
        }
        if(KEY_EMPTY.equals(email)){
            emailEditext.setError("Email cannot be empty");
            return false;
        }
        if(KEY_EMPTY.equals(phoneNumber)){
            phoneNumberEditText.setError("Phone number cannot be empty");
            return false;
        }
        if(!userPassword.equals(userConfirmPassword)){
            confirmMessage.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private void register(){
        final JSONObject request = new JSONObject();
        try{
            request.put(KEY_FIRSTNAME, firstName);
            request.put(KEY_LASTNAME, lastName);
            request.put(KEY_USERNAME, userName);
            request.put(KEY_PASSWORD, userPassword);
            request.put(KEY_EMAIL, email);
            request.put(KEY_PHONENUMBER, phoneNumber);
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, link, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt(KEY_STATUS) == 0) {
                        Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                        startActivity(intent);
                    } else if (response.getInt(KEY_STATUS) == 1) {
                        userNameEditText.setError("UserName already taken");
                        userNameEditText.requestFocus();
                    } else if(response.getInt(KEY_STATUS)== 3){
                        Toast.makeText(getApplicationContext(), response.getString(KEY_MESSAGE), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.getString(KEY_MESSAGE), Toast.LENGTH_LONG).show();
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

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void InsertData(final String firstName, final String lastName, final String userName, final String userPassword, final String email, final String phoneNumber){
        class writeData extends AsyncTask<String, Void, String>{
            @Override
            protected String doInBackground(String... params){
                String fName = firstName;
                String lName = lastName;
                String uName = userName;
                String uPassword = userPassword;
                String uemail = email;
                String pNumber = phoneNumber;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("firstName", fName));
                nameValuePairs.add(new BasicNameValuePair("lastName", lName));
                nameValuePairs.add(new BasicNameValuePair("userName", uName));
                nameValuePairs.add(new BasicNameValuePair("userPassword", uPassword));
                nameValuePairs.add(new BasicNameValuePair("email", uemail));
                nameValuePairs.add(new BasicNameValuePair("phoneNumber", pNumber));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(link);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                }
            }
        }
        writeData writeData = new writeData();
        writeData.execute(firstName, lastName, userName, userPassword, email, phoneNumber);
    }
}
