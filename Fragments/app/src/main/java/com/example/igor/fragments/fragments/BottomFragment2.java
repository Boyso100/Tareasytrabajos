package com.example.igor.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igor.fragments.R;


public class BottomFragment2 extends Fragment {

    public static BottomFragment2 newInstance() {
        BottomFragment2 frag = new BottomFragment2();

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomfragment_2, null);

        return v;
    }
}
