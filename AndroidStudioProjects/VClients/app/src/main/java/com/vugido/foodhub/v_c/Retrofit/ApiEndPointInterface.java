package com.vugido.foodhub.v_c.Retrofit;



import com.vugido.foodhub.v_c.models.login.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    /// LOGIN API

    @FormUrlEncoded
    @POST("/CLIENTS_APP/LOGIN.php")
    Call<Response> clientLogin(@FieldMap Map<String, Object> queryMap);
    /// ADD NEW SUB CATEGORY API
    @FormUrlEncoded
    @POST("/CLIENTS_APP/ADD_NEW_CLIENT_SUB_NAME.php")
    Call<com.vugido.foodhub.v_c.models.error.Response>addNewSubCategory(@FieldMap Map<String, Object> queryMap);


    // FETCH ALL SUB CATEGORIES API

    @FormUrlEncoded
    @POST("/CLIENTS_APP/FETCH_CLIENT_SUB_TABLE.php")
    Call<com.vugido.foodhub.v_c.models.sub_cat.Response> fetchSubCategories(@FieldMap Map<String, Object> queryMap);



    /// ADD NEW PRODUCT API

    @FormUrlEncoded
    @POST("/CLIENTS_APP/ADD_NEW_PRODUCT.php")
    Call<com.vugido.foodhub.v_c.models.error.Response>addNewProduct(@FieldMap Map<String, Object> queryMap);



    /// ALL PRODUCTS API
    @FormUrlEncoded
    @POST("/CLIENTS_APP/GET_PRODUCTS.php")
    Call<com.vugido.foodhub.v_c.models.all_products.Response> getAllProducts(@FieldMap Map<String, Object> queryMap);




    /// UPDATE API...
    @FormUrlEncoded
    @POST("/CLIENTS_APP/PRODUCT_UPDATE.php")
    Call<com.vugido.foodhub.v_c.models.error.Response> update(@FieldMap Map<String, Object> queryMap);

//
    /// DELETE API...
    @FormUrlEncoded
    @POST("/CLIENTS_APP/DELETE_PRODUCT.php")
    Call<com.vugido.foodhub.v_c.models.error.Response> delete(@FieldMap Map<String, Object> queryMap);












}
