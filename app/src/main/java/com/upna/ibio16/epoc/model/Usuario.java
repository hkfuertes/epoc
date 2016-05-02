package com.upna.ibio16.epoc.model;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Usuario {
    private final String DEFAULT_FORMAT = "EEE MMM dd kk:mm:ss z yyyy";

    private String nombre, apellidos;
    private Date fechaNacimiento;
    private String sexo;

    public Usuario (String nombre, String apellidos, Date fechaNacimiento, String sexo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        setFechaNacimiento(fechaNacimiento);
        this.sexo = sexo;
    }


    public Usuario (String nombre, String apellidos, String fechaNacimiento, String format, String sexo){
        this(nombre,apellidos,new Date(),sexo);
        this.setFechaNacimiento(fechaNacimiento, format);
    }

    public Usuario (String nombre, String apellidos, String fechaNacimiento, String sexo){
        this(nombre,apellidos,new Date(),sexo);
        this.setFechaNacimiento(fechaNacimiento);
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getNombreCompleto(){
        return getNombre() + " "+ getApellidos();
    }

    public void setFechaNacimiento(String fechaNacimiento, String format){
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
            this.fechaNacimiento =  df.parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setFechaNacimiento(String fechaNacimiento){
        try {
            DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT, Locale.ENGLISH);
            this.fechaNacimiento =  df.parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento(){
       return this.fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
