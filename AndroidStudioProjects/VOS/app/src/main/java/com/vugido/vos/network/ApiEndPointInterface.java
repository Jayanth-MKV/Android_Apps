package com.vugido.vos.network;



import com.vugido.vos.models.Categories.Response;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    // Demo RxJava Request...
   /* @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Observable<com.vugido.vugidoupdate.models.TestingRxJava.Response> getRxJavaDemoResponse(@FieldMap Map<String, Object> queryMap);
*/

    @POST("/VUGIDO_CLIENT_PANEL/VOS_ACTIVE_CATEGORIES.php")
    Call<Response> getVosInventory();


    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/VOS_GET_ALL_PRODUCTS_DETAILS.php")
    Call<com.vugido.vos.models.AllProducts.Response> getAllProducts(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO_CLIENT_PANEL/ADD_UPDATE_PP.php")
    Call<com.vugido.vos.models.Status.Response> updatePPP(@FieldMap Map<String, Object> queryMap);

    @POST("/VDBACKEND/GET_ALL_ORDERS.php")
    Call<com.vugido.vos.models.Analytics.AllOrders.Response> getAllOrders();

    @FormUrlEncoded
    @POST("/VDBACKEND/GET_VOS_ODERD_IDS.php")
    Call<com.vugido.vos.models.Analytics.CustomOrders.Response> getCustomOrderIds(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VDBACKEND/GET_VOS_ORDER_PRODUCTS.php")
    Call<com.vugido.vos.models.Analytics.Products.Response> getProductByOrderIds(@FieldMap Map<String, Object> queryMap);




    //////////////////////////// HOME PAGE CONTENT END.....




  /*  /// VERIFY OTP
    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/VERIFY_OTP.php")
    Call<com.vugido.StudentTime.models.updated.login.verify_data.Response>sendUserOTP(@FieldMap Map<String, Object> queryMap);

*/

   /* @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_HOME_PAGE.php")
    Call<com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.Response> fetchHomePageContent();

    @POST("/NEW_V_INVENTORY_END/ALL_CATEGORIES.php")
    Call<com.vugido.StudentTime.models.CategoryFragmentModel.Response> fetchAllCategories();

    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_ALL_PRODUCTS.php")
    Call<Response>fetchAllProducts(@FieldMap Map<String, Object> queryMap);*/

/*
    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/ADD_TO_CART.php")
    Call<com.vugido.StudentTime.models.updated.ToCartModel.Response>addToCart(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_CART_PRODUCTS.php")
    Call<com.vugido.StudentTime.models.updated.cart.FetchCart.Response>fetchCartItems(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/DELETE_CART_PRODUCTS.php")
    Call<com.vugido.StudentTime.models.updated.cart.DelCart.Response>deleteCartItems(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_USER_ADDRESS.php")
    Call<com.vugido.StudentTime.models.updated.Manage_Address.Fetch_All_Address.Response>fetchAllAddresses(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/SET_PRIMARY_ADDRESS.php")
    Call<SetPrimaryAID>setPrimaryAddress(@FieldMap Map<String, Object> queryMap);


    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_SLOT.php")
    Call<com.vugido.StudentTime.models.updated.slots_data.Response> getAllSlots();


    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_DELIVERY_METHODS_INFO.php")
    Call<com.vugido.StudentTime.models.updated.delivery_methods.Response> getDeliveryMethodsInfo();


    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/ADD_USER_ADDRESS.php")
    Call<com.vugido.StudentTime.models.Location.Response>addNewAddress(@FieldMap Map<String, String> queryMap);*/

    //////////////////////////////////////////////////////











}
