package com.dailyneeds.vugido.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.activities.CartActivity;
import com.dailyneeds.vugido.activities.MainActivity;
import com.dailyneeds.vugido.adapters.MyPurchasesAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.models.OnTheWay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPurchases extends Fragment implements NetworkQueries.NetworkQueryInterfaceWithCode {
    private RecyclerView recyclerView;
    private Context context;
    private RelativeLayout relativeLayout;
    private TextView CartCount;
    Activity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_purchases,container,false);
        recyclerView=view.findViewById(R.id.my_purchases_recyclerView);
        relativeLayout=view.findViewById(R.id.purchases_empty_layout);

        // get the data from server and bind to the adapter..
        NetworkQueries networkQueries=new NetworkQueries(context,this, ConfigVariables.GET_USER_PURCHASES,buildParams(),1);
        networkQueries.sendRequestCode("Getting your purchases");


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

      /*  MenuItem menuItem = menu.findItem(R.id.search);
        menuItem.setVisible(false);*/

        final MenuItem cart = menu.findItem(R.id.cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(context).getCartCount(),CartCount);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(cart);
            }
        });
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
    public void networkQueryInterface(String Response, int code) {


        if (code==1){

            // bind the data..



            try {
                JSONObject jsonObject=new JSONObject(Response);
                boolean error =jsonObject.getBoolean("error");


                if(!error){


                    JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                    List<OnTheWay> OnTheWayList=jsonResponseParser.getJsonOrderInfoProductParser();
                    bindToAdapter(OnTheWayList);

                }else {


                    // show user no cart products available..
                  //  OrderEmptyLayout.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

    }

    private void bindToAdapter(List<OnTheWay> onTheWayList) {


       MyPurchasesAdapter myPurchasesAdapter=new MyPurchasesAdapter(context,onTheWayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,25,true,0));
        recyclerView.setAdapter(myPurchasesAdapter);




    }

    @Override
    public void networkQueryError(String error, int code) {

    }

    private Map<String ,String> buildParams(){
        Map<String,String> params=new HashMap<>();

        params.put("UID",new MyPreferences(context).getUID());

        return params;
    }
}
