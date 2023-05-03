package com.vugido.vugidoupdate.network.Retrofit;



import com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.Response;
import java.util.Map;

import io.reactivex.Observable;
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


    //Search Suggestions url
    @FormUrlEncoded
    @POST("/VUGIDO/USERS_MANAGEMENT/SEARCH_INDEX.php")
    Call<com.vugido.vugidoupdate.models.search.search_suggestions.Response>searchSuggestionsUrl(@FieldMap Map<String, Object> queryMap);

    //Verify url
    @FormUrlEncoded
    @POST("/VUGIDO/USERS_MANAGEMENT/VERIFY_OTP.php")
    Call<com.vugido.vugidoupdate.models.verify_response.Response>verifyUsrUrl(@FieldMap Map<String, Object> queryMap);


    //Sign-Up url
    @FormUrlEncoded
    @POST("/VUGIDO/USERS_MANAGEMENT/USERS_SIGN_UP.php")
    Call<com.vugido.vugidoupdate.models.login_sign_up_response.Response>signUpUrl(@FieldMap Map<String, Object> queryMap);

 @FormUrlEncoded
 @POST("/VUGIDO/USERS_MANAGEMENT/USERS_LOGIN.php")
 Call<com.vugido.vugidoupdate.models.login_sign_up_response.Response>loginUrl(@FieldMap Map<String, Object> queryMap);


 /////////fetching home page content views
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/GET_HOME_PAGE_CONTENT.php")
    Call<Response>getHomePageContent(@FieldMap Map<String, Object> queryMap);

    /// fetching home page content
    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response>getCircularViewData(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response>getSliderData(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response>getRectangularMedium(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response>getRectangularChips(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response>getSquareMini(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
    Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response>getSquareMedium(@FieldMap Map<String, Object> queryMap);



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
