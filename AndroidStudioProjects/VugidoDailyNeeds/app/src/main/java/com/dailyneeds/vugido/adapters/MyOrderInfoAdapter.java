package com.dailyneeds.vugido.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.models.OnTheWay;
import java.util.ArrayList;
import java.util.List;


public class MyOrderInfoAdapter extends RecyclerView.Adapter <MyOrderInfoAdapter.MyViewHolder>{

    private Context context;
    private List<OnTheWay> onTheWayList;
    private SEE_ITEMS see_items;
    public MyOrderInfoAdapter(Context context, List<OnTheWay> onTheWayList, SEE_ITEMS see_items){

        this.onTheWayList=onTheWayList;
        this.context=context;
        this.see_items= see_items;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.on_the_way,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final OnTheWay onTheWay=onTheWayList.get(position);

        holder.ID.setText(onTheWay.getOID());
        holder.ON.setText(String.format("%s %s", onTheWay.getDATE(), onTheWay.getTIME()));
        holder.COUNT.setText(onTheWay.getCount());
        holder.ITEM_PRICE.setText(String.format("Rs.%s/-", onTheWay.getItemPrice()));
        holder.L.setText(onTheWay.getLocation());

        if(onTheWay.getDMODE()==1){
            // slot mode
            holder.DC.setText(R.string.ten_rupees);
            holder.FP.setText(String.format("Rs.%s/-", String.valueOf(Double.parseDouble(onTheWay.getItemPrice()) + 10.0)));
            holder.DM.setText(String.format("Slot booked at %s", slotText(onTheWay.getSlot())));



        }else {
            //express mode..
            holder.DC.setText(String.format("Rs.%s/-", onTheWay.getDeliveryCharges()));
            holder.FP.setText(String.format("Rs.%s/-", onTheWay.getFinalPay()));
            holder.DM.setText(R.string.e_d_20min);


        }


        holder.SeeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get the items from the server...

                see_items.getItems(Integer.parseInt(onTheWay.getOID()));



            }
        });
        /*holder.stepView.getState().steps(new ArrayList<String>() {{
            add("Packing");
            add("Dispatching");
            add("Processing");
        }}).stepsNumber(3).animationDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime))
                //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();*/


       /* switch (onTheWay.getOrderStatus()){

            // set the status of the order..

            case 1:
                // packing..

                break;
            case 2:
                holder.stepView.getState().steps(new ArrayList<String>() {{
                    add("Packed");
                    add("Dispatching");
                    add("Processing");
                }}).stepsNumber(3).animationDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime))
                        //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                        // other state methods are equal to the corresponding xml attributes
                        .commit();
                holder.stepView.go(1,true);

                // dispatching..

                break;
            case  3:
                holder.stepView.getState().steps(new ArrayList<String>() {{
                    add("Packed");
                    add("Dispatched");
                    add("Processing");
                }}).stepsNumber(3).animationDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime))
                        //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                        // other state methods are equal to the corresponding xml attributes
                        .commit();
                holder.stepView.go(2,true);
                // Processing

                break;
                case 4:
                    holder.stepView.getState().steps(new ArrayList<String>() {{
                        add("Packed");
                        add("Dispatched");
                        add("Processed");
                    }}).stepsNumber(3).animationDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime))
                            //  .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                            // other state methods are equal to the corresponding xml attributes
                            .commit();
                    holder.stepView.go(2,true);
                    holder.stepView.done(true);
                    break;



        }*/



    }

    private String slotText(int slot) {



        List<String> strings=new ArrayList<>();
        String S="";
        strings.add(context.getResources().getString(R.string._5_00_am_6_00_am));
        strings.add(context.getResources().getString(R.string._6_00_am_7_00_am));
        strings.add(context.getResources().getString(R.string._7_00_am_8_00_am));
        strings.add(context.getResources().getString(R.string._8_00_am_9_00_am));
        strings.add(context.getResources().getString(R.string._9_00_am_10_00_am));
        strings.add(context.getResources().getString(R.string._10_00_am_11_00_am));
        strings.add(context.getResources().getString(R.string._11_00am_12_00_pm));
        strings.add(context.getResources().getString(R.string._12_00pm_1_00pm));
        strings.add(context.getResources().getString(R.string._1_00_pm_2_00_pm));
        strings.add(context.getResources().getString(R.string._2_00_pm_3_00_pm));
        strings.add(context.getResources().getString(R.string._3_00_pm_4_00_pm));
        strings.add(context.getResources().getString(R.string._4_00_pm_5_00_pm));
        strings.add(context.getResources().getString(R.string._5_00_pm_6_00_pm));
        strings.add(context.getResources().getString(R.string._6_00_pm_7_00_pm));
        strings.add(context.getResources().getString(R.string._7_00_pm_8_00_pm));
        strings.add(context.getResources().getString(R.string._8_00_pm_9_00pm));

        for(int i=0;i<strings.size();i++){

            if(slot-1==i){
                  S=strings.get(i);
                break;

            }

        }

            return S;
    }
    @Override
    public int getItemCount() {
        return onTheWayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ID, ON, COUNT, ITEM_PRICE,DC, FP, L,DM;
        Button SeeItems;
       // StepView stepView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ID=itemView.findViewById(R.id.oid);
            ON=itemView.findViewById(R.id.on);
            COUNT=itemView.findViewById(R.id.count_);
            ITEM_PRICE=itemView.findViewById(R.id.IP);
            DC=itemView.findViewById(R.id.DC);
            FP=itemView.findViewById(R.id.FP);
            L=itemView.findViewById(R.id.OrderLocation);
            SeeItems=itemView.findViewById(R.id.SI);
           // stepView=itemView.findViewById(R.id.step_view);
            DM=itemView.findViewById(R.id.DM);


        }
    }



    public  interface SEE_ITEMS{

        void getItems(int OID);

    }

}
