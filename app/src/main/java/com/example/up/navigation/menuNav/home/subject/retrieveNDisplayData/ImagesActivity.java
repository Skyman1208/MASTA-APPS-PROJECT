package com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.up.R;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";

    public RecyclerView mRecyclerView;
    public ImageAdapter mAdapter;
    public ProgressBar mProgressCircle;

    public FirebaseStorage mStorage;
    public DatabaseReference mDatabaseRef;
    public ValueEventListener mDBListener;

    public List<UploadModel> mUploadModels;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        EditText editText = findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { filter(s.toString()); }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploadModels = new ArrayList<>();
        mAdapter = new ImageAdapter(ImagesActivity.this, mUploadModels);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ImagesActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploadModels.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadModel uploadModel = postSnapshot.getValue(UploadModel.class);
                    uploadModel.setKey(postSnapshot.getKey());
                    mUploadModels.add(uploadModel);
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void filter(String text) {
        ArrayList<UploadModel> filteredlist = new ArrayList<>();

        for (UploadModel item : mUploadModels) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        mAdapter.filterList(filteredlist);
    }

    @Override
    public void onItemClick(int position) {
        UploadModel selectedItem = mUploadModels.get(position);
        final String selectedLink = selectedItem.getLink();
        if (selectedLink != "" || selectedLink != null) {
            Uri uri = Uri.parse(selectedLink); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "URL Link doesn't exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goButtonClicked(int position) { }

    @Override
    public void onWhatEverClick(int position) { Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show(); }

    @Override
    public void onDeleteClick(int position) {
        final UploadModel selectedItem = mUploadModels.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ImagesActivity.this, selectedItem.getName() + " is deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}