package com.upna.ibio16.epoc.main;

import com.orm.SugarApp;
import com.upna.ibio16.epoc.model.Leccion;
import com.upna.ibio16.epoc.model.PreguntaBooleana;
import com.upna.ibio16.epoc.model.Usuario;

/**
 * Created by hkfuertes on 06/04/16.
 */
public class MainApplication extends SugarApp{

    public void onCreate(){
        super.onCreate();

        //Inicializamos las tablas de los modelos.
        Usuario.findById(Usuario.class,(long) 1);
        Leccion.findById(Leccion.class,(long) 1);
        PreguntaBooleana.findById(PreguntaBooleana.class, (long) 1);
    }
}
