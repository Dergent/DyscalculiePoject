package be.thomasmore.dyscalculie;


 import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThemesFragment extends Fragment {


    public ThemesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themes, container, false);

        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(getActivity());
        //setContentView(R.layout.main);

        final RadioGroup radio = (RadioGroup) view.findViewById(R.id.radiogroup_themes);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button

                        Utils.changeToTheme(getActivity(), Utils.THEME_BLACKWHITE);
                        break;
                    case 1: // secondbutton
                        Utils.changeToTheme(getActivity(), Utils.THEME_PASTEL);

                        break;
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
