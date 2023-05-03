package com.vugido.online_groceries.fragments.bottom_sheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vugido.online_groceries.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class PayMethodsBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {


    private Context context;
    private Button COD,GPay,PPay;
    private PAY_METHOD_INTERFACE pay_method_interface;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        pay_method_interface= (PAY_METHOD_INTERFACE) context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pay_methods_bottom_sheet,container,false);
        COD=view.findViewById(R.id.cash_on_delivery);
        GPay=view.findViewById(R.id.gpay);


        COD.setOnClickListener(this);
        GPay.setOnClickListener(this);

//        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
//        getDialog().setCancelable(false);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cash_on_delivery:
                // action..
                pay_method_interface.payMethodInterface();
                Objects.requireNonNull(getDialog()).dismiss();
                break;

            case R.id.gpay:
                Toast.makeText(context,"Google Pay is Currently not accepted", Toast.LENGTH_LONG).show();
                break;

        }
    }


    public interface  PAY_METHOD_INTERFACE{

        void payMethodInterface();
    }
}
