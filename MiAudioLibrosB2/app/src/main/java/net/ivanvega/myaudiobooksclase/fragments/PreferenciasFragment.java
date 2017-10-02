package net.ivanvega.myaudiobooksclase.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import net.ivanvega.myaudiobooksclase.R;

/**
 * Created by alcohonsilver on 09/10/16.
 */

public class PreferenciasFragment extends PreferenceFragmentCompat
{

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //setPreferencesFromResource(R.xml.preferencias,"hi");
//        addPreferencesFromResource(R.xml.preferencias);
//    }


    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferencias);
       // setPreferencesFromResource(R.xml.preferencias, s);
    }


}
