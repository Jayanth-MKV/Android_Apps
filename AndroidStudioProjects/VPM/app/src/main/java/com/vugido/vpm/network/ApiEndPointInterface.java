package com.vugido.vpm.network;


import com.vugido.vpm.model.active_orders.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    @POST("VUGIDO_ADMIN/GET_ACTIVE_ORDERS.php")
    Call<Response> getAllActiveOrders();



   /* @FormUrlEncoded
    @POST("GET_ORDERED_ITEMS.php")
    Call<OrderedItems>getOrderedItems(@FieldMap Map<String, Object> queryMap);*/


}
