package be.thomasmore.dyscalculie;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Locale;

public class DatumsBerkenen extends Fragment {

    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datums_berkenen, container, false);
        final Button button = view.findViewById(R.id.buttonCalculate);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                    calculate_onClick(v);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculate_onClick(View v){
        DatePicker beginDatum = getView().findViewById(R.id.beginDatum);
        DatePicker eindDatum = getView().findViewById(R.id.eindDatum);
        TextView textView = getView().findViewById(R.id.resultaat);

        if (beginDatum.getYear() <= eindDatum.getYear()){
            int maand = 0;
            int dag = 0;

            int jaar = eindDatum.getYear() - beginDatum.getYear();

            if (beginDatum.getYear() == eindDatum.getYear() && (beginDatum.getMonth() >= eindDatum.getMonth() && beginDatum.getDayOfMonth() > eindDatum.getDayOfMonth())){
                textView.setText("De begindatum is voor de einddatum.");
            } else {
                if (eindDatum.getDayOfMonth() >= beginDatum.getDayOfMonth() && eindDatum.getMonth() >= beginDatum.getMonth()) {
                    dag = eindDatum.getDayOfMonth() - beginDatum.getDayOfMonth();
                    maand = eindDatum.getMonth() - beginDatum.getMonth();
                }
                else {
                    if (eindDatum.getDayOfMonth() < beginDatum.getDayOfMonth()){
                        if (eindDatum.getMonth() > beginDatum.getMonth()){
                            maand = eindDatum.getMonth() - beginDatum.getMonth() - 1;
                        }
                        if (beginDatum.getMonth() == 0 || beginDatum.getMonth() == 2 || beginDatum.getMonth() == 4 || beginDatum.getMonth() == 6 || beginDatum.getMonth() == 7 || beginDatum.getMonth() == 9 || beginDatum.getMonth() == 11) {
                            dag = 31 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                        }
                        if (beginDatum.getMonth() == 1 && beginDatum.getYear()%4 == 0){
                            dag = 29 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                        }
                        if (beginDatum.getMonth() == 1 && beginDatum.getYear()%4 != 0) {
                            dag = 28 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                        }
                        if (beginDatum.getMonth() == 3 || beginDatum.getMonth() == 5 || beginDatum.getMonth() == 8 || beginDatum.getMonth() == 10){
                            dag = 30 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                        }
                    }
                    if (eindDatum.getMonth() < beginDatum.getMonth()) {
                        maand = 12 - beginDatum.getMonth() + eindDatum.getMonth();
                        if (jaar != 0) {
                            jaar -= 1;
                        }
                        if (eindDatum.getDayOfMonth() < beginDatum.getDayOfMonth()) {
                            if (beginDatum.getMonth() == 1 || beginDatum.getMonth() == 3 || beginDatum.getMonth() == 5 || beginDatum.getMonth() == 7 || beginDatum.getMonth() == 8 || beginDatum.getMonth() == 10 || beginDatum.getMonth() == 12) {
                                dag = 31 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                            }
                            if (beginDatum.getMonth() == 2 && beginDatum.getYear() % 4 == 0) {
                                dag = 29 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                            }
                            if (beginDatum.getMonth() == 2 && beginDatum.getYear() % 4 != 0) {
                                dag = 28 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                            }
                            if (beginDatum.getMonth() == 4 || beginDatum.getMonth() == 6 || beginDatum.getMonth() == 9 || beginDatum.getMonth() == 11) {
                                dag = 30 - beginDatum.getDayOfMonth() + eindDatum.getDayOfMonth();
                            }
                            maand -= 1;
                        }
                        if (eindDatum.getDayOfMonth() >= beginDatum.getDayOfMonth()) {
                            dag = eindDatum.getDayOfMonth() - beginDatum.getDayOfMonth();
                        }
                    }
                    if (eindDatum.getDayOfMonth() == beginDatum.getDayOfMonth()) {
                        dag = 0;
                    }
                    if (eindDatum.getMonth() == beginDatum.getMonth()) {
                        maand = 0;
                    }
                }
                textView.setText("Verstreken tijd: " + dag + " dagen " + maand + " maanden " + jaar + " jaar.");
                textToSpeech.speak("Verstreken tijd: " + dag + " dagen " + maand + " maanden " + jaar + " jaar.", TextToSpeech.QUEUE_FLUSH, null);
            }
        } else {
            textView.setText("De begindatum is voor de einddatum.");
            textToSpeech.speak("De begindatum is voor de einddatum.", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
