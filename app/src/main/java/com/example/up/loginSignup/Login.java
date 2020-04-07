package com.example.up.loginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.up.R;
import com.example.up.navigation.MenuNavActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText userEmail,userPass;
    Button userLogin;
    ProgressBar progressBar;
    TextView tv_register;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userEmail = findViewById(R.id.et_email_login);
        userPass = findViewById(R.id.et_password_login);
        progressBar = findViewById(R.id.progressbar);
        userLogin = findViewById(R.id.btn_log_in);
        tv_register = findViewById(R.id.tv_register);
        firebaseAuth = FirebaseAuth.getInstance();

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignIn.class));
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                        userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        startActivity(new Intent(Login.this, MenuNavActivity.class));
                                    }else{
                                        Toast.makeText(Login.this, "Please verify your email address",
                                                Toast.LENGTH_LONG).show();
                                    }

                                }else{
                                    Toast.makeText(Login.this, task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
