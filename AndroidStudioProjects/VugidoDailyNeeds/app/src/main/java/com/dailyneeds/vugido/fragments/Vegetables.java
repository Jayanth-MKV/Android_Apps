package com.dailyneeds.vugido.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.adapters.ProductAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.models.DataItem;
import com.dailyneeds.vugido.models.Product;
import com.dailyneeds.vugido.network.ApiEndPointInterface;
import com.dailyneeds.vugido.network.RetrofitBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Vegetables extends Fragment implements NetworkQueries.NetworkQueryInterface{
    private Context context;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private TextView TimeLeft;
    private Activity activity;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView CartCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context=getContext();
        activity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=  inflater.inflate(R.layout.vegetables_fragment,container,false);
        recyclerView=view.findViewById(R.id.veg_recyclerView);
        TimeLeft=view.findViewById(R.id.main_time_left);
        imageView=view.findViewById(R.id.timerImage);
        linearLayout=view.findViewById(R.id.timer_linear_layout);

        startTimeLeftCounter();

        // get the data from the server....



       // bindToAdapter(null);
        getDefaultData();
      //  NetworkQueries networkQueries=new NetworkQueries(context,this ,ConfigVariables.VEGETABLES_URL,null);
      //  networkQueries.sendRequest("Getting All Vegetables");

       return view;
    }

    private void getDefaultData() {

        /*Create handle for the RetrofitInstance interface*/


        ApiEndPointInterface apiEndPointInterface=RetrofitBuilder.getInstance().getRetrofit();
        Call<List<DataItem>> dataItemCall=apiEndPointInterface.getvegetables();

       dataItemCall.enqueue(new Callback<List<DataItem>>() {
           @Override
           public void onResponse(Call<List<DataItem>> call, Response<List<DataItem>> response) {
               Log.e("Response",response.toString());
               bindToAdapter(response.body());
           }

           @Override
           public void onFailure(Call<List<DataItem>> call, Throwable t) {


               Log.e("Failed",t.toString());

           }
       });
     /*   RetrofitClientInstance.GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitClientInstance.GetDataService.class);
        Call<List<DataItem>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<DataItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataItem>> call, @NonNull Response<List<DataItem>> response) {

                Log.e("Res",response.toString());

                bindToAdapter(response.body());


            }

            @Override
            public void onFailure(@NonNull Call<List<DataItem>> call, @NonNull Throwable t) {


                Log.e("failed",t.toString());

            }
        });*/
    }

    private void startTimeLeftCounter() {

        CountDownTimer countDownTimer=  new CountDownTimer(

                ConfigVariables.getMilliSeconds(),1000 ) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                long remainedMins= millisUntilFinished/60000;
                long remainedHrs=millisUntilFinished/3600000;


                TimeLeft.setText( (remainedHrs)+":"+(remainedMins % 60) + ":" + (remainedSecs % 60));


              //  TimeLeft.setText( (millisUntilFinished/3600000)+":"+(remainedSecs / 60) + ":" + (remainedSecs % 60));
            }

            @Override
            public void onFinish() {

                linearLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);

            }
        };
        countDownTimer.start();
    }

    @Override
    public void networkQueryInterface(String Response) {

        // from here get the fetched data and bind to the product adapter...

        Log.e("VEG",Response);

        try {
            JSONObject jsonObject=new JSONObject(Response);
            boolean error =jsonObject.getBoolean("error");


            if(!error){


                 JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                 List<Product>productList=jsonResponseParser.getJsonProductsParser();
              //  bindToAdapter(productList);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void bindToAdapter(List<DataItem> dataItemsList) {

        productAdapter=new ProductAdapter(context,getFragmentManager(),dataItemsList);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new Space(2,10,true,0));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void networkQueryError(String error) {



    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Get the SearchView and set the searchable configuration
       /* SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
      //  searchView.setOnQueryTextListener(this);

*/


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);


    }

 /*   @Override
    public boolean onQueryTextSubmit(String query) {



        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        assert productAdapter!=null;
        productAdapter.getFilter().filter(newText);

        return false;
    }*/
}
