package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.saidelshibiny.badmintonplanningandroidapp.R;

public class LoginActivity extends AppCompatActivity implements LoginFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }
    //start the mainActivity
    Intent i = new Intent(this, MainActivity.class);
            i.putExtra(LoginFragment.USER_LOGGED_IN, isUser);
    startActivity(i);
}else {
        //if there is no user logged in, then display the fragment for the user to log in
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.login_content, new LoginFragment())
        .commit();
        }

@Override
public void onFragmentInteraction(Uri uri) {

        }


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
