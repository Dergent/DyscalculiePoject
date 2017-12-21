package be.thomasmore.dyscalculie;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;
import static be.thomasmore.dyscalculie.R.id.teBetalen;

public class GeldberekenenFragment extends Fragment {

    private TextToSpeech textToSpeech;
    private Timer timer;
    private int viewId;
    private final int REQ_CODE_SPEECH_OUTPUT = 14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geldberekenen, container, false);
        final Button button = view.findViewById(R.id.buttonCalculate);
        final EditText teBetalen = view.findViewById(R.id.teBetalen);
        final EditText gegeven = view.findViewById(R.id.gegevenBedrag);
        final SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);

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
                        if(pref.getBoolean("toggle", false)) {
                            textToSpeech.speak(teBetalen.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
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
                        if (pref.getBoolean("toggle", false)) {
                            textToSpeech.speak(gegeven.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                }, 600);
            }
        });


        gegeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (pref.getBoolean("toggle", false)){
                        textToSpeech.speak(gegeven.getText().toString() + " euro", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                calculate(v);
            }
        });

        final ImageView teBetalenSpeak = view.findViewById(R.id.teBetalenSpeak);
        final ImageView gegevenBedragSpeak = view.findViewById(R.id.gegevenBedragSpeak);

        teBetalenSpeak.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                startListen(v);
            }
        });

        gegevenBedragSpeak.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                startListen(v);
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculate(View v) {
        final SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);

        try {
            TextView tTeBetalen = getView().findViewById(teBetalen);
            TextView tGegevenBedrag = getView().findViewById(R.id.gegevenBedrag);
            TextView tWisselgeld = getView().findViewById(R.id.wisselgeld);
            SharedPreferences.Editor editor = pref.edit();

            if (!(tTeBetalen.getText().toString().isEmpty() || tGegevenBedrag.getText().toString().isEmpty())){
                double teBetalen = Double.parseDouble(tTeBetalen.getText().toString());
                double gegevenBedrag = Double.parseDouble(tGegevenBedrag.getText().toString());
                if (teBetalen <= gegevenBedrag) {
                    double wisselgeld = gegevenBedrag - teBetalen;

                    tWisselgeld.setText(String.format("%.2f", wisselgeld));

                    if (pref.getBoolean("toggle", false)) {
                        textToSpeech.speak("Je krijgt €" + String.format("%.2f", wisselgeld) + " terug", TextToSpeech.QUEUE_FLUSH, null);
                    }

                    editor.putString("geldBerekening", "Gegeven geld " + gegevenBedrag + " te betalen bedrag " + teBetalen + ", geld terug: " + String.format("%.2f", wisselgeld) + " euro");
                    editor.commit();
                } else {
                    double geldTeKort = teBetalen - gegevenBedrag;
                    String teKort = "Je komt: " + String.format("%.2f", geldTeKort) + " euro te kort!";

                    if (pref.getBoolean("toggle", false)) {
                        textToSpeech.speak("Je komt: €" + String.format("%.2f", geldTeKort) + " te kort!", TextToSpeech.QUEUE_FLUSH, null);
                    }
                    tWisselgeld.setText(teKort);

                    editor.putString("geldBerekening", "Gegeven geld " + gegevenBedrag + " te betalen bedrag " + teBetalen + ", je komt " + teKort + " euro te kort");
                    editor.commit();
                }
            } else {
                if (tTeBetalen.getText().toString().isEmpty()){
                    String error = "Te betalen is niet ingevuld";
                    tWisselgeld.setText(error);

                    if (pref.getBoolean("toggle", false)){
                        textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                if (tGegevenBedrag.getText().toString().isEmpty()){
                    String error = "Gegeven bedrag is niet ingevuld";
                    tWisselgeld.setText(error);

                    if (pref.getBoolean("toggle", false)){
                        textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                if (tTeBetalen.getText().toString().isEmpty() && tGegevenBedrag.getText().toString().isEmpty()){
                    String error = "De velden zijn niet ingevuld";
                    tWisselgeld.setText(error);

                    if (pref.getBoolean("toggle", false)){
                        textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        } catch (NumberFormatException e) {
            TextView tWisselgeld = getView().findViewById(R.id.wisselgeld);
            String error = "Enkel nummers invullen alstublieft";
            tWisselgeld.setText(error);

            if (pref.getBoolean("toggle", false)){
                textToSpeech.speak(error, TextToSpeech.QUEUE_FLUSH, null);
            }
        }

    }

    public void startListen(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Praat nu...");

        viewId = v.getId();
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT:
                if (resultCode == RESULT_OK && null != data){
                    if (viewId == R.id.teBetalenSpeak){
                        ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        EditText teBetalen = getView().findViewById(R.id.teBetalen);
                        teBetalen.setText(voiceInText.get(0));
                    }
                    if (viewId == R.id.gegevenBedragSpeak){
                        ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        EditText gegevenBedrag = getView().findViewById(R.id.gegevenBedrag);
                        gegevenBedrag.setText(voiceInText.get(0));
                    }
                }
                break;
        }
    }
}
