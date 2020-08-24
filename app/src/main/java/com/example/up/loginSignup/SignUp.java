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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private EditText et_userName, editTextEmail, editTextPassword, et_PhoneNo;
    private Button btn_signUp;
    private TextView tv_login;
    private long maxId, userID;
    private ProgressBar progressBarR;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_userName = findViewById(R.id.et_fullName_signUp);
        editTextEmail = findViewById(R.id.et_email_signUp);
        editTextPassword = findViewById(R.id.et_password_signUp);
        et_PhoneNo = findViewById(R.id.et_phoneNo_signUp);
        progressBarR = findViewById(R.id.progressbarR);
        progressBarR.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        tv_login = findViewById(R.id.tv_login);
        btn_signUp = findViewById(R.id.btn_register_signUp);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerUser())
                    startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean registerUser() {

        boolean passStatus = true;

        final String fullName = et_userName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phoneNo = et_PhoneNo.getText().toString().trim();
        final String userType = "0";
        final String userId = mDatabaseRef.push().getKey();

        if (fullName.isEmpty()) {
            et_userName.setError(getString(R.string.input_error_email));
            et_userName.requestFocus();

            passStatus = false;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();

            passStatus = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();

            passStatus = false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();

            passStatus = false;
        }
        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();

            passStatus = false;
        }

        if (phoneNo.isEmpty()) {
            et_PhoneNo.setError(getString(R.string.input_error_password));
            et_PhoneNo.requestFocus();

            passStatus = false;
        }

        if(passStatus)
        {
            progressBarR.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                UserManager userManager = new UserManager(fullName, email, password, phoneNo, userId, userType);

                                FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).setValue(userManager)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBarR.setVisibility(View.GONE);

                                        if (task.isSuccessful()) {
                                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(SignUp.this, "Registration complete, please verify account at your email", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        return passStatus;
    }
}