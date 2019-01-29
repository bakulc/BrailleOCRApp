package com.example.brailleocrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomTextSend extends AppCompatActivity {


    Button sendButton, clearButton;
    EditText enterText;
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
    }

    public void sendMessageWiFi() {
        String message = enterText.getText().toString();

    }

    public void clearText(EditText editText) {
        editText.setText("");
    }
}
