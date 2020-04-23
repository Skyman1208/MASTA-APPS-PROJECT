package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.example.up.loginSignup.LogIn;
import com.example.up.navigation.MenuNavActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mLayout = findViewById(R.id.bg_splash_screen);
        new colourAnimation(mLayout);

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
        }, 5000);
    }
}
