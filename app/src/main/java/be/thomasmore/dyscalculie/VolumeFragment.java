package be.thomasmore.dyscalculie;


import android.os.Bundle;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class VolumeFragment extends Fragment {

    private Spinner spinner;
    private static final String[]eenheden = {"ml", "cl", "dl", "l"};

    public VolumeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.dropDown);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item , eenheden);
        spinner.setSelection(0);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView ml = view.findViewById((R.id.mlText));
        final TextView cl = view.findViewById((R.id.clText));
        final TextView dl = view.findViewById((R.id.dlText));
        final TextView liter = view.findViewById((R.id.lText));

        final EditText origineleHoeveelheid = view.findViewById(R.id.origineleHoeveelheid);
        origineleHoeveelheid.setText("1");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!origineleHoeveelheid.getText().toString().isEmpty()) {
                    float basis = Float.parseFloat(String.valueOf(origineleHoeveelheid.getText().toString()));
                    switch (spinner.getSelectedItem().toString()) {
                        case "ml":
                            ml.setText(String.valueOf(basis));
                            cl.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            dl.setText(String.valueOf(String.format("%.6f", basis / 100)));
                            liter.setText(String.valueOf(String.format("%.9f", basis / 1000)));
                            break;
                        case "cl":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 10)));
                            cl.setText(String.valueOf(basis));
                            dl.setText(String.valueOf(String.format("%.3f", basis / 100)));
                            liter.setText(String.valueOf(String.format("%.6f", basis / 1000)));
                            break;
                        case "dl":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 100)));
                            cl.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            dl.setText(String.valueOf(basis));
                            liter.setText(String.valueOf(String.format("%.3f", basis / 10)));
                            break;
                        case "l":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            cl.setText(String.valueOf(String.format("%.1f", basis * 100)));
                            dl.setText(String.valueOf(String.format("%.1f", basis * 10)));
                            liter.setText(String.valueOf(basis));
                            break;
                        default:
                            ml.setText("0");
                            cl.setText("0");
                            dl.setText("0");
                            liter.setText("0");
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

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!origineleHoeveelheid.getText().toString().isEmpty()) {
                    float basis = Float.parseFloat(String.valueOf(origineleHoeveelheid.getText().toString()));
                    switch (spinner.getSelectedItem().toString()) {
                        case "mg":
                            ml.setText(String.valueOf(basis));
                            cl.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            dl.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            liter.setText(String.valueOf(String.format("%.9f", basis / 1000000000)));
                            break;
                        case "g":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            cl.setText(String.valueOf(basis));
                            dl.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            liter.setText(String.valueOf(String.format("%.6f", basis / 1000000)));
                            break;
                        case "kg":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            cl.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            dl.setText(String.valueOf(basis));
                            liter.setText(String.valueOf(String.format("%.3f", basis / 1000)));
                            break;
                        case "ton":
                            ml.setText(String.valueOf(String.format("%.1f", basis * 1000000000)));
                            cl.setText(String.valueOf(String.format("%.1f", basis * 1000000)));
                            dl.setText(String.valueOf(String.format("%.1f", basis * 1000)));
                            liter.setText(String.valueOf(basis));
                            break;
                        default:
                            ml.setText("0");
                            cl.setText("0");
                            dl.setText("0");
                            liter.setText("0");
                            break;
                    }
                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

}
