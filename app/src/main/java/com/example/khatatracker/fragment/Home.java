package com.example.khatatracker.fragment;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatatracker.Adapter;
import com.example.khatatracker.AddCustomer;
import com.example.khatatracker.Businessname;
import com.example.khatatracker.Database;
import com.example.khatatracker.R;
import com.example.khatatracker.customer;
import com.example.khatatracker.datamodel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {


    private CardView addcard;
    private TextView bus_name;
    private TextInputEditText bus_name1;
    private Button bus;
    BottomSheetDialog bottomSheetDialog;
    Adapter ad;
    customer cu;
    String busname;
    Database d;
    String businessname = "Enter Your Business Name";
    int mainbal1 = 0 , paidbal1 = 0,recivebal1 = 0;
   String bus1 = "Enter Your Business name";
    public List<datamodel> data;
    public List<String> lasttransactions = new ArrayList<>();
    private RecyclerView recy;
    private TextInputEditText search1;
    private ImageView filter;
    private TextView mainbal,paidbal,recivebal;
    private Button submit;
    private RadioGroup radio1;
    private ArrayList<datamodel> recive_filter;


    @Override
    public void onStart() {
        super.onStart();

        businessname();
        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("mysharedpref", Context.MODE_APPEND);
        businessname = sharedPreferences1.getString("busname",businessname);

        bus_name.setText(businessname);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_home, container, false);

      d = new Database(getActivity());
       recivebal = view.findViewById(R.id.recivebal);
       paidbal = view.findViewById(R.id.paidbal);
        addcard = view.findViewById(R.id.addcard);
        mainbal = view.findViewById(R.id.mainbal);
        filter = view.findViewById(R.id.filter);
       search1 = view.findViewById(R.id.search1);
        recy = view.findViewById(R.id.recy);




        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AddCustomer.class);
                startActivity(intent);
            }
        });
        bus_name = view.findViewById(R.id.bus_name);
bus_name.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.BottomSheetDialogTheme
        );
        View bottomsheet = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet,(LinearLayout)view.findViewById(R.id.bottom));
        bus_name1 = bottomsheet.findViewById(R.id.bus_name1);
        bus = bottomsheet.findViewById(R.id.bus);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysharedpref", Context.MODE_PRIVATE);
                SharedPreferences.Editor busname = sharedPreferences.edit();

                businessname = bus_name1.getText().toString();
                busname.putString("busname",businessname);
                busname.commit();
                bottomSheetDialog.dismiss();

                @SuppressLint("WrongConstant") SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("mysharedpref", Context.MODE_APPEND);
                String bus = sharedPreferences1.getString("busname","Business Name");
                bus_name.setText(bus);

            }
        });
        bottomSheetDialog.setContentView(bottomsheet);
        bottomSheetDialog.show();
    }
});






        search1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                   filter(editable.toString());
            }
        });
        data = d.readdata();
        bal();
        mainbal.setText("₹"+mainbal1);
        paidbal.setText("₹"+paidbal1);
        recivebal.setText("₹"+recivebal1);


        ad = new Adapter(data,getContext(),String.valueOf(mainbal1));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(layoutManager);
        recy.setAdapter(ad);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.BottomSheetDialogTheme2);
                View bottomsheet2 = LayoutInflater.from(getContext()).inflate(R.layout.filterbottomsheet,(LinearLayout)view.findViewById(R.id.bottom1));
                submit = bottomsheet2.findViewById(R.id.submit);
                radio1 = bottomsheet2.findViewById(R.id.radio1);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (radio1.getCheckedRadioButtonId()){
                            case R.id.all:
                                ad = new Adapter(data,getActivity(),String.valueOf(mainbal1));
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                recy.setLayoutManager(layoutManager);
                                recy.setAdapter(ad);
                                break;

                            case R.id.recive:
                                recivefilter("Yes");
                                ad = new Adapter(recive_filter,getActivity(),mainbal.getText().toString());
                                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
                                recy.setLayoutManager(layoutManager1);
                                recy.setAdapter(ad);
                                break;

                            case R.id.paid:
                                recivefilter("No");
                                ad = new Adapter(recive_filter,getActivity(),mainbal.getText().toString());
                                layoutManager = new LinearLayoutManager(getActivity());
                                recy.setLayoutManager(layoutManager);
                                recy.setAdapter(ad);
                                break;
                        }
                        bottomSheetDialog.dismiss();

                    }
                });
                bottomSheetDialog.setContentView(bottomsheet2);
                bottomSheetDialog.show();
            }
        });

        return view;
    }

    private void filter(String Text){

        List<datamodel> searchlist = new ArrayList<>();
        for(datamodel name1 : data){
            if (name1.getName().toLowerCase().contains(Text.toLowerCase())){
                searchlist.add(name1);

            }

        }


        ad.searchlist(searchlist);

    }

    public void recivefilter(String check){

        recive_filter = new ArrayList<>();
        for (datamodel item : data){
            if (item.getGave().contains(check)){
                recive_filter.add(item);
            }
        }

    }

    public void bal(){

        for (datamodel item : data){
            if (item.getGave().contains("Yes")){
                recivebal1 = recivebal1 + Integer.parseInt(item.getPrice());
            }else {
                paidbal1 = paidbal1 + Integer.parseInt(item.getPrice());
            }

            mainbal1 = recivebal1 - paidbal1 ;
        }
    }


    public void businessname(){
        Businessname businessname = new Businessname();
        busname= businessname.businessname;
    }
//    public void fetch(String criterea){
//        data.clear();
//        lasttransactions.clear();
//        Cursor cursor1 = d.readtransaction(criterea);
//        int balam =0, ram=0, pam=0;
//        while (cursor1.moveToNext()){
//            datamodel d = new datamodel();
//            d.setId(cursor1.getString(cursor1.getColumnIndex("id")));
//            d.setName(cursor1.getString(cursor1.getColumnIndex("name")));
//            d.setMobile(cursor1.getString(cursor1.getColumnIndex("mobile")));
//            d.setWhatsapp(cursor1.getString(cursor1.getColumnIndex("whatsapp")));
//            d.setAdress(cursor1.getString(cursor1.getColumnIndex("adress")));
//            data.add(d);
//            if (cursor1.getString(cursor1.getColumnIndex("recive")).equalsIgnoreCase("0")){
//                lasttransactions.add("-"+cursor1.getString(cursor1.getColumnIndex("paid")).toString());
//                pam+=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("paid")));
//            }else{
//                lasttransactions.add(cursor1.getString(cursor1.getColumnIndex("paid")).toString());
//                ram+=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("paid")));
//            }
//
//
//        }
//
//        paidbal.setText(""+pam);
//        mainbal.setText(""+(ram-pam));
//        recivebal.setText(""+ram);
//
//
//    }

}