package com.CodeNaroNa.vendor.relief.Deprecated.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CodeNaroNa.vendor.relief.Deprecated.Model.PrecautionData;
import com.CodeNaroNa.vendor.relief.R;

import java.util.ArrayList;

@Deprecated
public class PrecAdapter extends RecyclerView.Adapter<PrecAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<PrecautionData> mdata;

    public PrecAdapter(Activity activity, ArrayList<PrecautionData> mdata) {
        this.activity = activity;
        this.mdata = mdata;
    }


    @NonNull
    @Override
    public PrecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity).inflate(R.layout.question,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PrecAdapter.ViewHolder holder, final int position) {
        holder.question.setText(mdata.get(position).getQues());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.answer.getVisibility()==View.GONE) {
                    holder.answer.setVisibility(View.VISIBLE);
                    holder.answer.setText(mdata.get(position).getAnswer());
                }
                else
                    holder.answer.setVisibility(View.GONE);
            }
        });
        holder.answer.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView question;
        private TextView answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            answer=itemView.findViewById(R.id.answer);
            question=itemView.findViewById(R.id.ques);
        }
    }
}
