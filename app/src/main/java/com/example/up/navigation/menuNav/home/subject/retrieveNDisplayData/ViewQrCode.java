package com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.up.R;
import com.squareup.picasso.Picasso;

public class ViewQrCode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data_view_qrcode);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(ImagesActivity.EXTRA_URL);
        String name = intent.getStringExtra(ImagesActivity.EXTRA_NAME);

        ImageView imageView = findViewById(R.id.imageView2);
        TextView textViewName = findViewById(R.id.detailTitle);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewName.setText(name);
    }
}
