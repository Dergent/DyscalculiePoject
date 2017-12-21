package be.thomasmore.dyscalculie;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        LinearLayout mainLayout = view.findViewById(R.id.layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 10;

        LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(50,50);
        layoutParams.setMargins(10,10,10,100);
        layoutParamsImage.gravity = Gravity.CENTER;

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.clock);
        imageView.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView);

        TextView textView = new TextView(getContext());
        textView.setText("Tijd berekening: " + pref.getString("tijd", ""));
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(0, 15, 0, 0);
        textView.setLayoutParams(layoutParams);
        mainLayout.addView(textView);

        ImageView imageView1 = new ImageView(getContext());
        imageView1.setImageResource(R.drawable.percentage);
        imageView1.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView1);

        TextView textView1 = new TextView(getContext());
        textView1.setText("Procent berekening: " + pref.getString("procent", ""));
        textView1.setTextSize(25);
        textView1.setGravity(Gravity.CENTER);
        textView1.setTextColor(Color.BLACK);
        textView1.setPadding(0, 15, 0, 0);
        textView1.setLayoutParams(layoutParams);
        mainLayout.addView(textView1);

        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageResource(R.drawable.calendar);
        imageView2.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView2);

        TextView textView2 = new TextView(getContext());
        textView2.setText("Datum berekening: " + pref.getString("datum", ""));
        textView2.setTextSize(25);
        textView2.setGravity(Gravity.CENTER);
        textView2.setTextColor(Color.BLACK);
        textView2.setPadding(0, 10, 0, 0);
        textView2.setLayoutParams(layoutParams);
        mainLayout.addView(textView2);

        ImageView imageView3 = new ImageView(getContext());
        imageView3.setImageResource(R.drawable.euro);
        imageView3.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView3);

        TextView textView3 = new TextView(getContext());
        textView3.setText("Geld berekening: " + pref.getString("geldBerekening", ""));
        textView3.setTextSize(25);
        textView3.setGravity(Gravity.CENTER);
        textView3.setTextColor(Color.BLACK);
        textView3.setPadding(0, 10, 0, 0);
        textView3.setLayoutParams(layoutParams);
        mainLayout.addView(textView3);

        ImageView imageView4 = new ImageView(getContext());
        imageView4.setImageResource(R.drawable.geld);
        imageView4.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView4);

        TextView textView4 = new TextView(getContext());
        textView4.setText("Geld tellen: " + pref.getString("geldTellen", ""));
        textView4.setTextSize(25);
        textView4.setGravity(Gravity.CENTER);
        textView4.setTextColor(Color.BLACK);
        textView4.setPadding(0, 10, 0, 0);
        textView4.setLayoutParams(layoutParams);
        mainLayout.addView(textView4);

        ImageView imageView5 = new ImageView(getContext());
        imageView5.setImageResource(R.drawable.gewicht);
        imageView5.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView5);

        TextView textView5 = new TextView(getContext());
        textView5.setText("Gewicht omzetten: " + pref.getString("gewicht", ""));
        textView5.setTextSize(25);
        textView5.setGravity(Gravity.CENTER);
        textView5.setTextColor(Color.BLACK);
        textView5.setPadding(0, 10, 0, 0);
        textView5.setLayoutParams(layoutParams);
        mainLayout.addView(textView5);

        ImageView imageView6 = new ImageView(getContext());
        imageView6.setImageResource(R.drawable.bottle);
        imageView6.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView6);

        TextView textView6 = new TextView(getContext());
        textView6.setText("Volume omzetten: " + pref.getString("volume", ""));
        textView6.setTextSize(25);
        textView6.setGravity(Gravity.CENTER);
        textView6.setTextColor(Color.BLACK);
        textView6.setPadding(0, 10, 0, 0);
        textView6.setLayoutParams(layoutParams);
        mainLayout.addView(textView6);

        ImageView imageView7 = new ImageView(getContext());
        imageView7.setImageResource(R.drawable.ruler);
        imageView7.setLayoutParams(layoutParamsImage);
        mainLayout.addView(imageView7);

        TextView textView7 = new TextView(getContext());
        textView7.setText("Afstand omzetten: " + pref.getString("afstand", ""));
        textView7.setTextSize(25);
        textView7.setGravity(Gravity.CENTER);
        textView7.setTextColor(Color.BLACK);
        textView7.setPadding(0, 10, 0, 0);
        textView7.setLayoutParams(layoutParams);
        mainLayout.addView(textView7);

        return view;
    }
}
