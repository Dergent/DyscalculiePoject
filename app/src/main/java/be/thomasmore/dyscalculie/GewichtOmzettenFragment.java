package be.thomasmore.dyscalculie;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class GewichtOmzettenFragment extends Fragment {

    private Spinner spinner;
    private static final String[]eenheden = {"mg", "g", "kg", "ton"};
    private TextToSpeech textToSpeech;
    private Timer timer;

    public GewichtOmzettenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gewicht_omzetten, container, false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.dropDown);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item , eenheden);
        spinner.setSelection(0);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView mg = view.findViewById((R.id.mgText));
        final TextView g = view.findViewById((R.id.gText));
        final TextView kg = view.findViewById((R.id.kgText));
        final TextView ton = view.findViewById((R.id.tonText));


        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });


        final EditText origineleHoeveelheid = view.findViewById(R.id.origineleHoeveelheid);
        origineleHoeveelheid.setText("1");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!origineleHoeveelheid.getText().toString().isEmpty()) {
                    float basis = Float.parseFloat(String.valueOf(origineleHoeveelheid.getText().toString()));
                    switch (spinner.getSelectedItem().toString()) {
                        case "mg":
                            mg.setText(String.valueOf(basis));
                            g.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            kg.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            ton.setText(String.valueOf(String.format("%.9f", basis / 1000000000)));
                            break;
                        case "g":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            g.setText(String.valueOf(basis));
                            kg.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            ton.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            break;
                        case "kg":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            g.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            kg.setText(String.valueOf(basis));
                            ton.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            break;
                        case "ton":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000000000)));
                            g.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            kg.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            ton.setText(String.valueOf(basis));
                            break;
                        default:
                            mg.setText(0);
                            g.setText(0);
                            kg.setText(0);
                            ton.setText(0);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        origineleHoeveelheid.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!origineleHoeveelheid.getText().toString().isEmpty()) {
                    final float basis = Float.parseFloat(String.valueOf(origineleHoeveelheid.getText().toString()));
                    switch (spinner.getSelectedItem().toString()) {
                        case "mg":
                            mg.setText(String.valueOf(basis));
                            g.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            kg.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            ton.setText(String.valueOf(String.format("%.9f", basis / 1000000000)));
                            break;
                        case "g":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            g.setText(String.valueOf(basis));
                            kg.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            ton.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            break;
                        case "kg":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            g.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            kg.setText(String.valueOf(basis));
                            ton.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            break;
                        case "ton":
                            mg.setText(String.valueOf(String.format("%.1f", basis * 1000000000)));
                            g.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            kg.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            ton.setText(String.valueOf(basis));
                            break;
                        default:
                            mg.setText(0);
                            g.setText(0);
                            kg.setText(0);
                            ton.setText(0);
                            break;
                    }
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            textToSpeech.speak(origineleHoeveelheid.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }, 600);
                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

}
