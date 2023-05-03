package com.imgideongo.vfp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.activity.AddItemActivity;
import com.imgideongo.vfp.adapters.AbstractItemRecyclerViewAdapter;
import com.imgideongo.vfp.adapters.GridLayoutItemDecoration;
import com.imgideongo.vfp.adapters.RecyclerviewItemClickListner;
import com.imgideongo.vfp.models.ItemAbstractModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.imgideongo.vfp.activity.AddItemActivity.REFRESH;


public class DashBoard  extends Fragment implements View.OnClickListener{
FloatingActionButton AddButton;
Context context;
Activity activity;
private static  final int ITEM_INFO_CODE=1;
RefreshDashBoard refreshDashBoard;
private RecyclerView recyclerView;
TransparentFragment transparentFragment;
private FrameLayout frameLayout;
private ActionMdeInterface actionMdeInterface;
//private boolean isMultiSelect=false;
private List<ItemAbstractModel> selectedItemList=new ArrayList<>();
private List<ItemAbstractModel> itemAbstractModelList;
private   AbstractItemRecyclerViewAdapter abstractItemRecyclerViewAdapter;
boolean buttonClicked;
int CID;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        refreshDashBoard= (RefreshDashBoard) getContext();
        actionMdeInterface= (ActionMdeInterface) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dashboard,container,false);
       // assert getArguments() != null;
        //CID=getArguments().getInt("CID",0);
        AddButton=view.findViewById(R.id.add_button);
        AddButton.setOnClickListener(this);
        frameLayout=view.findViewById(R.id.transFragment);
      //  getDashBoardItems();
        recyclerView=view.findViewById(R.id.dashBoardRecyclerView);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            startAddItemActivity();
        }

    }

    private void startAddItemActivity() {

        Intent intent=new Intent(context, AddItemActivity.class);
        intent.putExtra("CID",CID);
        startActivityForResult(intent,ITEM_INFO_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==ITEM_INFO_CODE  && resultCode==REFRESH){
           //refresh the dashboard...
               Log.e("Refreshing ","started");
               refreshDashBoard.refreshDashBoard();

       }
    }



    public interface RefreshDashBoard{
        void refreshDashBoard();
    }

    private void getDashBoardItems(){
        addTransparentFragment();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Config.URL_GET_DASHBOARD_ITEMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean error=jsonObject.getBoolean("error");
                            if(!error){
                                parseJsonData(jsonObject);
                            }else{
                                //no data

                            }
                            removeFragment();
                            frameLayout.setVisibility(View.GONE);
                            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            //remove progress bar..
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
                Map<String,String> params=new HashMap<>();
                params.put("UID", String.valueOf(CID));
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }

    private void removeFragment() {
        FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
        fragmentTransaction.remove(transparentFragment);
        fragmentTransaction.commit();

    }

    private void parseJsonData(JSONObject jsonObject) {
        itemAbstractModelList=new ArrayList<>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for (int j=jsonArray.length()-1;j>=0;j--){
                JSONObject object=jsonArray.getJSONObject(j);
                ItemAbstractModel itemAbstractModel=new ItemAbstractModel();
                itemAbstractModel.setItemID(object.getString("ID"));
                itemAbstractModel.setItemName(object.getString("NAME"));
                itemAbstractModel.setItemState(object.getInt("STATE"));
                itemAbstractModel.setItemType(object.getString("TYPE"));
                itemAbstractModel.setRegular(object.getInt("REGULAR"));
                itemAbstractModelList.add(itemAbstractModel);
            }

            setRecyclerViewAdapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setRecyclerViewAdapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutItemDecoration gridLayoutItemDecoration=new GridLayoutItemDecoration(1,dpToPx(1),true,context);
        abstractItemRecyclerViewAdapter=new AbstractItemRecyclerViewAdapter(context,itemAbstractModelList,selectedItemList);
        recyclerView.setAdapter(abstractItemRecyclerViewAdapter);
        recyclerView.addItemDecoration(gridLayoutItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerviewItemClickListner recyclerviewItemClickListner=
                new RecyclerviewItemClickListner(context, recyclerView, new RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        buttonClicked=false;
                        if(actionMdeInterface.getActionModeState()) {
                            actionMdeInterface.actionMode(false, true);
                            selectedItem(position);
                           // return;
                        }
                    }


                    @Override
                    public void onLongClick(View view, int position) {
                        Log.e("OnLongClick","clicked");

                      /*  if(!actionMdeInterface.getActionModeState()) {
                            isMultiSelect=true;
                        }*/
                        actionMdeInterface.actionMode(true, false);
                        selectedItem(position);

                    }
                });
        recyclerView.addOnItemTouchListener(recyclerviewItemClickListner);
    }

    private void itemClickManagement(boolean button){

        Log.e("value",String.valueOf(button));
        if(button){

            Log.e("Button ","Clicked");
            // buttonClicked=false;
        }else {
            Log.e("Something","else");
        }
    }

    private   int dpToPx(int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

  private void addTransparentFragment(){
      activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
              WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
      transparentFragment=new TransparentFragment();
        FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.transFragment,transparentFragment,"TransparentFragment");
      fragmentTransaction.commit();
      frameLayout.setVisibility(View.VISIBLE);

  }


  private void selectedItem(int position){

        if(selectedItemList.contains(itemAbstractModelList.get(position))){
            selectedItemList.remove(itemAbstractModelList.get(position));
            Log.e("item","removed");
        }else {
            selectedItemList.add(itemAbstractModelList.get(position));
            Log.e("item","added");
        }

       // manageActionModeCount();
      refreshAdapter();
      actionMdeInterface.setActionModeTitleInfo(selectedItemList.size());


  }

    public void refreshAdapter() {

        abstractItemRecyclerViewAdapter.selectedItemlist=selectedItemList;
        abstractItemRecyclerViewAdapter.itemAbstractModelList=itemAbstractModelList;
        abstractItemRecyclerViewAdapter.notifyDataSetChanged();

    }

    public  interface RecyclerViewClickListener{
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }

    public interface  ActionMdeInterface{
        void actionMode(boolean longClick,boolean justClick);
        boolean getActionModeState();
        void setActionModeTitleInfo(int count);
    }





}
