package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.manage_address;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.foodhub.vugido.R;
import com.foodhub.vugido.models.updated.Manage_Address.Fetch_All_Address.DATAItem;

import java.util.List;

public class SelectAddressRecyclerViewAdapter extends RecyclerView.Adapter<SelectAddressRecyclerViewAdapter.MyAddressViewHolder> {

    private List<DATAItem> AddressList;
    private PassPrimaryAID passPrimaryAID;
    public SelectAddressRecyclerViewAdapter(List<DATAItem> addressList,PassPrimaryAID passPrimaryAID) {
        AddressList = addressList;
        this.passPrimaryAID=passPrimaryAID;
    }

    @NonNull
    @Override
    public MyAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.other_address_design_layout, parent, false);


        return new MyAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressViewHolder holder, int position) {

        final DATAItem dataItem=AddressList.get(position);
        String door,area,active_ph,alternate_ph,zip,county_st;
        door = "<font color=#000000>Door N.o/ Office: </font> <font color=#656565>"+ dataItem.getDOORNO()+"</font>";
        area= "<font color=#000000>Area / Street Name: </font> <font color=#656565>"+ dataItem.getAREANAME()+"</font>";
        active_ph="<font color=#000000>Active Phone: </font> <font color=#656565>"+ dataItem.getACTIVEPHONE()+"</font>";
     //   alternate_ph="<font color=#000000>Alternate Phone: </font> <font color=#656565>"+ dataItem.getALTERNATEPHONE()+"</font>";
        zip="<font color=#000000>ZipCode / PinCode: </font> <font color=#656565>"+ dataItem.getZIPCODE()+"</font>";
        county_st="<font color=#000000>Country / State: </font> <font color=#656565>"+ dataItem.getCOUNTRYSTATE()+"</font>";

        holder.UserName.setText(dataItem.getFIRSTNAME());
        holder.DoorNo.setText(Html.fromHtml(door));
        holder.Area.setText(Html.fromHtml(area));
        holder.ActivePhone.setText(Html.fromHtml(active_ph));
       // holder.AlternatePhone.setText(Html.fromHtml(alternate_ph));
        holder.ZipCode.setText(Html.fromHtml(zip));
        holder.CountryState.setText(Html.fromHtml(county_st));

        holder.AddressCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the aid of the address and close the bottom sheet
                // then set it as primary in the list also set in layout..
                passPrimaryAID.passPrimaryAid(dataItem.getAID());

            }
        });


    }

    @Override
    public int getItemCount() {
        return AddressList.size();
    }

    static class MyAddressViewHolder extends ViewHolder{



        TextView UserName,DoorNo,Area, ActivePhone, ZipCode,CountryState;
        Button AddressRemoveButton;
        CheckBox AddressCheckBox;


        MyAddressViewHolder(@NonNull View view) {
            super(view);
            AddressRemoveButton=itemView.findViewById(R.id.address_user_remove_button);
            UserName=view.findViewById(R.id.address_user_name);
            DoorNo=view.findViewById(R.id.address_user_door);
            Area=view.findViewById(R.id.address_user_street);
            ActivePhone=view.findViewById(R.id.address_user_phone);
//            AlternatePhone=view.findViewById(R.id.address_user_alternate_phone);
            ZipCode=view.findViewById(R.id.address_user_pincode);
            CountryState=view.findViewById(R.id.address_user_country);
            AddressCheckBox=view.findViewById(R.id.address_user_select_check_box);

        }
    }

    public interface PassPrimaryAID{
        void passPrimaryAid(int aid);
    }
}
