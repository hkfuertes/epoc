package com.upna.ibio16.epoc.model;

import com.orm.SugarRecord;


public class PreguntaBooleana{
    public PreguntaBooleana(){}

    public String pregunta;
    private Boolean respuesta = null;

    private Leccion leccion;

    public PreguntaBooleana(Leccion leccion, String pregunta, Boolean respuesta){
        this();
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.leccion = leccion;
    }

    public PreguntaBooleana(Leccion leccion, String pregunta, String respuesta){
        this(leccion, pregunta, Boolean.valueOf(respuesta));
    }

    public boolean corregir(Object respuesta) {
        if(respuesta instanceof Boolean){
            return respuesta.equals(this.respuesta);
        }else
            return false;
    }
    public boolean corregir(boolean respuesta){
        return this.respuesta.equals(Boolean.valueOf(respuesta));
    }

    @Override
    public String toString() {
        return pregunta+" ["+respuesta+"]";
    }

    public String getPregunta() {
        return pregunta;
    }
}