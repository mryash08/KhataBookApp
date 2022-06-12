package com.example.khatatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.view> {

    List<datamodel> data1;
    Context context;
    String balance;
    List<String> lasttransactions;


    public Adapter(List<datamodel> data, Context activity, String balance) {
        data1 = data;
        context = activity;
        this.balance = balance;
        this.lasttransactions = lasttransactions;
    }


    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.homerecy,parent,false);
        return new view(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {

        datamodel d = data1.get(position);
        int posi = position;
        holder.name.setText(d.getName());
//        holder.due_datedate.setText("("+d.getDuedate()+")");
//        holder.due_datedate.setTextColor(context.getColor(R.color.red));
        holder.date.setText(d.getMobile());
        holder.price.setText("₹"+d.getPrice());
        holder.gave.setText(d.getGave());
        if (holder.gave.getText().toString().equals("No")){
            holder.price.setTextColor(context.getColor(R.color.red));
            holder.gave.setText("You Will Gave");
        }else {
            holder.price.setTextColor(context.getColor(R.color.green));
            holder.gave.setText("You Will Got");
        }

        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,customer.class);
                intent.putExtra("position",posi);
                intent.putExtra("Bal",balance);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class view extends RecyclerView.ViewHolder{
        private TextView name,date,price,gave,due_datedate;
        private CardView card1;

        public view(@NonNull View itemView) {
            super(itemView);
            card1 = itemView.findViewById(R.id.card1);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
            gave = itemView.findViewById(R.id.gave);
            due_datedate = itemView.findViewById(R.id.due_datedate);
        }
    }

    public void searchlist(List<datamodel> searchlist){
             data1 = searchlist;
             notifyDataSetChanged();
    }
}
