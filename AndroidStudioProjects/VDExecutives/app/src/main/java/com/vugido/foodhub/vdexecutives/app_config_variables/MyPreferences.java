package com.vugido.foodhub.vdexecutives.app_config_variables;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static final String PREFERENCES_NAME = "VUGIDO_DELIVERY_AGENT";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
        editor = sharedPreferences.edit();

    }

    public void setFireBaseToken(String token){
        editor.putString("TOKEN",token);
        editor.apply();
    }

    public String getFireBaseToken(){
        return  sharedPreferences.getString("TOKEN",null);
    }



    public void tokenChanged(boolean s){

        editor.putBoolean("TOKEN_CHANGED",s);
        editor.apply();

    }
    public boolean isTokenChanged(){

        return sharedPreferences.getBoolean("TOKEN_CHANGED",false);
    }
    public void setUserName(String userName) {
        editor.putString("USERNAME", userName);
        editor.apply();
    }

    public void setUserMobile(String mobile) {
        editor.putString("MOBILE", mobile);
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString("USERNAME", null);
    }

    public String getUserMobile() {
        return sharedPreferences.getString("MOBILE", null);
    }

    public void setUserPrimaryAddress(boolean userPrimaryAddress) {
        editor.putBoolean("USER_PRIMARY_ADDRESS", userPrimaryAddress);
        editor.apply();
    }

    public boolean getUserPrimaryAddress() {
        return sharedPreferences.getBoolean("USER_PRIMARY_ADDRESS", false);
    }

    public void setDID(String DID) {
        editor.putString("DID", DID);
        editor.apply();
    }

    public String getDID() {
        return sharedPreferences.getString("DID", null);
    }

    public void setVerified(boolean status) {
        editor.putBoolean("VERIFIED", status);
        editor.apply();
    }

    public boolean getVerified() {
        return sharedPreferences.getBoolean("VERIFIED", false);
    }

}

