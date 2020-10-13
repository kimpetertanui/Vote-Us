package com.dekut.voteme.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dekut.voteme.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FeedbackActivity extends AppCompatActivity{
    Button feedbackButton;
    EditText feedbackEditText;
    private DatabaseReference mDatabase;
    private SharedPreferences sharedPreferences;
    private String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        initViews();

        registEvents();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void registEvents() {
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feed=feedbackEditText.getText().toString();
                sharedPreferences = getSharedPreferences("mypre", Context.MODE_PRIVATE);
                userId = sharedPreferences.getString("TOKEN", "");
                mDatabase.child("userInfo").child(userId).child("feedback").setValue(feed);
                finish();

            }
        });
    }

    private void initViews() {
        feedbackButton= (Button) findViewById(R.id.feedbackButton);
        feedbackEditText= (EditText) findViewById(R.id.feedbackEditText);
    }
}