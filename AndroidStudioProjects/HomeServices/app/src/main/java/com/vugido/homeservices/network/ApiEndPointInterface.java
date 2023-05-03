package com.vugido.homeservices.network;




import com.vugido.homeservices.models.user_reg.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_NEAR_IMAGES.php")
    Call<com.vugido.homeservices.models.near_page.images.Response> nearImages(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/BOOK_SERVICE.php")
    Call<com.vugido.homeservices.models.bookservice.Response> bookService(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_NEAR_PAGE.php")
    Call<com.vugido.homeservices.models.near_page.Response> fetchNearPage(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_HOME_PAGE.php")
    Call<com.vugido.homeservices.models.homepage.Response> fetchHomePage(@FieldMap Map<String, Object> queryMap);


    //user reg
    @FormUrlEncoded
    @POST("/ANDROID_APP/CREATE_USER.php")
    Call<com.vugido.homeservices.models.user.Response> userCre(@FieldMap Map<String, Object> queryMap);


    //user reg
    @FormUrlEncoded
    @POST("/ANDROID_APP/USER_REG.php")
    Call<Response> userReg(@FieldMap Map<String, Object> queryMap);


    




}
