package com.vugido.vugidoupdate.app_settings;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static final String PREFERENCES_NAME = "VUGIDO_";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context){
        sharedPreferences=context.getSharedPreferences(PREFERENCES_NAME,0);
        editor= sharedPreferences.edit();

    }

    public void tokenChanged(boolean s){

        editor.putBoolean("TOKEN_CHANGED",s);
        editor.apply();

    }
    public boolean isTokenChanged(){

        return sharedPreferences.getBoolean("TOKEN_CHANGED",false);
    }


    public void setCartCount(int Count){
        editor.putInt("COUNT",Count);
        editor.apply();

    }
    public int getCartCount(){

        return sharedPreferences.getInt("COUNT",0);
    }

    public void setUserName(String userName){
        editor.putString("USERNAME",userName);
        editor.apply();
    }

    public void setUserMobile(String mobile){
        editor.putString("MOBILE",mobile);
        editor.apply();
    }

    public String getUserName(){
        return  sharedPreferences.getString("USERNAME",null);
    }

    public String getUserMobile(){
        return  sharedPreferences.getString("MOBILE",null);
    }

    public void setUserPrimaryAddress(boolean userPrimaryAddress){
        editor.putBoolean("USER_PRIMARY_ADDRESS",userPrimaryAddress);
        editor.apply();
    }
    public boolean getUserPrimaryAddress(){
        return sharedPreferences.getBoolean("USER_PRIMARY_ADDRESS",false);
    }
    public  void setUID(String UID){
        editor.putString("UID",UID);
        editor.apply();
    }
    public String getUID(){
        return sharedPreferences.getString("UID",null);
    }

    public void setVerified(boolean status){
        editor.putBoolean("VERIFIED",status);
        editor.apply();
    }
    public boolean getVerified(){
        return sharedPreferences.getBoolean("VERIFIED",false);
    }


    // slot variables
    public void setSlotStatus(boolean status){
        editor.putBoolean("SLOT_STATUS",status);
        editor.apply();
    }
    public boolean getSlotStatus(){
        return sharedPreferences.getBoolean("SLOT_STATUS",false);
    }
    public void setSlotId(int status){
        editor.putInt("SLOT_ID",status);
        editor.apply();
    }
    public int getSlotId(){
        return sharedPreferences.getInt("SLOT_ID",0);
    }

    public void setSlotName(String status){
        editor.putString("SLOT_NAME",status);
        editor.apply();
    }
    public String getSlotName(){
        return sharedPreferences.getString("SLOT_NAME",null);
    }

    public void setIsTomorrow(boolean status){
        editor.putBoolean("ORDER_DAY",status);
        editor.apply();
    }
    public boolean getIsTomorrow(){
        return sharedPreferences.getBoolean("ORDER_DAY",false);
    }




    // ----------------------------------------------


    public void setOrderedTime(String clientName){
        editor.putString("Time",clientName);
        editor.apply();
    }
    public String getOrderedTime(){
        return sharedPreferences.getString("Time",null);
    }








    //if true referred else normal
    public void setNormalOrReferred(boolean referred){
        editor.putBoolean("REFERRED",referred);
        editor.apply();

    }
    public boolean getNormalOrReferred(){
        return sharedPreferences.getBoolean("REFERRED",false);
    }
    public void setFireBaseToken(String token){
        editor.putString("TOKEN",token);
        editor.apply();
    }

    public String getFireBaseToken(){
        return  sharedPreferences.getString("TOKEN",null);
    }








    public void setReferralCode(String ReferralCode){
        editor.putString("RC_ID",ReferralCode);
        editor.apply();
    }
    public String getReferralCode(){
       return sharedPreferences.getString("RC_ID",null);
    }
    public void setAPIKEY(String apikey) {
        editor.putString("APIKEY",apikey);
        editor.apply();
    }
    public void setReferralCodeStatus(boolean status){
        editor.putBoolean("REFERRAL_STATUS",status);
        editor.apply();
    }
    public boolean getReferralStatus(){
        return sharedPreferences.getBoolean("REFERRAL_STATUS",false);
    }

    public void setUserFirstOrderStatus(int firstOrderStatus){
        editor.putInt("FIRST_ORDER_STATUS",firstOrderStatus);
        editor.apply();
    }

    public int getFirstOrderStatus(){
        return sharedPreferences.getInt("FIRST_ORDER_STATUS",0);
    }

    public String getAPIKEY(){
        return sharedPreferences.getString("APIKEY",null);
    }

    public void setFirstTime(boolean first_time) {
        editor.putBoolean("FIRST_TIME",first_time);
        editor.apply();
    }


    public  boolean getFirstTime(){
        return  sharedPreferences.getBoolean("FIRST_TIME",false);
    }
}
