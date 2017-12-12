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


public class PercentenFragment extends Fragment {

    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_percenten, container, false);
        final Button button = view.findViewById(R.id.buttonCalculate);
        final EditText oPrijs = view.findViewById(R.id.oorspronkelijkePrijs);
        final EditText percentage = view.findViewById(R.id.percentage);
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });

        oPrijs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textToSpeech.speak(oPrijs.getText().toString() + " euro", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        percentage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textToSpeech.speak(percentage.getText().toString() + " procent", TextToSpeech.QUEUE_FLUSH, null);
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
        TextView tOorspronkelijkePrijs = getView().findViewById(R.id.oorspronkelijkePrijs);
        TextView tPercentage = getView().findViewById(R.id.percentage);
        TextView tNieuwePrijs = getView().findViewById(R.id.nieuwePrijs);

        float oorspronkelijkePrijs = Float.parseFloat(tOorspronkelijkePrijs.getText().toString());
        float percentage = Float.parseFloat(tPercentage.getText().toString());
        float nieuwePrijs = oorspronkelijkePrijs * (1 - (percentage / 100));

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");


        tNieuwePrijs.setText(decimalFormat.format(nieuwePrijs));
        textToSpeech.speak(decimalFormat.format(nieuwePrijs) + " euro", TextToSpeech.QUEUE_FLUSH, null);

    }
}
