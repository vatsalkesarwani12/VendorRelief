package com.CodeNaroNa.vendor.relief.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class VendorData extends AppCompatActivity {
    private TextView state,city,phone,cate,ot,ct,name,add;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_data);
        intialiseView();

        Intent intent=getIntent();
        id=intent.getStringExtra("Number");

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null)
        id=user.getPhoneNumber();*/

        db.collection("Vendor")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot data=task.getResult();
                            if (data.exists()){
                            state.setText(data.getData().get("State").toString());
                            city.setText(data.getData().get("City").toString());
                            phone.setText(data.getData().get("Phone Number").toString());
                            cate.setText(data.getData().get("Shop Category").toString());
                            ot.setText(data.getData().get("Opening Time").toString());
                            ct.setText(data.getData().get("Closing Time").toString());
                            name.setText(data.getData().get("Shop Name").toString());
                            add.setText(data.getData().get("Address").toString());}
                        }
                    }
                });
    }

    private void intialiseView() {
        state=findViewById(R.id.vstate);
        city=findViewById(R.id.vcity);
        phone=findViewById(R.id.vphone);
        cate=findViewById(R.id.vcategory);
        ot=findViewById(R.id.vopen);
        ct=findViewById(R.id.vclose);
        name=findViewById(R.id.vname);
        add=findViewById(R.id.vaddress);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
    }
}
