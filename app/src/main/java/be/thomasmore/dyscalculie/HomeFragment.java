package be.thomasmore.dyscalculie;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextToSpeech textToSpeech;

    public HomeFragment() {
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        //fragmentTransaction.addToBackStack(fragment.toString());
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final SharedPreferences pref = getContext().getSharedPreferences("dyscalculie", 0);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final TextClock textClock = view.findViewById(R.id.homeClock);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("nl_NL"));
                }
            }
        });

        textClock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (pref.getBoolean("toggle", false)){
                    textToSpeech.speak(textClock.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });


        return view;
    }

}
