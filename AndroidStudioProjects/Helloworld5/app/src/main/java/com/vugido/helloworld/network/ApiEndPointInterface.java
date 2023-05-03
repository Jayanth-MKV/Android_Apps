package com.vugido.helloworld.network;


import com.vugido.helloworld.models.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPointInterface {

    //--------------------------------------------------

    @FormUrlEncoded
    @POST("/Testing/User_login.php")
    Call<Response> checkLogin(@FieldMap Map<String, Object> map);




//    @FormUrlEncoded
//    @POST("/APP_BACKEND/GET_SECTION_INFO.php")
//    Call<Response> getSecInfo(@FieldMap Map<String, Object> queryMap);




}
