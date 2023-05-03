package com.vugido.vugidoccs.Retrofit;

import com.vugido.vugidoccs.models.InActiveUsers.Response;

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


    /////////fetching home page content views
    @FormUrlEncoded
    @POST("/VDBACKEND/USERS_BY_GAP.php")
    Call<Response>getInActiveUsersByGap(@FieldMap Map<String, Object> queryMap);
    @FormUrlEncoded
    @POST("/VUGIDO_ADMIN/ZeroUsersList.php")
    Call<com.vugido.vugidoccs.models.OrderCountUsers.Response>getZerOrderedUsers(@FieldMap Map<String, Object> queryMap);

    /// fetching home page content
  //  @FormUrlEncoded
  //  @POST("/VUGIDO/CLIENTS_MANAGEMENT/SOURCE_TABLE.php")
 //   Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response>getCircularViewData(@FieldMap Map<String, Object> queryMap);

  /*  @FormUrlEncoded
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


*/





}
