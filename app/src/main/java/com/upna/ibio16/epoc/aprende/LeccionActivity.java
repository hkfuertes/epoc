package com.upna.ibio16.epoc.aprende;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.upna.ibio16.epoc.R;
import com.upna.ibio16.epoc.model.Leccion;
import com.upna.ibio16.epoc.model.PreguntaBooleana;
import com.upna.ibio16.epoc.utilities.LeccionImporter;
import com.upna.ibio16.epoc.video.YoutubeView;

import java.util.Iterator;
import java.util.List;

public class LeccionActivity extends AppCompatActivity implements PreguntasView.CorrectionListener, View.OnClickListener {

    private YoutubeView youtubeView;
    private TextView titulo;
    private TextView contenido;
    private FloatingActionButton siguiente;
    private Leccion leccion;
    private Iterator<Leccion> lIterator;
    private PreguntasView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leccion);

        this.setTitle("Aprende EPOC");

        lIterator = Leccion.findAll(Leccion.class);
        if(lIterator != null){
            LeccionImporter.importFromXML(this);
            lIterator = Leccion.findAll(Leccion.class);
        }

        youtubeView = (YoutubeView) findViewById(R.id.leccion_video);
        titulo = (TextView) findViewById(R.id.leccion_titulo);
        contenido = (TextView) findViewById(R.id.leccion_contenido);

        siguiente = (FloatingActionButton) findViewById(R.id.leccion_siguiente);
        siguiente.setOnClickListener(this);

        if(lIterator.hasNext()) pintarLeccion(lIterator.next());
    }

    private void pintarLeccion(Leccion leccion){
        titulo.setText(leccion.titulo);
        contenido.setText(leccion.contenido);
        if(!leccion.video_id.equals("")) {
            youtubeView.setVisibility(View.VISIBLE);
            youtubeView.setVideoId(leccion.video_id);
        }else{
            youtubeView.setVisibility(View.GONE);
        }

        this.leccion = leccion;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.leccion_siguiente) {
            if (leccion.tienePreguntas())
                createAndShowQuestionsDialog();
            else
                siguiente();
        }
    }

    private void siguiente(){
        if(lIterator.hasNext())
            pintarLeccion(lIterator.next());
        else
            createAndShowFinishDialog();

    }

    private void createAndShowFinishDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Enhorabuena")
                .setMessage("¡Has terminado las lecciones!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void createAndShowQuestionsDialog(){
        view = new PreguntasView(this);
        view.setCorrectionListener(this);
        view.addLeccion(leccion);
        view.getDialog().show();;

    }
    
    @Override
    public void onTestCorrected(boolean pass) {
        if(pass){
            siguiente();
        }else{
            Toast.makeText(this, "Alguna respuesta es incorrecta, revisalo.",Toast.LENGTH_LONG).show();
        }
    }
}
