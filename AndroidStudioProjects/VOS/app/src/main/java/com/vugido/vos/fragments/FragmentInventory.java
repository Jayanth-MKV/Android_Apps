package com.vugido.vos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vugido.vos.R;
import com.vugido.vos.adapters.InventoryMainAdapter;
import com.vugido.vos.design.Space;
import com.vugido.vos.models.Categories.DATAItem;
import com.vugido.vos.models.Categories.Response;
import com.vugido.vos.network.RetrofitBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class FragmentInventory extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_inventory_layout,container,false);
        recyclerView=view.findViewById(R.id.inventory_recycler);
        fetchInventoryCategories();
        return view;
    }

    private void fetchInventoryCategories() {

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getVosInventory();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError())
                    {
                        List<DATAItem> dataItemList=response.body().getDATA();
                        bindAdapterData(dataItemList);
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    private void bindAdapterData(List<DATAItem> dataItemList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,10,true,0));
        InventoryMainAdapter inventoryMainAdapter=new InventoryMainAdapter(dataItemList,context);
        recyclerView.setAdapter(inventoryMainAdapter);

    }
}
