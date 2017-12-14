package be.thomasmore.dyscalculie;

/**
 * Created by Zento on 14/12/2017.
 */
import android.app.Activity;
import android.content.Intent;

public class Utils {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_BLACKWHITE = 1;
    public final static int THEME_PASTEL = 2;
    /** * Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_BLACKWHITE:
                activity.setTheme(R.style.ZwartWit);
                break;
            case THEME_PASTEL:
                activity.setTheme(R.style.Pastel);
                break;
        }
    }
}

