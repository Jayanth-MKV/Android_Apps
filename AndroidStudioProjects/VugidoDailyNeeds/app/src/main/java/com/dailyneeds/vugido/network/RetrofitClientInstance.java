package com.dailyneeds.vugido.network;

import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.models.DataItem;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.POST;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(ConfigVariables.VEGETABLES_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public interface GetDataService {

        @POST("V_INVENTORY_END/VEGETABLES.php")
        Call<List<DataItem>> getAllPhotos();
    }
}
