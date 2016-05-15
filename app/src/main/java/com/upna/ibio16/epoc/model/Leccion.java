package com.upna.ibio16.epoc.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

public class Leccion extends SugarRecord{
    public Leccion(){};
    /* My Class */
    public static final String LECCION_TAG = "leccion_tag";
    public String titulo, contenido, video_id;

    public Leccion(String titulo, String contenido, String video_id){
        this.contenido = contenido;
        this.titulo = titulo;
        this.video_id = video_id;
    }

    public String toString(){
            return titulo;
    }

    // Get Preguntas from sugarOrm
    public List<PreguntaBooleana> getPreguntas() {
        return null;
    }

    public boolean tienePreguntas() {
        return true;
    }
}
