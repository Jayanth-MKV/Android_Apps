package com.dailyneeds.vugido.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.adapters.SlotsListAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.models.SlotInterval;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotBooking extends DialogFragment implements SlotsListAdapter.SLotID, View.OnClickListener {
    private boolean DEFAULT_SLOT=true;
    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    private int SlotId=0;
    private Button SlotOK;
    private Slot slot;
    private int DEFAULT_Slot;
    private TextView SlotsText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        slot= (Slot) getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.slot_booking, container, false);
        recyclerView=view.findViewById(R.id.my_slots);
        SlotOK=view.findViewById(R.id.slot_fix);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        SlotsText=view.findViewById(R.id.as);

        SlotOK.setOnClickListener(this);

        // slot settings....

        switch (ConfigVariables.xyz()){
            case ConfigVariables.NO_SLOTS:
                // recommend user for tomorrow
                DEFAULT_Slot=0;
                Log.e("No","slots");
                passEntireSlot();
                SlotsText.setText(R.string.t_a_s);
                break;
                case ConfigVariables.ALL_SLOTS:
                    // show user all slots..
                    DEFAULT_Slot=0;
                    Log.e("all","slots");
                    passEntireSlot();
                    SlotsText.setText(R.string.tod_a_s);

                    break;

                    case ConfigVariables.CUSTOM_SLOTS:

                Log.e("custom","slots");
                SlotsText.setText(R.string.tod_a_s);
                // custom slots..
                DEFAULT_Slot=ConfigVariables.Userslot();
                if(DEFAULT_Slot>=1 && DEFAULT_Slot <16){

                    // pass the slot
                    passSlot(DEFAULT_Slot);


                }else if(DEFAULT_Slot==16){

                    DEFAULT_Slot=0;
                    Log.e("No","slots");
                    passEntireSlot();
                    SlotsText.setText(R.string.t_a_s);


                }

                break;

        }




        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    if(DEFAULT_SLOT){
                        Log.e("Default",String.valueOf(DEFAULT_Slot));
                        slot.slot(DEFAULT_Slot+1);

                    }
                    else {
                        Log.e("SlotId", String.valueOf(SlotId));
                        slot.slot(SlotId);
                    }
                    Objects.requireNonNull(getDialog()).dismiss();
                }
                return true;
            }
        });


        return view;
    }


    private  void passEntireSlot(){

        List<String> strings=new ArrayList<>();
        strings.add(getResources().getString(R.string._5_00_am_6_00_am));
        strings.add(getResources().getString(R.string._6_00_am_7_00_am));
        strings.add(getResources().getString(R.string._7_00_am_8_00_am));
        strings.add(getResources().getString(R.string._8_00_am_9_00_am));// 4th slot but 3 item
        strings.add(getResources().getString(R.string._9_00_am_10_00_am));// if 5th slot start from 5th item
        strings.add(getResources().getString(R.string._10_00_am_11_00_am));
        strings.add(getResources().getString(R.string._11_00am_12_00_pm));
        strings.add(getResources().getString(R.string._12_00pm_1_00pm));
        strings.add(getResources().getString(R.string._1_00_pm_2_00_pm));
        strings.add(getResources().getString(R.string._2_00_pm_3_00_pm));
        strings.add(getResources().getString(R.string._3_00_pm_4_00_pm));
        strings.add(getResources().getString(R.string._4_00_pm_5_00_pm));
        strings.add(getResources().getString(R.string._5_00_pm_6_00_pm));
        strings.add(getResources().getString(R.string._6_00_pm_7_00_pm));
        strings.add(getResources().getString(R.string._7_00_pm_8_00_pm));
        strings.add(getResources().getString(R.string._8_00_pm_9_00pm));


        List<SlotInterval> slotIntervalList=new ArrayList<>();
        for(int i=0;i<strings.size();i++){

            SlotInterval slotInterval=new SlotInterval();
            slotInterval.setSlotInterval(i+1);
            slotInterval.setSlotName(strings.get(i));
            if(i==0)
                slotInterval.setChecked(true);
            else
                slotInterval.setChecked(false);
            slotIntervalList.add(slotInterval);


        }

        setAdapter(slotIntervalList);


    }

    private void passSlot(int slot) {


        List<String> strings=new ArrayList<>();
        strings.add(getResources().getString(R.string._5_00_am_6_00_am));
        strings.add(getResources().getString(R.string._6_00_am_7_00_am));
        strings.add(getResources().getString(R.string._7_00_am_8_00_am));
        strings.add(getResources().getString(R.string._8_00_am_9_00_am));// 4th slot but 3 item
        strings.add(getResources().getString(R.string._9_00_am_10_00_am));// if 5th slot start from 5th item
        strings.add(getResources().getString(R.string._10_00_am_11_00_am));
        strings.add(getResources().getString(R.string._11_00am_12_00_pm));
        strings.add(getResources().getString(R.string._12_00pm_1_00pm));
        strings.add(getResources().getString(R.string._1_00_pm_2_00_pm));
        strings.add(getResources().getString(R.string._2_00_pm_3_00_pm));
        strings.add(getResources().getString(R.string._3_00_pm_4_00_pm));
        strings.add(getResources().getString(R.string._4_00_pm_5_00_pm));
        strings.add(getResources().getString(R.string._5_00_pm_6_00_pm));
        strings.add(getResources().getString(R.string._6_00_pm_7_00_pm));
        strings.add(getResources().getString(R.string._7_00_pm_8_00_pm));
        strings.add(getResources().getString(R.string._8_00_pm_9_00pm));

        List<String> slotList=new ArrayList<>();
        for(int i=slot;i<strings.size();i++){

            slotList.add(strings.get(i));
        }


        List<SlotInterval> slotIntervalList=new ArrayList<>();
        int s=slot+1;

        for(int i=0;i<slotList.size();i++,s++){

            SlotInterval slotInterval=new SlotInterval();
            slotInterval.setSlotInterval(s);
            slotInterval.setSlotName(slotList.get(i));
            if(i==0)
            slotInterval.setChecked(true);
            else
                slotInterval.setChecked(false);
            slotIntervalList.add(slotInterval);


        }

        setAdapter(slotIntervalList);





    }

    private void setAdapter(List<SlotInterval> slotList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        SlotsListAdapter slotsListAdapter=new SlotsListAdapter(context,slotList,this);
        recyclerView.setAdapter(slotsListAdapter);


    }


    @Override
    public void slodID(int slot) {

        SlotId=slot;
        DEFAULT_SLOT=false;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.slot_fix){

            if(DEFAULT_SLOT) {
                slot.slot(DEFAULT_Slot + 1);
            }
            else {
                slot.slot(SlotId);
            }
            Objects.requireNonNull(getDialog()).dismiss();

        }

    }


    public interface Slot{

        void slot(int slot);
    }
}
