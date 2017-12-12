package be.thomasmore.dyscalculie;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class GeldberekenenFragment extends Fragment {

    private TextToSpeech textToSpeech;

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

        teBetalen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textToSpeech.speak(teBetalen.getText().toString() + " euro", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        gegeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textToSpeech.speak(gegeven.getText().toString() + " euro", TextToSpeech.QUEUE_FLUSH, null);
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
        TextView tTeBetalen = getView().findViewById(R.id.teBetalen);
        TextView tGegevenBedrag = getView().findViewById(R.id.gegevenBedrag);
        TextView tWisselgeld = getView().findViewById(R.id.wisselgeld);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        double teBetalen = Double.parseDouble(tTeBetalen.getText().toString());
        double gegevenBedrag = Double.parseDouble(tGegevenBedrag.getText().toString());
        if (teBetalen <= gegevenBedrag) {
            double wisselgeld = gegevenBedrag - teBetalen;

            tWisselgeld.setText(decimalFormat.format(wisselgeld));
            textToSpeech.speak(decimalFormat.format(wisselgeld) + " euro", TextToSpeech.QUEUE_FLUSH, null);
        } else {
            double geldTeKort = teBetalen - gegevenBedrag;
            String teKort = "Je komt: " + decimalFormat.format(geldTeKort) + " euro te kort!";
            tWisselgeld.setText(teKort);
            textToSpeech.speak(teKort + " euro", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
