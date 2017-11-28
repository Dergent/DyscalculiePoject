package be.thomasmore.dyscalculie;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GeldberekenenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geldberekenen, container, false);
        final Button button = view.findViewById(R.id.buttonCalculate);
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

        int teBetalen = Integer.parseInt(tTeBetalen.getText().toString());
        double gegevenBedrag = Integer.parseInt(tGegevenBedrag.getText().toString());
        if (teBetalen <= gegevenBedrag) {
            double wisselgeld = gegevenBedrag - teBetalen;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            tWisselgeld.setText(decimalFormat.format(wisselgeld));
        } else {
            double geldTeKort = teBetalen - gegevenBedrag;
            String teKort = "Je komt :" + geldTeKort + " euro te kort!";
            tWisselgeld.setText(teKort);
        }
    }
}
