package be.thomasmore.dyscalculie;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences pref = this.getSharedPreferences("dyscalculie", 0);
        if (pref.getBoolean("toggle", false) == false){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("toggle", false);
            editor.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showThemesDialog();
            return true;
        }else if(id == R.id.about){
            showAboutDialog();
        }else if(id == R.id.toggleTextToSpeech){
            showToggleTextToSpeechDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.percentages) {
            replaceFragment(new PercentenFragment());
        } else if (id == R.id.geldberekenen) {
            replaceFragment(new GeldberekenenFragment());
        } else if (id == R.id.hoofdmenu) {
            replaceFragment(new HomeFragment());
        }else if (id == R.id.tijdBerekenen) {
            replaceFragment(new TijdBerekenenFragment());
        } else if (id == R.id.geldTellen) {
            replaceFragment(new GeldTellenFragment());
        } else if (id == R.id.datumsBerekenen) {
            replaceFragment(new DatumsBerkenen());
        } else if (id == R.id.history) {
            replaceFragment(new HistoryFragment());
        } else if (id == R.id.volumeOmzetten) {
            replaceFragment(new VolumeFragment());
        } else if (id == R.id.gewichtOmzetten) {
            replaceFragment(new GewichtOmzettenFragment());
        } else if (id == R.id.afstandenOmzetten){
            replaceFragment(new AfstandOmzettenFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public void onClickButton (View v) {
        int id = v.getId();

        if (id == R.id.solden) {
            replaceFragment(new PercentenFragment());
        } else if (id == R.id.wisselgeld) {
            replaceFragment(new GeldberekenenFragment());
        } else if (id == R.id.geldTellen) {
            replaceFragment(new GeldTellenFragment());
        } else if (id == R.id.tijdBerekenen) {
            replaceFragment(new TijdBerekenenFragment());
        } else if (id == R.id.datumsBerekenen) {
            replaceFragment(new DatumsBerkenen());
        } else if (id == R.id.gewichtOmzetten) {
            replaceFragment(new GewichtOmzettenFragment());
        } else if (id == R.id.volumeOmzetten) {
            replaceFragment(new VolumeFragment());
        } else if (id == R.id.afstandenOmzetten) {
            replaceFragment(new AfstandOmzettenFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void onClickButtonThemes (View v) {
        switch (v.getId()) {
            case R.id.defaultTheme:
                Utils.changeToTheme(this, Utils.THEME_DEFAULT);
                break;
            case R.id.zwartwit:
                Utils.changeToTheme(this, Utils.THEME_BLACKWHITE);
                break;
            case R.id.pastel:
                Utils.changeToTheme(this, Utils.THEME_PASTEL);
                break;
        }

    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_about, null);
        builder.setTitle("About")
                .setView(viewInflater);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showThemesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_themes, null);
        builder.setTitle("Thema's")
                .setView(viewInflater);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToggleTextToSpeechDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_toggle_tts, null);
        builder.setTitle("Tekst naar spraak")
                .setView(viewInflater);
        AlertDialog dialog = builder.create();
        Switch s = (Switch) viewInflater.findViewById(R.id.toggle);
        SharedPreferences pref = this.getSharedPreferences("dyscalculie", 0);
        s.setChecked(pref.getBoolean("toggle", false));
        dialog.show();
    }

    public void isToggled(View v){
        Switch s = v.findViewById(R.id.toggle);
        SharedPreferences pref = this.getSharedPreferences("dyscalculie", 0);
        SharedPreferences.Editor editor = pref.edit();
        if (s.isChecked()){
            editor.putBoolean("toggle", true);
        }else{
            editor.putBoolean("toggle", false);
        }
        boolean isToggled = s.isChecked();

        editor.commit();
    }


}
