package com.vugido_business_panel.Retrofit;


import com.vugido_business_panel.models.Categories.addCategory.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {




    

    /// UPDATE API...
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/PRODUCT_UPDATE.php")
    Call<com.vugido_business_panel.models.Updater.Response> update(@FieldMap Map<String, Object> queryMap);


    /// DELETE API...
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/DELETE_PRODUCT.php")
    Call<com.vugido_business_panel.models.Updater.delete.Response> delete(@FieldMap Map<String, Object> queryMap);



    /// ALL PRODUCTS API
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/GET_PRODUCTS.php")
    Call<com.vugido_business_panel.models.All_Products.Response> getAllProducts(@FieldMap Map<String, Object> queryMap);



    /// LOGIN API

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/LOGIN.php")
    Call<com.vugido_business_panel.models.login.Response> clientLogin(@FieldMap Map<String, Object> queryMap);


    /// ADD NEW SUB CATEGORY API
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/ADD_NEW_CLIENT_SUB_NAME.php")
    Call<Response>addNewSubCategory(@FieldMap Map<String, Object> queryMap);


    // FETCH ALL SUB CATEGORIES API

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/FETCH_CLIENT_SUB_TABLE.php")
    Call<com.vugido_business_panel.models.Categories.fetchCategories.Response> fetchSubCategories(@FieldMap Map<String, Object> queryMap);



    /// ADD NEW PRODUCT API

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/ADD_NEW_PRODUCT.php")
    Call<com.vugido_business_panel.models.Product.addProduct.Response>addNewProduct(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/USER/ANDROID_APP/ON_GOING.php")
    Call<com.vugido_business_panel.models.newOrders.Response>getOnGoingOrder(@FieldMap Map<String, Object> queryMap);

    // send FCM Token...
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/SEND_FIREBASE_TOKEN.php")
    Call<com.vugido_business_panel.models.status.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);


// send FCM Token...
    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/GET_ACTIVE_ORDERS.php")
    Call<com.vugido_business_panel.models.orders.Response>getPendingOrders(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/AP/InternalMenuManagement/PHP_FILES/ADMINS/ORDER_ACCEPTED.php")
    Call<com.vugido_business_panel.models.status.Response>orderAcceptance(@FieldMap Map<String, Object> queryMap);


/*
    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/IN_STOCK.php")
    Call<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response> toggleStock(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/PRICE_UPDATE.php")
    Call<com.vugidovugidoclientpanel.models.Updater.edit_price.Response> updatePrice(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/GET_PRODUCTS.php")
    Call<com.vugidovugidoclientpanel.models.All_Products.Response> getAllProducts(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/FETCH_USER_PRODUCT_CATEGORIES.php")
    Call<com.vugidovugidoclientpanel.models.Categories.mainCategories.Response> fetchMainCategories(@FieldMap Map<String, Object> queryMap);*/










}
