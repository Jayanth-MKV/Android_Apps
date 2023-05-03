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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.activities.CartActivity;
import com.dailyneeds.vugido.activities.MainActivity;
import com.dailyneeds.vugido.adapters.MyOrderInfoAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.models.OnTheWay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrders extends Fragment implements NetworkQueries.NetworkQueryInterface, MyOrderInfoAdapter.SEE_ITEMS{


    private Context context;
    private RecyclerView  recyclerView;
    private RelativeLayout OrderEmptyLayout;
    private FragmentActivity fragmentActivity;
    private TextView CartCount;
    private Activity activity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context=getContext();
        activity=getActivity();
        fragmentActivity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_orders,container,false);
        OrderEmptyLayout=view.findViewById(R.id.order_empty_layout);
        recyclerView=view.findViewById(R.id.orderInfo_recyclerView);
        Button cartButton = view.findViewById(R.id.to_cart);
        NetworkQueries networkQueries=new NetworkQueries(context,this, ConfigVariables.GET_ORDER_INFO,buildParams());
        networkQueries.sendRequest("Getting Order Info");
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, CartActivity.class));
            }
        });




        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

       /* MenuItem menuItem = menu.findItem(R.id.search);
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



    private Map<String,String> buildParams(){

        Map<String,String> params=new HashMap<>();
        params.put("UID",new MyPreferences(context).getUID());
        return  params;

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
    public void networkQueryInterface(String Response) {

        // here parse the data from the server...


        try {
            JSONObject jsonObject=new JSONObject(Response);
            boolean error =jsonObject.getBoolean("error");


            if(!error){


                JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                List<OnTheWay> OnTheWayList=jsonResponseParser.getJsonOrderInfoProductParser();
                bindToAdapter(OnTheWayList);

            }else {

                // show user no cart products available..
                OrderEmptyLayout.setVisibility(View.VISIBLE);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void bindToAdapter(List<OnTheWay> onTheWayList) {


        List<OnTheWay> list=sortList(onTheWayList);


        MyOrderInfoAdapter myOrderInfoAdapter=new MyOrderInfoAdapter(context,list,this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,25,true,0));
        recyclerView.setAdapter(myOrderInfoAdapter);



    }

    private List<OnTheWay> sortList(List<OnTheWay> onTheWayList) {

        Collections.sort(onTheWayList,new MyList());


        return onTheWayList;
    }

    class MyList implements Comparator<OnTheWay> {


        @Override
        public int compare(OnTheWay onTheWay, OnTheWay t1) {
            return Integer.parseInt(t1.getOID())-Integer.parseInt(onTheWay.getOID()) ;
        }
    }

    @Override
    public void networkQueryError(String error) {

        // show the error...





    }

    @Override
    public void getItems(int OID) {


        OrderedItemsBottomSheet orderedItemsBottomSheet=new OrderedItemsBottomSheet();
        Bundle bundle=new Bundle();
        bundle.putInt("OID",OID);
        orderedItemsBottomSheet.setArguments(bundle);
        orderedItemsBottomSheet.show(fragmentActivity.getSupportFragmentManager(),"BOTTOM_SHEET");




    }




   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


    }*/

}
