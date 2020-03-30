package com.example.up.navigation.menuNav.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;

public class HomeFragment extends Fragment {

    private CardView biology, chemistry, physics;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        biology = root.findViewById(R.id.biology);
        chemistry = root.findViewById(R.id.chemistry);
        physics = root.findViewById(R.id.physics);

        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBiology = new Intent(getActivity(), ImagesActivity.class);
                startActivity(goToBiology);
            }
        });

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChemistry = new Intent(getActivity(), ImagesActivity.class);
                startActivity(goToChemistry);
            }
        });

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPhysics = new Intent(getActivity(), ImagesActivity.class);
                startActivity(goToPhysics);
            }
        });

        return root;
    }
}
