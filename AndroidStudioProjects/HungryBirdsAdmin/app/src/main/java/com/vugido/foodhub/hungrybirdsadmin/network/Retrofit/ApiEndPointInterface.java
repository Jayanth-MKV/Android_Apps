package com.vugido.foodhub.hungrybirdsadmin.network.Retrofit;


import com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/GET_ORDERED_PRODUCTS.php")
    Call<com.vugido.foodhub.hungrybirdsadmin.models.orders.ordered_items.Response>getOrderedProducts(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/GET_ACTIVE_ORDERS.php")
    Call<com.vugido.foodhub.hungrybirdsadmin.models.orders.AO.Response>GET_AO(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/SLIDER_DELETE.php")
    Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response>delSLider(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/TOGGLE_SLIDER.php")
    Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response>toggleSlider(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/GET_SLIDERS.php")
    Call<Response>fetchSliders(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/ADMIN_APP/ADD_SLIDER.php")
    Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response>addSlider(@FieldMap Map<String, Object> queryMap);



}
