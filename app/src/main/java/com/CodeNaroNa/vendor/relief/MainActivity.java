package com.CodeNaroNa.vendor.relief;

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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    private TextView sign;
    private Spinner city ,state;
    private FirebaseFirestore db;
    private ArrayAdapter<String> cc,ss;
    private ArrayList<String> ccc,sss;
    private ArrayList<UserData> data;
    String City=null,State=null;
    private RecyclerView mrecyclerView;
    private UserAdapter mAdapter;
    private Button Get,pre;

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
                                data.add(new UserData(document.getData().get("Shop Category").toString(),
                                        document.getData().get("Shop Name").toString(),
                                        document.getData().get("Opening Time").toString(),
                                        document.getData().get("Closing Time").toString(),
                                        document.getData().get("Phone Number").toString()));
                                mAdapter.notifyDataSetChanged();
                            }
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
                            }

                        }
                    });
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

        city.setOnItemSelectedListener(this);
        state.setOnItemSelectedListener(this);

        ccc=new ArrayList<>();
        ccc.add("City");
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
                            Collections.sort(sss);
                        } else {
                            Log.d("Fail", "Error getting documents: ", task.getException());
                        }
                    }
                });

        cc=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ccc);
        ss=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sss);

        city.setAdapter(cc);
        state.setAdapter(ss);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int spinner_id=parent.getId();
        switch(spinner_id)
        {
            case R.id.ustate:
                if (!parent.getItemAtPosition(position).equals("State"))
                {
                    State=sss.get(position);

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
                                    }

                                }
                            });

                }
                return;

            case R.id.ucity:
                if (!parent.getItemAtPosition(position).equals("City"))
                {
                    City=ccc.get(position);
                }
                return;

            default:return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
