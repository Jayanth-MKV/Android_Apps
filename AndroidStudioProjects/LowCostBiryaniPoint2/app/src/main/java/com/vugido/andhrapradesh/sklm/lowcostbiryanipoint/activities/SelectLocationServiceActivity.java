package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.AvailableLocations.AllLocationAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.ServiceAvailability.LOCATIONSItem;
import com.foodhub.vugido.models.ServiceAvailability.LocationSelector;
import com.foodhub.vugido.models.ServiceAvailability.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SelectLocationServiceActivity extends AppCompatActivity implements AllLocationAdapter.LocationSetter {
    Toolbar toolbar;
    AllLocationAdapter allLocationAdapter;
    List<LocationSelector> locationSelectorList;
    RecyclerView recyclerView;
    View Progress;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location_service);
        recyclerView=findViewById(R.id.recycler);
        Progress=findViewById(R.id.my_progress);
        toolbar=findViewById(R.id.login_activity_toolbar);
        button=findViewById(R.id.button11);
        Progress.setVisibility(View.GONE);
        toolbar.setTitle("Select Your Location");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        button.setEnabled(false);
        button.setOnClickListener(v -> {

            startActivity(new Intent(SelectLocationServiceActivity.this,AppIntroduction.class));
            finish();

        });

        fetchLocations();
    }

    private void fetchLocations() {
        Progress.setVisibility(View.VISIBLE);
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/").getAllLocations();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    bindData(response.body().getLOCATIONS());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Progress.setVisibility(View.GONE);
            }
        });
    }

    private void bindData(List<LOCATIONSItem> locations) {
        locationSelectorList=new ArrayList<>();
        for (LOCATIONSItem locationsItem:locations){

            locationSelectorList.add(new LocationSelector(String.valueOf(locationsItem.getZIP()),locationsItem.getLID(),locationsItem.getLOCATION(),false));
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        allLocationAdapter=new AllLocationAdapter(this,locationSelectorList);
        recyclerView.setAdapter(allLocationAdapter);
    }


    @Override
    public void locationSetter(String URL, String Location, String Zip,int id) {

        for(int i=0;i<locationSelectorList.size();i++){
            locationSelectorList.get(i).setChecked(false);

            if(locationSelectorList.get(i).getID()==id){
                locationSelectorList.get(i).setChecked(true);
                new MyPreferences(this).setBaseLocationURL(String.valueOf(locationSelectorList.get(i).getID()));
                new  MyPreferences(this).setBaseLocationName(locationSelectorList.get(i).getNAME());
                new MyPreferences(this).setBaseLocationZIP(locationSelectorList.get(i).getZIP());
            }
        }
        if(allLocationAdapter!=null){
            allLocationAdapter.notifyDataSetChanged();
        }

        button.setEnabled(true);
    }
}
