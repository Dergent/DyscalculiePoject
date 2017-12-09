package be.thomasmore.dyscalculie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GeldTellenFragment extends Fragment {
    List<String> filenames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geld_tellen, container, false);
        ImageView imageViewPlus = view.findViewById(R.id.plus1);
        ImageView imageViewMinus = view.findViewById(R.id.minus1);
        ImageView imageViewPlus2 = view.findViewById(R.id.plus2);
        ImageView imageViewMinus2 = view.findViewById(R.id.minus2);
        final List<ImageView> imageViewsPlus = new ArrayList<>();
        final List<ImageView> imageViewsMinus = new ArrayList<>();
        imageViewsPlus.add(imageViewPlus);
        imageViewsMinus.add(imageViewMinus);
        imageViewsPlus.add(imageViewPlus2);
        imageViewsMinus.add(imageViewMinus2);

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
            default:
                newNumber = 0;
                break;
        }
        editAmount.setText(String.valueOf(newNumber));
    }

    public void minusOne_onClick(View v) {
        int number;
        EditText editAmount = null;
        int newNumber;
        switch (v.getId()){
            case R.id.minus1:
                editAmount = getView().findViewById(R.id.hoeveel1cent);
                number = Integer.parseInt(editAmount.getText().toString());
                if (checkNumber(number)){
                    newNumber = number - 1;
                } else {
                    newNumber = number;
                }
                break;
            case R.id.minus2:
                editAmount = getView().findViewById(R.id.hoeveel2cent);
                number = Integer.parseInt(editAmount.getText().toString());
                if (checkNumber(number)){
                    newNumber = number - 1;
                } else {
                    newNumber = number;
                }
                break;
            default:
                newNumber = 0;
                break;
        }
        editAmount.setText(String.valueOf(newNumber));
    }

    public boolean checkNumber(int number) {
        if (number <= 0){
            return false;
        }
        return true;
    }

}
