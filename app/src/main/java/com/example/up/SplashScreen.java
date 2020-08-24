package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.up.loginSignup.LogIn;
import com.example.up.loginSignup.SignUp;
import com.example.up.loginSignup.UserManager;
import com.example.up.navigation.MenuNavActivity;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class SplashScreen extends AppCompatActivity {

    TextView appsStatus;
    String userType = "-1";
    ProgressBar progressBar;
    public static final String EXTRA_USERTYPEss = "userType";
    final FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        appsStatus = findViewById(R.id.tv_appsStatus_ss);
        progressBar = findViewById(R.id.progressBarss);

        new CountDownTimer(1500, 1000) {
            public void onTick(long millisUntilFinished) {
                appsStatus.setText("Checking internet & database connection...");
            }

            public void onFinish() {
                if(isWorkingInternetPersent()) {

                    new CountDownTimer(1500, 1000) {

                        public void onTick(long millisUntilFinished) {
                            appsStatus.setText("Identifying user...");
                        }

                        public void onFinish() {
                            if(mUser != null) {

                                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Users");
                                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.hasChild(mUser.getUid())) {

                                            DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                                            mDatabaseRef.child(mUser.getUid()).child("userType").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    userType = dataSnapshot.getValue(String.class);

                                                    new CountDownTimer(1500, 1000) {
                                                        public void onTick(long millisUntilFinished) {
                                                            appsStatus.setText(mUser.getEmail());
                                                        }

                                                        public void onFinish() {
                                                            appsStatus.setText("Navigate to MASTA APPS Main Screen");
                                                            Intent intent = new Intent(SplashScreen.this, MenuNavActivity.class);
                                                            intent.putExtra(SplashScreen.EXTRA_USERTYPEss, userType);
                                                            startActivity(intent);
                                                        }
                                                    }.start();
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Toast.makeText(SplashScreen.this, "Database Error", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                        else {
                                            appsStatus.setText("Navigate to MASTA APPS Login Screen");
                                            Intent intent = new Intent(SplashScreen.this, LogIn.class);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        appsStatus.setText("Database Error");
                                    }
                                });
                            }
                            else {
                                appsStatus.setText("Navigate to MASTA APPS Login Screen");
                                Intent intent = new Intent(SplashScreen.this, LogIn.class);
                                startActivity(intent);
                            }
                        }
                    }.start();
                }
                else
                    appsStatus.setText("Internet & database are NOT CONNECTED");
            }
        }.start();
    }

    public boolean isWorkingInternetPersent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
}
