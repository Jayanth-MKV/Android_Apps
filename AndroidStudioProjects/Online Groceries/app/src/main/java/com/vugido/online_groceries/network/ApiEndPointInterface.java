package com.vugido.online_groceries.network;



import com.vugido.online_groceries.models.user_reg.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    //.................................

    @FormUrlEncoded
    @POST("/APP/GET_CID_PRODUCTS.php")
    Call<com.vugido.online_groceries.models.product.categories.Response>getCategoryProducts(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/GET_HOMEPAGE.php")
    Call<com.vugido.online_groceries.models.homepage.updated.Response> homePage(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/TO_CART.php")
    Call<com.vugido.online_groceries.models.cart.addtocart.Response>addToCart(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/GET_CART_PRODUCTS.php")
    Call<com.vugido.online_groceries.models.cart.get_cart.Response>fetchCartItems(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/DELETE_CART_PRODUCT.php")
    Call<com.vugido.online_groceries.models.cart.cart_delete.Response>deleteFromCart(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/SHIPPING_INFO.php")
    Call<com.vugido.online_groceries.models.shipping.Response>getShippingInfo(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/APP/ONLINE_RAZOR_ID.php")
    Call<com.vugido.online_groceries.models.check_o.Response>fetchCheckOut(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/PLACE_ORDER.php")
    Call<com.vugido.online_groceries.models.orders.order_placed.Response>placeOrder(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/USER_ORDERS.php")
    Call<com.vugido.online_groceries.models.orders.all_orders.Response>orders(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/APP/GET_ORDER_INFO.php")
    Call<com.vugido.online_groceries.models.orders.tracking.Response>getOrderTrackingInfo(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/APP/SEARCH_INDEX.php")
    Call<com.vugido.online_groceries.models.search_indexer.Response>searchSuggestionsUrl(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/APP/GET_SEARCH_ONCLICK_DATA.php")
    Call<com.vugido.online_groceries.models.search_clicked.Response>getSearchOnClickResults(@FieldMap Map<String, Object> queryMap);


    //user reg
    @FormUrlEncoded
    @POST("/APP/CREATE_USER.php")
    Call<com.vugido.online_groceries.models.user.Response> userCre(@FieldMap Map<String, Object> queryMap);


    //user reg
    @FormUrlEncoded
    @POST("/APP/USER_REG.php")
    Call<Response> userReg(@FieldMap Map<String, Object> queryMap);


    // send FCM Token...
    @FormUrlEncoded
    @POST("/APP/SEND_FIREBASE_TOKEN.php")
    Call<com.vugido.online_groceries.models.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);

    //......................................










    @FormUrlEncoded
    @POST("/ANDROID_APP/DBOY.php")
    Call<com.vugido.online_groceries.models.did.Response>did(@FieldMap Map<String, Object> queryMap);







    //user reg


    @FormUrlEncoded
    @POST("/ANDROID_APP/GET_CID_PRODUCTS.php")
    Call<com.vugido.online_groceries.models.clients_menu.Response> cid_menu(@FieldMap Map<String, Object> queryMap);









}
