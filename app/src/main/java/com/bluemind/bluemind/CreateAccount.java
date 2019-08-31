package com.bluemind.bluemind;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class CreateAccount extends AppCompatActivity {

    private static String link = "http://www.limbukuldip.com/createAccount.php";
    private EditText firstNameEditText, lastNameEditText, userNameEditText, userPasswordEditText, emailEditext, phoneNumberEditText;
    String userID, firstName, lastName, userName, userPassword, email, phoneNumber;
    private Button createButton;
    JSONParser parser = new JSONParser();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
        userNameEditText = (EditText) findViewById(R.id.userName);
        userPasswordEditText = (EditText) findViewById(R.id.userPassword);
        emailEditext = (EditText) findViewById(R.id.email);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);

        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getData();
                //InsertData(userID, firstName, lastName, userName, userPassword, email, phoneNumber);
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
            }
        });
    }

    public void getData(){
        userID = userNameEditText.getText().toString();
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        userName = userNameEditText.getText().toString();
        userPassword = userPasswordEditText.getText().toString();
        email = emailEditext.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();
    }

    public void InsertData(final String userID, final String firstName, final String lastName, final String userName, final String userPassword, final String email, final String phoneNumber){
        class writeData extends AsyncTask<String, Void, String>{
            @Override
            protected String doInBackground(String... params){
                String uID = userID;
                String fName = firstName;
                String lName = lastName;
                String uName = userName;
                String uPassword = userPassword;
                String uemail = email;
                String pNumber = phoneNumber;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("userID", uID));
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
        writeData.execute(userID, firstName, lastName, userName, userPassword, email, phoneNumber);
    }
}
