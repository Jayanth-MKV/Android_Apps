package com.foodhub.vugido.sklm.fresh_cuts.network.Retrofit;


import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    ////////////////////////////////

//home page
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/FRESH_CUTS/PHP_FILES/NEW_HOME_PAGE.php")
    Call<Response> fetchHomePageContent(@FieldMap Map<String, Object> queryMap);

//    //Verify url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/NEW_VERIFY.php")
//    Call<com.foodhub.vugido.models.verify_response.Response>verifyUsrUrl(@FieldMap Map<String, Object> queryMap);
//
//
//    //Sign-Up url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/USERS_SIGN_UP.php")
//    Call<com.foodhub.vugido.models.login_sign_up_response.Response>signUpUrl(@FieldMap Map<String, Object> queryMap);
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/USERS_LOGIN.php")
//    Call<com.foodhub.vugido.models.login_sign_up_response.Response>loginUrl(@FieldMap Map<String, Object> queryMap);
//









    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/GET_CART_PRODUCTS.php")
    Call<com.foodhub.vugido.sklm.fresh_cuts.models.cart.Response>getCartPids(@FieldMap Map<String, Object> queryMap);







}
