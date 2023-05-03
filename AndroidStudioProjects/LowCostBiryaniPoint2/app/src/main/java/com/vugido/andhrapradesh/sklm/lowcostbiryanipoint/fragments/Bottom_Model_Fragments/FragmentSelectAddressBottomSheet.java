package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Bottom_Model_Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.manage_address.SelectAddressRecyclerViewAdapter;
import com.foodhub.vugido.models.updated.Manage_Address.Fetch_All_Address.DATAItem;

import java.util.List;

public class FragmentSelectAddressBottomSheet extends BottomSheetDialogFragment implements SelectAddressRecyclerViewAdapter.PassPrimaryAID {

    private RecyclerView recyclerView;
    private Context context;
    private GetAddressesBottomSheet getAddressesBottomSheet;
    private ChangePrimaryAID changePrimaryAID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        getAddressesBottomSheet= (GetAddressesBottomSheet) getContext();
        changePrimaryAID= (ChangePrimaryAID) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_address_bottom_sheet_layout, container, false);

        // call all address here..
        recyclerView=view.findViewById(R.id.BottomAddressRecyclerView);


      /*  getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                coordinatorLayout =  d.findViewById(R.id.bottom_coordinator_layout);
                bottomSheetInternal = d.findViewById(R.id.locUXView);
                bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal);
                bottomSheetBehavior.setHidable(false);
                BottomSheetBehavior.from((View)coordinatorLayout.getParent()).setPeekHeight(bottomSheetInternal.getHeight());
                bottomSheetBehavior.setPeekHeight(bottomSheetInternal.getHeight());
                coordinatorLayout.getParent().requestLayout();

            }
        });*/
        //fetch all available user address

        fetchUserAllAddresses();

        return view;
    }

    private void fetchUserAllAddresses() {

        List<DATAItem> AddressList=getAddressesBottomSheet.getAddressesSelectionBottomSheet();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        SelectAddressRecyclerViewAdapter selectAddressRecyclerViewAdapter=new SelectAddressRecyclerViewAdapter(AddressList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(selectAddressRecyclerViewAdapter);



    }

    @Override
    public void passPrimaryAid(int aid) {

        changePrimaryAID.changePrimaryAid(aid);
        dismiss();

    }

    public interface ChangePrimaryAID{
         void changePrimaryAid(int aid);
    }

    public interface GetAddressesBottomSheet{
        List<DATAItem> getAddressesSelectionBottomSheet();
    }


}
