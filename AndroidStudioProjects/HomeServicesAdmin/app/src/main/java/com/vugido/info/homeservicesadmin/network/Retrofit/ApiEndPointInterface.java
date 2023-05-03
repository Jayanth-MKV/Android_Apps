package com.vugido.info.homeservicesadmin.network.Retrofit;





import com.vugido.info.homeservicesadmin.models.status.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    @FormUrlEncoded
    @POST("/ANDROID_APP/ADD_CLIENT_IMAGES.php")
    Call<com.vugido.info.homeservicesadmin.models.Response>nearUploadImage(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_NEAR_PAGE.php")
    Call<com.vugido.info.homeservicesadmin.models.near_page.Response> fetchNearPage(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDROID_APP/IMAGE_UPLOAD.php")
    Call<com.vugido.info.homeservicesadmin.models.Response>sendImage(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_HOME_PAGE.php")
    Call<com.vugido.info.homeservicesadmin.models.homepage.Response> fetchHomePage(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDROID_APP/ADD_NEAR_INFO.php")
    Call<com.vugido.info.homeservicesadmin.models.Response>addNewNearProduct(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/ADD_SERVICE_INFO.php")
    Call<com.vugido.info.homeservicesadmin.models.Response>addNewServiceProduct(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_CATEGORIES.php")
    Call<com.vugido.info.homeservicesadmin.models.services.c.Response>fetchSubCategories(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDROID_APP/ADD_CATEGORY.php")
    Call<com.vugido.info.homeservicesadmin.models.Response>addNewCategory(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDROID_APP/UPDATE_USER_ORDERS_STATUS.php")
    Call<Response>updateStatus(@FieldMap Map<String, Object> queryMap);

    // send FCM Token...
    @FormUrlEncoded
    @POST("/ANDROID_APP/ADMIN_GET_SERVICES.php")
    Call<com.vugido.info.homeservicesadmin.models.services.Response>getServices(@FieldMap Map<String, Object> queryMap);



    // send FCM Token...
    @FormUrlEncoded
    @POST("/ANDROID_APP/VTM_SEND_FIREBASE_TOKEN.php")
    Call<Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);


//    //user reg
//    @FormUrlEncoded
//    @POST("/ANDROID_APP/CREATE_USER.php")
//    Call<com.vugido.homeservices.models.user.Response> userCre(@FieldMap Map<String, Object> queryMap);
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
