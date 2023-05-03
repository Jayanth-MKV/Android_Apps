package com.dailyneeds.vugido.Retrofit;



import com.dailyneeds.vugido.models.CartDelete.Response;
import com.dailyneeds.vugido.models.CategoriesModel.CategoriesResponse;
import com.dailyneeds.vugido.models.ComingSoon;
import com.dailyneeds.vugido.models.ResponseComingSoon;
import com.dailyneeds.vugido.models.ResultsItem;
import com.dailyneeds.vugido.models.SubCategoryModel.SubCategoryResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {

    @POST("V_INVENTORY_END/ALL_CATEGORIES.php")
    Call<CategoriesResponse> categories();


    @FormUrlEncoded
    @POST("V_INVENTORY_END/GET_PRODUCTS.php")
    Call<SubCategoryResponse>sub_categories(@FieldMap Map<String, Object> queryMap);

    @POST("V_INVENTORY_END/GET_HOME_PAGE.php")
    Call<ResponseComingSoon> comingSoon();


    @FormUrlEncoded
    @POST("VDBACKEND/DELETE_CART_PRODUCTS.php")
    Call<Response>deleteCartItems(@FieldMap Map<String, Object> queryMap);



}
