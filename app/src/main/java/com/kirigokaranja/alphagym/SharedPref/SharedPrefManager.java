package com.kirigokaranja.alphagym.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.kirigokaranja.alphagym.Model.User;

public class SharedPrefManager {

    private static SharedPrefManager sharedPrefManager;
    private static Context mcontext;

    private static final String SHARED_PREF_NAME = "sharedprefalpha";
    private static final String USER_ID = "kuserid";
    private static final String USER_FNAME = "kuserfname";
    private static final String USER_LNAME = "kuserlname";
    private static final String USER_EMAIL = "kemail";
    private static final String USER_PASSOWRD = "kpassword";

    public SharedPrefManager(Context context) {

        mcontext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (sharedPrefManager == null) {
            sharedPrefManager = new SharedPrefManager(context);
        }
        return sharedPrefManager;
    }

    public boolean Login(User user){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(USER_ID, user.getId());
        editor.putString(USER_FNAME, user.getFirst_name());
        editor.putString(USER_LNAME, user.getLast_name());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_PASSOWRD, user.getPassword());
        editor.apply();
        return true;
    }

    public boolean areLoggedIn(){

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(USER_EMAIL, null) != null)
            return true;
        return false;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(USER_ID, 0),
                sharedPreferences.getString(USER_FNAME, null),
                sharedPreferences.getString(USER_LNAME, null),
                sharedPreferences.getString(USER_EMAIL, null),
                sharedPreferences.getString(USER_PASSOWRD, null)
        );
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
