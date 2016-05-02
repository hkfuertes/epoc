package com.upna.ibio16.epoc.aprende;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.upna.ibio16.epoc.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AprendeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout epoc,resp, trata, exar;

    public AprendeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_aprende, container, false);

        //Recuperamos los objetos
        epoc = (LinearLayout)v.findViewById(R.id.aprende_epoc);
        resp = (LinearLayout)v.findViewById(R.id.aprende_respiracion);
        trata = (LinearLayout)v.findViewById(R.id.aprende_tratamiento);
        exar = (LinearLayout)v.findViewById(R.id.aprende_exarcerbaciones);


        //Listeners
        epoc.setOnClickListener(this);
        resp.setOnClickListener(this);
        trata.setOnClickListener(this);
        exar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aprende_epoc:
                startActivity(new Intent(this.getActivity(), LeccionActivity.class));
                break;
            case R.id.aprende_respiracion:
                Snackbar.make(v, "Aprende como respirar correctamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.aprende_tratamiento:
                Snackbar.make(v, "¿Cual es tu tratamiento?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.aprende_exarcerbaciones:
                Snackbar.make(v, "Exacerbaciones", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

        }
    }
}
