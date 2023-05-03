package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.updated.slots_data.SLOTSItem;

import java.util.List;


public class SlotsListAdapter extends RecyclerView.Adapter<SlotsListAdapter.MyViewHolder> {
private List<SLOTSItem> slotIntervalList;
private Context context;
private SLOT_ID slot_id;

public SlotsListAdapter(Context context, List<SLOTSItem> slotIntervalList, SLOT_ID slot_id){

    this.context=context;
    this.slotIntervalList=slotIntervalList;
    this.slot_id=slot_id;

}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.slot_time_row_design,parent,false);

        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myHolder, final int position) {
   final SLOTSItem slotsItem=slotIntervalList.get(position);

    myHolder.SlotName.setText(slotsItem.getSLOT());
    myHolder.checkBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new MyPreferences(context).setSlotStatus(true);
            new MyPreferences(context).setSlotId(slotsItem.getSLOTID());
            new MyPreferences(context).setSlotName(slotsItem.getSLOT());
          //  Toast.makeText(context,slotsItem.getSLOTID()+" ID", Toast.LENGTH_SHORT).show();
            slot_id.getSlotId(slotsItem.getSLOTID(),slotsItem.getSLOT());
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
        SlotName=itemView.findViewById(R.id.slot_time_text);
        checkBox=itemView.findViewById(R.id.slot_time_check_box);
    }
}

public interface SLOT_ID{
    void getSlotId(int id, String name);

}


}
