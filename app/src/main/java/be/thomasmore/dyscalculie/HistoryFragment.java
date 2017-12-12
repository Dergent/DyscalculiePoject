package be.thomasmore.dyscalculie;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        LinearLayout mainLayout = view.findViewById(R.id.layout);
        SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 10;


        TextView textView = new TextView(getContext());
        textView.setText("Tijd berekening: " + pref.getString("tijd", ""));
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        mainLayout.addView(textView);

        TextView textView1 = new TextView(getContext());
        textView1.setText("Procent berekening: " + pref.getString("procent", ""));
        textView1.setTextSize(25);
        textView1.setGravity(Gravity.CENTER);
        textView1.setLayoutParams(layoutParams);
        mainLayout.addView(textView1);

        TextView textView2 = new TextView(getContext());
        textView2.setText("Procent berekening: " + pref.getString("datum", ""));
        textView2.setTextSize(25);
        textView2.setGravity(Gravity.CENTER);
        textView2.setLayoutParams(layoutParams);
        mainLayout.addView(textView2);

        TextView textView3 = new TextView(getContext());
        textView3.setText("Geld berekening: " + pref.getString("geldBerekening", ""));
        textView3.setTextSize(25);
        textView3.setGravity(Gravity.CENTER);
        textView3.setLayoutParams(layoutParams);
        mainLayout.addView(textView3);

        TextView textView4 = new TextView(getContext());
        textView4.setText("Geld tellen: " + pref.getString("geldTellen", ""));
        textView4.setTextSize(25);
        textView4.setGravity(Gravity.CENTER);
        textView4.setLayoutParams(layoutParams);
        mainLayout.addView(textView4);

        return view;
    }
}
