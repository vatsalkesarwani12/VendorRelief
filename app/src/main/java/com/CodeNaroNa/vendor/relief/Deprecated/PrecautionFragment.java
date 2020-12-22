package com.CodeNaroNa.vendor.relief.Deprecated;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.CodeNaroNa.vendor.relief.Deprecated.Adapter.PrecAdapter;
import com.CodeNaroNa.vendor.relief.Deprecated.Model.PrecautionData;
import com.CodeNaroNa.vendor.relief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

@Deprecated
public class PrecautionFragment extends Fragment {

    private FirebaseFirestore db;
    private RecyclerView mrecylerView;
    private ArrayList<PrecautionData> data;
    private PrecAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db=FirebaseFirestore.getInstance();
        View view =inflater.inflate(R.layout.fragment_precaution, container,false);
        mrecylerView=view.findViewById(R.id.precRecycle);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        data=new ArrayList<>();
        mAdapter=new PrecAdapter(getActivity(),data);
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

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

}


