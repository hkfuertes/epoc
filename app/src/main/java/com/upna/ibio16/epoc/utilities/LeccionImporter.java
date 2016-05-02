package com.upna.ibio16.epoc.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.upna.ibio16.epoc.R;
import com.upna.ibio16.epoc.model.Leccion;
import com.upna.ibio16.epoc.model.PreguntaBooleana;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hkfuertes on 06/04/16.
 */
public class LeccionImporter {

    public static void importFromXML(Context context){
        Leccion leccion = null;
        PreguntaBooleana pregunta;
        String r = null, p = null;

        boolean estoyEnLeccion = false;
        boolean estoyEnPregunta = false;
        boolean estoyEnContenido = false;

        Resources res = context.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.lecciones);

        //  ***** BEGIN XML PARSING *****
        try {
            String titulo = null, videoId = null, contenido = null;
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {

                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    if(xrp.getName().equals("leccion"))
                    {
                        estoyEnLeccion = true;
                    }
                    else if (xrp.getName().equals("contenido") && estoyEnLeccion)
                    {
                        titulo = xrp.getAttributeValue(null, "titulo");
                        videoId = xrp.getAttributeValue(null, "video");
                        estoyEnContenido = true;
                    }
                    else if(xrp.getName().equals("pregunta") && estoyEnLeccion)
                    {
                        r = xrp.getAttributeValue(null,"respuesta");
                        estoyEnPregunta = true;
                    }
                }else if(xrp.getEventType() == XmlResourceParser.TEXT){
                    if(estoyEnContenido && estoyEnLeccion)
                        contenido = xrp.getText();
                    else if(estoyEnPregunta && estoyEnLeccion)
                        p = xrp.getText();
                }else if(xrp.getEventType() == XmlResourceParser.END_TAG){
                    if(xrp.getName().equals("leccion"))
                    {
                        estoyEnLeccion=false;
                    }
                    else if(xrp.getName().equals("pregunta"))
                    {
                        estoyEnPregunta = false;
                        pregunta = new PreguntaBooleana(leccion, p, r);
                        pregunta.save();
                    }
                    else if(xrp.getName().equals("contenido"))
                    {
                        estoyEnContenido = false;
                        leccion = new Leccion(titulo, contenido, videoId);
                        leccion.save();
                    }
                }

                xrp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
