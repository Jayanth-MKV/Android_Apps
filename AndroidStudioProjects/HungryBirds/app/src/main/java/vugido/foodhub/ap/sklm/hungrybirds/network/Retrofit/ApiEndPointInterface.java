package vugido.foodhub.ap.sklm.hungrybirds.network.Retrofit;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vugido.foodhub.ap.sklm.hungrybirds.models.user_reg.Response;

public interface ApiEndPointInterface {


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/UPDATE_PLACE_ORDER.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.status.Response> placeOrder(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/FETCH_ALL_PRODUCTS.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.category.Response> getCategoryProducts(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/GET_APP_INFO.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.app_info.Response> getAppInfo(@FieldMap Map<String, Object> queryMap);



    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/GET_CART_PRODUCTS.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.cart.Response> getCartProducts(@FieldMap Map<String, Object> queryMap);


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/GET_HOME_PAGE.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.Response> getHomePage(@FieldMap Map<String, Object> queryMap);



    //user reg
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/CREATE_USER.php")
    Call<vugido.foodhub.ap.sklm.hungrybirds.models.user.Response> userCre(@FieldMap Map<String, Object> queryMap);


    //user reg
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/HUNGRY_BIRDS/PHP_FILES/USER_REG.php")
    Call<Response> userReg(@FieldMap Map<String, Object> queryMap);








}
