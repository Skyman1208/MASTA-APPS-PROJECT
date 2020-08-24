package com.example.up.navigation.menuNav.profile;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.loginSignup.LogIn;
import com.example.up.loginSignup.UserManager;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.provider.OpenableColumns.DISPLAY_NAME;
import static com.example.up.loginSignup.LogIn.currUserType;


public class ProfileFragment extends Fragment {

    private DatabaseReference reff;
    private EditText et_userName_profile;
    private EditText editTextEmail_profile;
    private EditText editTextPassword_profile;
    private EditText et_PhoneNo_profile;
    private Button btn_save_changes;
    private FirebaseAuth firebaseAuth;
    private CircleImageView image_view_profile;
    private static final int PICK_IMAGE =1;
    public List<UserManager> mCheckUserManager;
    private StorageReference storageReference;
    Uri imageUri;
    String DISPLAY_NAME = null;
    String thisUserType = currUserType;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        et_userName_profile = root.findViewById(R.id.et_name_profile);
        editTextEmail_profile = root.findViewById(R.id.et_email_profile);
        editTextPassword_profile = root.findViewById(R.id.et_password_profile);
        et_PhoneNo_profile = root.findViewById(R.id.et_phoneNo_profile);
        btn_save_changes = root.findViewById(R.id.button_save);
        image_view_profile = root.findViewById(R.id.image_view);

        image_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select picture"), PICK_IMAGE);
            }
        });

        mCheckUserManager = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();


        final String curremail = firebaseAuth.getCurrentUser().getEmail();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reff = FirebaseDatabase.getInstance().getReference().child("Users");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCheckUserManager.clear();
                Context c = getActivity().getApplicationContext();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(curremail)){
                        editTextEmail_profile.setText(curremail);
                        et_userName_profile.setText(ds.child("userName").getValue(String.class));
                        et_PhoneNo_profile.setText(ds.child("userPhoneNo").getValue(String.class));
                        editTextPassword_profile.setText(ds.child("userPassword").getValue(String.class));

                    }
                    if(user.getPhotoUrl() !=null){
                        Picasso.with(c).load(user.getPhotoUrl()).into(image_view_profile);
                    }
                }

//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                String userName = et_userName_profile.getText().toString().trim();
                String email = editTextEmail_profile.getText().toString().trim();
                String userPhoneNo = et_PhoneNo_profile.getText().toString().trim();
                String userPassword = editTextPassword_profile.getText().toString().trim();


                updateProfile(userId, userName, email, userPhoneNo, userPassword, thisUserType);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            Context c = getActivity().getApplicationContext();
            try {

                Picasso.with(c).load(imageUri).into(image_view_profile);
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    // UploadImage method
    private void uploadImage() {
        String uid = FirebaseAuth.getInstance().getUid();
        final Context c = getActivity().getApplicationContext();
        if (imageUri != null) {


            // Defining the child of storageReference
            final StorageReference ref = FirebaseStorage.getInstance().getReference().child("profileImages").child(uid + ".jpeg");
            // adding listeners on upload
            // or failure of image
            ref.putFile(imageUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    getDownloadUrl(ref);
                                    Toast.makeText(getActivity(), "Image Updated!", Toast.LENGTH_SHORT).show();

                                }
                            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded

                            Toast
                                    .makeText(getActivity(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());

                                }
                            });
        }
    }
    private void getDownloadUrl(StorageReference ref){
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d(TAG, "onSuccess: " + uri);
                setUserProfileUrl(uri);
            }
        });
    }
    private void setUserProfileUrl(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Updated succesfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean updateProfile (String userId, String userName, String email, String userPhoneNo, String userPassword, String userType){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        UserManager userManager = new UserManager(userName, email,userPassword, userPhoneNo, userId, userType);
        dR.setValue(userManager);
        Toast.makeText(getContext(),"Info Updated", Toast.LENGTH_LONG).show();
        return true;
    }

}