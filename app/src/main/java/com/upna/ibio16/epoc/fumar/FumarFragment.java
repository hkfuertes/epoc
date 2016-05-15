package com.upna.ibio16.epoc.fumar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.upna.ibio16.epoc.R;


public class FumarFragment extends Fragment {

    public FumarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FumarFragment newInstance(String param1, String param2) {
        FumarFragment fragment = new FumarFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
        View v = inflater.inflate(R.layout.fragment_test_video, container, false);

        VideoView player = (VideoView) v.findViewById(R.id.video_player);
        player.setVideoPath("https://www.dropbox.com/s/u3fvqq9xew0ncyg/video_starwars_prueba.mp4?dl=1");
        player.start();
        */

        View v = inflater.inflate(R.layout.fragment_fumar, container, false);

        return v;
    }
}
