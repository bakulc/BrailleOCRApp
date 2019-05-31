package com.example.brailleocrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CustomTextSend extends AppCompatActivity {


    Button sendButton, clearButton;
    EditText enterText;

    private static final int SERVERPORT = 80;
    private static final String SERVER_IP = "192.168.4.1";

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_send);

        sendButton  = findViewById(R.id.sendButton);
        clearButton = findViewById(R.id.clearButton);
        enterText = findViewById(R.id.enterText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageWiFi();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText(enterText);
            }
        });

        requestQueue = Volley.newRequestQueue(this);

    }



    public void sendMessageWiFi() {
        String url = "http://"+SERVER_IP+":"+SERVERPORT+"/"+enterText.getText();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });

        requestQueue.add(stringRequest);
    }

    public void clearText(EditText editText) {
        editText.setText("");
    }

}
