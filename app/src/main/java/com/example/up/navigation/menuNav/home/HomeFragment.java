package com.example.up.navigation.menuNav.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.up.R;
import com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData.ImagesActivity;

public class HomeFragment extends Fragment {

    private CardView biology, chemistry, physics, addMath, mathP, mathS, sciP, sciS, rbtP, rbtS, AsasSainsKomp, SainsKomp, teknoMakluKomuni;
    public static final String EXTRA_SUBJECT = "subject";
    RelativeLayout mLayout;
    AnimationDrawable animDrawable;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        biology = root.findViewById(R.id.biology);
        chemistry = root.findViewById(R.id.chemistry);
        physics = root.findViewById(R.id.physics);
        addMath = root.findViewById(R.id.addmath);
        mathP = root.findViewById(R.id.math_pri);
        mathS = root.findViewById(R.id.math_sec);
        sciP = root.findViewById(R.id.science_pri);
        sciS = root.findViewById(R.id.science_sec);
        rbtP = root.findViewById(R.id.rbt_pri);
        rbtS = root.findViewById(R.id.rbt_sec);
        AsasSainsKomp = root.findViewById(R.id.asaskomputrersains);
        SainsKomp = root.findViewById(R.id.komputersains);
        teknoMakluKomuni = root.findViewById(R.id.tmk);

        mLayout = root.findViewById(R.id.bg);
        animDrawable = (AnimationDrawable) mLayout.getBackground();
        animDrawable.setEnterFadeDuration(5);
        animDrawable.setExitFadeDuration(2000);
        animDrawable.start();

        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Biology");
                startActivity(intent);
            }
        });

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Chemistry");
                startActivity(intent);
            }
        });

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Physics");
                startActivity(intent);
            }
        });

        addMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "AddMath");
                startActivity(intent);
            }
        });
        mathP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Math_P");
                startActivity(intent);
            }
        });
        mathS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Math_S");
                startActivity(intent);
            }
        });
        sciP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Science_P");
                startActivity(intent);
            }
        });
        sciS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "Science_S");
                startActivity(intent);
            }
        });
        rbtP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "RekaBentukTeknologi_P");
                startActivity(intent);
            }
        });
        rbtS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "RekaBentukTeknologi_S");
                startActivity(intent);
            }
        });
        AsasSainsKomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "AsasSainsKomputer");
                startActivity(intent);
            }
        });
        SainsKomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "SainsKomputer");
                startActivity(intent);
            }
        });
        teknoMakluKomuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(HomeFragment.EXTRA_SUBJECT, "TeknologiMaklumatKomunikasi");
                startActivity(intent);
            }
        });

        return root;
    }
}
