package com.example.igor.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igor.fragments.R;



public class TopFragment2 extends Fragment {

    public static TopFragment2 newInstance() {
        TopFragment2 frag = new TopFragment2();

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.topfragment_2, null);

        return v;
    }
}
