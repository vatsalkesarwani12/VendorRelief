package com.CodeNaroNa.vendor.relief;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Precaution extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView mrecylerView;
    private ArrayList<PrecautionData> data;
    private PrecAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precaution);
        initialView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        data=new ArrayList<>();
        mAdapter=new PrecAdapter(this,data);
        mrecylerView.setAdapter(mAdapter);

        db.collection("Data-Facts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d("Sucess5", document.getId() + " => " + document.getData());
                                data.add(new PrecautionData(document.getData().get("Q").toString(),
                                        document.getData().get("Facts").toString()));
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void initialView() {
        db=FirebaseFirestore.getInstance();
        mrecylerView=findViewById(R.id.precRecycle);
    }
}
