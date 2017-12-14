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

public class AfstandOmzettenFragment extends Fragment {
    private Spinner spinner;
    private static final String[]eenheden = {"mm", "cm", "dm", "m", "km"};
    private TextToSpeech textToSpeech;
    private Timer timer;

    public AfstandOmzettenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_afstand_omzetten, container, false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.dropDown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item , eenheden);
        spinner.setSelection(0);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView mm = view.findViewById((R.id.mmText));
        final TextView cm = view.findViewById((R.id.cmText));
        final TextView dm = view.findViewById((R.id.dmText));
        final TextView m = view.findViewById((R.id.mText));
        final TextView km = view.findViewById((R.id.kmText));

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
                        case "mm":
                            mm.setText(String.valueOf(basis));
                            cm.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            dm.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            m.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            km.setText(String.valueOf(String.format("%.8f", basis / 100000)));
                            break;
                        case "cm":
                            mm.setText(String.valueOf(String.format("%.2f", basis * 10)));
                            cm.setText(String.valueOf(basis));
                            dm.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            m.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            km.setText(String.valueOf(String.format("%.7f", basis / 10000)));
                            break;
                        case "dm":
                            mm.setText(String.valueOf(String.format("%.4f", basis *100)));
                            cm.setText(String.valueOf(String.format("%.3f", basis * 10)));
                            dm.setText(String.valueOf(basis));
                            m.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            km.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            break;
                        case "m":
                            mm.setText(String.valueOf(String.format("%.5f", basis * 1000)));
                            cm.setText(String.valueOf(String.format("%.4f", basis * 100)));
                            dm.setText(String.valueOf(String.format("%.3f", basis * 10)));
                            m.setText(String.valueOf(basis));
                            km.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            break;
                        case "km":
                            mm.setText(String.valueOf(String.format("%.8f", basis * 1000000)));
                            cm.setText(String.valueOf(String.format("%.7f", basis * 100000)));
                            dm.setText(String.valueOf(String.format("%.6f", basis * 10000)));
                            m.setText(String.valueOf(String.format("%.5f", basis * 1000)));
                            km.setText(String.valueOf(basis));
                            break;
                        default:
                            mm.setText(0);
                            cm.setText(0);
                            dm.setText(0);
                            m.setText(0);
                            km.setText(0);
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
                    float basis = Float.parseFloat(String.valueOf(origineleHoeveelheid.getText().toString()));
                    switch (spinner.getSelectedItem().toString()) {
                        case "mm":
                            mm.setText(String.valueOf(basis));
                            cm.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            dm.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            m.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            km.setText(String.valueOf(String.format("%.8f", basis / 100000)));
                            break;
                        case "cm":
                            mm.setText(String.valueOf(String.format("%.2f", basis * 10)));
                            cm.setText(String.valueOf(basis));
                            dm.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            m.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            km.setText(String.valueOf(String.format("%.7f", basis / 10000)));
                            break;
                        case "dm":
                            mm.setText(String.valueOf(String.format("%.4f", basis *100)));
                            cm.setText(String.valueOf(String.format("%.3f", basis * 10)));
                            dm.setText(String.valueOf(basis));
                            m.setText(String.valueOf(String.format("%.4f", basis / 100)));
                            km.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            break;
                        case "m":
                            mm.setText(String.valueOf(String.format("%.5f", basis * 1000)));
                            cm.setText(String.valueOf(String.format("%.4f", basis * 100)));
                            dm.setText(String.valueOf(String.format("%.3f", basis * 10)));
                            m.setText(String.valueOf(basis));
                            km.setText(String.valueOf(String.format("%.5f", basis / 1000)));
                            break;
                        case "km":
                            cm.setText(String.valueOf(String.format("%.8f", basis * 1000000)));
                            cm.setText(String.valueOf(String.format("%.7f", basis * 100000)));
                            dm.setText(String.valueOf(String.format("%.6f", basis * 10000)));
                            m.setText(String.valueOf(String.format("%.5f", basis * 1000)));
                            km.setText(String.valueOf(basis));
                            break;
                        default:
                            mm.setText(0);
                            cm.setText(0);
                            dm.setText(0);
                            m.setText(0);
                            km.setText(0);
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
