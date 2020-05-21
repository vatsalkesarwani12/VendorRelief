package com.CodeNaroNa.vendor.relief.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

import com.CodeNaroNa.vendor.relief.Adapter.UserAdapter;
import com.CodeNaroNa.vendor.relief.Model.UserData;
import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private FirebaseAuth mAuth;
    private ImageView sign;
    private Spinner city ,state,category;
    private FirebaseFirestore db;
    private ArrayAdapter<String> cc,ss,caty;
    private String[] shopcatt={"Shop Category","All","Grocery","Dairy","Medicine","Others"};
    private ArrayList<String> ccc,sss;
    private ArrayList<UserData> data;
    String City=null,State=null,Category=null;
    private RecyclerView mrecyclerView;
    private UserAdapter mAdapter;
    private Button Get;
    private ImageView pre,vendor,spreaddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseView();
        Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDetail())
                {
                    recycleData();
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Precaution.class));
            }
        });
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getApplicationContext()!=MainActivity.this)
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        spreaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoronaSpreadData.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=mAuth.getCurrentUser();
        if (user!= null){
            Toast.makeText(this, "Welcome Vendor", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),VendorActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }

    private void recycleData() {
        //Toast.makeText(this, "Yes U Can See Data", Toast.LENGTH_SHORT).show();
        data=new ArrayList<>();
        mAdapter=new UserAdapter(this,data);
        mrecyclerView.setAdapter(mAdapter);

        db.collection("Vendor")
                .whereEqualTo("City",City)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document :task.getResult())
                            {
                                Log.d("Sucess10",document.getData().toString());
                                if (!Category.equals("All")) {
                                    if (document.getData().get("Shop Category").toString().equals(Category)) {
                                        data.add(new UserData(document.getData().get("Shop Category").toString(),
                                                document.getData().get("Shop Name").toString(),
                                                document.getData().get("Opening Time").toString(),
                                                document.getData().get("Closing Time").toString(),
                                                document.getData().get("Phone Number").toString()));
                                    }
                                }
                                else {
                                    data.add(new UserData(document.getData().get("Shop Category").toString(),
                                            document.getData().get("Shop Name").toString(),
                                            document.getData().get("Opening Time").toString(),
                                            document.getData().get("Closing Time").toString(),
                                            document.getData().get("Phone Number").toString()));
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                            mAdapter.notifyDataSetChanged();
                            if (task.getResult().size() == 0)
                                Toast.makeText(MainActivity.this, "No Vendor Found", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("FAILURE", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private boolean checkDetail()
    {
        if (State==null)
        {
            return false;
        }
        else
        {
            db.collection("State-City")
                    .document(State)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {
                                Map<String, Object> map = task.getResult().getData();
                                ccc.clear();
                                ccc.add("City");
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    ccc.add(entry.getKey());
                                    Log.d("Sucess4", entry.getKey());
                                }
                                Collections.sort(ccc);
                                cc.notifyDataSetChanged();
                            }

                        }
                    });
        }
        if (City==null)
        {
            db.collection("State-City")
                    .document(State)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {
                                ccc.clear();
                                ccc.add("City");
                                Map<String, Object> map = task.getResult().getData();
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    ccc.add(entry.getKey());
                                    Log.d("Sucess4", entry.getKey());
                                }
                                Collections.sort(ccc);
                                cc.notifyDataSetChanged();
                            }

                        }
                    });
            return false;
        }
        if(Category == null)
        {
            return false;
        }
        return true;
    }

    private void initialiseView() {
        city=findViewById(R.id.ucity);
        state=findViewById(R.id.ustate);
        db=FirebaseFirestore.getInstance();
        Get=findViewById(R.id.Get);
        mAuth=FirebaseAuth.getInstance();
        mrecyclerView=findViewById(R.id.recycle);
        sign=findViewById(R.id.sign);
        pre=findViewById(R.id.pre);
        spreaddata=findViewById(R.id.data);
        vendor=findViewById(R.id.vendor);
        category=findViewById(R.id.ucategory);


        city.setOnItemSelectedListener(this);
        state.setOnItemSelectedListener(this);
        category.setOnItemSelectedListener(this);

        ccc=new ArrayList<>();
        //ccc.add("City");
        sss=new ArrayList<>();
        sss.add("State");

        db.collection("State-City")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("Sucess2", document.getId() + " => " + document.getData());
                                sss.add(document.getId());
                            }
                        } else {
                            Log.d("Fail", "Error getting documents: ", task.getException());
                        }
                    }
                });


        cc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ccc);
        ss=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sss);
        caty=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,shopcatt);

        city.setAdapter(cc);
        state.setAdapter(ss);
        category.setAdapter(caty);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int spinner_id=parent.getId();
        switch(spinner_id)
        {
            case R.id.ustate:
                if (parent.getItemAtPosition(position).equals("State"))
                {
                    //Toast.makeText(this, "State", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    State=sss.get(position);
                    Toast.makeText(this, ""+State, Toast.LENGTH_SHORT).show();

                    db.collection("State-City")
                            .document(State)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    if (task.isSuccessful()) {
                                        ccc.clear();
                                        ccc.add("City");
                                        Map<String, Object> map = task.getResult().getData();
                                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                                            ccc.add(entry.getKey());
                                            Log.d("Sucess3", entry.getKey());
                                        }
                                        Collections.sort(ccc);
                                        cc.notifyDataSetChanged();
                                    }

                                }
                            });

                }
                return;

            case R.id.ucity:
                City=ccc.get(position);
                /*if (parent.getItemAtPosition(position).equals("City"))
                {
                    return;
                }
                else
                {
                    City=ccc.get(position);
                    Toast.makeText(this, ""+City, Toast.LENGTH_SHORT).show();
                }*/
                return;

            case R.id.ucategory:
                if (!parent.getItemAtPosition(position).equals("Shop Category")){
                    Category=shopcatt[position];
                }
                return;
            default:return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
