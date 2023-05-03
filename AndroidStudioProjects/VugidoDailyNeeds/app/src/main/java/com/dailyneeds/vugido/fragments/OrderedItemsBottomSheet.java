package com.dailyneeds.vugido.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.adapters.MyOrderedItemAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.models.CartProducts;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderedItemsBottomSheet extends BottomSheetDialogFragment implements NetworkQueries.NetworkQueryInterfaceWithCode {

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
        View view=inflater.inflate(R.layout.order_items_layout,container,false);
        recyclerView=view.findViewById(R.id.items_recycler_view);
        Bundle bundle=getArguments();
        assert bundle != null;
        int OID=bundle.getInt("OID");
        NetworkQueries networkQueries=new NetworkQueries(context,this, ConfigVariables.GET_ORDER_ITEMS,buildParams(OID),1);
        networkQueries.sendRequestCode("Getting Items");





        return view;
    }

    private Map<String, String> buildParams(int OID) {

        Map<String,String> params=new HashMap<>();
        params.put("UID",new MyPreferences(context).getUID());
        params.put("OID", String.valueOf(OID));
        return  params;

    }
    @Override
    public void networkQueryInterface(String Response, int code) {



        if(code==1){


            try {
                JSONObject jsonObject=new JSONObject(Response);
                boolean error =jsonObject.getBoolean("error");


                if(!error){


                    JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                    List<CartProducts> productList=jsonResponseParser.getJsonOrderedProductParser();
                    bindToAdapter(productList);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

    }

    private void bindToAdapter(List<CartProducts> productList) {


        MyOrderedItemAdapter myOrderedItemAdapter=new MyOrderedItemAdapter(context,productList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,10,true,0));
        recyclerView.setAdapter(myOrderedItemAdapter);


    }



    @Override
    public void networkQueryError(String error, int code) {




    }
}
