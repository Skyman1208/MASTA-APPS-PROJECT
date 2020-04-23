package com.example.up.navigation.menuNav.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.colourAnimation;

public class AboutFragment extends Fragment {

    RelativeLayout mLayout;
    private TextView tv_about;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);

        mLayout = root.findViewById(R.id.bg_about);
        new colourAnimation(mLayout);

        tv_about = root.findViewById(R.id.txt_about);
        tv_about.setText(R.string.about_apps);

        return root;
    }
}
