package com.example.khatatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class updatedata extends AppCompatActivity {

    private TextInputEditText cu_name,cu_mo,cu_price,cu_ad,cu_wa;
    public  String name , mobile , price , gave;
    public String whatsapp , adress , paymentcomplatedate,duedate,paymentmethod1,paymentcheck;
    private Button btn1;
    public Spinner spinner;
    public RadioGroup radio;
    public ImageView date , due;
    public TextView due_date,date_co;
    public String[] paymentmethod = {"Cash","UPI","Check","Bank Transfer"};
    int position;
    Database d;
    List<datamodel> data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        cu_name = findViewById(R.id.cu_name);
        cu_mo = findViewById(R.id.cu_mo);
        btn1= findViewById(R.id.btn1);
        cu_price = findViewById(R.id.cu_price) ;
        spinner = findViewById(R.id.spinner);
        due = findViewById(R.id.due);
        due_date = findViewById(R.id.due_date);
        date = findViewById(R.id.date);
        date_co = findViewById(R.id.date_co);
        radio = findViewById(R.id.radio);
        cu_ad = findViewById(R.id.cu_ad);
        cu_wa = findViewById(R.id.cu_wa);



        position = getIntent().getIntExtra("position",0);

        d = new Database(updatedata.this);
        data1 = d.readdata();
        datamodel d1 = data1.get(position);
        cu_name.setText(d1.getName());
        cu_mo.setText(d1.getMobile());
        cu_price.setText(d1.getPrice());
        cu_ad.setText(d1.getAdress());
        cu_wa.setText(d1.getWhatsapp());
        if (d1.getPaymentcheck().equals("Done")){
            radio.check(R.id.rado_co);
        }else {
            radio.check(R.id.rado_pe);
        }
        due_date.setText(d1.getDuedate());
        date_co.setText(d1.getPaymentcomplatedate());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radio.getCheckedRadioButtonId()==R.id.rado_co){
                    paymentcheck = "Done";
                }else if (radio.getCheckedRadioButtonId()==R.id.rado_pe){
                    paymentcheck = "Pending";
                }

                name = cu_name.getText().toString();
                mobile = cu_mo.getText().toString();
                price = cu_price.getText().toString();
                whatsapp = cu_wa.getText().toString();
                adress = cu_ad.getText().toString();

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        paymentmethod1 = paymentmethod[i];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                 d.updatedata(name,mobile,price,paymentcheck,paymentmethod1,adress,whatsapp);
                 Intent intent = new Intent(updatedata.this,customer.class);
                 startActivity(intent);
            }
        });
    }
}