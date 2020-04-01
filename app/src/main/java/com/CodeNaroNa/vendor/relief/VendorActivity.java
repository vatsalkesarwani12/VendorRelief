package com.CodeNaroNa.vendor.relief;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class VendorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button signOut,saveData;
    private TextInputEditText shopName,address;
    private Spinner shopCat,state,city,open,openampm,close,closeampm;
    private String ssshopCat=null,ssState=null,ssCity=null,ssopen=null,ssopenampm=null,ssclose=null,sscloseampm=null;
    private String[] shopcatt={"Shop Category","Grocery","Dairy","Medicine","Others"};
    private String[] time={"Select","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] ampm={"Select","AM","PM"};
    private ArrayAdapter<String> spct,tm,am;
    private ArrayAdapter<State> st;
    private ArrayAdapter<City> ct;

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
    }

    private void storeVendorDetail() {

        FirebaseUser user=mAuth.getCurrentUser();
        //Toast.makeText(this, ""+user.getPhoneNumber(), Toast.LENGTH_SHORT).show();

        Map<String,Object> vendorData=new HashMap<>();
        vendorData.put("Shop Name",shopName.getText().toString());
        vendorData.put("Shop Category",ssshopCat);
        vendorData.put("State","state");
        vendorData.put("City","city");
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

        spct=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,shopcatt);
        tm=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,time);
        am=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ampm);

        shopCat.setAdapter(spct);
        open.setAdapter(tm);
        openampm.setAdapter(am);
        close.setAdapter(tm);
        closeampm.setAdapter(am);

    }

    private void intialiseViews() {
        mAuth=FirebaseAuth.getInstance();
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

            default:return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
