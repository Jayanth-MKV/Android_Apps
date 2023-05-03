package com.imgideongo.vfp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.helper.MyPreferences;
import com.imgideongo.vfp.models.ActiveOrder;
import com.imgideongo.vfp.models.ActiveOrderItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveOrders extends Fragment {
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

        View view=inflater.inflate(R.layout.active_orders,container,false);
        recyclerView=view.findViewById(R.id.activeOrdersRecyclerView);

        //check connection...
        getAllActiveOrders();

        return  view;
    }

    private void getAllActiveOrders() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.GET_ACTIVE_VFP_ORDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray=new JSONArray(response);

                            parseJson(jsonArray);

                            //JSONObject jsonObject=jsonArray.getJSONObject(0);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> param=new HashMap<>();
                param.put("CID",new MyPreferences(context).getUID());
                return param;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    private void parseJson(JSONArray jsonArray) {
        List<ActiveOrder> activeOrderList=new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){

            try {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ActiveOrder activeOrder=new ActiveOrder();
                activeOrder.setOID(jsonObject.getInt("OID"));
                activeOrder.setQUANTITY(jsonObject.getInt("Q"));

                //get Agent info

                JSONArray array=jsonObject.getJSONArray("AGENT");
                JSONObject object=array.getJSONObject(0);

                activeOrder.setAID(object.getInt("AID"));
                activeOrder.setAgentName(object.getString("NAME"));
                activeOrder.setPic(object.getString("PIC"));
                activeOrder.setAbout(object.getString("ABOUT"));
                activeOrder.setContact(object.getString("CONTACT"));

                // now get the Items Info...

                JSONArray array1=jsonObject.getJSONArray("ITEMS");
                List<ActiveOrderItems> activeOrderItemsList=new ArrayList<>();
                for(int j=0;j<array1.length();j++){

                    JSONObject object1=array1.getJSONObject(j);
                    ActiveOrderItems activeOrderItems=new ActiveOrderItems();

                    activeOrderItems.setItemName(object1.getString("ITEM_NAME"));
                    activeOrderItems.setCount(object1.getString("ITEM_COUNT"));
                    activeOrderItemsList.add(activeOrderItems);


                }

                activeOrder.setActiveOrderItemsList(activeOrderItemsList);

                // all to list..
                activeOrderList.add(activeOrder);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        addRecyclerViewData(activeOrderList);



    }

    private void addRecyclerViewData(List<ActiveOrder> activeOrderList) {




    }


}
