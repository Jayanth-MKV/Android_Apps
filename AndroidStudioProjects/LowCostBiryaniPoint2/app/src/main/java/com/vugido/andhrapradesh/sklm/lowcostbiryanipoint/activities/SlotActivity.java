package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.ServiceAvailability;
import com.foodhub.vugido.fragments.Bottom_Model_Fragments.SlotSelectionBottomSheet;
import com.foodhub.vugido.models.updated.delivery_methods.DATA;
import com.foodhub.vugido.models.updated.delivery_methods.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;

public class SlotActivity extends AppCompatActivity implements OnClickListener, CompoundButton.OnCheckedChangeListener, SlotSelectionBottomSheet.BottomSlotSheetCanceled ,
        SlotSelectionBottomSheet.ShowDialog {

    private Toolbar toolbar;
    private Button SlotProceedButton, ChangeSlot;
    private RadioButton Slot,Express;
    private LinearLayout SelectedSlotLayout;
    private TextView slotName,SlotCharge,ExpressCharge,ExpDes;
    private View myProgress;
    private DATA data;
    private float DP;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new MyPreferences(this).setSlotStatus(false);
        new MyPreferences(this).setSlotId(0);
        new MyPreferences(this).setSlotName(null);

        setContentView(R.layout.activity_slot_activity);
        toolbar=findViewById(R.id.slot_toolbar);
        SlotProceedButton=findViewById(R.id.SlotProceedButton);
        ChangeSlot=findViewById(R.id.change_slot);
        Slot=findViewById(R.id.slot_radio_box);
        Express=findViewById(R.id.express_radio_box);
        SelectedSlotLayout=findViewById(R.id.selected_slot_layout);
        slotName=findViewById(R.id.slot_name);
        SlotCharge=findViewById(R.id.slot_charge);
        ExpressCharge=findViewById(R.id.ex_charges);
        ExpDes=findViewById(R.id.ex_time_out);
        myProgress=findViewById(R.id.my_progress);

        Slot.setOnCheckedChangeListener(this);
        Express.setOnCheckedChangeListener(this);


        ChangeSlot.setOnClickListener(this);

        SlotProceedButton.setOnClickListener(this);

        SlotProceedButton.setEnabled(false);

        toolbar.setTitle("Delivery Time");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        // hiding the select slot linear layout.
        SelectedSlotLayout.setVisibility(View.GONE);

        fetchDeliverMethods();


    }

    private void fetchDeliverMethods() {

        myProgress.setVisibility(View.VISIBLE);
        Call<Response> call = RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getDeliveryMethodsInfo();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok true
                         data=response.body().getDATA();
                      deliverySetters(data);

                    }else {

                        // no methods..
                    }

                }
                myProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                myProgress.setVisibility(View.GONE);

                            }
        });
    }

    private void deliverySetters(DATA data) {

        if(data.isSLOT() && data.isEXPRESS()){
            // ok
            Slot.setEnabled(true);
            Express.setEnabled(true);
            SlotCharge.setText(data.getSLOTDESCRIPTION());
            ExpDes.setText(data.getEXPRESSDESCRIPTION());
            ExpressCharge.setText(String.format(Locale.getDefault(),"Standard Express Charges: Rs.%d/-", data.getEXPRESSCHARGES()));

        }else if(data.isSLOT() && !data.isEXPRESS()){
            Express.setEnabled(false);
            Slot.setEnabled(true);
            SlotCharge.setText(data.getSLOTDESCRIPTION());
            ExpDes.setText(data.getEXPRESSDESCRIPTION());
            ExpressCharge.setText(String.format(Locale.getDefault(),"Standard Express Charges: Rs.%d/-", data.getEXPRESSCHARGES()));



        }else if(data.isEXPRESS() && !data.isSLOT()){
            // accepting express not slot
            Express.setEnabled(true);
            Slot.setEnabled(false);
            SlotCharge.setText(data.getSLOTDESCRIPTION());
            ExpDes.setText(data.getEXPRESSDESCRIPTION());
            ExpressCharge.setText(String.format(Locale.getDefault(),"Standard Express Charges: Rs.%d/-", data.getEXPRESSCHARGES()));

        }else {

            // no methods...or no service..
            Express.setEnabled(false);
            Slot.setEnabled(false);
            ExpDes.setText(data.getEXPRESSDESCRIPTION());
            SlotCharge.setText(data.getSLOTDESCRIPTION());
            ExpressCharge.setText(String.format(Locale.getDefault(),"Standard Express Charges: Rs.%d/-", data.getEXPRESSCHARGES()));

            showServiceAvailabilityDialog(data.getDES());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.SlotProceedButton:
                // bind all the data to intent and send to next activity..\

                Intent intent=new Intent(this,CheckOutActivity.class);
                intent.putExtra("DC",DP);
                startActivityForResult(intent,ORDER_PLACED_CODE);
                break;
            case R.id.change_slot:
                SlotSelectionBottomSheet slotSelectionBottomSheet=new SlotSelectionBottomSheet();
                slotSelectionBottomSheet.show(getSupportFragmentManager(),"SLOT_SHEET");
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        if(b){
            if(compoundButton.getId()==R.id.express_radio_box){

                Slot.setChecked(false);
                Express.setChecked(true);
              //  DM=1;
                DP=data.getEXPRESSCHARGES();
                SlotProceedButton.setEnabled(true);
                Toast.makeText(this,String.valueOf(DP),Toast.LENGTH_LONG).show();
                new MyPreferences(this).setSlotStatus(false);
                SelectedSlotLayout.setVisibility(View.GONE);


            }else {

                // check for slot availability
                Express.setChecked(false);
                Slot.setChecked(true);
                if(Slot.isChecked()){
                    //
                    SlotSelectionBottomSheet slotSelectionBottomSheet=new SlotSelectionBottomSheet();
                    slotSelectionBottomSheet.show(getSupportFragmentManager(),"SLOT_SHEET");
                   // new MyPreferences(this).setSlotStatus(true);
                    DP=data.getSLOTCHARGES();
                    Toast.makeText(this,String.valueOf(DP),Toast.LENGTH_LONG).show();
                }

            }

        }

    }


    @Override
    public void slotSelected(int id, String name) {


        //Toast.makeText(this, String.valueOf(id + name), Toast.LENGTH_SHORT).show();
        //Log.e("Preferences Info",new MyPreferences(this).getSlotName()+new MyPreferences(this).getSlotId()+new MyPreferences(this).getSlotStatus());
        if(new MyPreferences(this).getSlotStatus()){
            Slot.setChecked(true);
            SlotProceedButton.setEnabled(true);
            SelectedSlotLayout.setVisibility(View.VISIBLE);
            if(new MyPreferences(this).getIsTomorrow())
                slotName.setText("Tomorrow :"+name);
            else
                slotName.setText("Today :"+name);



        }else {
            SlotProceedButton.setEnabled(false);
            Slot.setChecked(false);
            SelectedSlotLayout.setVisibility(View.GONE);
        }



    }

    @Override
    public void bottomSlotSheetCanceled() {


        Toast.makeText(this,"bottom called",Toast.LENGTH_SHORT).show();
        if(new MyPreferences(this).getSlotStatus()){
            Slot.setChecked(true);
            SlotProceedButton.setEnabled(true);
            SelectedSlotLayout.setVisibility(View.VISIBLE);
            slotName.setText(new MyPreferences(this).getSlotName());
            Toast.makeText(this,"bottom called ok ",Toast.LENGTH_SHORT).show();


        }else {
            SlotProceedButton.setEnabled(false);
            Slot.setChecked(false);
            SelectedSlotLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProgress(boolean show) {
        if(show){
            myProgress.setVisibility(View.VISIBLE);
        }else {
            myProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showServiceDialog(String msg) {
        showServiceAvailabilityDialog(msg);
    }

    private void showServiceAvailabilityDialog(String msg) {
        ServiceAvailability serviceAvailability=new ServiceAvailability();
        Bundle bundle=new Bundle();
        bundle.putString("MSG",msg);
        serviceAvailability.setArguments(bundle);
        serviceAvailability.show(getSupportFragmentManager(),"SERVICE");

    }

}
