package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Delivery_Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.SlotActivity;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.fragments.Bottom_Model_Fragments.FragmentSelectAddressBottomSheet;
import com.foodhub.vugido.models.Location.SetPrimaryAID;
import com.foodhub.vugido.models.updated.Manage_Address.Fetch_All_Address.DATAItem;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Choose_Existing_Address extends Fragment implements OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Button ChangeAddressButton,Proceed;
    private Context context;
    private FragmentActivity fragmentActivity;
    private FloatingActionButton floatingActionButton;
    private SetFragmentGetAddress setFragmentGetAddress;
    private GET_ADDRESSES getAddresses;
    private List<DATAItem> addressesList;
    private TextView UserName,DoorNo,Area, ActivePhone, ZipCode,CountryState;
    private CheckBox AddressCheckBox;
    private ShowProgress showProgress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        fragmentActivity=getActivity();
        getAddresses= (GET_ADDRESSES) getContext();
        setFragmentGetAddress= (SetFragmentGetAddress) getContext();
        showProgress= (ShowProgress) getContext();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__choose__existing__address, container, false);
        ChangeAddressButton=view.findViewById(R.id.ChangePrimaryAddressButton);
        floatingActionButton=view.findViewById(R.id.add_address_floating_button);
        Proceed=view.findViewById(R.id.AddressContinueButton);

        Proceed.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        ChangeAddressButton.setOnClickListener(this);

        UserName=view.findViewById(R.id.address_user_name);
        DoorNo=view.findViewById(R.id.address_user_door);
        Area=view.findViewById(R.id.address_user_street);
        ActivePhone=view.findViewById(R.id.address_user_phone);
        ZipCode=view.findViewById(R.id.address_user_pincode);
        CountryState=view.findViewById(R.id.address_user_country);
        AddressCheckBox=view.findViewById(R.id.address_user_select_check_box);

        Proceed.setEnabled(false);
        AddressCheckBox.setOnCheckedChangeListener(this);

        addressesList=getAddresses.getFetchedUserAddresses();
        bindAddressesList(addressesList);
        return view;
    }

    private void bindAddressesList(List<DATAItem> addressesList) {

        for (DATAItem dataItem : addressesList){

            if(dataItem.getISPRIMARY()==1){
                // it is a primary address break and bind this...
                setPrimaryAddress(dataItem);
                break;
            }

        }

    }

    private void setPrimaryAddress(DATAItem dataItem) {
        String door,area,active_ph,alternate_ph,zip,county_st;
        door = "<font color=#000000>Door N.o/ Office: </font> <font color=#656565>"+ dataItem.getDOORNO()+"</font>";
        area= "<font color=#000000>Area / Street Name: </font> <font color=#656565>"+ dataItem.getAREANAME()+"</font>";
        active_ph="<font color=#000000>Active Phone: </font> <font color=#656565>"+ dataItem.getACTIVEPHONE()+"</font>";
       // alternate_ph="<font color=#000000>Alternate Phone: </font> <font color=#656565>"+ dataItem.getALTERNATEPHONE()+"</font>";
        zip="<font color=#000000>ZipCode / PinCode: </font> <font color=#656565>"+ dataItem.getZIPCODE()+"</font>";
        county_st="<font color=#000000>Country / State: </font> <font color=#656565>"+ dataItem.getCOUNTRYSTATE()+"</font>";

        UserName.setText(dataItem.getFIRSTNAME());
        DoorNo.setText(Html.fromHtml(door));
        Area.setText(Html.fromHtml(area));
        ActivePhone.setText(Html.fromHtml(active_ph));
       // AlternatePhone.setText(Html.fromHtml(alternate_ph));
        ZipCode.setText(Html.fromHtml(zip));
        CountryState.setText(Html.fromHtml(county_st));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.ChangePrimaryAddressButton:
                FragmentSelectAddressBottomSheet fragmentSelectAddressBottomSheet=new FragmentSelectAddressBottomSheet();
                fragmentSelectAddressBottomSheet.show(fragmentActivity.getSupportFragmentManager(),"BOTTOM_SHEET");
                break;
            case R.id.add_address_floating_button:

                setFragmentGetAddress.setFragmentGetAddress();
                break;

            case R.id.AddressContinueButton:

                // start a slot activity..

                // send server to set the primary address then start activity..
                Proceed.setEnabled(false);
                sendPrimaryAddressId();

                break;

        }

    }

    private void sendPrimaryAddressId() {

        showProgress.showProgress(true);
        Map<String, Object> map=new HashMap<>();
        map.put("UID", new MyPreferences(context).getUID());// user id
        map.put("AID",getAID());

        Call<SetPrimaryAID> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).setPrimaryAddress(map);

        call.enqueue(new Callback<SetPrimaryAID>() {
            @Override
            public void onResponse(@NonNull Call<SetPrimaryAID> call, @NonNull Response<SetPrimaryAID> response) {

                if(response.isSuccessful()){
                    // no problem
                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok successfully set
                        // start slot activity now..
                        Intent intent=new Intent(context,SlotActivity.class);
                        fragmentActivity.startActivityForResult(intent,ORDER_PLACED_CODE);
                    }else {

                        // error in setting aid
                        // else so error dialog
                    }
                    Proceed.setEnabled(true);


                }else {

                    // problem
                    Proceed.setEnabled(true);

                }
                showProgress.showProgress(false);


            }

            @Override
            public void onFailure(@NonNull Call<SetPrimaryAID> call, @NonNull Throwable t) {
                Proceed.setEnabled(true);
                showProgress.showProgress(false);

            }
        });



    }

    private Object getAID() {
        for(DATAItem dataItem:addressesList){
            if(dataItem.getISPRIMARY()==1){

                return String.valueOf(dataItem.getAID());
            }
        }
        return null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId()==R.id.address_user_select_check_box){

            if(buttonView.isChecked()){
                //ok enable button to proceed
                Proceed.setEnabled(true);
            }else {
                Proceed.setEnabled(false);
                // disable button

            }
        }

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/

    public interface SetFragmentGetAddress{

        void setFragmentGetAddress();

    }

    public interface GET_ADDRESSES{

        List<DATAItem>  getFetchedUserAddresses();
    }

    public interface ShowProgress{

        void showProgress(boolean show);
    }
}
