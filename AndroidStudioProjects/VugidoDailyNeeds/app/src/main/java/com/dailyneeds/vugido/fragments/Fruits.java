package com.dailyneeds.vugido.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.adapters.ProductAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.models.DataItem;
import com.dailyneeds.vugido.models.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Fruits extends Fragment implements NetworkQueries.NetworkQueryInterface {

    private Context context;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.vegetables_fragment,container,false);
        recyclerView=view.findViewById(R.id.veg_recyclerView);
       NetworkQueries networkQueries=new NetworkQueries(context, this,ConfigVariables.FRUITS_URL,null);
        networkQueries.sendRequest("Getting All Fruits");


        return view;
    }

    @Override
    public void networkQueryInterface(String Response) {


        try {
            JSONObject jsonObject=new JSONObject(Response);
            boolean error =jsonObject.getBoolean("error");


            if(!error){


                JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                List<Product> productList=jsonResponseParser.getJsonProductsParser();
               // bindToAdapter(productList);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void bindToAdapter(List<DataItem> productList) {

        productAdapter=new ProductAdapter(context,getFragmentManager(),productList);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new Space(2,10,true,0));
        recyclerView.setAdapter(productAdapter);
    }
    @Override
    public void networkQueryError(String error) {




    }
}
