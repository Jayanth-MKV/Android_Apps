package com.vugido.vugidoupdate.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.adapters.ChipRecatangularRecyclerViewAdapter;
import com.vugido.vugidoupdate.adapters.HomePage.HomePageMainAdapter;
import com.vugido.vugidoupdate.adapters.ListProductsRecyclerViewAdapter;
import com.vugido.vugidoupdate.adapters.RectangularMediumRecyclerViewAdapter;
import com.vugido.vugidoupdate.adapters.SliderViewAdapter;
import com.vugido.vugidoupdate.adapters.SquareMediumRecyclerViewAdapter;
import com.vugido.vugidoupdate.adapters.SquareMiniRecyclerViewAdapter;
import com.vugido.vugidoupdate.designs.Space;
import com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.DATAITEMItem;
import com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.Response;
import com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.DATAItem;
import com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.DATA;
import com.vugido.vugidoupdate.models.home_page.ChipsRectangluarModel;
import com.vugido.vugidoupdate.models.home_page.CircularViewModel;
import com.vugido.vugidoupdate.adapters.CircularViewRecyclerAdapter;
import com.vugido.vugidoupdate.models.home_page.HomePageMainModel;
import com.vugido.vugidoupdate.models.home_page.ListProductsViewModel;
import com.vugido.vugidoupdate.models.home_page.RectangularMediumViewModel;
import com.vugido.vugidoupdate.models.home_page.SliderViewModel;
import com.vugido.vugidoupdate.models.home_page.SquareMediumModel;
import com.vugido.vugidoupdate.models.home_page.SquareMiniModel;
import com.vugido.vugidoupdate.network.Retrofit.RetrofitBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment{


    private int PageNumber = 1;
    private int ItemCount = 4;
    // variables for pagination....
    private boolean isLoading= true;
    private int pastVisibleItem,visibleItemCount,totalItemCount,previousTotal=0;
    private int viewThreshold = 4 ;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter=null;
    MaterialCardView materialCardViewCenter;
    LottieAnimationView lottieAnimationViewCenter;

    private RecyclerView HomeMainRecyclerView;
    private Context context;

    private LinearLayoutManager layoutManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        ////////////////////////////////////////////
        HomeMainRecyclerView=view.findViewById(R.id.HomeMainRecyclerView);
        materialCardViewCenter=view.findViewById(R.id.CenterProgressBar);
        lottieAnimationViewCenter=view.findViewById(R.id.lottie_progress_center);


        performPagination();

        HomeMainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.e("onScroll","yes");
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if(dy > 0){


                    Log.e("dy>0","true");
                    if(isLoading){
                        if(totalItemCount > previousTotal){
                            isLoading=false;
                            previousTotal=totalItemCount;

                        }
                    }

                    if(!isLoading && (totalItemCount-visibleItemCount)<=(pastVisibleItem+ viewThreshold)){

                        PageNumber++;
                        performPagination();
                        Log.e("scrolled","page called");
                        isLoading=true;

                    }

                }
            }

        });

        return view;
    }

    private void performPagination(){

        /// SHOW PROGRESS BAR...
        if(PageNumber==1){
            materialCardViewCenter.setVisibility(View.VISIBLE);
            //play lottie
            lottieAnimationViewCenter.playAnimation();
        }else {
            materialCardViewCenter.setVisibility(View.GONE);

        }
        Map<String, Object> map=new HashMap<>();
        map.put("PN",String.valueOf(PageNumber));// user id
        map.put("IC",String.valueOf(ItemCount));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getHomePageContent(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NotNull Call<Response> call, @NotNull retrofit2.Response<Response> response) {

                //shimmerFrameLayout.startShimmerAnimation();
                Log.e("Get Response","ok");
                // hide progress bar

                if(PageNumber==1){
                    materialCardViewCenter.setVisibility(View.GONE);
                    //play lottie
                    lottieAnimationViewCenter.pauseAnimation();
                }else {
                    // bottom progress

                }
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok
                        Log.e("No error","ok");
                        if(response.body().getDATA().isSTATUS()){
                            //ok data present
                            List<DATAITEMItem>dataItemList=response.body().getDATA().getDATAITEM();
                            Toast.makeText(context,"fetched",Toast.LENGTH_LONG).show();
                            Log.e("Data",dataItemList.toString());
                            bindHomePageData(dataItemList);
                        }else {

                            // no data all fetched...
                            Toast.makeText(context,"That's All No More To Fetch !!",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        // server error
                    }

                }else {

                    //error
                }
            }

            @Override
            public void onFailure(@NotNull Call<Response> call, @NotNull Throwable t) {

                // hide progress bar

               // progressBar.setVisibility(View.GONE);
                Log.e("error",t.toString());
            }
        });



    }

    private void bindHomePageData(List<DATAITEMItem> dataItemList) {

        Log.e("bind method","called");
        if(homePageMainAdapter==null){
            homePageMainModelList=new ArrayList<>();
            layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            HomeMainRecyclerView.setLayoutManager(layoutManager);

            for(DATAITEMItem dataItem:dataItemList){

               homePageMainModelList.add(getViewTypeData(dataItem));
               callSourceData(dataItem);
            }
            homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,context);
            HomeMainRecyclerView.setAdapter(homePageMainAdapter);
            Log.e("adaopter","attached");


        }else {

            for(DATAITEMItem dataItem:dataItemList){

                homePageMainModelList.add(getViewTypeData(dataItem));
                callSourceData(dataItem);
            }

            homePageMainAdapter.notifyDataSetChanged();
            Log.e("adaopter","changed..");


        }


    }


// RxJava
   /* private void fetchDataUsingRxJava(int hid) {

        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(hid));


        //Observable.concat();
        compositeDisposable.add(RetrofitBuilder.getInstance().getRetrofit().getRxJavaDemoResponse(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.vugido.vugidoupdate.models.TestingRxJava.Response>() {
                    @Override
                    public void accept(com.vugido.vugidoupdate.models.TestingRxJava.Response response) throws Exception {

                        showData(response.getDATA().toString());
                        Toast.makeText(context,response.getDATA().toString(),Toast.LENGTH_LONG).show();

                    }
                }));


    }

    private void showData(String toString) {
        Toast.makeText(context,toString,Toast.LENGTH_LONG).show();
        Log.e("ok RxJava",toString);

    }*/



    private void callSourceData(DATAITEMItem dataItem) {

        switch (dataItem.getVID()){
            case 1:
                bindCircularData(dataItem);
                break;
            case 2:
                bindSliderData(dataItem);
               // setSliderData(dataItem);
                break;
            case 3:
                bindRectMediumData(dataItem);
                //setRectangularMediumRecyclerView(dataItem);
                break;
            case 4:
                bindChipRectData(dataItem);
                //setChipRectangularRecyclerView(dataItem);
                break;
            case 5:
                bindSquareMiniData(dataItem);
                //setSquareMiniRecyclerView(dataItem);
                break;
            case 6:
                bindSquareMediumData(dataItem);
                //setSquareMediumRecyclerView(dataItem);
                break;
        }


    }

    private void bindRectMediumData(final DATAITEMItem dataItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataItem.getHID()));

        final List<RectangularMediumViewModel> rectangularMediumViewModelList=new ArrayList<>();

        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getRectangularMedium(map);

        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        //ok
                        List<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.DATAItem> dataItemList=response.body().getDATA();

                        for (com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.DATAItem dataItem1:dataItemList){

                            rectangularMediumViewModelList.add(new RectangularMediumViewModel(dataItem1.getLOGO(),dataItem1.getBUSINESSNAME(),4.5f));
                        }


                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getRectangularMediumViewModelList().toString());
                                homePageMainModelList.get(i).getRectangularMediumViewModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getRectangularMediumViewModelList().toString());
                                homePageMainModelList.get(i).getRectangularMediumViewModelList().addAll(rectangularMediumViewModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getRectangularMediumViewModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }


                    }else {

                        //server error
                    }
                }else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel.Response> call, Throwable t) {

            }
        });

    }

    private void bindChipRectData(final DATAITEMItem dataItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataItem.getHID()));

        final List<ChipsRectangluarModel> chipsRectangluarModelList=new ArrayList<>();

        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getRectangularChips(map);

        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response> response) {
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok
                        List<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.DATAItem> dataItemList=response.body().getDATA();
                        for(com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.DATAItem dataItem1:dataItemList){
                            chipsRectangluarModelList.add(new ChipsRectangluarModel(dataItem1.getIMAGE(),dataItem1.getTNAME()));

                        }

                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getChipsRectangluarModelList().toString());
                                homePageMainModelList.get(i).getChipsRectangluarModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getChipsRectangluarModelList().toString());
                                homePageMainModelList.get(i).getChipsRectangluarModelList().addAll(chipsRectangluarModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getChipsRectangluarModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }

                    }else {
                        //server error
                    }
                }else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularChipsModel.Response> call, Throwable t) {

            }
        });
    }

    private void bindSquareMiniData(final DATAITEMItem dataItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataItem.getHID()));

        final List<SquareMiniModel> squareMiniModelList=new ArrayList<>();
        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getSquareMini(map);

        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok
                        List<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.DATAItem> dataItemList=response.body().getDATA();

                        for (com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.DATAItem dataItem1:dataItemList){

                            squareMiniModelList.add(new SquareMiniModel(dataItem1.getIMAGE(),dataItem1.getTAGNAME()));
                        }

                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getSquareMiniModelList().toString());
                                homePageMainModelList.get(i).getSquareMiniModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getSquareMiniModelList().toString());
                                homePageMainModelList.get(i).getSquareMiniModelList().addAll(squareMiniModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getSquareMiniModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }
                    }else {
                        //server error
                    }
                }else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel.Response> call, Throwable t) {

            }
        });
    }

    private void bindSquareMediumData(final DATAITEMItem dataItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataItem.getHID()));

        final List<SquareMediumModel> squareMediumModelList=new ArrayList<>();

        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getSquareMedium(map);
        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        List<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.DATAItem> dataItemList=response.body().getDATA();

                        for(com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.DATAItem dataItem1:dataItemList){

                            squareMediumModelList.add(new SquareMediumModel(dataItem1.getIMAGE(),dataItem1.getTNAME(),String.valueOf(dataItem1.getPRICE()),dataItem1.getDESCRIPTION()));

                        }
                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getSquareMediumModelList().toString());
                                homePageMainModelList.get(i).getSquareMediumModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getSquareMediumModelList().toString());
                                homePageMainModelList.get(i).getSquareMediumModelList().addAll(squareMediumModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getSquareMediumModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }


                    }else {
                        //server error
                    }

                }else {

                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel.Response> call, Throwable t) {

            }
        });
    }

    private void bindSliderData(final DATAITEMItem dataItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataItem.getHID()));
        final List<SliderViewModel> sliderViewModelList=new ArrayList<>();
        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getSliderData(map);

        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        List<DATAItem> dataItemList=response.body().getDATA();

                        for(DATAItem dataItem1:dataItemList){
                            sliderViewModelList.add(new SliderViewModel(dataItem1.getIMAGE(),dataItem1.getID()));
                        }
                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getSliderViewModelList().toString());
                                homePageMainModelList.get(i).getSliderViewModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getSliderViewModelList().toString());
                                homePageMainModelList.get(i).getSliderViewModelList().addAll(sliderViewModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getSliderViewModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }


                    }else {
                        //server problem
                    }

                }else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call, Throwable t) {

            }
        });
    }

    private void bindCircularData(final DATAITEMItem dataitemItem) {
        Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataitemItem.getHID()));
        final List<CircularViewModel> circularViewModelList=new ArrayList<>();
        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getCircularViewData(map);
        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response> call, retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        List<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.DATAItem> dataItemList=response.body().getDATA();

                        for (com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.DATAItem dataItem :dataItemList){

                            circularViewModelList.add(new CircularViewModel(dataItem.getTYPENAME(),dataItem.getIMAGE()));

                        }

                        for(int i=0;i<homePageMainModelList.size();i++){

                            Log.e("HID matching","ok");
                            if(homePageMainModelList.get(i).getHID()==dataitemItem.getHID()){

                                Log.e("HID matched","ok");
                                Log.e("List Preview",homePageMainModelList.get(i).getCircularViewModelList().toString());
                                homePageMainModelList.get(i).getCircularViewModelList().clear();
                                Log.e("List Cleared",homePageMainModelList.get(i).getCircularViewModelList().toString());
                                homePageMainModelList.get(i).getCircularViewModelList().addAll(circularViewModelList);
                                Log.e("List Changed",homePageMainModelList.get(i).getCircularViewModelList().toString());
                                homePageMainAdapter.notifyDataSetChanged();
                                break;

                            }
                        }


                    }else {
                        // server error
                    }

                }else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel.Response> call, Throwable t) {

            }
        });


    }

    private HomePageMainModel getViewTypeData(DATAITEMItem dataitemItem) {
        switch (dataitemItem.getVID()){
            case 1:
                return setCircularHorizontalAdapter(dataitemItem);
            case 2:
                return setSliderData(dataitemItem);
            case 3:
                return  setRectangularMediumRecyclerView(dataitemItem);
            case 4:
                return setChipRectangularRecyclerView(dataitemItem);
            case 5:
                return setSquareMiniRecyclerView(dataitemItem);
            case 6:
                return setSquareMediumRecyclerView(dataitemItem);
            case 7:
                return setListRecyclerViewAdapter(dataitemItem);
            default:
                return null;

        }
    }

    private HomePageMainModel setSliderData(DATAITEMItem dataitemItem){

        final List<SliderViewModel>sliderViewModelList=new ArrayList<>();
        sliderViewModelList.add(new SliderViewModel("",1));
        sliderViewModelList.add(new SliderViewModel("",1));
        sliderViewModelList.add(new SliderViewModel("",1));
        sliderViewModelList.add(new SliderViewModel("",1));
        sliderViewModelList.add(new SliderViewModel("",1));
        sliderViewModelList.add(new SliderViewModel("",1));

        //call
       /* Map<String, Object> map=new HashMap<>();
        map.put("HID",String.valueOf(dataitemItem.getHID()));

        Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call=RetrofitBuilder.getInstance().getRetrofit().getSliderData(map);
        call.enqueue(new Callback<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response>() {
            @Override
            public void onResponse(@NotNull Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call, @NotNull retrofit2.Response<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        List<DATAItem> dataItemList=response.body().getDATA();

                        for (DATAItem dataItem :dataItemList){

                            sliderViewModelList.add(new SliderViewModel(dataItem.getIMAGE(),dataItem.getID()));

                        }
                        homePageMainModelList.add(HomePageMainModel.createSliderViewModelList(HomePageMainModel.SLIDER_VIEW,sliderViewModelList));
                        homePageMainAdapter.notifyDataSetChanged();

                    }else {
                        // server error
                    }

                }else {
                    // error
                }

            }

            @Override
            public void onFailure(@NotNull Call<com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel.Response> call, @NotNull Throwable t) {

            }
        });

*/

        return HomePageMainModel.createSliderViewModelList(HomePageMainModel.SLIDER_VIEW,sliderViewModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());
    }

    private HomePageMainModel setListRecyclerViewAdapter(DATAITEMItem dataitemItem) {

        List<ListProductsViewModel> listProductsViewModelList=new ArrayList<>();
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.c,"Apple","Rs.30/-","Foods","30%","1 plate"));
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.a,"Grapes","Rs.100/-","Meals","45%","1 plate"));
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.d,"Promogranate","","Dry fruits","90%","1 Plate"));
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.e,"Apple","Rs.87/-","Fruits","80%","1 Plate"));
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.curry1,"Apple","Rs.50/-","Tiffen's","30%","1 Unit"));
        listProductsViewModelList.add(new ListProductsViewModel(R.drawable.b,"Pine-Apple","Rs.100/-","Burger","20%","1 plate"));
        return HomePageMainModel.createListViewModelList(HomePageMainModel.LIST_VIEW,listProductsViewModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());

    }

    private HomePageMainModel setChipRectangularRecyclerView(DATAITEMItem dataitemItem) {

        List<ChipsRectangluarModel> chipsRectangluarModelList=new ArrayList<>();
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Apple"));
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Grapes"));
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Promogranate"));
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Apple"));
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Apple"));
        chipsRectangluarModelList.add(new ChipsRectangluarModel("","Pine-Apple"));

        return HomePageMainModel.createChipsRectangularViewModelList(HomePageMainModel.CHIP_RECTANGULAR_VIEW,chipsRectangluarModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());
    }

    private HomePageMainModel setSquareMiniRecyclerView(DATAITEMItem dataitemItem) {

        List<SquareMiniModel> squareMiniModelList=new ArrayList<>();

        squareMiniModelList.add(new SquareMiniModel("","Chicken"));
        squareMiniModelList.add(new SquareMiniModel("","Pizza"));
        squareMiniModelList.add(new SquareMiniModel("","Chicken"));
        squareMiniModelList.add(new SquareMiniModel("","Egg Rolls"));
        squareMiniModelList.add(new SquareMiniModel("","Chicken"));
        squareMiniModelList.add(new SquareMiniModel("","Burger"));
        squareMiniModelList.add(new SquareMiniModel("","Chicken"));
        squareMiniModelList.add(new SquareMiniModel("","Chicken"));

        return HomePageMainModel.createSquareMiniViewModelList(HomePageMainModel.SQUARE_MINI,squareMiniModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());

    }

    private HomePageMainModel setSquareMediumRecyclerView(DATAITEMItem dataitemItem) {

        List<SquareMediumModel> squareMediumModelList=new ArrayList<>();
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        squareMediumModelList.add(new SquareMediumModel("","Chicken Biryani","Rs.50/-","1 plate approx 2 persons"));
        return HomePageMainModel.createSquareMediumViewModelList(HomePageMainModel.SQUARE_MEDIUM_VIEW,squareMediumModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());


    }

    private HomePageMainModel setRectangularMediumRecyclerView(DATAITEMItem dataitemItem) {

        List<RectangularMediumViewModel>  rectangularMediumViewModelList=new ArrayList<>();
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","7 Biryanies",4.0f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","Low Cost Biryani Point",4.5f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","SVD",4.1f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","Sunrise ",3.0f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","Day and Night",3.5f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","7 Biryanies",5.0f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","7 Biryanies",1.0f));
        rectangularMediumViewModelList.add(new RectangularMediumViewModel("","7 Biryanies",2.5f));
        return HomePageMainModel.createRectangularMediumViewModelList(HomePageMainModel.MEDIUM_RECTANGULAR_VIEW,rectangularMediumViewModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());
    }

    private HomePageMainModel setCircularHorizontalAdapter(DATAITEMItem dataitemItem) {

        final List<CircularViewModel> circularViewModelList=new ArrayList<>();

        circularViewModelList.add(new CircularViewModel("HI",""));
        circularViewModelList.add(new CircularViewModel("Hi",""));
        circularViewModelList.add(new CircularViewModel("Hi",""));
        circularViewModelList.add(new CircularViewModel("Hi",""));
        circularViewModelList.add(new CircularViewModel("HI",""));
        circularViewModelList.add(new CircularViewModel("HI",""));
        circularViewModelList.add(new CircularViewModel("HI",""));

        return HomePageMainModel.createCircularViewModelList(HomePageMainModel.CIRCULAR_VIEW,circularViewModelList,dataitemItem.getTITLE(),dataitemItem.getHID(),dataitemItem.getBGCOLOR(),dataitemItem.getTITLECOLOR());

    }

}