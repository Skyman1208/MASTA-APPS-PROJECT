package com.example.up.navigation.menuNav.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.loginSignup.LogIn;
import com.example.up.loginSignup.UserManager;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private DatabaseReference reff;

    public List<UserManager> mCheckUserManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        CircleImageView circle_image_view = root.findViewById(R.id.image_view);
        EditText et_userName_profile = root.findViewById(R.id.et_name_profile);
        EditText editTextEmail_profile = root.findViewById(R.id.et_email_profile);
        EditText editTextPassword_profile = root.findViewById(R.id.et_password_profile);
        EditText et_PhoneNo_profile = root.findViewById(R.id.et_phoneNo_profile);
        Button btn_save_changes = root.findViewById(R.id.button_save);

        /*mCheckUserManager = new ArrayList<>();
        Intent intentFromHome = getActivity().getIntent();
        final String currEmail = intentFromHome.getStringExtra(LogIn.EXTRA_EMAIL);*/

        /*reff = FirebaseDatabase.getInstance().getReference().child("Users");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCheckUserManager.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserManager checkUserManager = postSnapshot.getValue(UserManager.class);
                    checkUserManager.setuKey(postSnapshot.getKey());
                    mCheckUserManager.add(checkUserManager);
                }

                for (UserManager item : mCheckUserManager) {
                    if (item.getEmail() == currEmail) {
                        String fullName = dataSnapshot.child("userName").getValue().toString();
                        String userEmail = dataSnapshot.child("email").getValue().toString();
                        String userPassword = dataSnapshot.child("userPassword").getValue().toString();
                        String userPhoneNo = dataSnapshot.child("userPhoneNo").getValue().toString();

                        et_userName_profile.setText(fullName);
                        editTextEmail_profile.setText(userEmail);
                        editTextPassword_profile.setText(userPassword);
                        et_PhoneNo_profile.setText(userPhoneNo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return root;
    }
}
