package com.CodeNaroNa.vendor.relief;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    private TextInputEditText phone;
    private Button gen,verify;
    private EditText otp;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private MaterialCardView fabpop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intialiseView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDetail1())
                fabpop.setVisibility(View.VISIBLE);
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDetail2())
                fabpop.setVisibility(View.GONE);
            }
        });
    }

    private void intialiseView() {
        phone=findViewById(R.id.phone);
        gen=findViewById(R.id.genOtp);
        verify=findViewById(R.id.verify);
        otp=findViewById(R.id.otp);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        fabpop=findViewById(R.id.fabpop);
    }
    private Boolean checkDetail1(){
        if (phone.getText().toString().isEmpty())
        {
            phone.setError("Field can't be Empty");
            return false;
        }
        return true;
    }
    private Boolean checkDetail2(){
        if(otp.getText().toString().isEmpty())
        {
            otp.setError("Field can't be Empty");
            return false;
        }
        return true;
    }
}
