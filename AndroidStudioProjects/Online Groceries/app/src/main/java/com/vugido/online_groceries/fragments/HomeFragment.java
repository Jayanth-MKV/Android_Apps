package com.vugido.online_groceries.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.homepage.HomePageMainAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.models.homepage.DATA;
import com.vugido.online_groceries.models.homepage.HomePageMainModel;
import com.vugido.online_groceries.models.homepage.Response;
import com.vugido.online_groceries.models.homepage.SECTIONSItem;
import com.vugido.online_groceries.models.homepage.SERVICECLIENTSItem;
import com.vugido.online_groceries.models.homepage.SERVICECLIENTSTYPEItem;
import com.vugido.online_groceries.network.RetrofitBuilder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TabLayout tabIndicator;
    private ViewPager viewPager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private RecyclerView CategoriesRecyclerView,rc;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter;

    private LinearLayoutManager layoutManager;

    private TextView cdes,ctitle;

    ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.e("called","called onCreateView");


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);
        CategoriesRecyclerView=view.findViewById(R.id.grid_recycler_view);
        rc=view.findViewById(R.id.home_recyclerView);
        // view pager
        tabIndicator = view.findViewById(R.id.tab_indicator);
        viewPager =view.findViewById(R.id.home_viewpager);
        swipeRefreshLayout.setOnRefreshListener(this);

        ctitle=view.findViewById(R.id.grid_category_title);
        cdes=view.findViewById(R.id.textView);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchHomePageData);


        imageView=view.findViewById(R.id.strip_ad_image);
        return view;
    }



    private void fetchHomePageData() {



        Map<String,Object> map=new HashMap<>();

        map.put("UID",new MyPreferences(context).getUID());

//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().homePage(map);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//                swipeRefreshLayout.setRefreshing(false);
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(!response.body().isSTATUS()){
//
//
//                        bindData(response.body().getDATA());
//
//
//                    }else
//                    {
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
//
//            }
//        });

    }

    private void bindData(DATA data) {

//        List<SliderModel> hightlightItemList=new ArrayList<>();
//
//        hightlightItemList.add(new SliderModel(R.drawable.demo_img,""));
//        hightlightItemList.add(new SliderModel(R.drawable.a,""));
//        hightlightItemList.add(new SliderModel(R.drawable.b,""));
//        hightlightItemList.add(new SliderModel(R.drawable.c,""));
//        hightlightItemList.add(new SliderModel(R.drawable.d,""));


        cdes.setText(data.getSERVICEDES());
        ctitle.setText(data.getSERVICETITLE());
        //SliderAdapter myHomeSlider = new SliderAdapter(data.getSLIDER(),context);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(10);
       // viewPager.setAdapter(myHomeSlider);
        tabIndicator.setupWithViewPager(viewPager);


        Glide.with(context).load(data.getiMAGE()).into(imageView);



//        // CATEGORIES DATA
//        List<CategoryModel> myHomeDataCATEGORIES=new ArrayList<>();
//        //categories
//
//        myHomeDataCATEGORIES.add(new CategoryModel("Pizzas",R.drawable.demo_img,1));
//        myHomeDataCATEGORIES.add(new CategoryModel("Dosas",R.drawable.a,2));
//        myHomeDataCATEGORIES.add(new CategoryModel("Burgers",R.drawable.b,3));
//        myHomeDataCATEGORIES.add(new CategoryModel("Sandwich",R.drawable.c,4));
//        myHomeDataCATEGORIES.add(new CategoryModel("Pani Puris",R.drawable.d,5));
//        myHomeDataCATEGORIES.add(new CategoryModel("Pizzas",R.drawable.demo_img,6));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
//        CategoriesRecyclerView.setAdapter(new CategoryRecyclerViewAdapter(data.getSERVICECATEGORIES(),context,data.getSERVICEDES(),data.getSERVICETITLE()));
//
//        CategoriesRecyclerView.setNestedScrollingEnabled(false);




        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(layoutManager);


        //////////////////
        List<SECTIONSItem> categoriesItemList=new ArrayList<>();

        for(SERVICECLIENTSTYPEItem serviceclientstypeItem: data.getSERVICECLIENTSTYPE()){
            categoriesItemList.add(new SECTIONSItem(serviceclientstypeItem.getTITLE(),serviceclientstypeItem.getID(),serviceclientstypeItem.getDESCRIBER()));
        }


        List<SERVICECLIENTSItem> productsmenuItemList = new ArrayList<>(data.getSERVICECLIENTS());


//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.demo_img,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.e,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.a,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.b,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//
//
//
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.c,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.b,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.d,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.demo_img,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));








//        for(SECTIONSItem dataItem:categoriesItemList){
//
//            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(categoriesItemList,HomePageMainModel.SQUARE_MEDIUM_VIEW,getCategoryType(dataItem,productsmenuItemList),dataItem.getCNAME(),dataItem.getSID(),"","#000000",dataItem.getDes()));
//
//        }
//        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,context);
//        rc.setAdapter(homePageMainAdapter);
//        rc.setNestedScrollingEnabled(false);








   }

    private List<SERVICECLIENTSItem> getCategoryType(SECTIONSItem dataItem, List<SERVICECLIENTSItem> productsmenuItemList) {

        List<SERVICECLIENTSItem> productsmenuItemList1=new ArrayList<>();

        for(SERVICECLIENTSItem productsmenuItem:productsmenuItemList){

            if(productsmenuItem.getTYPEID()==dataItem.getSID()){
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;




    }


    @Override
    public void onRefresh() {

        fetchHomePageData();
    }
}
