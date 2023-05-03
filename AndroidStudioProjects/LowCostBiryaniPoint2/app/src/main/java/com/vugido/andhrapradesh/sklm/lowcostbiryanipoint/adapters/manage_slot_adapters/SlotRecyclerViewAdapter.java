package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.manage_slot_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.foodhub.vugido.R;

public class SlotRecyclerViewAdapter extends RecyclerView.Adapter<SlotRecyclerViewAdapter.MySlotViewHolder> {


    public SlotRecyclerViewAdapter(){


    }

    @NonNull
    @Override
    public MySlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_time_row_design, parent, false);

        return new MySlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySlotViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class  MySlotViewHolder extends ViewHolder{

        TextView time;
        MaterialCheckBox materialCheckBox;

        MySlotViewHolder(@NonNull View itemView) {
            super(itemView);

            time=itemView.findViewById(R.id.slot_time_text);
            materialCheckBox=itemView.findViewById(R.id.slot_time_check_box);

        }
    }
}
