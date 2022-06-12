package com.example.khatatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class transadapter extends RecyclerView.Adapter<transadapter.Viewholder> {

    List<AddTrans> transList = new ArrayList<>();
    Context customer;

    public transadapter(List<AddTrans> transList, customer customer) {
        this.transList = transList;
        this.customer=customer;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(customer).inflate(R.layout.transaction_iem,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            AddTrans a = transList.get(position);
            holder.paid.setText(a.getPaid());
            holder.recive.setText(a.getRecive());

    }

    @Override
    public int getItemCount() {
        return transList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView recive;
        private TextView paid;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            recive = itemView.findViewById(R.id.recive);
            paid = itemView.findViewById(R.id.paid);

        }
    }
}
