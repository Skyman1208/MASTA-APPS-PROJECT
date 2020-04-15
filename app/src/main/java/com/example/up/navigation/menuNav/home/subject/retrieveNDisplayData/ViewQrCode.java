package com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.up.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ViewQrCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data_view_qrcode);

        Intent intent = getIntent();
        String link = intent.getStringExtra(ImagesActivity.EXTRA_URL);
        String name = intent.getStringExtra(ImagesActivity.EXTRA_NAME);

        ImageView imageView = findViewById(R.id.iv_displayQrCode);
        TextView textViewName = findViewById(R.id.detailTitle);

        if(!link.equals("")) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try{
                BitMatrix bitMatrix = multiFormatWriter.encode(link, BarcodeFormat.QR_CODE,
                        2500, 2500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                imageView.setImageBitmap(bitmap);
                textViewName.setText(name);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
