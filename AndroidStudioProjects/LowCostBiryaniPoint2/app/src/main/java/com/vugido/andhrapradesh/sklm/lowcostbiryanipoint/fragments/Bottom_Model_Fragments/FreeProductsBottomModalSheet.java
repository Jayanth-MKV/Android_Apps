package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Bottom_Model_Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.FreeProductsAdapter.FreeProductsAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.CartOfferModel.ProductsFree.DATAItem;
import com.foodhub.vugido.models.CartOfferModel.ProductsFree.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class FreeProductsBottomModalSheet extends BottomSheetDialogFragment implements View.OnClickListener {


    private Context context;
    private RecyclerView recyclerView;
    private List<DATAItem> dataItemList;
    private View Progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.free_products,container,false);
       recyclerView=view.findViewById(R.id.BottomFreeProductsRecyclerView);
       Progress=view.findViewById(R.id.my_progress);

        assert getArguments() != null;
        String HID=getArguments().getString("HID");
        fetchCartFreeProducts(HID);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


        }
    }

    private void fetchCartFreeProducts(String HidString){
        Map<String,Object> map=new HashMap<>();
        Progress.setVisibility(View.VISIBLE);
        map.put("HID",HidString);
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).getCartFreeProducts(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.CartOfferModel.ProductsFree.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.CartOfferModel.ProductsFree.Response> response) {

               // Log.e("Res",response.toString());
               Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok data got...
                        dataItemList=response.body().getDATA();
                        bindData2Adapter(dataItemList);
                    }else {
                    }
                }
            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.CartOfferModel.ProductsFree.Response> call, Throwable t) {
                Progress.setVisibility(View.GONE);
            }
        });

    }

    private void bindData2Adapter(List<DATAItem> dataItemList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        FreeProductsAdapter freeProductsAdapter=new FreeProductsAdapter(dataItemList,context);
        recyclerView.setAdapter(freeProductsAdapter);

    }

    private void playMusic() {


        MediaPlayer ring= MediaPlayer.create(context,R.raw.reveal);
        ring.start();

    }

}
