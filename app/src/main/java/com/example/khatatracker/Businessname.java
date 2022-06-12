package com.example.khatatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.khatatracker.fragment.Home;
import com.google.android.material.textfield.TextInputEditText;

public class Businessname extends AppCompatActivity {

    private Button bus;
    private TextInputEditText bus_name1;
    public  String businessname;
    FrameLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessname);
        bus = findViewById(R.id.bus);
        main = findViewById(R.id.main);
        bus_name1 = findViewById(R.id.bus_name1);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String Firsttime  = sharedPreferences.getString("FirstTimeInstall","");

        if (Firsttime.equals("Yes")){
            Intent intent = new Intent(Businessname.this,MainActivity.class);
            startActivity(intent);
        }else {


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();


        }

        businessname =  bus_name1.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Busname",businessname);
        editor.apply();
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Businessname.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}