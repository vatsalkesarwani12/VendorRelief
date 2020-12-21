package com.CodeNaroNa.vendor.relief.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.CodeNaroNa.vendor.relief.Adapter.UserAdapter;
import com.CodeNaroNa.vendor.relief.CoronaSpreadDataFragment;
import com.CodeNaroNa.vendor.relief.FragmentsKotlin.HomeFragment;
import com.CodeNaroNa.vendor.relief.Model.UserData;
import com.CodeNaroNa.vendor.relief.PrecautionFragment;
import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

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
