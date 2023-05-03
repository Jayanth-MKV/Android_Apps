package com.dailyneeds.vugido.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.models.SlotInterval;
import java.util.List;


public class SlotsListAdapter extends RecyclerView.Adapter<SlotsListAdapter.MyViewHolder> {
private List<SlotInterval> slotIntervalList;
private Context context;
private SLotID sLotID;

public SlotsListAdapter(Context context,List<SlotInterval> slotIntervalList,SLotID sLotID){

    this.context=context;
    this.slotIntervalList=slotIntervalList;
    this.sLotID=sLotID;

}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.slot_list_row,parent,false);

        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myHolder, final int position) {

        final SlotInterval slotInterval=slotIntervalList.get(position);
        myHolder.SlotName.setText(slotInterval.getSlotName());

        if(slotInterval.isChecked()){

            myHolder.checkBox.setChecked(true);

        }else {
            myHolder.checkBox.setChecked(false);
        }

        myHolder.SlotName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,String.valueOf(slotInterval.getSlotInterval()),Toast.LENGTH_LONG).show();
            }
        });

        myHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<slotIntervalList.size();i++){
                    slotIntervalList.get(i).setChecked(false);

                }
                slotIntervalList.get(position).setChecked(true);

                notifyDataSetChanged();
                sLotID.slodID(slotInterval.getSlotInterval());

            }
        });


    }

    @Override
    public int getItemCount() {
        return slotIntervalList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView SlotName;
    CheckBox checkBox;
    MyViewHolder(@NonNull View itemView) {
        super(itemView);

        SlotName=itemView.findViewById(R.id.SlotName);
        checkBox=itemView.findViewById(R.id.SlotCheckBox);
    }
}


public  interface  SLotID{

    void slodID(int slot);
}



}
