package com.example.khatatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.khatatracker.fragment.Home;
import com.example.khatatracker.fragment.Money;
import com.example.khatatracker.fragment.More;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottombar;

    FrameLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottombar = findViewById(R.id.bottombar);
        main = findViewById(R.id.main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, new Home());
        fragmentTransaction.commit();

        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){

                    case R.id.it1:
                        fragmentTransaction.replace(R.id.main, new Home());
                        break;
                    case R.id.it2: fragmentTransaction.replace(R.id.main,new Money());
                        break;
                    case R.id.it3: fragmentTransaction.replace(R.id.main,new More());
                        break;


                }
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}