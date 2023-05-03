package com.dailyneeds.vugido.fragments.categories_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.Retrofit.RetrofitBuilder;
import com.dailyneeds.vugido.activities.CartActivity;
import com.dailyneeds.vugido.activities.MainActivity;
import com.dailyneeds.vugido.adapters.sectioned_recycler_view.MySection;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.fragments.ProductAddOnDialog;
import com.dailyneeds.vugido.models.SubCategoryModel.SectionedItemModel;
import com.dailyneeds.vugido.models.SubCategoryModel.SubCategoryItem;
import com.dailyneeds.vugido.models.SubCategoryModel.SubCategoryResponse;
import com.dailyneeds.vugido.models.SubCategoryModel.SubMenuItem;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment implements ProductAddOnDialog.UpdateCartCount {

    private Context context;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    private TextView CartCount;
    Activity activity;

    Sprite sprite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getContext();
        activity=getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        assert getArguments() != null;
        int CID = getArguments().getInt("CID");
        recyclerView = view.findViewById(R.id.recyclerview_categories);


        // now fetch the data from the server using the category id above CID...

        progressBar=view.findViewById(R.id.spin_kit);
        sprite= new WanderingCubes();
        progressBar.setProgressDrawable(sprite);
        progressBar.setVisibility(View.VISIBLE);

        fetchData(CID);


        return view;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cart){


            // showStartDate();
            activity.startActivityForResult(new Intent(context, CartActivity.class), MainActivity.ORDER_PLACED_CODE);
            return true;

        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // configure your search here
        super.onCreateOptionsMenu(menu, inflater);
        final MenuItem cart = menu.findItem(R.id.cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(context).getCartCount(),CartCount);
        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));






    }


    private void fetchData(int CID) {


        HashMap<String, Object> request = new HashMap<>();
        request.put("CID", CID);

        Call<SubCategoryResponse> call = RetrofitBuilder.getInstance().getRetrofit().sub_categories(request);
        call.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<SubCategoryResponse> call, @NonNull Response<SubCategoryResponse> response) {

                if (response.isSuccessful()) {

                    // now fetch the list or pass the list directly to list...
                    progressBar.setVisibility(View.GONE);

                    assert response.body() != null;
                    Log.e("ItemsData", response.body().toString());

                    List<SubCategoryItem> subCategoryItems = response.body().getITEMS();
                    List<SubMenuItem> subMenuItems = response.body().getMENU();


                    setSectionedRecyclerView(subCategoryItems, subMenuItems,CID);


                } else {

                    Toast.makeText(context, "response not get ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SubCategoryResponse> call, @NonNull Throwable t) {

                Toast.makeText(context, "please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSectionedRecyclerView(List<SubCategoryItem> subCategoryItemList, List<SubMenuItem> subMenuItemList,int CID) {

        //   List<SectionedItemModel> sectionedItemModelList=new ArrayList<>();

        SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter=new SectionedRecyclerViewAdapter();

        for (int i = 0; i < subMenuItemList.size(); i++) {
            List<SubCategoryItem> SectionedsubCategoryItems = new ArrayList<>();
            boolean found = false;

            for (int j = 0; j < subCategoryItemList.size(); j++) {

                if (Integer.parseInt(subCategoryItemList.get(j).getCID()) == subMenuItemList.get(i).getCID()) {

                    SectionedsubCategoryItems.add(subCategoryItemList.get(j));
                    found = true;

                }

            }

            if (found) {
                sectionedRecyclerViewAdapter.addSection
                        (new MySection(subMenuItemList.get(i).getSUBNAME(), SectionedsubCategoryItems, context,getFragmentManager(),CID));

            }






            }

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedRecyclerViewAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.addItemDecoration(new Space(2,5,true,0));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(sectionedRecyclerViewAdapter);


// Add your Sections

// Set up your RecyclerView with the SectionedRecyclerViewAdapter
            // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            //  recyclerView.setAdapter(sectionAdapter);
        }

    @Override
    public void updateCartCount(int Count) {

        assert CartCount!=null;
        ConfigVariables.setupBadge(Count,CartCount);

    }
}

