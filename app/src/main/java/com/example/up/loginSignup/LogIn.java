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
import com.example.up.SplashScreen;
import com.example.up.navigation.MenuNavActivity;
import com.example.up.navigation.menuNav.home.HomeFragment;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LogIn extends AppCompatActivity {

    EditText userEmail,userPass;
    Button userLogin;
    ProgressBar progressBar;
    TextView tv_register;
    String userTypeMASTA = "-1";
    public static final String EXTRA_USERTYPEl = "userType";

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
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(), userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    final FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                        String dbPath = "Users/" + mUser.getUid() + "/userType";
                                        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference(dbPath);
                                        mDatabaseRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                userTypeMASTA = dataSnapshot.getValue(String.class);

                                                Intent intent = new Intent(LogIn.this, MenuNavActivity.class);
                                                intent.putExtra(LogIn.EXTRA_USERTYPEl, String.valueOf(userTypeMASTA));
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Toast.makeText(LogIn.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        Intent intent = new Intent(LogIn.this, MenuNavActivity.class);
                                        intent.putExtra(LogIn.EXTRA_USERTYPEl, String.valueOf(userTypeMASTA));
                                        startActivity(intent);
                                    }
                                    else{
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
        });
    }

    @Override
    public void onBackPressed() {

    }
}