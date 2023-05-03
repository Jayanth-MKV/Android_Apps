package com.dailyneeds.vugido.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dailyneeds.vugido.R;

import java.util.Objects;

public class PlaceOrder extends DialogFragment implements View.OnClickListener {
    private Button Cancel,PlaceOrder;
    InitiatePlaceOrder initiatePlaceOrder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiatePlaceOrder= (InitiatePlaceOrder) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.place_order_dialog,container,false);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        Cancel=view.findViewById(R.id.cancelButton);
        PlaceOrder=view.findViewById(R.id.placeOrder);
        Cancel.setOnClickListener(this);
        PlaceOrder.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.cancelButton:
                // cancel
                dismiss();
                initiatePlaceOrder.initiatePlaceOrder(false);
                break;
            case R.id.placeOrder:

                //initiate the place order network operation...
                dismiss();
                initiatePlaceOrder.initiatePlaceOrder(true);
                break;

        }

    }

    public interface InitiatePlaceOrder{


        void initiatePlaceOrder(boolean status);


    }
}
