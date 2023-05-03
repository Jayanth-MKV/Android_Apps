package com.vugido.vugidoinventorymanagement.network.Retrofit;




import com.vugido.vugidoinventorymanagement.models.new_images.Response;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiEndPointInterface {


    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/FETCH_ALL_PRODUCTS.php")
    Call<com.vugido.vugidoinventorymanagement.models.all_products_of_category.Response>fetchAllProducts(@FieldMap Map<String, Object> queryMap);



    //upload data url...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/UPLOAD_DATA_REFER_SYSTEM.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> uploadData(@FieldMap Map<String, Object> queryMap);


    //upload data url...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/UPLOAD_DATA.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> uploadDataGeneral(@FieldMap Map<String, Object> queryMap);


    //upload data url...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/UPLOAD_DATA_COINED.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> uploadDataCoined(@FieldMap Map<String, Object> queryMap);


    //upload data url...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/DELETE_PRODUCT.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> updateDeleteProduct(@FieldMap Map<String, Object> queryMap);


    //upload data url...
    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/EDIT_PRODUCT.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> updateEditData(@FieldMap Map<String, Object> queryMap);











    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/FETCH_FOOD_CATEGORIES.php")
    Call<Response> fetchFoodCategories();

   /* @POST("/NEW_V_INVENTORY_END/ALL_CATEGORIES.php")
    Call<com.vugido.StudentTime.models.CategoryFragmentModel.Response> fetchAllCategories();*/

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/ADD_NEW_MAIN_CATEGORY.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response>addNewMainCategory(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/TOGGLE_PRODUCT.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> toggleProduct(@FieldMap Map<String, Object> map);


    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/TOGGLE_APP_SERVICE.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> toggleAppService();


    @POST("/ANDHRA_PRADESH/SRIKAKULAM/LOW_COST_BIRYANI_POINT/PHP_FILES/GET_APP_SERVICE.php")
    Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> getAppService();
}
