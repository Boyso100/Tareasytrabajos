package net.ivanvega.myaudiobooksclase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import net.ivanvega.myaudiobooksclase.fragments.PreferenciasFragment;

/**
 * Created by alcohonsilver on 10/10/16.
 */

public class PreferenciasActivity extends
        AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_fragment_preferencias);
        getSupportFragmentManager().
            beginTransaction().
            replace(android.R.id.content, new PreferenciasFragment())
            .commit();
    }
}
