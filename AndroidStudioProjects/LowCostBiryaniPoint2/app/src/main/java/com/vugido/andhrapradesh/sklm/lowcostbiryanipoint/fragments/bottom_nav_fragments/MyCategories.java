package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.CategoryRecyclerViewAdapter;
import com.foodhub.vugido.models.CategoryFragmentModel.DATAItem;
import com.foodhub.vugido.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class MyCategories extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_plans, container, false);
        recyclerView=view.findViewById(R.id.categories_fragment_recycler_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        fetchHomePageData();
        return  view;
    }

    private void fetchHomePageData() {



     /*   Call<Response> call = RetrofitBuilder.getInstance().getRetrofit().();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {



                if (response.isSuccessful()){

                    assert response.body() != null;
                    List<DATAItem> dataItemList=response.body().getDATA();

                    parseAllCategoriesDataList(dataItemList);


                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {



            }
        });*/


    }

    private void parseAllCategoriesDataList(List<DATAItem> dataItemList) {


        ////////////grid data
        List<CategoryModel> CategoryModelList=new ArrayList<>();


        for (DATAItem dataItem:dataItemList){

            CategoryModelList.add(new CategoryModel(dataItem.getCATTITLE(),dataItem.getICON(),dataItem.getID()));
        }


        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter=new CategoryRecyclerViewAdapter(CategoryModelList,context);
        recyclerView.setAdapter(categoryRecyclerViewAdapter);

    }
}
