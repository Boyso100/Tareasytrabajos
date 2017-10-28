package com.example.igor.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igor.fragments.R;


public class CentralFragment1 extends Fragment {

    /**
     * Método de factoría que nos permite instanciar objetos. Especialmente útil si queremos pasar
     * datos al fragment para poder mostrarlos después en pantalla.
     *
     * @return
     */
    public static CentralFragment1 newInstance() {
        CentralFragment1 frag = new CentralFragment1();

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.centralfragment_1, null);

        return v;
    }
}
