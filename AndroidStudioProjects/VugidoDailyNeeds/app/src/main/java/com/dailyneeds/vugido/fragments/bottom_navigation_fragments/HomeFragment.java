package com.dailyneeds.vugido.fragments.bottom_navigation_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.Retrofit.RetrofitBuilder;
import com.dailyneeds.vugido.activities.CartActivity;
import com.dailyneeds.vugido.activities.CategoryActivity;
import com.dailyneeds.vugido.activities.MainActivity;
import com.dailyneeds.vugido.adapters.SliderAdapterExample.SliderAdapterExample;
import com.dailyneeds.vugido.adapters.categories_adapter.Categories_Adapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.fragments.ProductAddOnDialog;
import com.dailyneeds.vugido.models.CategoriesModel.CategoriesItem;
import com.dailyneeds.vugido.models.CategoriesModel.CategoriesResponse;
import com.dailyneeds.vugido.models.ResponseComingSoon;
import com.dailyneeds.vugido.models.ResultsItem;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.android.material.card.MaterialCardView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;

public class HomeFragment extends Fragment implements Categories_Adapter.InvokeCategoryActivity, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final int CART_CODE=100;

    private View view;
    private Context mcontext;
    private RecyclerView recyclerView;
    private TextView CartCount;
    private SliderView sliderView;
  //  ProgressBar progressBar;
    //Sprite sprite;
    Activity activity;
    TextView prompt;
    private CountUpdateer countUpdateer;
    MaterialCardView whatsapp;
    private SwipeRefreshLayout swipeRefreshLayout;




    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        activity=getActivity();
        countUpdateer= (CountUpdateer) getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_categories);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);

        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 3, LinearLayoutManager.HORIZONTAL, false);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mcontext);
       // LinearLayoutManager gridLayoutManager=new GridLayoutManager(mcontext,2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,2,true,0));
        recyclerView.setNestedScrollingEnabled(false);


        //sliderView = view.findViewById(R.id.imageSlider);

      //  progressBar=view.findViewById(R.id.spin_kit);
       // sprite= new WanderingCubes();
        //progressBar.setProgressDrawable(sprite);
      //  progressBar.setVisibility(View.VISIBLE);



        prompt=view.findViewById(R.id.prompt_msg);
        prompt.setVisibility(View.GONE);


        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(() -> {


            // get the data here
           // f//etchHomePageData();

            CallCategories(recyclerView);

            // call coming soon items..

            // callComingSoon();
            getHomePageAllInfo();

        });




        whatsapp=view.findViewById(R.id.whats_app_icon);
        whatsapp.setOnClickListener(this);

        // swipe listener..

        return view;
    }

    private void getHomePageAllInfo() {





    }

    private void callComingSoon() {



        Call<ResponseComingSoon> call = RetrofitBuilder.getInstance().getRetrofit().comingSoon();
        call.enqueue(new Callback<ResponseComingSoon>() {
            @Override
            public void onResponse(@NonNull Call<ResponseComingSoon> call, @NonNull Response<ResponseComingSoon> response) {
                if (response.isSuccessful()){



                    assert response.body() != null;
                    sliderView.setSliderAdapter(new SliderAdapterExample(mcontext,response.body().getResults()));
                    sliderView.startAutoCycle();
                    sliderView.setIndicatorAnimation(IndicatorAnimations.DROP);
                    sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);




                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseComingSoon> call, @NonNull Throwable t) {

                Toast.makeText(mcontext, "please check your connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void CallCategories(final RecyclerView recyclerView) {




        Call<CategoriesResponse> call = RetrofitBuilder.getInstance().getRetrofit().categories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoriesResponse> call, @NonNull Response<CategoriesResponse> response) {
                if (response.isSuccessful()){

                   // progressBar.setVisibility(View.GONE);

                    assert response.body() != null;

                    setData(response.body().getResults());
                    swipeRefreshLayout.setRefreshing(false);



                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoriesResponse> call, @NonNull Throwable t) {

                Toast.makeText(mcontext, "please check your connection", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    private void setData(List<CategoriesItem> results) {

        Categories_Adapter categories_adapter = new Categories_Adapter(results,mcontext,this);
        recyclerView.setAdapter(categories_adapter);
        prompt.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cart){


            // showStartDate();
            activity.startActivityForResult(new Intent(mcontext, CartActivity.class), MainActivity.ORDER_PLACED_CODE);
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
        ConfigVariables.setupBadge(new MyPreferences(mcontext).getCartCount(),CartCount);
        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));






    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void invokeCategoryActivity(String name, int Id) {

            Bundle args = new Bundle();
            args.putString("N", name);
            args.putInt("ID",Id);



         Intent intent=new Intent(mcontext, CategoryActivity.class);
          intent.putExtra("BUNDLE",args);
          startActivityForResult(intent,CART_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== CART_CODE && resultCode== RESULT_CANCELED){


            Log.e("executed","cart code from fragment..");

            // update the cart here..
            if(CartCount!=null)
            ConfigVariables.setupBadge(new MyPreferences(mcontext).getCartCount(),CartCount);



        }else if(requestCode== CART_CODE && resultCode==999){

            countUpdateer.countUpdater();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.whats_app_icon:

                // start whatsApp


                try {
                    String text = "Dear Vugido Team :";// Replace with your message.

                    String toNumber = "917447337566"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
              /*  Uri uri= Uri.parse("smsto" + "9346834541");
                Intent i=new Intent(Intent.ACTION_SENDTO,uri);
                i.setPackage("com.whatsapp");
                startActivity(i);*/





                break;
        }


    }

    @Override
    public void onRefresh() {


        CallCategories(recyclerView);

        // call coming soon items..

        // callComingSoon();
        getHomePageAllInfo();


    }

    /*  @Override
    public void updateCartCount(int Count) {


        assert CartCount!=null;
        ConfigVariables.setupBadge(Count,CartCount);

    }*/

    public  interface CountUpdateer{

        void countUpdater();
    }

}
