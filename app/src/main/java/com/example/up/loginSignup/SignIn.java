package com.example.up.loginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.up.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail;
    EditText editTextPassword;
    TextView tv_login;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = findViewById(R.id.et_email_signUp);
        editTextPassword = findViewById(R.id.et_password_signUp);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        tv_login = findViewById(R.id.tv_login);
        findViewById(R.id.btn_register_signUp).setOnClickListener(this);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, Login.class));
            }
        });
    }



        private void registerUser() {
            final String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();



            if (email.isEmpty()) {
                editTextEmail.setError(getString(R.string.input_error_email));
                editTextEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError(getString(R.string.input_error_email_invalid));
                editTextEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError(getString(R.string.input_error_password));
                editTextPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                editTextPassword.setError(getString(R.string.input_error_password_length));
                editTextPassword.requestFocus();
                return;
            }




            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                User user = new User(
                                        email

                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(SignIn.this, "Registration complete, please verify account at your email", Toast.LENGTH_LONG).show();
                                                    }else{
                                                        Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });


                                        } else {
                                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

         @Override
         public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_register_signUp:
                    registerUser();
                    break;

                }
        }


}
