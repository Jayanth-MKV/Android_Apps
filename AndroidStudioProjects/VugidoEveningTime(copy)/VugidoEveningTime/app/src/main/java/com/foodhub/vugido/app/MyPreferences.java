package com.foodhub.vugido.app;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static final String PREFERENCES_NAME = "VUGIDO_FOOD_HUB";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context){
        sharedPreferences=context.getSharedPreferences(PREFERENCES_NAME,0);
        editor= sharedPreferences.edit();

    }

    public float getCouponValue(){

        return sharedPreferences.getFloat("COUPON",0);
    }

    public void setCouponValue(Float s){

        editor.putFloat("COUPON",s);
        editor.apply();

    }


    public void setGmail(String s){

        editor.putString("GMAIL",s);
        editor.apply();

    }
    public String getLM(){

        return sharedPreferences.getString("LM",null);
    }

    public void setLM(String s){

        editor.putString("LM",s);
        editor.apply();

    }
    public String getDNO(){

        return sharedPreferences.getString("DNO",null);
    }

    public void setDNO(String s){

        editor.putString("DNO",s);
        editor.apply();

    }
    public String getGmail(){

        return sharedPreferences.getString("GMAIL",null);
    }


    public String getOrderablePro(){

        return sharedPreferences.getString("O_PRO",null);
    }

    public void setOrderablePro(String s){

        editor.putString("O_PRO",s);
        editor.apply();

    }












    //--------------------------------------------------------------------------

    public void setAPP_ON(int s){

        editor.putInt("APP_ON",s);
        editor.apply();

    }
    public int isAPP_ON(){

        return sharedPreferences.getInt("APP_ON",0);
    }

    public void setDC(int Count){
        editor.putInt("DEL_DC",Count);
        editor.apply();

    }


    public int getDC(){
        return sharedPreferences.getInt("DEL_DC",0);


    }



    public void setTableNumber(int Count){
        editor.putInt("T_NO",Count);
        editor.apply();

    }
    public int getCID(){

        return sharedPreferences.getInt("CID",0);
    }
    public int getTableNumber(){

        return sharedPreferences.getInt("T_NO",0);
    }




    public String getCartProducts(){

        return sharedPreferences.getString("IN_CART_PID",null);
    }

    public void setCartProducts(String string){
        editor.putString("IN_CART_PID",string);
        editor.apply();

    }



    public int getCoinsCount(){

        return sharedPreferences.getInt("CCOUNT",0);
    }


    public void tokenChanged(boolean s){

        editor.putBoolean("TOKEN_CHANGED",s);
        editor.apply();

    }
    public boolean isTokenChanged(){

        return sharedPreferences.getBoolean("TOKEN_CHANGED",false);
    }


    public void setCartPrice(float cartPrice){
        editor.putFloat("CP",cartPrice);
        editor.apply();

    }
    public float getCartPrice(){

        return sharedPreferences.getFloat("CP",0);
    }


    public void setCartCount(int Count){
        editor.putInt("COUNT",Count);
        editor.apply();

    }
    public int getCartCount(){

        return sharedPreferences.getInt("COUNT",0);
    }


    public void setOrderableCartCount(int Count){
        editor.putInt("ORDERABLE_COUNT",Count);
        editor.apply();

    }
    public int getOrderableCartCount(){

        return sharedPreferences.getInt("ORDERABLE_COUNT",0);
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

    public void setUserPrimaryAddress(String userPrimaryAddress){
        editor.putString("USER_PRIMARY_ADDRESS",userPrimaryAddress);
        editor.apply();
    }
    public String getMapPhone(){
        return sharedPreferences.getString("MAP_PHONE",null);
    }

    public void setMapPhone(String userPrimaryAddress){
        editor.putString("MAP_PHONE",userPrimaryAddress);
        editor.apply();
    }
    public String getUserPrimaryAddress(){
        return sharedPreferences.getString("USER_PRIMARY_ADDRESS",null);
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


    public void setBaseLocationURL(String status){
        editor.putString("BLURL",status);
        editor.apply();
    }
    public String getBaseLocationURL(){
        return sharedPreferences.getString("BLURL",null);
    }

    public void setBaseLocationName(String status){
        editor.putString("BLNAME",status);
        editor.apply();
    }
    public String getBaseLocationName(){
        return sharedPreferences.getString("BLNAME",null);
    }

    public void setBaseLocationZIP(String status){
        editor.putString("BLZIP",status);
        editor.apply();
    }
    public String getBaseLocationZIP(){
        return sharedPreferences.getString("BLZIP",null);
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

    public  boolean getIsSignUp(){
        return  sharedPreferences.getBoolean("IS_SIGN_UP",false);
    }
    public void isSignUp(boolean b) {
        editor.putBoolean("IS_SIGN_UP",b);
        editor.apply();

    }
    public void setChannelId(int channelId) {
        editor.putInt("CH_ID",channelId);
        editor.apply();
    }
    public int getChannelId() {

        return sharedPreferences.getInt("CH_ID",0);
    }

    public void setDS(int distanceserved) {

        editor.putInt("DIS_SER",distanceserved);
        editor.apply();
    }

    public int getDS() {

        return sharedPreferences.getInt("DIS_SER",0);

    }

    public void setSM(String servicemsg) {
        editor.putString("SER_MSG",servicemsg);
        editor.apply();
    }
    public String getSM() {

        return sharedPreferences.getString("SER_MSG",null);

    }

    public void setDAC(int deliveryappliedfrom) {
        editor.putInt("DEL_CH_CR",deliveryappliedfrom);
        editor.apply();
    }

    public int getDAC() {

        return sharedPreferences.getInt("DEL_CH_CR",0);

    }

    public void setDelTimeMsg(String deliverytime) {
        editor.putString("SER_MSG",deliverytime);
        editor.apply();
    }

    public String getDelTimeMsg() {

        return sharedPreferences.getString("SER_MSG",null);

    }
    public void setServiceFrom(String servicefrom) {
        editor.putString("SER_FROM",servicefrom);
        editor.apply();

    }

    public String getServiceFrom() {

        return sharedPreferences.getString("SER_FROM",null);

    }

    public void setServiceTill(String servicetill) {
        editor.putString("SER_END",servicetill);
        editor.apply();
    }

    public String getServiceTill() {

        return sharedPreferences.getString("SER_END",null);

    }



    public void setLocLat(String servicetill) {
        editor.putString("LOC_LAT",servicetill);
        editor.apply();
    }

    public String getLocLat() {

        return sharedPreferences.getString("LOC_LAT",null);

    }


    public void setLocLan(String servicetill) {
        editor.putString("LOC_LON",servicetill);
        editor.apply();
    }

    public String getLocLan() {

        return sharedPreferences.getString("LOC_LON",null);

    }
}
