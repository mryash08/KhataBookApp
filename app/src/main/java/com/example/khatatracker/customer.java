package com.example.khatatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khatatracker.fragment.Home;

import java.net.URLEncoder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class customer extends AppCompatActivity {

    private CircleImageView circcle;
    private TextView name;
    ImageView backarrrow , call , menu;
    List<datamodel> data;
    Database database;
    List<AddTrans> transList;

    int position1;
    RecyclerView recy;
    Itemadapter itemadapter;
    datamodel d;
    Home home = new Home();
    private Toolbar toolbar;
    private CardView addtran;
    String mainbal;
    private LinearLayout call1,report,whatsapp,sms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        circcle = findViewById(R.id.circcle);
                name = findViewById(R.id.name);
                toolbar = findViewById(R.id.toolbar);
        call = findViewById(R.id.call);
        backarrrow = findViewById(R.id.backarrrow);
        addtran = findViewById(R.id.addtran);
        call1 = findViewById(R.id.call1);
        report = findViewById(R.id.report);
        whatsapp = findViewById(R.id.whatsapp);
        sms= findViewById(R.id.sms);
        recy = findViewById(R.id.recy);
        setSupportActionBar(toolbar);



      itemadapter = new Itemadapter(customer.this);
      recy.setLayoutManager(new LinearLayoutManager(this));
      recy.setAdapter(itemadapter);

        position1 = getIntent().getIntExtra("position",0);
        String Balance = getIntent().getStringExtra("Bal");


        database = new Database(customer.this);
       data = database.readdata();
       d = data.get(position1);
        String mobile = d.getMobile();
        String Whatsapp = d.getWhatsapp();
        Uri u = Uri.parse("tel:" + d.getMobile());





        addtran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(customer.this,Addtransaction.class);
                i.putExtra("key",data.get(position1).getName().toString());
                i.putExtra("bal",Balance);
                i.putExtra("phone",mobile);
                startActivity(i);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                startActivity(i);
                Toast.makeText(customer.this, ""+mobile, Toast.LENGTH_SHORT).show();
            }
        });
        name.setText(d.getName());
        backarrrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customer.this,Home.class);
                startActivity(intent);
            }
        });

        call1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);
                        Toast.makeText(customer.this, ""+mobile, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(customer.this, "Your Report Send Shortly", Toast.LENGTH_SHORT).show();
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://api.whatsapp.com/send?phone=91"+whatsapp
                    +"&text="+"hi"));
                    startActivity(i);

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customer.this,customer.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);


                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(mobile, null, "hi", pi,null);

                Toast.makeText(customer.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();

            }
        });

        transList = database.readtransaction();
        transadapter trans = new transadapter(transList,customer.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(customer.this);
        recy.setLayoutManager(layoutManager);
        recy.setAdapter(trans);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.update:  Intent i = new Intent(customer.this,updatedata.class);
                               i.putExtra("position",position1);
                                  startActivity(i);
                                break;


            case R.id.delete:   database.delete(d.getId());
                                 Intent i1 = new Intent(customer.this,MainActivity.class);
                                 startActivity(i1);
                                break;
        }
        
        return super.onOptionsItemSelected(item);
    }

//    public boolean iswhatsappinstalled() {
//        PackageManager packageManager = getPackageManager();
//        boolean whatsappinstalled;
//
//        try {
//            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
//            whatsappinstalled = true;
//        } catch (PackageManager.NameNotFoundException e) {
//
//        }whatsappinstalled = false;
//
//        return whatsappinstalled;
//
//    }
}