package com.saidelshibiny.badmintonplanningandroidapp.Fragments;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.R;
import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   MainFragment.OnFragmentInteractionListener,
                   CheckInPlayers.OnFragmentInteractionListener,
                   AddPlayerFragment.OnFragmentInteractionListener,
                   ProtectFragment.OnFragmentInteractionListener,
                   MatchingPlayers.OnFragmentInteractionListener,
                    ScoreFragment.OnFragmentInteractionListener,
                   FootworkDrills.OnFragmentInteractionListener,
                   Timer.OnFragmentInteractionListener,
                   Coaches.OnFragmentInteractionListener,
                   LoginFragment.OnFragmentInteractionListener,
                   CreditsFragment.OnFragmentInteractionListener,
                   Rules.OnFragmentInteractionListener,
                   SplashScreen.OnFragmentInteractionListener{


/**
 * @author Said Elshibiny
 * The main activity were we manage switching between pages and more
**/



    //Create fragment manager
    FragmentManager fm;

    //create the a public static variable for the fab
    public static FloatingActionButton fab;
    public static  Context context;

    //Variable for splash screen time on screen in milliseconds
    private static int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_content, new SplashScreen());
        // transaction.addToBackStack(null);
        transaction.commit();

        /**
         * @author Said Elshibiny
         * Handler created to launch the main fragment after 2000 milliseconds
         */
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new MainFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        },SPLASH_TIME);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


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
        // Inflate the menu; this adds items to the action bar if it is present.
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
//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_log_out) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(LoginFragment.USER_LOGGED_IN, false).apply();
            Intent intent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(intent);
        }

        else if(id == R.id.action_credits) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_content, new CreditsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       // fm = getSupportFragmentManager();

//        Fragment timerFragment = new Timer();

        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.add(R.id.main_content, timerFragment);

        if (id == R.id.nav_check_in) {
            transaction.replace(R.id.main_content, new CheckInPlayers());
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_match_players) {
            transaction.replace(R.id.main_content, new MatchingPlayers());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_footwork_drills) {
            transaction.replace(R.id.main_content, new FootworkDrills());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_timer) {
            transaction.replace(R.id.main_content, new Timer());
            transaction.addToBackStack(null);
            transaction.commit();
//            transaction.show(timerFragment);
//            transaction.commit();

        } else if (id == R.id.nav_coaches) {
            transaction.replace(R.id.main_content, new Coaches());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_rules) {
            transaction.replace(R.id.main_content, new Rules());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_email) {
            String[] UsersEmails = {"test1@test.com", "test2@test.com"};
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, UsersEmails);
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "Dear All,");
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}




