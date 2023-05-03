package com.dailyneeds.vugido.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dailyneeds.vugido.R;

import java.util.Objects;

public class ExpressDelivery extends DialogFragment {
    private Button Close;
private TextView D,Dc;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.express_delivery, container, false);
        D=view.findViewById(R.id.D);
        Dc=view.findViewById(R.id.DC);
        TextView msg=view.findViewById(R.id.msg);
        TextView DT=view.findViewById(R.id.d_text);
        TextView DCT=view.findViewById(R.id.dc_text);
        assert getArguments() != null;

        if(getArguments().getInt("A")==0){
            D.setText(String.format("%s Kms", getArguments().getString("D")));
            Dc.setText(String.format("Rs.%s/-", getArguments().getString("DC")));
        }else {

            msg.setText(R.string.e_d_n);
            D.setVisibility(View.GONE);
            Dc.setVisibility(View.GONE);
            DCT.setVisibility(View.GONE);
            DT.setVisibility(View.GONE);
        }

        Close=view.findViewById(R.id.close);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
        return view;

    }
}
