package com.CodeNaroNa.vendor.relief.Deprecated.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class VendorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button saveData;
    private ImageView signOut,showData,datab,prec;
    private TextInputEditText shopName,address;
    private Spinner shopCat,state,city,open,openampm,close,closeampm;
    private String ssshopCat=null,ssState=null,ssCity=null,ssopen=null,ssopenampm=null,ssclose=null,sscloseampm=null,sscity=null,ssstate=null;
    private String[] shopcatt={"Shop Category","Grocery","Dairy","Medicine","Others"};
    private String[] time={"Select","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] ampm={"Select","AM","PM"};
    private ArrayList<String> cc,ss;
    private ArrayAdapter<String> spct,tm,am,ssss,cccc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        intialiseViews();
        spinnerListener();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDetail())
                {
                    if(checkSpinner()){
                        storeVendorDetail();
                    }
                }
            }
        });
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), VendorData.class);
                intent.putExtra("Number",mAuth.getCurrentUser().getPhoneNumber());
                startActivity(intent);
            }
        });
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Precaution.class));
            }
        });
        datab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoronaSpreadData.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }

    private void storeVendorDetail() {

        FirebaseUser user=mAuth.getCurrentUser();
        //Toast.makeText(this, ""+user.getPhoneNumber(), Toast.LENGTH_SHORT).show();

        Map<String,Object> vendorData=new HashMap<>();
        vendorData.put("Shop Name",shopName.getText().toString());
        vendorData.put("Shop Category",ssshopCat);
        vendorData.put("State",ssstate);
        vendorData.put("City",sscity);
        vendorData.put("Address",address.getText().toString());
        vendorData.put("Opening Time",ssopen+" "+ssopenampm);
        vendorData.put("Closing Time",ssclose+" "+sscloseampm);

        db.collection("Vendor")
                .document(""+user.getPhoneNumber())
                .update(vendorData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete())
                        {
                            Toast.makeText(VendorActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        }
                        else if (task.isCanceled())
                        {
                            Toast.makeText(VendorActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkSpinner() {
        if(ssshopCat == null)
        {
            saveData.setError("Select Shop Category");
            return false;
        }
        else {
            saveData.setError(null);
        }
        if (ssstate==null)
        {
            saveData.setError("Select State");
            return false;
        }
        else
        {
            saveData.setError(null);
            db.collection("State-City")
                    .document(ssstate)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {
                                Map<String, Object> map = task.getResult().getData();
                                cc.clear();
                                cc.add("City");
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    cc.add(entry.getKey());
                                    Log.d("Sucess3", entry.getKey());
                                }
                                Collections.sort(cc);
                                cccc.notifyDataSetChanged();
                            }

                        }
                    });
        }
        if (sscity==null)
        {
            saveData.setError("Select City");
            db.collection("State-City")
                    .document(ssstate)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {
                                cc.clear();
                                cc.add("City");
                                Map<String, Object> map = task.getResult().getData();
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    cc.add(entry.getKey());
                                    Log.d("Sucess3", entry.getKey());
                                }
                                Collections.sort(cc);
                                cccc.notifyDataSetChanged();
                            }

                        }
                    });
            return false;
        }
        else
        {
            saveData.setError(null);
        }
        if(ssopen == null)
        {
            saveData.setError("Select Opening Date");
            return false;
        }
        else {
            saveData.setError(null);
        }
        if(ssopenampm == null)
        {
            saveData.setError("Select Opening Date AM or PM");
            return false;
        }
        else {
            saveData.setError(null);
        }
        if(ssclose == null)
        {
            saveData.setError("Select Closing Date");
            return false ;
        }
        else {
            saveData.setError(null);
        }
        if(sscloseampm == null)
        {
            saveData.setError("Select Closing Date AM or PM");
            return false;
        }
        else {
            saveData.setError(null);
        }
        return true;
    }

    private boolean checkDetail() {
        if(shopName.getText().toString().isEmpty())
        {
            shopName.setError("Field can't be Epmty");
            return false;
        }
        if (address.getText().toString().isEmpty())
        {
            address.setError("Field can't be Empty");
            return false;
        }
        return true;
    }

    private void spinnerListener() {
        shopCat.setOnItemSelectedListener(this);
        state.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);
        open.setOnItemSelectedListener(this);
        openampm.setOnItemSelectedListener(this);
        closeampm.setOnItemSelectedListener(this);
        close.setOnItemSelectedListener(this);


        cc=new ArrayList<>();
        //cc.add("City");
        ss=new ArrayList<>();
        ss.add("State");

        db.collection("State-City")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("Sucess2", document.getId() + " => " + document.getData());
                                ss.add(document.getId());
                            }
                        } else {
                            Log.d("Fail", "Error getting documents: ", task.getException());
                        }
                    }
                });


        spct=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,shopcatt);
        tm=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,time);
        am=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ampm);
        ssss=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ss);
        cccc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,cc);

        shopCat.setAdapter(spct);
        open.setAdapter(tm);
        openampm.setAdapter(am);
        close.setAdapter(tm);
        closeampm.setAdapter(am);
        state.setAdapter(ssss);
        city.setAdapter(cccc);
    }

    private void intialiseViews() {
        mAuth=FirebaseAuth.getInstance();
        showData=findViewById(R.id.showData);
        db= FirebaseFirestore.getInstance();
        signOut=findViewById(R.id.signOut);
        saveData=findViewById(R.id.save);
        shopName=findViewById(R.id.shopName);
        address=findViewById(R.id.address);
        shopCat=findViewById(R.id.shopCategory);
        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        open=findViewById(R.id.opening);
        openampm=findViewById(R.id.openampm);
        close=findViewById(R.id.closing);
        closeampm=findViewById(R.id.closeampm);
        datab=findViewById(R.id.dataB);
        prec=findViewById(R.id.prec);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int spinner_id=parent.getId();
        switch (spinner_id){
            case R.id.shopCategory :
                if (!parent.getItemAtPosition(position).equals("Shop Category")){
                    ssshopCat=shopcatt[position];
                }
                return;

            case R.id.opening:
                if (!parent.getItemAtPosition(position).equals("Select")){
                    ssopen=time[position];
                }
                return;

            case R.id.openampm:
                if (!parent.getItemAtPosition(position).equals("Select")){
                    ssopenampm=ampm[position];
                }
                return;

            case R.id.closing:
                if (!parent.getItemAtPosition(position).equals("Select")){
                    ssclose=time[position];
                }
                return;

            case R.id.closeampm:
                if (!parent.getItemAtPosition(position).equals("Select")){
                    sscloseampm=ampm[position];
                }
                return;

            case R.id.state:
                if (!parent.getItemAtPosition(position).equals("State"))
                {
                    ssstate=ss.get(position);

                    db.collection("State-City")
                            .document(ssstate)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    if (task.isSuccessful()) {
                                        cc.clear();
                                        cc.add("City");
                                        Map<String, Object> map = task.getResult().getData();
                                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                                            cc.add(entry.getKey());
                                            Log.d("Sucess3", entry.getKey());
                                        }
                                        Collections.sort(cc);
                                        cccc.notifyDataSetChanged();
                                    }

                                }
                            });

                }
                return;

            case R.id.city:
                sscity=cc.get(position);
                return;

            default:return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
