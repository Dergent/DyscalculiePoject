package be.thomasmore.dyscalculie;

import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeldTellenFragment extends Fragment {
    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geld_tellen, container, false);

        ImageView cent1Plus = view.findViewById(R.id.plus1);
        ImageView cent1Min = view.findViewById(R.id.minus1);

        ImageView cent2Plus = view.findViewById(R.id.plus2);
        ImageView cent2Min = view.findViewById(R.id.minus2);

        ImageView cent5Plus = view.findViewById(R.id.plus5);
        ImageView cent5Min = view.findViewById(R.id.minus5);

        ImageView cent10Plus = view.findViewById(R.id.plus10);
        ImageView cent10Min = view.findViewById(R.id.minus10);

        ImageView cent20Plus = view.findViewById(R.id.plus20);
        ImageView cent20Min = view.findViewById(R.id.minus20);

        ImageView cent50Plus = view.findViewById(R.id.plus50);
        ImageView cent50Min = view.findViewById(R.id.minus50);

        ImageView euro1Plus = view.findViewById(R.id.plus1euro);
        ImageView euro1Min = view.findViewById(R.id.minus1euro);

        ImageView euro2Plus = view.findViewById(R.id.plus2euro);
        ImageView euro2Min = view.findViewById(R.id.minus2euro);

        ImageView euro5Plus = view.findViewById(R.id.plus5euro);
        ImageView euro5Min = view.findViewById(R.id.minus5euro);

        ImageView euro10Plus = view.findViewById(R.id.plus10euro);
        ImageView euro10Min = view.findViewById(R.id.minus10euro);

        ImageView euro20Plus = view.findViewById(R.id.plus20euro);
        ImageView euro20Min = view.findViewById(R.id.minus20euro);

        ImageView euro50Plus = view.findViewById(R.id.plus50euro);
        ImageView euro50Min = view.findViewById(R.id.minus50euro);

        ImageView euro100Plus = view.findViewById(R.id.plus100euro);
        ImageView euro100Min = view.findViewById(R.id.minus100euro);

        ImageView euro200Plus = view.findViewById(R.id.plus200euro);
        ImageView euro200Min = view.findViewById(R.id.minus200euro);

        ImageView euro500Plus = view.findViewById(R.id.plus500euro);
        ImageView euro500Min = view.findViewById(R.id.minus500euro);

        final List<ImageView> imageViewsPlus = new ArrayList<>();
        final List<ImageView> imageViewsMinus = new ArrayList<>();
        imageViewsPlus.add(cent1Plus);
        imageViewsMinus.add(cent1Min);
        imageViewsPlus.add(cent2Plus);
        imageViewsMinus.add(cent2Min);
        imageViewsPlus.add(cent5Plus);
        imageViewsMinus.add(cent5Min);
        imageViewsPlus.add(cent10Plus);
        imageViewsMinus.add(cent10Min);
        imageViewsPlus.add(cent20Plus);
        imageViewsMinus.add(cent20Min);
        imageViewsPlus.add(cent50Plus);
        imageViewsMinus.add(cent50Min);
        imageViewsPlus.add(euro1Plus);
        imageViewsMinus.add(euro1Min);
        imageViewsPlus.add(euro2Plus);
        imageViewsMinus.add(euro2Min);
        imageViewsPlus.add(euro5Plus);
        imageViewsMinus.add(euro5Min);
        imageViewsPlus.add(euro10Plus);
        imageViewsMinus.add(euro10Min);
        imageViewsPlus.add(euro20Plus);
        imageViewsMinus.add(euro20Min);
        imageViewsPlus.add(euro50Plus);
        imageViewsMinus.add(euro50Min);
        imageViewsPlus.add(euro100Plus);
        imageViewsMinus.add(euro100Min);
        imageViewsPlus.add(euro200Plus);
        imageViewsMinus.add(euro200Min);
        imageViewsPlus.add(euro500Plus);
        imageViewsMinus.add(euro500Min);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });

        for (ImageView imageView: imageViewsPlus) {
            imageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    plusOne_onClick(v);
                }
            });
        }
        for (ImageView imageView: imageViewsMinus) {
            imageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    minusOne_onClick(v);
                }
            });
        }

        Button button = view.findViewById(R.id.buttonCalculate);
        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                calculate_onClick(v);
            }
        });
        return view;
    }

    public void plusOne_onClick(View v){
        int number;
        EditText editAmount = null;
        int newNumber;
        switch (v.getId()){
            case R.id.plus1:
                editAmount = getView().findViewById(R.id.hoeveel1cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus2:
                editAmount = getView().findViewById(R.id.hoeveel2cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus5:
                editAmount = getView().findViewById(R.id.hoeveel5cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus10:
                editAmount = getView().findViewById(R.id.hoeveel10cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus20:
                editAmount = getView().findViewById(R.id.hoeveel20cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus50:
                editAmount = getView().findViewById(R.id.hoeveel50cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus1euro:
                editAmount = getView().findViewById(R.id.hoeveel1euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus2euro:
                editAmount = getView().findViewById(R.id.hoeveel2euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus5euro:
                editAmount = getView().findViewById(R.id.hoeveel5euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus10euro:
                editAmount = getView().findViewById(R.id.hoeveel10euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus20euro:
                editAmount = getView().findViewById(R.id.hoeveel20euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus50euro:
                editAmount = getView().findViewById(R.id.hoeveel50euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus100euro:
                editAmount = getView().findViewById(R.id.hoeveel100euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus200euro:
                editAmount = getView().findViewById(R.id.hoeveel200euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            case R.id.plus500euro:
                editAmount = getView().findViewById(R.id.hoeveel500euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = number + 1;
                break;
            default:
                newNumber = 0;
                break;
        }
        editAmount.setText(String.valueOf(newNumber));
        textToSpeech.speak(editAmount.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    public void minusOne_onClick(View v) {
        int number;
        EditText editAmount = null;
        int newNumber;
        switch (v.getId()){
            case R.id.minus1:
                editAmount = getView().findViewById(R.id.hoeveel1cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus2:
                editAmount = getView().findViewById(R.id.hoeveel2cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus5:
                editAmount = getView().findViewById(R.id.hoeveel5cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus10:
                editAmount = getView().findViewById(R.id.hoeveel10cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus20:
                editAmount = getView().findViewById(R.id.hoeveel20cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus50:
                editAmount = getView().findViewById(R.id.hoeveel50cent);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus1euro:
                editAmount = getView().findViewById(R.id.hoeveel1euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus2euro:
                editAmount = getView().findViewById(R.id.hoeveel2euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus5euro:
                editAmount = getView().findViewById(R.id.hoeveel5euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus10euro:
                editAmount = getView().findViewById(R.id.hoeveel10euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus20euro:
                editAmount = getView().findViewById(R.id.hoeveel20euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus50euro:
                editAmount = getView().findViewById(R.id.hoeveel50euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus100euro:
                editAmount = getView().findViewById(R.id.hoeveel100euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus200euro:
                editAmount = getView().findViewById(R.id.hoeveel200euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            case R.id.minus500euro:
                editAmount = getView().findViewById(R.id.hoeveel500euro);
                number = Integer.parseInt(editAmount.getText().toString());
                newNumber = calculateNumberMinus(number);
                break;
            default:
                newNumber = 0;
                break;
        }
        editAmount.setText(String.valueOf(newNumber));
        textToSpeech.speak(editAmount.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    public int calculateNumberMinus(int number) {
        if (number > 0){
           return number - 1;
        } else {
            return number;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculate_onClick(View v){
        double total;

        EditText cent1 = getView().findViewById(R.id.hoeveel1cent);
        total = (Double.parseDouble(cent1.getText().toString()))/100;
        EditText cent2 = getView().findViewById(R.id.hoeveel2cent);
        total += (Double.parseDouble(cent2.getText().toString()))*2/100;
        EditText cent5 = getView().findViewById(R.id.hoeveel5cent);
        total += (Double.parseDouble(cent5.getText().toString()))*5/100;
        EditText cent10 = getView().findViewById(R.id.hoeveel10cent);
        total += (Double.parseDouble(cent10.getText().toString()))*10/100;
        EditText cent20 = getView().findViewById(R.id.hoeveel20cent);
        total += (Double.parseDouble(cent20.getText().toString()))*20/100;
        EditText cent50 = getView().findViewById(R.id.hoeveel50cent);
        total += (Double.parseDouble(cent50.getText().toString()))*50/100;
        EditText euro1 = getView().findViewById(R.id.hoeveel1euro);
        total += Double.parseDouble(euro1.getText().toString());
        EditText euro2 = getView().findViewById(R.id.hoeveel2euro);
        total += Double.parseDouble(euro2.getText().toString())*2;
        EditText euro5 = getView().findViewById(R.id.hoeveel5euro);
        total += Double.parseDouble(euro5.getText().toString())*5;
        EditText euro10 = getView().findViewById(R.id.hoeveel10euro);
        total += Double.parseDouble(euro10.getText().toString())*10;
        EditText euro20 = getView().findViewById(R.id.hoeveel20euro);
        total += Double.parseDouble(euro20.getText().toString())*20;
        EditText euro50 = getView().findViewById(R.id.hoeveel50euro);
        total += Double.parseDouble(euro50.getText().toString())*50;
        EditText euro100 = getView().findViewById(R.id.hoeveel100euro);
        total += Double.parseDouble(euro100.getText().toString())*100;
        EditText euro200 = getView().findViewById(R.id.hoeveel200euro);
        total += Double.parseDouble(euro200.getText().toString())*200;
        EditText euro500 = getView().findViewById(R.id.hoeveel500euro);
        total += Double.parseDouble(euro500.getText().toString())*500;

        TextView textView = getView().findViewById(R.id.totaal);
        textView.setText(String.format("%.2f", total));
        textToSpeech.speak(String.format("%.2f", total) + " euro", TextToSpeech.QUEUE_FLUSH, null);

        SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("geldTellen", "totaal is " + String.format("%.2f", total) + " euro");
        editor.commit();
    }

}
