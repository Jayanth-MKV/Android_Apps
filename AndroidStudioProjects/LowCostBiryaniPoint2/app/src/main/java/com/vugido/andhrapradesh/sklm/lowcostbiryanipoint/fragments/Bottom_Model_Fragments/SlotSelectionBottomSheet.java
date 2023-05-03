package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Bottom_Model_Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.SlotsListAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.updated.slots_data.Response;
import com.foodhub.vugido.models.updated.slots_data.SLOTSItem;
import com.foodhub.vugido.models.updated.slots_data.TODAYSLOTS;
import com.foodhub.vugido.models.updated.slots_data.TOMMOROWSLOTS;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class SlotSelectionBottomSheet extends BottomSheetDialogFragment implements RadioGroup.OnCheckedChangeListener, SlotsListAdapter.SLOT_ID, View.OnClickListener {

    private Context context;
    private RecyclerView recyclerView;
    private boolean DEFAULT_SLOT=true;

    private int SlotId=0;
   // private Button SlotOK;
    private int DEFAULT_Slot;
  //  private GetSlotInfo getSlotInfo;
    private RadioButton Today,Tomorrow;
    private RadioGroup radioGroup;
    private TODAYSLOTS todayslots;
    private TOMMOROWSLOTS tommorowslots;
    private TextView SlotDes;
    private BottomSlotSheetCanceled bottomSlotSheetCanceled;
    private Button CancelOkSlotSheet;
    private ShowDialog showDialog;
    private FragmentActivity fragmentActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        fragmentActivity=getActivity();
        bottomSlotSheetCanceled= (BottomSlotSheetCanceled) getContext();
        showDialog= (ShowDialog) getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.slot_selection_bottom_sheet,container,false);
        recyclerView=view.findViewById(R.id.BottomSlotRecyclerView);
        Today=view.findViewById(R.id.today_button);
        Tomorrow=view.findViewById(R.id.tommorow_button);
        radioGroup=view.findViewById(R.id.SlotRadioGroup);
        SlotDes=view.findViewById(R.id.slot_description);
        CancelOkSlotSheet=view.findViewById(R.id.SlotBottomButton);
        CancelOkSlotSheet.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);

        fetchSlotsData();

        return view;
    }

    private void fetchSlotsData() {

        showDialog.showProgress(true);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).getAllSlots();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        Log.e("status","true");
                        // ok data got
                        // check for today
                        if(response.body().isTODAY() && response.body().isTOMMOROW()){
                            // ok today slots exist also tomorrow
                            Today.setVisibility(View.VISIBLE);
                            Tomorrow.setVisibility(View.VISIBLE);
                            todayslots= response.body().getTODAYSLOTS();
                            tommorowslots=response.body().getTOMMOROWSLOTS();
                            setSlotData(true);


                        }else if(response.body().isTOMMOROW() && !response.body().isTODAY()){
                            // set only tomorrow
                            Tomorrow.setVisibility(View.VISIBLE);
                            Today.setVisibility(View.GONE);
                            tommorowslots=response.body().getTOMMOROWSLOTS();
                            setSlotData(false);


                        }else if(response.body().isTODAY() && !response.body().isTOMMOROW()){
                            Today.setVisibility(View.VISIBLE);
                            Tomorrow.setVisibility(View.GONE);
                            todayslots=response.body().getTODAYSLOTS();
                            setSlotData(true);


                        }else {
                            //  both not allowed..
                           Today.setVisibility(View.GONE);
                           Tomorrow.setVisibility(View.GONE);
                           SlotDes.setText("Currently No Slots");
                           // setSlotData(false);
                            Toast.makeText(context,"Currently No Slots / Service",Toast.LENGTH_LONG).show();
                            Objects.requireNonNull(getDialog()).dismiss();
                            bottomSlotSheetCanceled.bottomSlotSheetCanceled();
                            showDialog.showServiceDialog(response.body().getDES());

                            // setSlotData(false,false);

                        }

                    }else {

                        Objects.requireNonNull(getDialog()).dismiss();
                        bottomSlotSheetCanceled.bottomSlotSheetCanceled();
                        showDialog.showServiceDialog(response.body().getDES());
                        Today.setVisibility(View.GONE);
                        Tomorrow.setVisibility(View.GONE);
                        Toast.makeText(context,"Currently No Slots / Service",Toast.LENGTH_LONG).show();
                        // no service...

                    }

                }else {
                    Today.setVisibility(View.GONE);
                    Tomorrow.setVisibility(View.GONE);
                    // not ok error
                }

                showDialog.showProgress(false);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Log.e("status","false");
                showDialog.showProgress(false);

            }
        });
    }


    private void setSlotData(boolean today){

        if(today){
            new MyPreferences(context).setIsTomorrow(false);
            Today.setChecked(true);
            SlotDes.setText(todayslots.getDESCRIPTION());
            setAdapter(todayslots.getSLOTS());
        }else {
            new MyPreferences(context).setIsTomorrow(true);
            Tomorrow.setChecked(true);
            SlotDes.setText(tommorowslots.getDESCRIPTION());
            setAdapter(tommorowslots.getSLOTS());
        }


    }



    private void setAdapter(List<SLOTSItem> slotList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        SlotsListAdapter slotsListAdapter=new SlotsListAdapter(context,slotList,this);
        recyclerView.setAdapter(slotsListAdapter);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

       if(group.getId()==R.id.SlotRadioGroup){

            switch (checkedId){
                case R.id.today_button:
                   // new MyPreferences(context).setIsTomorrow(false);
                    refreshAdapter(true);
                    break;
                case R.id.tommorow_button:
                  //  new MyPreferences(context).setIsTomorrow(true);
                    refreshAdapter(false);
                    break;
            }
        }

    }

    private void refreshAdapter(boolean today) {

        recyclerView.removeAllViews();
        setSlotData(today);


    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        bottomSlotSheetCanceled.bottomSlotSheetCanceled();
    }

    @Override
    public void getSlotId(int id,String name) {


        Objects.requireNonNull(getDialog()).dismiss();
        bottomSlotSheetCanceled.slotSelected(id,name);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SlotBottomButton:
                Objects.requireNonNull(getDialog()).dismiss();
                bottomSlotSheetCanceled.bottomSlotSheetCanceled();
                break;
        }
    }

    public interface BottomSlotSheetCanceled{

        void bottomSlotSheetCanceled();
        void slotSelected(int id, String name);

    }

    public interface ShowDialog{
        void showProgress(boolean show);
        void showServiceDialog(String msg);

    }
}
