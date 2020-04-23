package com.example.up.navigation.menuNav.uploadData;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.colourAnimation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UploadFragment extends Fragment {

    RelativeLayout mLayout;
    private Spinner s_Subjects;
    private Button mButtonUpload;
    private EditText mTittleName;
    private EditText mLink;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Bitmap bitmap;
    private long maxId;
    private DatabaseReference mDatabaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload, container, false);

        mButtonUpload = root.findViewById(R.id.btn_upload);
        mTittleName = root.findViewById(R.id.et_tittle);
        mLink = root.findViewById(R.id.et_link);
        mImageView = root.findViewById(R.id.iv_upload_image);
        mProgressBar = root.findViewById(R.id.pb_upload_image);
        s_Subjects = root.findViewById(R.id.s_subjects);

        mLayout = root.findViewById(R.id.bg_upload_details);
        new colourAnimation(mLayout);

        String [] MASTA_Subjects = {"-", "Physics", "Chemistry", "Biology", "Additional Mathematics",
                "Mathematics (PRIMARY)", "Mathematics (SECONDARY)", "Science (PRIMARY)", "Science (SECONDARY)",
                "Rekabentuk Teknologi (PRIMARY)", "Rekabentuk Teknologi (SECONDARY)", "Teknologi Maklumat dan Komunikasi",
                "Asas Sains Komputer", "Sains Komputer"};
        final List<String> MASTA_Subjects_List = new ArrayList<>(Arrays.asList(MASTA_Subjects));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item, MASTA_Subjects_List) {

            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                    return false;
                else
                    return true;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextSize(15);
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                    tv.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                }
                return view;
            }
        };
        s_Subjects.setAdapter(adapter);
        s_Subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maxId = 0;
                if(position >= 0){
                    TextView textView = (TextView)parent.getChildAt(0);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    // Notify the selected item text
                    //Toast.makeText(getActivity(), "Selected : " + selectedSubject, Toast.LENGTH_SHORT).show();
                }
                if (position == 1){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Physics");
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
                if (position == 2){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Chemistry");
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
                if (position == 3){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Biology");
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
                if (position == 4){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/AddMath");
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
                if (position == 5){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Math_P");
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
                if (position == 6){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Math_S");
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
                if (position == 7){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Science_P");
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
                if (position == 8){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/Science_S");
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
                if (position == 9){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/RekaBentukTeknologi_P");
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
                if (position == 10){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/RekaBentukTeknologi_S");
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
                if (position == 11){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/TeknologiMaklumatKomunikasi");
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
                if (position == 12){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/AsasSainsKomputer");
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
                if (position == 13){
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads/Subjects/SainsKomputer");
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String linkText = mLink.getText().toString();

                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try{
                        if(linkText.isEmpty()) {
                            mProgressBar.setVisibility(View.GONE);
                            mImageView.setImageResource(R.drawable.qrcode);
                        }
                        else
                        {
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            BitMatrix bitMatrix = multiFormatWriter.encode(linkText, BarcodeFormat.QR_CODE,
                                    2500, 2500);
                            bitmap = barcodeEncoder.createBitmap(bitMatrix);
                            mImageView.setImageBitmap(bitmap);
                        }

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                if (s_Subjects.getSelectedItemId() != 0 && mTittleName.getText().toString() != "" && mLink.getText().toString() != "") {
                    if (Patterns.WEB_URL.matcher(mLink.getText().toString()).matches()) {
                        UploadModel uploadModel = new UploadModel(mTittleName.getText().toString().trim(), mLink.getText().toString().trim());
                        String uploadId = String.valueOf(maxId + 1);
                        mDatabaseRef.child(uploadId).setValue(uploadModel);

                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_LONG).show();

                        mTittleName.setText("");
                        mLink.setText("");
                        mImageView.setImageResource(R.drawable.qrcode);
                        s_Subjects.setSelection(0);
                    }
                    else
                        Toast.makeText(getActivity(), "URL is invalid!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getActivity(), "Please fill all the blanks.", Toast.LENGTH_SHORT).show();

                mProgressBar.setVisibility(View.GONE);
            }
        });

        return root;
    }
}