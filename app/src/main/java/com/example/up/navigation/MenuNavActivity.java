package com.example.up.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.up.R;
import com.example.up.SplashScreen;
import com.example.up.loginSignup.LogIn;
import com.example.up.loginSignup.UserManager;
import com.example.up.navigation.menuNav.home.HomeFragment;
import com.example.up.navigation.menuNav.profile.ProfileFragment;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MenuNavActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int userTypeMASTA = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nav);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView navView = findViewById(R.id.nav_view);

        navView.getMenu().findItem(R.id.action_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MenuNavActivity.this, LogIn.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                return true;
            }
        });

        Intent intent = getIntent();
        int userTypeMASTAss = -1;
        userTypeMASTAss = Integer.valueOf(intent.getStringExtra(SplashScreen.EXTRA_USERTYPEss));
        int userTypeMASTAl = -1;
        userTypeMASTAl = Integer.valueOf(intent.getStringExtra(LogIn.EXTRA_USERTYPEl));

//        Toast.makeText(MenuNavActivity.this, userTypeMASTAss + " " + userTypeMASTAl, Toast.LENGTH_SHORT).show();

        if(userTypeMASTAss >= userTypeMASTAl)
            userTypeMASTA = userTypeMASTAss;
        else
            userTypeMASTA = userTypeMASTAl;

        if(userTypeMASTA == 1) {
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_upload).setVisible(true);
        }
        if(userTypeMASTA == 0)  {
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_upload).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
