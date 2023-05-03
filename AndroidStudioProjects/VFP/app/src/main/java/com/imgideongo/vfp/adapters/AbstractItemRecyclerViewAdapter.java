package com.imgideongo.vfp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.helper.MyPreferences;
import com.imgideongo.vfp.models.ItemAbstractModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractItemRecyclerViewAdapter extends RecyclerView.Adapter<AbstractItemRecyclerViewAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    public List<ItemAbstractModel> itemAbstractModelList;
    public List<ItemAbstractModel> selectedItemlist;
    private Context context;
    private Dialogbox dialogbox;
    public AbstractItemRecyclerViewAdapter(Context context, List<ItemAbstractModel> itemAbstractModelList,
                                           List<ItemAbstractModel> selectedItemlist){
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemAbstractModelList=itemAbstractModelList;
        this.context=context;
        dialogbox= (Dialogbox) context;
        this.selectedItemlist=selectedItemlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.dashboard_row_design,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final ItemAbstractModel itemAbstractModel=itemAbstractModelList.get(i);
        String type=itemAbstractModel.getItemType();
        if(type.toLowerCase().equals("veg")){
            myViewHolder.ItemType.setImageResource(R.drawable.veg);
        }else {
            myViewHolder.ItemType.setImageResource(R.drawable.non_veg);
        }
        final int State=itemAbstractModel.getItemState();
        if(State==1){
            myViewHolder.ItemState.setText(R.string.inactive);
            myViewHolder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.red));
        }else {
            myViewHolder.ItemState.setText(R.string.active);
            myViewHolder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        myViewHolder.ItemName.setText(itemAbstractModel.getItemName());
        if(selectedItemlist.contains(itemAbstractModelList.get(i))){
            myViewHolder.cardView.setBackgroundResource(R.color.selected_item_state);
        }else {
            myViewHolder.cardView.setBackgroundResource(R.color.colorAccent);
        }
        myViewHolder.ItemState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //de-activate the item state
                deActivateItemState(myViewHolder,itemAbstractModel);

            }
        });

    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public int getItemViewType(int position) {
        return  position;
    }

    @Override
    public int getItemCount() {
        return itemAbstractModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ItemType;
        Button ItemState;
        TextView ItemName;
        CardView cardView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemType=itemView.findViewById(R.id.item_type);
            ItemState=itemView.findViewById(R.id.active_state);
            ItemName=itemView.findViewById(R.id.item_name);
            cardView=itemView.findViewById(R.id.item_card_view);
        }
    }


    private void deActivateItemState(final MyViewHolder myViewHolder, final ItemAbstractModel itemAbstractModel){
        //show transparent fragment
        final int NewState;
        if(itemAbstractModel.getItemState()==0){
            NewState=1;
        }else {
            NewState=0;
        }
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.URL_DEACTIVATE_ITEM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean error=jsonObject.getBoolean("error");
                            if(!error){
                                //update success
                                // remove transparent fragment
                                //show dialog box..
                                dialogbox.dialogBoxVisibility(NewState);
                                if(itemAbstractModel.getItemState()==0){
                                    //item de-activated
                                    itemAbstractModel.setItemState(1);
                                    myViewHolder.ItemState.setText(R.string.inactive);
                                    myViewHolder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.red));

                                }else {
                                    //item activated
                                    itemAbstractModel.setItemState(0);
                                    myViewHolder.ItemState.setText(R.string.active);
                                    myViewHolder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.green));
                                }
                                notifyDataSetChanged();

                            }else {
                                //update un success

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //update un success
                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("STATE",String.valueOf(NewState));
                params.put("CID",new MyPreferences(context).getUID());
                params.put("RID",itemAbstractModel.getItemID());
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public interface Dialogbox{

        void dialogBoxVisibility(int State);
    }

}
