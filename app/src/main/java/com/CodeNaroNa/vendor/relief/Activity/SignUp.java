package com.CodeNaroNa.vendor.relief.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    private TextInputEditText phone;
    private Button gen,verify;
    private EditText otp;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private MaterialCardView fabpop;
    private String codeSent;
    private Map<String ,Object> dd;
    private RadioGroup userselected;
    private RadioButton selection;
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intialiseView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fabpop.getVisibility()==View.VISIBLE)
        {
            fabpop.setVisibility(View.GONE);
        }
        else
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null)
        {
            startActivity(new Intent(getApplicationContext(),VendorActivity.class));
        }
        else {
            gen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkDetail1()) {
                        fabpop.setVisibility(View.VISIBLE);
                        verifyPhoneNumber();
                    }
                }
            });


            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkDetail2()) {
                        verifySignInCode();
                        fabpop.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void verifySignInCode() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, otp.getText().toString());
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(SignUp.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                            int selectedId = userselected.getCheckedRadioButtonId();
                            selection = (RadioButton) findViewById(selectedId);
                            Toast.makeText(getApplicationContext(),selection.getText().toString(),Toast.LENGTH_SHORT).show();

                            if (selection.getText().toString().equals("New User")) {
                                dd.put("Phone Number",mAuth.getCurrentUser().getPhoneNumber());
                                dd.put("Shop Name","");
                                dd.put("Shop Category","");
                                dd.put("State","");
                                dd.put("City","");
                                dd.put("Address","");
                                dd.put("Opening Time","");
                                dd.put("Closing Time","");
                                db.collection("Vendor")
                                        .document("" + mAuth.getCurrentUser().getPhoneNumber())
                                        .set(dd)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isComplete()) {
                                                    startActivity(new Intent(getApplicationContext(), VendorActivity.class));
                                                }
                                            }
                                        });
                            }
                            else
                                if (selection.getText().toString().equals("Existing User")){
                                    startActivity(new Intent(getApplicationContext(), VendorActivity.class));
                                }
                        }
                        else {
                            //Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Snackbar.make(parentLayout,task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void verifyPhoneNumber() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone.getText().toString(),60,TimeUnit.SECONDS,this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(SignUp.this, "Enter OTP", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        //Toast.makeText(SignUp.this, "Cannot create Account "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Snackbar.make(parentLayout,"Cannot create Account "+e.getMessage(),Snackbar.LENGTH_SHORT).show();

                        Log.e("Error",e.getMessage());
                    }
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        Log.v("Success1",s);
                        codeSent=s;
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
        parentLayout = findViewById(R.id.sign_up_parent_layout);
        db=FirebaseFirestore.getInstance();
        userselected=findViewById(R.id.userSelected);
        dd=new HashMap<>();
    }
    private Boolean checkDetail1(){
        if (phone.getText().toString().length()!=13)
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
        if (userselected.getCheckedRadioButtonId()!=R.id.newUser && userselected.getCheckedRadioButtonId()!=R.id.ExistingUser)
        {
            gen.setError("");
            //Toast.makeText(this, "Select user", Toast.LENGTH_SHORT).show();
            Snackbar.make(parentLayout,"Select user",Snackbar.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }
}
