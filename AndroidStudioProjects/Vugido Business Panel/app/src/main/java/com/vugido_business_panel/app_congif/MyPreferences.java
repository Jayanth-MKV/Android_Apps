package com.vugido_business_panel.app_congif;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static final String PREFERENCES_NAME = "VUGIDO_CLIENT_PANEL";
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

    ////logo

    public void setLogoUrl(String logoUrl) {
        editor.putString("LOGO", logoUrl);
        editor.apply();
    }

    public String getLogoUrl() {
        return sharedPreferences.getString("LOGO",null);
    }



    //logo

    // contact...


    public void setContact(String contact) {
        editor.putString("CONTACT", contact);
        editor.apply();
    }

    public String getContact() {
        return sharedPreferences.getString("CONTACT", null);
    }



    //contact


    //location name
    public void setLocationName(String locationName){

        editor.putString("LOCATION_NAME",locationName);
        editor.apply();
    }
    public String getLocationName(){

        return sharedPreferences.getString("LOCATION_NAME",null);
    }

    ///////location name

    /////// longitude

    public void setLongitude(String longitude) {
        editor.putString("LONGITUDE", longitude);
        editor.apply();
    }

    public String getLongitude() {
        return sharedPreferences.getString("LONGITUDE", null);
    }



    // longitude


    //latitude

    public void setLatitude(String latitude ) {
        editor.putString("LATITUDE", latitude);
        editor.apply();
    }

    public String getLatitude() {
        return sharedPreferences.getString("LATITUDE", null);
    }


    // latitude


    // created on

    public void setCreatedOn(String createdOn ) {
        editor.putString("CREATED_ON", createdOn);
        editor.apply();
    }

    public String getCreatedOn() {
        return sharedPreferences.getString("CREATED_ON", null);
    }


    // created on





    public void setBusinessName(String BusinessName) {
        editor.putString("BUSINESS_NAME", BusinessName);
        editor.apply();
    }

    public String getBusinessName() {
        return sharedPreferences.getString("BUSINESS_NAME", null);
    }


    public void setClientName(String adminName) {
        editor.putString("ADMIN_NAME", adminName);
        editor.apply();
    }

    public void setClientEmail(String email) {
        editor.putString("EMAIL", email);
        editor.apply();
    }

    public String getClientName() {
        return sharedPreferences.getString("CLIENT_NAME", null);
    }

    public String getClientEmail() {
        return sharedPreferences.getString("EMAIL", null);
    }

    public void setCID(String CID) {
        editor.putString("CID", CID);
        editor.apply();
    }

    public String getCID() {
        return sharedPreferences.getString("CID", null);
    }

    public void setVerified(boolean status) {
        editor.putBoolean("VERIFIED", status);
        editor.apply();
    }

    public boolean getVerified() {
        return sharedPreferences.getBoolean("VERIFIED", false);
    }


}