package com.example.khatatracker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khatatracker.Adapter;
import com.example.khatatracker.R;

public class Money extends Fragment {

    Home home = new Home();
    private TextView bus_name;

    @Override
    public void onStart() {
        super.onStart();
        bus_name.setText(home.businessname);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money, container, false);

        bus_name = view.findViewById(R.id.bus_name);
        bus_name.setText(home.businessname);






        return  view;
    }
}