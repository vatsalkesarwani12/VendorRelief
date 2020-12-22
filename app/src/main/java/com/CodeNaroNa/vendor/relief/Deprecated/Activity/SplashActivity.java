package com.CodeNaroNa.vendor.relief.Deprecated.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.CodeNaroNa.vendor.relief.R;
import com.google.firebase.auth.FirebaseAuth;


@Deprecated
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {
                    mainIntent = new Intent(SplashActivity.this,VendorActivity.class);
                    Log.d("SSSS","User Present");
                }
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}