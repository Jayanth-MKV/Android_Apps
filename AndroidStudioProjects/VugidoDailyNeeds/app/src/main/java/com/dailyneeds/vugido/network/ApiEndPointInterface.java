
package com.dailyneeds.vugido.network;



import com.dailyneeds.vugido.models.DataItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiEndPointInterface {


    @POST("V_INVENTORY_END/demo.php")
    Call<List<DataItem>>getvegetables();
}
