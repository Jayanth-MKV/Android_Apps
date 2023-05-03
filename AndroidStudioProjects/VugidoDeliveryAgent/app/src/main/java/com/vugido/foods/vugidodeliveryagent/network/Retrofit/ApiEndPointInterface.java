package com.vugido.foods.vugidodeliveryagent.network.Retrofit;

import com.vugido.foods.vugidodeliveryagent.models.status.Response;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/UPDATE_USER_ORDERS_STATUS.php")
    Call<Response>updateStatus(@FieldMap Map<String, Object> queryMap);


    //get pending orders..
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/FETCH_ORDERRED_PRODUCTS_BY_OID.php")
    Call<com.vugido.foods.vugidodeliveryagent.models.orderedProducts.Response>getOrderedProducts(@FieldMap Map<String, Object> queryMap);


    //get pending orders..
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/GET_ALL_ACTIVE_ORDERS.php")
    Call<com.vugido.foods.vugidodeliveryagent.models.orders.pending_orders.Response>getPendingOrders(@FieldMap Map<String, Object> queryMap);



    // send FCM Token...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/VTM_SEND_FIREBASE_TOKEN.php")
    Call<Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/VTM_UPDATE_DBOY_LOCATION.php")
    Call<Response>updateLocationUrl(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/VTM_LOGIN.php")
    Call<com.vugido.foods.vugidodeliveryagent.models.login.Response>loginUrl(@FieldMap Map<String, Object> queryMap);



}
