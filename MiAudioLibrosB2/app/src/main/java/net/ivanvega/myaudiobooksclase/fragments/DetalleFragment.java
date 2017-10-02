package net.ivanvega.myaudiobooksclase.fragments;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import net.ivanvega.myaudiobooksclase.R;
import net.ivanvega.myaudiobooksclase.modelo.BookInfo;
import net.ivanvega.myaudiobooksclase.modelo.DAOBookInfo;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment
implements MediaPlayer.OnPreparedListener,
        MediaController.MediaPlayerControl,
        View.OnTouchListener
{

    public static String ARG_POSITION = "index";
    AppCompatActivity activity;

    private OnFragmentInteractionListener mListener;

    MediaPlayer mediaPlayer;
    MediaController mediaController;

    @Override
    public void onStop() {
        super.onStop();
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
        }catch (Exception e){
            Log.d("MYSUPERAUDIOLIBROS",e.getMessage());
        }
    }

    public DetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate
                (R.layout.fragment_detalle,
                container, false);

        Bundle args = getArguments();

        if(args != null){
            int index = args.getInt(ARG_POSITION);
            setUpBookInfo (index, inflatedView);
        }else{
            setUpBookInfo (0, inflatedView);
        }

        return inflatedView;
    }

    private void setUpBookInfo(int index,
                               View inflatedView) {
        BookInfo book =
                DAOBookInfo.getAllBooks().get(index);

        TextView txtTitulo =
                (TextView)inflatedView.findViewById(R.id.textView1);
        TextView txtAutor =
                (TextView)inflatedView.findViewById(R.id.textView2);
        ImageView img =
                (ImageView)inflatedView.findViewById(R.id.imageView1);

        txtTitulo.setText(book.getName());
        txtAutor.setText(book.getAutor());
        img.setImageResource(book.getResourceImage());

        inflatedView.setOnTouchListener(this);
        Uri audio = Uri.parse(book.getUrl());

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);

        try{
            mediaPlayer.setDataSource(activity, audio);
            mediaPlayer.prepareAsync();
            Toast.makeText(activity,"Descargando...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaController = new MediaController(activity);

    }

    public void updateBookView(int position){
        setUpBookInfo(position, getView());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }


    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(
                activity.findViewById(R.id.main_fragment_detalle)
        );

        if (PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("pref_autoreproducir",false)) {

            mediaController.show();
            start();

        }

        Toast.makeText(activity,"Presione para reproducir", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mediaController.show();
        return false;
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
