package com.imgideongo.vfp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static final String PREFERENCES_NAME = "VFP";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context){
        sharedPreferences=context.getSharedPreferences(PREFERENCES_NAME,0);
        editor= sharedPreferences.edit();

    }

    public void setFireBaseToken(String token){
        editor.putString("TOKEN",token);
        editor.apply();
    }

    public String getFireBaseToken(){
        return  sharedPreferences.getString("TOKEN",null);
    }
    public void setLogoString(String logoString){
        editor.putString("LOGO",logoString);
        editor.apply();
    }

    public String getLogoString(){
        return sharedPreferences.getString("LOGO",null);
    }
    public void setUserName(String userName){
        editor.putString("USERNAME",userName);
        editor.apply();
    }

    public void setUserMobile(String mobile){
        editor.putString("MOBILE",mobile);
        editor.apply();
    }

    public void setClientName(String clientName){
        editor.putString("CNAME",clientName);
        editor.apply();
    }

    public String getClientName(){
        return sharedPreferences.getString("CNAME",null);
    }

    public void setUserPrimaryAddress(String userPrimaryAddress){
        editor.putString("USER_PRIMARY_ADDRESS",userPrimaryAddress);
        editor.apply();
    }



    public  void setUID(String UID){
        editor.putString("UID",UID);
        editor.apply();
    }

    public void setLoginInState(boolean loginInState){
        editor.putBoolean("LOGIN",loginInState);
        editor.apply();
    }

    public void setClientTimings(String clientTimings){
        editor.putString("CT",clientTimings);
        editor.apply();
    }
    public String getClientTimings(){
        return sharedPreferences.getString("CT",null);
    }

    public boolean getLoginState(){
        return sharedPreferences.getBoolean("LOGIN",false);
    }
    public String getUID(){
        return sharedPreferences.getString("UID",null);
    }

    public String getUserName(){
        return  sharedPreferences.getString("USERNAME",null);
    }
    public String getUserMobile(){
        return  sharedPreferences.getString("MOBILE",null);
    }
    public String getUserPrimaryAddress(){
        return sharedPreferences.getString("USER_PRIMARY_ADDRESS",null);
    }









}
