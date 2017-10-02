package net.ivanvega.myaudiobooksclase.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import net.ivanvega.myaudiobooksclase.R;
import net.ivanvega.myaudiobooksclase.SelectorAdapter;
import net.ivanvega.myaudiobooksclase.modelo.BookInfo;
import net.ivanvega.myaudiobooksclase.modelo.DAOBookInfo;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by SERVIDOR on 26/09/2016.
 */

public class SelectorFragment extends Fragment {
    AppCompatActivity activity;
    GridView gridView;
    SelectorAdapter adaptador;
    private OnFragmentInteractionListener mListener;
    private List<BookInfo> lstBooks;


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity =
                (AppCompatActivity) context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View inflatedView = inflater.inflate
                (R.layout.fragment_selector_layout,
                    container,
                   false
                        );

        gridView = (GridView)
                inflatedView.findViewById
                        (R.id.gridView);

        lstBooks = DAOBookInfo.getAllBooks();
        
        adaptador  = new SelectorAdapter(activity,
                lstBooks);

        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        mListener.onFragmentInteraction(
                                Uri.parse(String.valueOf(position))
                        );

                        SharedPreferences pref =  activity.getSharedPreferences("net.ivanvegaaudiolibros_internal", MODE_PRIVATE);
                        pref.edit().putInt("position",position).commit();

                    }
                }
        );

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                CharSequence [] menu = {"Compartir", "Borrar","Insertar"};
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Toast.makeText(activity,"Compartiendo en redes sociales...", 
                                        Toast.LENGTH_SHORT ).show();
                                break;
                            case 1:
                                lstBooks.remove(position);
                                adaptador.notifyDataSetChanged();
                                break;
                            case 2:
                                lstBooks.add(lstBooks.get(position));
                                adaptador.notifyDataSetChanged();
                                break;
                        }    
                    }
                });
                builder.create().show();
                return true;
            }
        });

        return inflatedView;

    }
}
