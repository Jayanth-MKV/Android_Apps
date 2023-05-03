package com.vugidovugidoclientpanel.Retrofit;


import com.vugidovugidoclientpanel.models.Categories.addCategory.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {




    

    /// UPDATE API...
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/PRODUCT_UPDATE.php")
    Call<com.vugidovugidoclientpanel.models.Updater.Response> update(@FieldMap Map<String, Object> queryMap);


    /// DELETE API...
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/DELETE_PRODUCT.php")
    Call<com.vugidovugidoclientpanel.models.Updater.delete.Response> delete(@FieldMap Map<String, Object> queryMap);



    /// ALL PRODUCTS API
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/GET_PRODUCTS.php")
    Call<com.vugidovugidoclientpanel.models.All_Products.Response> getAllProducts(@FieldMap Map<String, Object> queryMap);



    /// LOGIN API

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/LOGIN.php")
    Call<com.vugidovugidoclientpanel.models.login.Response> clientLogin(@FieldMap Map<String, Object> queryMap);


    /// ADD NEW SUB CATEGORY API
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/ADD_NEW_CLIENT_SUB_NAME.php")
    Call<Response>addNewSubCategory(@FieldMap Map<String, Object> queryMap);


    // FETCH ALL SUB CATEGORIES API

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/FETCH_CLIENT_SUB_TABLE.php")
    Call<com.vugidovugidoclientpanel.models.Categories.fetchCategories.Response> fetchSubCategories(@FieldMap Map<String, Object> queryMap);



    /// ADD NEW PRODUCT API

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/ADD_NEW_PRODUCT.php")
    Call<com.vugidovugidoclientpanel.models.Product.addProduct.Response>addNewProduct(@FieldMap Map<String, Object> queryMap);






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
