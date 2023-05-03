package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.network.Retrofit;


public interface ApiEndPointInterface {


//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_ALL_COIN_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response>fetchEntireCoinedProducts(@FieldMap Map<String, Object> queryMap);
//
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_ALL_AFFILIATED_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.AffiliatedProductModel.Entire.Response>fetchEntireAffiliatedProducts(@FieldMap Map<String, Object> queryMap);
//
//    /////////////////////////////
//
//    @GET("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_ALL_SERVICE_LOCATIONS.php")
//    Call<com.foodhub.vugido.models.ServiceAvailability.Response> getAllLocations();
//
//    ////////////////////////////////
//
//    //fetch coins transaction..
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_COIN_TRANSACTIONS.php")
//    Call<com.foodhub.vugido.models.Transactions.Response>fetchCoinsTransaction(@FieldMap Map<String, Object> queryMap);
//
//
//    //fetch coins page...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_COINS_PAGE.php")
//    Call<com.foodhub.vugido.models.CoinsModel.Response>fetchCoinsPage(@FieldMap Map<String, Object> queryMap);
//
//    //fetch Affilate product ...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_AFFILIATED_PRODUCT.php")
//    Call<com.foodhub.vugido.models.AffiliatedProductModel.Response>getAffiliateProduct(@FieldMap Map<String, Object> queryMap);
//
//
//    // SENDING RC CODE...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/SEND_USER_USED_REFERRAL_CODE.php")
//    Call<com.foodhub.vugido.models.status.Response>sendRC(@FieldMap Map<String, Object> queryMap);
//
//
//    // check for app update..
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/APP_UPDATE.php")
//    Call<com.foodhub.vugido.models.AppUpdate.Response>getAppUpdateStatus(@FieldMap Map<String, Object> queryMap);
//
//    // get free products...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_CART_OFFER_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.CartOfferModel.ProductsFree.Response>getCartFreeProducts(@FieldMap Map<String, Object> queryMap);
//
//
//
//    // Cart offers eligibility check
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/CHECK_CART_OFFERS_ELIGIBILITY.php")
//    Call<com.foodhub.vugido.models.CartOfferModel.Response>checkCartEligibilityOffers(@FieldMap Map<String, Object> queryMap);
//
//
//    // Notify User received notification
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_OFFERS_PAGE.php")
//    Call<com.foodhub.vugido.models.OffersPageModel.Response>fetchOfferPageData(@FieldMap Map<String, Object> queryMap);
//
//
//
//    // Notify User received notification
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/UPDATE_USERS_RECEIVED_NOTIFICATION.php")
//    Call<com.foodhub.vugido.models.status.Response>notifyServerNotificationReceived(@FieldMap Map<String, Object> queryMap);
//
//
//
//
//    // get User order tracking info
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_ORDER_TRACKING_INFO.php")
//    Call<com.foodhub.vugido.models.tracking.Response>getOrderTrackingInfo(@FieldMap Map<String, Object> queryMap);
//
//    // send user on click search submit results.
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_SEARCH_ONCLICK_DATA.php")
//    Call<com.foodhub.vugido.models.search.search_submit.Response>getSearchOnClickResults(@FieldMap Map<String, Object> queryMap);
//
//
//
//    // send user on click notification analytics..
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/UPDATE_NOTIFICATION_RS.php")
//    Call<com.foodhub.vugido.models.status.Response>sendUserNotificationAnalytics(@FieldMap Map<String, Object> queryMap);
//
//
//    //Search Suggestions url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/SEARCH_INDEX.php")
//    Call<com.foodhub.vugido.models.search.search_suggestions.Response>searchSuggestionsUrl(@FieldMap Map<String, Object> queryMap);
//
//
//    // send FCM Token...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_USER_ORDERS_BY_UID.php")
//    Call<com.foodhub.vugido.models.my_orders.Response>getOrdersData(@FieldMap Map<String, Object> queryMap);
//
//
//
//
//    // send FCM Token...
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/SEND_FIREBASE_TOKEN.php")
//    Call<com.foodhub.vugido.models.status.Response>sendUserFCM(@FieldMap Map<String, Object> queryMap);
//
//
//    //Verify url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/NEW_VERIFY.php")
//    Call<com.foodhub.vugido.models.verify_response.Response>verifyUsrUrl(@FieldMap Map<String, Object> queryMap);
//
//
//    //Sign-Up url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/USERS_SIGN_UP.php")
//    Call<com.foodhub.vugido.models.login_sign_up_response.Response>signUpUrl(@FieldMap Map<String, Object> queryMap);
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/USERS_LOGIN.php")
//    Call<com.foodhub.vugido.models.login_sign_up_response.Response>loginUrl(@FieldMap Map<String, Object> queryMap);
//
//
//
//    // place order url
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/UPDATE_PLACE_ORDER.php")
//    Call<com.foodhub.vugido.models.status.Response>placeOrder(@FieldMap Map<String, Object> queryMap);
//
//
//    // transfer cart to orderable..
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/TO_ORDERABLE.php")
//    Call<com.foodhub.vugido.models.status.Response>transferCartToOrderable(@FieldMap Map<String, Object> queryMap);
//
//    //get primary address..
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_PRIMARY_ADDRESS.php")
//    Call<com.foodhub.vugido.models.primary_address.Response>getUserPrimaryAddress(@FieldMap Map<String, Object> queryMap);
//
//
//    ///////////////////////////////////////////////
//
//    /////////Sending user info for  otp..
//   /* @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/REQUEST_SMS.php")
//    Call<com.vugido.StudentTime.models.updated.login.Response>sendUserLoginInfo(@FieldMap Map<String, Object> queryMap);
//
//
//    /// VERIFY OTP
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/VERIFY_OTP.php")
//    Call<com.vugido.StudentTime.models.updated.login.verify_data.Response>sendUserOTP(@FieldMap Map<String, Object> queryMap);
//*/
//    ///////////////////////////////////////////////////////
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/NEW_HOME_PAGE.php")
//    Call<com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.Response> fetchHomePageContent(@FieldMap Map<String, Object> queryMap);
//
//   /* @POST("/NEW_V_INVENTORY_END/ALL_CATEGORIES.php")
//    Call<com.vugido.StudentTime.models.CategoryFragmentModel.Response> fetchAllCategories();*/
//
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_ALL_PRODUCTS.php")
//    Call<Response>fetchAllProducts(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/ADD_TO_CART.php")
//    Call<com.foodhub.vugido.models.updated.ToCartModel.Response>addToCart(@FieldMap Map<String, Object> queryMap);
//
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/NEW_ADD_TO_CART.php")
//    Call<com.foodhub.vugido.models.updated.ToCartModel.Response>AffiliateAddToCart(@FieldMap Map<String, Object> queryMap);
//
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_CART_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.updated.cart.FetchCart.Response>fetchCartItems(@FieldMap Map<String, Object> queryMap);
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/DELETE_CART_PRODUCTS.php")
//    Call<com.foodhub.vugido.models.updated.cart.DelCart.Response>deleteCartItems(@FieldMap Map<String, Object> queryMap);
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/FETCH_USER_ADDRESS.php")
//    Call<com.foodhub.vugido.models.updated.Manage_Address.Fetch_All_Address.Response>fetchAllAddresses(@FieldMap Map<String, Object> queryMap);
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/SET_PRIMARY_ADDRESS.php")
//    Call<SetPrimaryAID>setPrimaryAddress(@FieldMap Map<String, Object> queryMap);
//
//
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_SLOT.php")
//    Call<com.foodhub.vugido.models.updated.slots_data.Response> getAllSlots();
//
//
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/GET_DELIVERY_METHODS_INFO.php")
//    Call<com.foodhub.vugido.models.updated.delivery_methods.Response> getDeliveryMethodsInfo();
//
//
//    @FormUrlEncoded
//    @POST("/VUGIDO/INVENTORY_MANAGEMENT_FILES/PHP_FILES/ADD_USER_ADDRESS.php")
//    Call<com.foodhub.vugido.models.Location.Response>addNewAddress(@FieldMap Map<String, String> queryMap);
//
//    //////////////////////////////////////////////////////
//
//
//
//
//






}
