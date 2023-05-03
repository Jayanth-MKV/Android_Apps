package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodhub.vugido.R;

import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.ALLPRODUCTSItem;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.CATEGORIESItem;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.DATA;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.MINIBANNER;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.OFFERSItem;

import java.util.List;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Context context;
   /* private List<SliderModel> sliderModelList;
    MaterialCardView whatsapp;
   // private FloatingActionButton floatingActionButton;
    private ImageView MiniBannerImage;*/

  /*  private ViewPager SliderViewPager;
    private RecyclerView CategoriesRecyclerView;
    private RecyclerView SeasonalRecyclerView,LeafyRecyclerView,SpecialRecyclerView,CommonRecyclerView;*/

    // All View Buttons
   // private Button RiceViewAll,StarterViewAll,RoasterViewAll,VegCurriesViewAll;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);





      /* // floatingActionButton=view.findViewById(R.id.floating_search);

       // MiniBannerImage=view.findViewById(R.id.strip_ad_image);
        SliderViewPager=view.findViewById(R.id.view_pager_banner);
        CategoriesRecyclerView=view.findViewById(R.id.grid_recycler_view);


        SeasonalRecyclerView=view.findViewById(R.id.seasonal_fruits_recycler_view);
        LeafyRecyclerView=view.findViewById(R.id.starters_items_recycler_view);
        SpecialRecyclerView=view.findViewById(R.id.roasted_items_recycler_view);
        CommonRecyclerView=view.findViewById(R.id.veg_curries_items_recycler_view);
       // whatsapp=view.findViewById(R.id.whats_app_icon);
*/









    /*   // recyclerView = view.findViewById(R.id.home_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
*/

     //   whatsapp.setOnClickListener(this);

        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {


                // get the data here
                fetchHomePageData();

            }
        });


      /*  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    //cardView.setVisibility(View.VISIBLE);
                    floatingActionButton.show();
                }
            }
        });*/

     /* RiceViewAll=view.findViewById(R.id.rice_view_all);
      RiceViewAll.setOnClickListener(this);
      StarterViewAll=view.findViewById(R.id.starters_category_view_all);
      StarterViewAll.setOnClickListener(this);
      RoasterViewAll=view.findViewById(R.id.roasted_category_view_all);
      RoasterViewAll.setOnClickListener(this);
      VegCurriesViewAll=view.findViewById(R.id.veg_category_view_all);
      VegCurriesViewAll.setOnClickListener(this);*/
        return view;


    }


    private void parseData(DATA data) {


        // here fetch the entire data and set to layout..
      /*  MINIBANNER minibanner=data.getMINIBANNER();

        setMiniBannerLayout(minibanner);*/


       /* List<OFFERSItem> offersItemList=data.getOFFERS();

        setOffersItemList(offersItemList);


       List<CATEGORIESItem> categoriesItemList= data.getCATEGORIES();

       setCategoriesList(categoriesItemList);



       List<ALLPRODUCTSItem> allproductsItemList= data.getALLPRODUCTS();


       setAllProductsList(allproductsItemList);*/



    }

    private void setAllProductsList(List<ALLPRODUCTSItem> allproductsItemList) {


       /* List<COMMONVEGETABLESItem> riceitemsItemList=allproductsItemList.get(0).getCOMMONVEGETABLES();
        CommonRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        CommonRecyclerViewAdapter commonRecyclerViewAdapter =new CommonRecyclerViewAdapter(context,riceitemsItemList);
        CommonRecyclerView.setAdapter(commonRecyclerViewAdapter);
        CommonRecyclerView.setNestedScrollingEnabled(false);


        List<SEASONALVEGETABLESItem> startersItemList=allproductsItemList.get(1).getSEASONALVEGETABLES();
        SeasonalRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        SeasonalRecyclerViewAdapter seasonalRecyclerViewAdapter =new SeasonalRecyclerViewAdapter(context,startersItemList);
        SeasonalRecyclerView.setAdapter(seasonalRecyclerViewAdapter);
        SeasonalRecyclerView.setNestedScrollingEnabled(false);


        List<LEAFYVEGETABLESItem> roasteditemsItemList=allproductsItemList.get(2).getLEAFYVEGETABLES();
        LeafyRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        LeafyRecyclerViewAdapter leafyRecyclerViewAdapter =new LeafyRecyclerViewAdapter(context,roasteditemsItemList);
        LeafyRecyclerView.setAdapter(leafyRecyclerViewAdapter);
        LeafyRecyclerView.setNestedScrollingEnabled(false);


        List<SPECIALVEGETABLESItem> vegcurriesItemList=allproductsItemList.get(3).getSPECIALVEGETABLES();
        SpecialRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        SpecialRecyclerViewAdapter specialRecyclerViewAdapter =new SpecialRecyclerViewAdapter(context,vegcurriesItemList);
        SpecialRecyclerView.setAdapter(specialRecyclerViewAdapter);
        SpecialRecyclerView.setNestedScrollingEnabled(false);*/









    }

    private void setCategoriesList(List<CATEGORIESItem> categoriesItemList) {



        // here load the main data...



      /*  LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter=new HorizontalRecyclerViewAdapter(categoriesItemList,context);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(horizontalRecyclerViewAdapter);*/



    }

    private void setOffersItemList(List<OFFERSItem> offersItemList) {


      /*  SliderBannerAdapter sliderBannerAdapter=new SliderBannerAdapter(context,offersItemList);
        SliderViewPager.setAdapter(sliderBannerAdapter);
        SliderViewPager.setPageMargin(20);
        SliderViewPager.setClipToPadding(false);*/

    }

    private void setMiniBannerLayout(MINIBANNER minibanner) {


     //   Glide.with(context).load(minibanner.getIMAGE()).into(MiniBannerImage);


        //Log.e("image mini",minibanner.getIMAGE());


    }

    private void fetchHomePageData() {


        // call retrofit....




       // Call<com.vugido.StudentTime.models.VEG_HOME_PAGE_MODEL.Response> call=RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent();

     /*   call.enqueue(new Callback<com.vugido.StudentTime.models.VEG_HOME_PAGE_MODEL.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.StudentTime.models.VEG_HOME_PAGE_MODEL.Response> call, @NonNull retrofit2.Response<com.vugido.StudentTime.models.VEG_HOME_PAGE_MODEL.Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // data not null fetched..
                        com.vugido.StudentTime.models.VEG_HOME_PAGE_MODEL.DATA data=response.body().getDATA();


                        parseData(data);
                        Log.e("data","received");



                    }else {

                        // no data returned null

                        Log.e("no data","received");

                    }


                }

                swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {


                swipeRefreshLayout.setRefreshing(false);

            }
        });*/


      /*  Call<Response> call = RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                Log.e("Response",response.message());
                if(response.isSuccessful()){


                    assert response.body()!=null;
                    List<DATAItem> dataItemList=response.body().getDATA();
                    List<TYPEINDICATORItem> typeindicatorItemList=response.body().getTYPEINDICATOR();

                    homeLayoutSeparator(dataItemList,typeindicatorItemList);

                }


                swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {


                Log.e("Error",t.toString());
                swipeRefreshLayout.setRefreshing(false);

            }
        });
*/


        //swipeRefreshLayout.setRefreshing(false);


   /*     sliderModelList.add(new SliderModel(R.drawable.casalo, "#ffffff"));
        sliderModelList.add(new SliderModel(R.drawable.curry1, "#ffffff"));


        sliderModelList.add(new SliderModel(R.drawable.bannere, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.curry1, "#ffffff"));
        sliderModelList.add(new SliderModel(R.drawable.search, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.casalo, "#ffffff"));


        sliderModelList.add(new SliderModel(R.drawable.bannere, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.curry1, "#ffffff"));
*/

        ////////////grid data
      /*  List<GridCategoryModel> gridCategoryModelList=new ArrayList<>();
        gridCategoryModelList.add(new GridCategoryModel("Meals",R.drawable.curry1));
        gridCategoryModelList.add(new GridCategoryModel("Mini Meals",R.drawable.casalo));

        gridCategoryModelList.add(new GridCategoryModel("Curries",R.drawable.casalo));
        gridCategoryModelList.add(new GridCategoryModel("Water",R.drawable.curry1));*/



        ////////////////Horizontal Data

     /* List<HorizontalCategoryModel> horizontalCategoryModelList=new ArrayList<>();
        horizontalCategoryModelList.add(new HorizontalCategoryModel("Vegetables",R.drawable.vegetables));
        horizontalCategoryModelList.add(new HorizontalCategoryModel("Fruits",R.drawable.fruit));
        horizontalCategoryModelList.add(new HorizontalCategoryModel("Home Meals & Curries",R.drawable.curry1));
        horizontalCategoryModelList.add(new HorizontalCategoryModel("Mini Curries",R.drawable.curry2));
*/


        ///////////// Horizontal Data

       // List<HomePageModel> homePageModelList = new ArrayList<>();

      /*  //promo
        homePageModelList.add(new HomePageModel(1, R.drawable.bannere, "#000000"));*/

    /*    //offers
        homePageModelList.add(new HomePageModel(sliderModelList, 0));*/

        //categories
      //  homePageModelList.add(HomePageModel.createHorizontalCategoryModel(3,horizontalCategoryModelList));

      /*  // some offer or promo..
        homePageModelList.add(new HomePageModel(1, R.drawable.bannere, "#ffffff"));*/

        //Today's deal
        //homePageModelList.add(new HomePageModel(2,gridCategoryModelList,"Today's Deal"));

        // Home meals and curries
       // homePageModelList.add(new HomePageModel(2,gridCategoryModelList,"Home Meals & Curries"));

        // offers
      //  homePageModelList.add(new HomePageModel(sliderModelList, 0));

        // Vegetables..
        //homePageModelList.add(new HomePageModel(2,gridCategoryModelList,"Vegetables"));

      /*  //
        homePageModelList.add(new HomePageModel(1, R.drawable.bannere, "#ffffff"));*/

        // fruits
        //homePageModelList.add(new HomePageModel(2,gridCategoryModelList,"Fruits"));


        // mini curries
      //  homePageModelList.add(new HomePageModel(2,gridCategoryModelList,"Mini Curries"));


        // end of list








      /*  HomePageRecyclerViewAdapter homePageRecyclerViewAdapter = new HomePageRecyclerViewAdapter(homePageModelList, context);
        recyclerView.setAdapter(homePageRecyclerViewAdapter);
        homePageRecyclerViewAdapter.notifyDataSetChanged();*/


    }



    /*private void homeLayoutSeparator(List<DATAItem> dataItemList, List<TYPEINDICATORItem> typeindicatorItemList) {
        List<HomePageModel> homePageModelList = new ArrayList<>();



        for (DATAItem dataItem: dataItemList){

            switch (dataItem.getLID()){
                case 1:
                    //Sliders
                    List<SLIDERSDATAItem> slidersdataItemList=dataItem.getSLIDERSDATA();
                    sliderModelList = new ArrayList<>();

                    for(SLIDERSDATAItem slidersdataItem:slidersdataItemList){

                        sliderModelList.add(new SliderModel(slidersdataItem.getIMAGE(),slidersdataItem.getBG()));
                    }
                    //offers
                    homePageModelList.add(new HomePageModel(sliderModelList, 0));
                    break;
                case 2:
                    //Mini banners

                    MINIDATA minidata=dataItem.getMINIDATA();
                    //promo
                    homePageModelList.add(new HomePageModel(1,minidata.getIMAGE(), minidata.getBG()));

                    break;
                case 3:
                    //Grid Layout

                    List<GRIDDATAItem> griddataItemList=dataItem.getGRIDDATA();
                    List<GridCategoryModel> gridCategoryModelList=new ArrayList<>();

                    for(GRIDDATAItem griddataItem:griddataItemList){
                        gridCategoryModelList.add(new GridCategoryModel(griddataItem.getTNAME(),griddataItem.getIMAGE()));

                    }
                    homePageModelList.add(new HomePageModel(2,gridCategoryModelList,dataItem.getTITLE()));

                    break;
                case 4:
                    //Horizontal Layout

                    List<HORIZONTALDATAItem> horizontaldataItemList=dataItem.getHORIZONTALDATA();
                    List<HorizontalCategoryModel> horizontalCategoryModelList=new ArrayList<>();

                    for (HORIZONTALDATAItem horizontaldataItem:horizontaldataItemList){

                        horizontalCategoryModelList.add(new HorizontalCategoryModel(horizontaldataItem.getCATTITLE(),horizontaldataItem.getICON()));


                    }
                    homePageModelList.add(HomePageModel.createHorizontalCategoryModel(3,horizontalCategoryModelList));


                    break;
            }

        }

        HomePageRecyclerViewAdapter homePageRecyclerViewAdapter = new HomePageRecyclerViewAdapter(homePageModelList, context);
        recyclerView.setAdapter(homePageRecyclerViewAdapter);
        homePageRecyclerViewAdapter.notifyDataSetChanged();




    }*/

    @Override
    public void onRefresh() {

        fetchHomePageData();
    }


    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()){
          /*  case R.id.whats_app_icon:

                // start whatsApp


                try {
                    String text = "Dear Vugido Team :";// Replace with your message.

                    String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                */
              /*  Uri uri= Uri.parse("smsto" + "9346834541");
                Intent i=new Intent(Intent.ACTION_SENDTO,uri);
                i.setPackage("com.whatsapp");
                startActivity(i);*/





              //  break;
          /*  case R.id.rice_view_all:
                // launch the all products activity..
                intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("CID",2);
                intent.putExtra("TITLE","Seasonal Vegetables");
                startActivity(intent);
                break;
            case R.id.starters_category_view_all:
                intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("CID",3);
                intent.putExtra("TITLE","Leafy Vegetables");
                startActivity(intent);
                break;
            case R.id.roasted_category_view_all:
                intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("CID",4);
                intent.putExtra("TITLE","Special Items");
                startActivity(intent);
                break;

            case R.id.veg_category_view_all:
                intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("CID",1);
                intent.putExtra("TITLE","Common Vegetables");
                startActivity(intent);
                break;*/
        }
    }
}
