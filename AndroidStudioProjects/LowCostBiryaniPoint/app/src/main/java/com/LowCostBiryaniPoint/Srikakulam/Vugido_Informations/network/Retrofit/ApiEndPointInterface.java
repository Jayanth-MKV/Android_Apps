package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.network.Retrofit;


import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel.Response;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/GET_HOME_PAGE.php")
    Call<Response> fetchHomePageContent(@FieldMap Map<String, Object> queryMap);



    /////////Sending user info for  otp..
  /*  @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/REQUEST_SMS.php")
    Call<com.vugido.StudentTime.models.updated.login.Response>sendUserLoginInfo(@FieldMap Map<String, Object> queryMap);


    /// VERIFY OTP
    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/VERIFY_OTP.php")
    Call<com.vugido.StudentTime.models.updated.login.verify_data.Response>sendUserOTP(@FieldMap Map<String, Object> queryMap);



    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_HOME_PAGE.php")
    Call<com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.Response> fetchHomePageContent();
*/
   /* @POST("/NEW_V_INVENTORY_END/ALL_CATEGORIES.php")
    Call<com.vugido.StudentTime.models.CategoryFragmentModel.Response> fetchAllCategories();*/
/*
    @FormUrlEncoded
    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_ALL_PRODUCTS.php")
    Call<Response>fetchAllProducts(@FieldMap Map<String, Object> queryMap);


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
    Call<com.vugido.StudentTime.models.Location.Response>addNewAddress(@FieldMap Map<String, String> queryMap);

    //////////////////////////////////////////////////////

*/









}
