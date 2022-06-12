package com.example.khatatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khatatracker.fragment.Home;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.Calendar;

public class AddCustomer extends AppCompatActivity {

    private TextInputEditText cu_name,cu_mo,cu_price,cu_ad,cu_wa;
    public  String name , mobile , price , gave;
    public String whatsapp , adress , paymentcomplatedate,duedate,paymentmethod1,paymentcheck;
    private Button btn1,btn2;
    public Spinner spinner;
    public RadioGroup radio;
    public ImageView date , due;
    public TextView due_date,date_co;
    public String[] paymentmethod = {"Cash","UPI","Check","Bank Transfer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        cu_name = findViewById(R.id.cu_name);
        cu_mo = findViewById(R.id.cu_mo);
        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        cu_price = findViewById(R.id.cu_price) ;
        spinner = findViewById(R.id.spinner);
        due = findViewById(R.id.due);
        due_date = findViewById(R.id.due_date);
        date = findViewById(R.id.date);
        date_co = findViewById(R.id.date_co);
        radio = findViewById(R.id.radio);
        cu_ad = findViewById(R.id.cu_ad);
        cu_wa = findViewById(R.id.cu_wa);

        Database d = new Database(this);



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

                gave = "Yes";

                d.insertdata(name,mobile,price,gave,paymentcheck,paymentmethod1,duedate,paymentcomplatedate,adress,whatsapp);
                Intent intent = new Intent(AddCustomer.this, Home.class);
                startActivity(intent);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
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
                gave = "No";
                d.insertdata(name,mobile,price,gave,paymentcheck,paymentmethod1,duedate,paymentcomplatedate,adress,whatsapp);
                Intent intent = new Intent(AddCustomer.this, Home.class);
                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker();
            }
        });

        due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duedatepicker();
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,paymentmethod);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                paymentmethod1 = paymentmethod[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void datepicker(){

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                paymentcomplatedate = i+"-"+(i1+1)+"-"+i2;
                date_co.setText(paymentcomplatedate);

            }
        },calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void duedatepicker(){

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                duedate = i+"-"+(i1+1)+"-"+i2;
                due_date.setText(duedate);

            }
        },calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


}