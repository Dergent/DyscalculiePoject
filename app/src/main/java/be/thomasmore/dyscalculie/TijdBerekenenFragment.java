package be.thomasmore.dyscalculie;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


@RequiresApi(api = Build.VERSION_CODES.M)
public class TijdBerekenenFragment extends Fragment {

    TimePicker beginTimePicker;
    TimePicker endTimePicker;

    public TijdBerekenenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tijd_berekenen, container, false);

        //=(TimePicker)getView().findViewById(R.id.beginTimePicker);
        //TimePicker endTimePicker=(TimePicker)getView().findViewById(R.id.endTimePicker);

        final Button button = view.findViewById(R.id.buttonCalculate);
        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                calculate(v);
            }
        });

        beginTimePicker=(TimePicker)view.findViewById(R.id.beginTimePicker);
        beginTimePicker.setIs24HourView(true);

        endTimePicker=(TimePicker)view.findViewById(R.id.endTimePicker);
        endTimePicker.setIs24HourView(true);

        return view;
    }

    public void calculate(View v) {
        /*int hour;
        int minutes;
        int beginHour = beginTimePicker.getHour();
        int beginMinutes = beginTimePicker.getMinute();

        int endHour = endTimePicker.getHour();
        int endMinutes = endTimePicker.getMinute();

        TextView tijdverschil = (TextView) getView().findViewById(R.id.tijdverschil);

        hour = endHour - beginHour;
        minutes = endMinutes - beginMinutes;

        if (beginMinutes > endMinutes){
            hour--;
            minutes = 60 - beginMinutes + endMinutes;
        }

        tijdverschil.setText("Het verschil is " + hour + " uur en "+ minutes + " minuten");

        if (beginHour > endHour) {
            tijdverschil.setText("De begin tijd is later dan de eind tijd");
            if (beginMinutes > endMinutes) {
                tijdverschil.setText("Het verschil is " + hour + "uur en "+ minutes + "minuten");
            }
        }

        if (beginHour == endHour){
            if (beginMinutes > endMinutes){
                tijdverschil.setText("De begin tijd is later dan de eind tijd");
            }
        }*/


        int hour;
        int minutes;
        int beginHour = beginTimePicker.getHour();
        int beginMinutes = beginTimePicker.getMinute();

        int endHour = endTimePicker.getHour();
        int endMinutes = endTimePicker.getMinute();

        TextView tijdverschil = (TextView) getView().findViewById(R.id.tijdverschil);

        hour = endHour - beginHour;
        minutes = endMinutes - beginMinutes;

        if (beginMinutes > endMinutes){
            hour--;
            minutes = beginMinutes - endMinutes;
        }

        if (hour < 0){
            int hourCounter=0;
            while (!(endHour==beginHour)){
                if (beginHour == 24){
                    beginHour = 0;
                }
                beginHour++;
                hourCounter++;
            }
            hour = hourCounter;
            hour = 23 - beginHour + endHour;
            tijdverschil.setText("Het verschil is " + hour + "uur en "+ minutes + "minuten \n" +
                                    "(vandaag " + beginHour + ":" + beginMinutes + " en morgen " + endHour + ":" + endMinutes+")");
        }
        else{
            tijdverschil.setText("Het verschil is " + hour + " uur en "+ minutes + " minuten");
        }
        if (beginHour == endHour && beginMinutes > endMinutes){
            hour = 23;
            minutes = 60 - beginMinutes + endMinutes;
            tijdverschil.setText("Het verschil is " + hour + "uur en "+ minutes + "minuten \n" +
                    "(vandaag " + beginHour + ":" + beginMinutes + " en morgen " + endHour + ":" + endMinutes+")");
        }





        if (beginHour > endHour) {
            tijdverschil.setText("De begin tijd is later dan de eind tijd");
            if (beginMinutes > endMinutes) {
                tijdverschil.setText("Het verschil is " + hour + "uur en "+ minutes + "minuten");
            }
        }

        if (beginHour == endHour){
            if (beginMinutes > endMinutes){
                tijdverschil.setText("De begin tijd is later dan de eind tijd");
            }
        }
    }


}
