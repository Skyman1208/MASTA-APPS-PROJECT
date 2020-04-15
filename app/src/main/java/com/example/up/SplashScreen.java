package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.up.loginSignup.LogIn;
import com.example.up.navigation.MenuNavActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser mUser = mAuth.getCurrentUser();
                if(mUser != null)
                    startActivity(new Intent(SplashScreen.this, MenuNavActivity.class));
                else
                    startActivity(new Intent(SplashScreen.this, LogIn.class));
                finish();
            }
        }, 1000);
    }
}
