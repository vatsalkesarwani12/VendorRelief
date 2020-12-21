package com.CodeNaroNa.vendor.relief.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.CodeNaroNa.vendor.relief.CoronaSpreadDataFragment;
import com.CodeNaroNa.vendor.relief.FragmentsKotlin.HomeFragment;
import com.CodeNaroNa.vendor.relief.FragmentsKotlin.PrecautionFragment;
import com.CodeNaroNa.vendor.relief.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                new HomeFragment()).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new com.CodeNaroNa.vendor.relief.FragmentsKotlin.HomeFragment();
                    break;
                case R.id.nav_data:
                    selectedFragment = new CoronaSpreadDataFragment();
                    break;
                case R.id.nav_precaution:
                    selectedFragment = new PrecautionFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                    selectedFragment).commit();

            return true;
        }
    };
}
