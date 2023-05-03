package com.vugido.brain_cord.network;

import com.vugido.brain_cord.models.section.Response;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    //--------------------------------------------------



    @FormUrlEncoded
    @POST("/APP_BACKEND/GET_SECTION_INFO.php")
    Call<Response> getSecInfo(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/APP_BACKEND/FETCH_QUIZ_QUESTION.php")
    Call<com.vugido.brain_cord.models.quiz.Response> getQuizInfo(@FieldMap Map<String, Object> queryMap);


    @GET("/APP_BACKEND/LOGIN_ACCESS.php")
    Call<com.vugido.brain_cord.models.login_access.Response> loginAccess();


    @FormUrlEncoded
    @POST("/APP_BACKEND/USER_LOGIN.php")
    Call<com.vugido.brain_cord.models.quiz_reg.Response> login(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP_BACKEND/SUBMIT_ANSWER.php")
    Call<com.vugido.brain_cord.models.Response> answer(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/APP_BACKEND/FEEDBACK.php")
    Call<com.vugido.brain_cord.models.Response> feedback(@FieldMap Map<String, Object> queryMap);











    // --------------------------------------------------




//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/SHIPPING_INFO.php")
//    Call<com.foodhub.vugido.models.shipping.Response>getShippingInfo(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/GET_SEARCH_ONCLICK_DATA.php")
//    Call<com.foodhub.vugido.models.search_clicked.Response>getSearchOnClickResults(@FieldMap Map<String, Object> queryMap);
//
//    // send FCM Token...
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/SEND_FIREBASE_TOKEN.php")
//    Call<com.foodhub.vugido.models.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/SEARCH_INDEX.php")
//    Call<com.foodhub.vugido.models.search_indexer.Response>searchSuggestionsUrl(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/GET_ORDER_INFO.php")
//    Call<com.foodhub.vugido.models.order_info.Response>getOrderTrackingInfo(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/DBOY.php")
//    Call<com.foodhub.vugido.models.did.Response>did(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/USER_ORDERS.php")
//    Call<com.foodhub.vugido.models.orders.Response>orders(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/PLACE_ORDER.php")
//    Call<com.foodhub.vugido.models.Response>placeOrder(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/ONLINE_RAZOR_ID.php")
//    Call<com.foodhub.vugido.models.check_o.Response>fetchCheckOut(@FieldMap Map<String, Object> queryMap);
//
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/GET_CART_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.cart.Response>fetchCartItems(@FieldMap Map<String, Object> queryMap);
//
//
//    //user reg
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/GET_HOMEPAGE.php")
//    Call<com.foodhub.vugido.models.homepage.Response> homePage(@FieldMap Map<String, Object> queryMap);
//
//
//
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/GET_CID_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.clients_menu.Response> cid_menu(@FieldMap Map<String, Object> queryMap);
//
//
//    //user reg
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/CREATE_USER.php")
//    Call<com.foodhub.vugido.models.user.Response> userCre(@FieldMap Map<String, Object> queryMap);
//
//
//    //user reg
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/USER_REG.php")
//    Call<Response> userReg(@FieldMap Map<String, Object> queryMap);
//
//
//



}
