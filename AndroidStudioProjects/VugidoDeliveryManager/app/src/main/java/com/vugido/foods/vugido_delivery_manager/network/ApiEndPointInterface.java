package com.vugido.foods.vugido_delivery_manager.network;


import com.vugido.foods.vugido_delivery_manager.models.login.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


/*
    @FormUrlEncoded
    @POST("VUGIDO/VUGIDO_TEAM_MANAGEMENT/UPDATE_DBOY_LOCATION.php")
    Call<Response>updateLocationUrl(@FieldMap Map<String, Object> queryMap);*/

    @FormUrlEncoded
    @POST("VUGIDO/VUGIDO_TEAM_MANAGEMENT/VTM_LOGIN.php")
    Call<Response>loginUrl(@FieldMap Map<String, Object> queryMap);



}
