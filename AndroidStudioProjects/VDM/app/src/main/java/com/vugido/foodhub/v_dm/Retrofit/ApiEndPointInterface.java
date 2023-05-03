package com.vugido.foodhub.v_dm.Retrofit;


import com.vugido.foodhub.v_dm.models.login.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {
//
//
//
//    //get pending orders..
//    @FormUrlEncoded
//    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/FETCH_ORDERRED_PRODUCTS_BY_OID.php")
//    Call<com.vugido.foods.vugidodeliveryagent.models.orderedProducts.Response>getOrderedProducts(@FieldMap Map<String, Object> queryMap);
//
//
//    //get pending orders..
//    @FormUrlEncoded
//    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/GET_ALL_ACTIVE_ORDERS.php")
//    Call<com.vugido.foods.vugidodeliveryagent.models.orders.pending_orders.Response>getPendingOrders(@FieldMap Map<String, Object> queryMap);
//
//
//
    // send FCM Token...
    @FormUrlEncoded
    @POST("/ANDROID_APP/S_FCM_CHECK.php")
    Call<com.vugido.foodhub.v_dm.models.status.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);


//    @FormUrlEncoded
//    @POST("/ANDROID_APP/VTM_UPDATE_DBOY_LOCATION.php")
//    Call<com.vugido.foodhub.vdexecutives.models.status.Response>updateLocationUrl(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDROID_APP/STAFF_LOGIN.php")
    Call<Response>loginUrl(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/UPDATE_USER_ORDER_STATUS.php")
    Call<com.vugido.foodhub.v_dm.models.status.Response>updateStatus(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_ALL_ACTIVE_ORDERS.php")
    Call<com.vugido.foodhub.v_dm.models.active_orders.Response>GET_ALL_ACTIVE_ORDERS(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDROID_APP/FETCH_ORDERRED_PRODUCTS_BY_OID.php")
    Call<com.vugido.foodhub.v_dm.models.products.Response>getOrderedProducts(@FieldMap Map<String, Object> queryMap);



}
