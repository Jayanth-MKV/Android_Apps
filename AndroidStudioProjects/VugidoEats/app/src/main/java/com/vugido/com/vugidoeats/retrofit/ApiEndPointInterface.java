package com.vugido.com.vugidoeats.retrofit;


import com.vugido.com.vugidoeats.models.HomePage.Response;

import java.util.Map;


import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

//    @FormUrlEncoded
//    @GET("/AP/In


    // send FCM Token...
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/SEND_FIREBASE_TOKEN.php")
    Call<com.vugido.com.vugidoeats.models.status.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/FETCH_MENU.php")
    Call<Response> fetchHomePage(@FieldMap Map<String, Object> queryMap);
    // check for app update..
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/APP_UPDATE.php")
    Call<com.vugido.com.vugidoeats.models.AppUpdate.Response>getAppUpdateStatus(@FieldMap Map<String,Object> queryMap);



    //Verify url
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/NEW_VERIFY.php")
    Call<com.vugido.com.vugidoeats.models.verify_response.Response>verifyUsrUrl(@FieldMap Map<String, Object> queryMap);


    //Sign-Up url
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/USERS_SIGN_UP.php")
    Call<com.vugido.com.vugidoeats.models.login_sign_up_response.Response>signUpUrl(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/USERS_LOGIN.php")
    Call<com.vugido.com.vugidoeats.models.login_sign_up_response.Response>loginUrl(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/GET_CART_PRODUCTS.php")
    Call<com.vugido.com.vugidoeats.models.cart.Response>getCartPids(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/ON_GOING.php")
    Call<com.vugido.com.vugidoeats.models.Orders.Response>getOnGoingOrder(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/ORDER_REQUEST.php")
    Call<com.vugido.com.vugidoeats.models.status.Response>sendOrderRequest(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/PAY_REQUEST.php")
    Call<com.vugido.com.vugidoeats.models.Response>parequesed(@FieldMap Map<String, Object> queryMap);



}
