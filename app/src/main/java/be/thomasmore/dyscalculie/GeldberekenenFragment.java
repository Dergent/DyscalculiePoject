package be.thomasmore.dyscalculie;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static be.thomasmore.dyscalculie.R.id.teBetalen;

public class GeldberekenenFragment extends Fragment {

    private TextToSpeech textToSpeech;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geldberekenen, container, false);
        final Button button = view.findViewById(R.id.buttonCalculate);
        final EditText teBetalen = view.findViewById(R.id.teBetalen);
        final EditText gegeven = view.findViewById(R.id.gegevenBedrag);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });



        teBetalen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null){
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        textToSpeech.speak(teBetalen.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }, 600);
            }
        });

        gegeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null){
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        textToSpeech.speak(gegeven.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }, 600);
            }
        });


        gegeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    textToSpeech.speak(gegeven.getText().toString() + " euro", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                calculate(v);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculate(View v) {
        TextView tTeBetalen = getView().findViewById(teBetalen);
        TextView tGegevenBedrag = getView().findViewById(R.id.gegevenBedrag);
        TextView tWisselgeld = getView().findViewById(R.id.wisselgeld);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);
        SharedPreferences.Editor editor = pref.edit();

        if (!(tTeBetalen.getText().toString().isEmpty() || tGegevenBedrag.getText().toString().isEmpty())){
            double teBetalen = Double.parseDouble(tTeBetalen.getText().toString());
            double gegevenBedrag = Double.parseDouble(tGegevenBedrag.getText().toString());
            if (teBetalen <= gegevenBedrag) {
                double wisselgeld = gegevenBedrag - teBetalen;

                tWisselgeld.setText(decimalFormat.format(wisselgeld));
                textToSpeech.speak("Je krijgt " + decimalFormat.format(wisselgeld) + " euro terug", TextToSpeech.QUEUE_FLUSH, null);

                editor.putString("geldBerekening", "Je krijgt " + decimalFormat.format(wisselgeld) + " euro terug");
                editor.commit();
            } else {
                double geldTeKort = teBetalen - gegevenBedrag;
                String teKort = "Je komt: " + decimalFormat.format(geldTeKort) + " euro te kort!";
                textToSpeech.speak("Je komt: " + decimalFormat.format(geldTeKort) + " euro te kort!", TextToSpeech.QUEUE_FLUSH, null);
                tWisselgeld.setText(teKort);

                editor.putString("geldBerekening", teKort);
                editor.commit();
            }
        } else {
            if (tTeBetalen.getText().toString().isEmpty()){
                String error = "Te betalen is niet ingevuld";
                tWisselgeld.setText(error);
                textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
            }
            if (tGegevenBedrag.getText().toString().isEmpty()){
                String error = "Gegeven bedrag is niet ingevuld";
                tWisselgeld.setText(error);
                textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
            }
            if (tTeBetalen.getText().toString().isEmpty() && tGegevenBedrag.getText().toString().isEmpty()){
                String error = "De velden zijn niet ingevuld";
                tWisselgeld.setText(error);
                textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}
