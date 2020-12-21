package com.CodeNaroNa.vendor.relief.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CodeNaroNa.vendor.relief.R;
import com.CodeNaroNa.vendor.relief.Model.UserData;
import com.CodeNaroNa.vendor.relief.Model.VendorData;

import java.util.ArrayList;

@Deprecated
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<UserData> mdata;

    public UserAdapter(Activity activity, ArrayList<UserData> mdata) {
        this.activity = activity;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity).inflate(R.layout.user_frame,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(mdata.get(position).getName());
        holder.cat.setText(mdata.get(position).getCat());
        holder.ct.setText(mdata.get(position).getCt());
        holder.ot.setText(mdata.get(position).getOt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), VendorData.class);
                intent.putExtra("Number",mdata.get(position).getNum());
                //Toast.makeText(activity, ""+mdata.get(position).getNum(), Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name ,cat,ot,ct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sname);
            cat=itemView.findViewById(R.id.scat);
            ot=itemView.findViewById(R.id.ott);
            ct=itemView.findViewById(R.id.ctt);
        }
    }
}
