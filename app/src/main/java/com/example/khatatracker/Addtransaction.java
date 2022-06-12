package com.example.khatatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class Addtransaction extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView persoonname, balance, time_ta, date_ta;
    TextInputEditText notes, editpaid, editrecevied;
    ImageView time_ta1, date_ta1;
    private Button save;
    String mobile;
    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtransaction);


        toolbar = findViewById(R.id.toolbar);
        persoonname = findViewById(R.id.persoonname);
        save = findViewById(R.id.save);
        balance = findViewById(R.id.balance);
        notes = findViewById(R.id.notes);
        time_ta = findViewById(R.id.time_ta);
        time_ta1 = findViewById(R.id.time_da1);
        date_ta = findViewById(R.id.date_ta);
        date_ta1 = findViewById(R.id.date_ta1);
        editpaid = findViewById(R.id.editpaid);
        editrecevied = findViewById(R.id.editrecevied);
        database = new Database(Addtransaction.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getStringExtra("key");
        String balance1 = getIntent().getStringExtra("bal");
        mobile = getIntent().getStringExtra("phone");


        persoonname.setText(name);
        balance.setText(balance1);

        time_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Addtransaction.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String time = i + ":" + i1;
                        time_ta.setText(time);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date_ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(Addtransaction.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String fd = i2 + "/" + (i1 + 1) + "/" + i;
                        date_ta.setText(fd);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedata();
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(Addtransaction.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void savedata(View view) {

            String recive = "0";
            String paid = "0";
            String notes1 = "";
            String date = "";
            String time = "";
            String phone = mobile;
            int k = 0;

            if (TextUtils.isEmpty(editrecevied.getText().toString())) {
                k++;
            }
            if (TextUtils.isEmpty(editpaid.getText().toString())) {
                k++;
            }
            if (k > 1) {
                Toast.makeText(this, "Enter Received Or Paid Amount", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(notes.getText().toString())) {
                Toast.makeText(this, "Enter Note", Toast.LENGTH_SHORT).show();
            } else {
                recive = "" + editrecevied.getText().toString();
                paid = "" + editpaid.getText().toString();
                date = date_ta.getText().toString();
                time = time_ta.getText().toString();
                notes1 = notes.getText().toString();
            }

       AddTrans addTrans = new AddTrans(phone,recive,paid,date,time,notes1);
//        Toast.makeText(Addtransaction.this, ""+addTrans.getRecive(), Toast.LENGTH_SHORT).show();

            database.addtransaction(addTrans);
        }
    }
