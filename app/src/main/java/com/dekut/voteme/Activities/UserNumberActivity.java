package com.dekut.voteme.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.dekut.voteme.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;



public class UserNumberActivity extends AppCompatActivity {
    Button auth_button_register;
    private static final String TWITTER_KEY = "22KCx7yRsZ3ZdDHONO0LQcAMC";
    private static final String TWITTER_SECRET = "f3Pe45jWME88Yn2fZ6XKHMLr6ctPxVlXtHzY8yXhyk5zGDa0pZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_number);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        auth_button_register= (Button) findViewById(R.id.auth_button_register);
        final DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);

        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                SharedPreferences sharedPreferences=getSharedPreferences("mypre", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("phone",phoneNumber);
                editor.commit();
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });
        auth_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitsButton.performClick();
            }
        });

    }
}
