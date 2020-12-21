package com.CodeNaroNa.vendor.relief.activityKotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.CodeNaroNa.vendor.relief.Activity.MainActivity
import com.CodeNaroNa.vendor.relief.Activity.VendorActivity
import com.CodeNaroNa.vendor.relief.R
import com.google.firebase.auth.FirebaseAuth

@Suppress("PropertyName", "PrivatePropertyName")
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent: Intent = if (FirebaseAuth.getInstance().currentUser != null)
                Intent(this, VendorActivity::class.java)
            else
                Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}