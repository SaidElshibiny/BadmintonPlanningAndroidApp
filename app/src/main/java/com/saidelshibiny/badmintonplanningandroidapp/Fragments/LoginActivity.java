package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.saidelshibiny.badmintonplanningandroidapp.Database.User;
import com.saidelshibiny.badmintonplanningandroidapp.R;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    //keys for user sharedPref
    public static final String ADMIN_USERNAME = "USERNAME";
    public static final String ADMIN_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }

    //get the shared pref
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    //we check to see if the user is logged in by checking the shared preferences
    boolean isLoggedIn = sharedPref.getBoolean(LoginFragment.USER_LOGGED_IN, false);
        if(isLoggedIn){

            //if is logged in, then we grab  from the shared preferences
        String username = sharedPref.getString(LoginActivity.ADMIN_USERNAME, "");
        String password = sharedPref.getString(LoginActivity.ADMIN_PASSWORD, "");

        //create the user
        User isUser = new User(username, password);

        //start the mainActivity
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(LoginFragment.USER_LOGGED_IN, isUser);
        startActivity(i);
    }
    else {
        // If no user is logged in, display the LoginFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content, new LoginFragment())
                .commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*
  Kill the activity and take it out of the history when back is pressed
   */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            Log.d("KEYBOARD", "Hiding keyboard");
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}

