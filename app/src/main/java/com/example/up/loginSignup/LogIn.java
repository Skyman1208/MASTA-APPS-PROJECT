package com.example.up.loginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class LogIn extends AppCompatActivity {

    EditText userEmail,userPass;
    //TextView userForgotPass;
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
        //userForgotPass = findViewById(R.id.text_view_forget_password);
        progressBar = findViewById(R.id.progressbar);
        userLogin = findViewById(R.id.btn_log_in);
        tv_register = findViewById(R.id.tv_register);
        firebaseAuth = FirebaseAuth.getInstance();

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEditTextField())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                        userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        startActivity(new Intent(LogIn.this, MenuNavActivity.class));
                                    }
                                    else {
                                        Toast.makeText(LogIn.this, "Please verify your email address",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(LogIn.this, task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            }
        });

        /*userForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LogIn.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LogIn.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });*/
    }

    private boolean checkEditTextField()
    {
        boolean passStatus = true;

        if (userEmail.getText().toString().isEmpty()) {
            userEmail.setError(getString(R.string.input_error_email));
            userEmail.requestFocus();

            passStatus = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString().trim()).matches()) {
            userEmail.setError(getString(R.string.input_error_email_invalid));
            userEmail.requestFocus();

            passStatus = false;
        }

        if (userPass.getText().toString().isEmpty()) {
            userPass.setError(getString(R.string.input_error_password));
            userPass.requestFocus();

            passStatus = false;
        }

        if (userPass.getText().toString().length() < 6) {
            userPass.setError(getString(R.string.input_error_password_length));
            userPass.requestFocus();

            passStatus = false;
        }

        return passStatus;
    }
}
