package com.upna.ibio16.epoc.aprende;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.upna.ibio16.epoc.R;
import com.upna.ibio16.epoc.model.Leccion;
import com.upna.ibio16.epoc.model.PreguntaBooleana;

import java.util.Iterator;
import java.util.List;

public class PreguntasView extends LinearLayout {
    private static final int PADDING = 10;
    private static final String TAG = "PreguntasViewLogger";
    private LayoutInflater factory;
    private Context context;
    private AlertDialog dialog;
    private CorrectionListener llistener = null;

    public PreguntasView(Context context) {
        super(context);
        init(context);
    }

    private int dpToPx(int sizeInDp){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (sizeInDp*scale + 0.5f);
    }

    private void init(Context context){
        this.context = context;
        factory = LayoutInflater.from(context);
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.setPadding(dpToPx(PADDING), dpToPx(PADDING), dpToPx(PADDING), dpToPx(PADDING));
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public void addPregunta(PreguntaBooleana pregunta){
        View current = factory.inflate(R.layout.pregunta_layout, null);
        TextView currentText = (TextView) current.findViewById(R.id.pregunta_layout_pregunta);
        currentText.setText(pregunta.getPregunta());

        RadioGroup radioGroup = (RadioGroup) current.findViewById(R.id.pregunta_layout_respuesta);
        radioGroup.setTag(pregunta);

        //Añadimos
        this.addView(current);
    }

    public void addLeccion(Leccion leccion){
        List<PreguntaBooleana> preguntas = leccion.getPreguntas();

        for(int i=0; i< preguntas.size(); i++){
            this.addPregunta(preguntas.get(i));
        }
    }

    public boolean correcto(){
        PreguntaBooleana current = null;
        RadioGroup radioGroup = null;
        boolean ret_val = true;
        for (int i = 0; i < this.getChildCount(); i++) {
            if (this.getChildAt(i) instanceof LinearLayout) {
                radioGroup = (RadioGroup) this.getChildAt(i).findViewById(R.id.pregunta_layout_respuesta);
                current = (PreguntaBooleana) radioGroup.getTag();
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.pregunta_layout_true:
                        ret_val &= current.corregir(true);
                        break;
                    case R.id.pregunta_layout_false:
                        ret_val &= current.corregir(false);
                        break;
                }

            }
        }
        //Si todas son true devuelve true
        return ret_val;
    }

    public String toString(){
        String ret_val = "";
        for (int i = 0; i < this.getChildCount(); i++) {
            ret_val += this.getChildAt(i).getClass().getName()+"\n";
        }
        return ret_val;
    }

    public AlertDialog getDialog(){
        dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.leccion_pregunta_titulo)
                .setPositiveButton(R.string.leccion_pregunta_boton, null)
                .setNegativeButton(R.string.leccion_pregunta_boton_cancelar, null)
                .setView(this.getScrollableView())
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface d) {

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        boolean pass = correcto();
                        if (pass)
                            dialog.dismiss();

                        if (llistener != null)
                            llistener.onTestCorrected(pass);
                    }
                });
            }
        });

        return dialog;
    }

    public ScrollView getScrollableView(){
        ScrollView sv = new ScrollView(context);
        sv.addView(this);
        return sv;
    }


    public void setCorrectionListener(CorrectionListener llistener){
        this.llistener = llistener;
    }

    /*
    Interface for testing if its correct or not.
     */
    public interface CorrectionListener{
        void onTestCorrected(boolean pass);
    }
}
