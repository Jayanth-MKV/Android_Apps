package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.dialogs;

import android.app.Activity;
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

import com.foodhub.vugido.R;

import java.util.Objects;

public class ServiceAvailability extends DialogFragment {

    private Activity activity;
    private Context context;
    private TextView msg;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.service_availability, container, false);
        Button reVerify = view.findViewById(R.id.button5);
        msg=view.findViewById(R.id.textView21);
        String msg1= Objects.requireNonNull(getArguments()).getString("MSG");
        msg.setText(msg1);
        reVerify.setOnClickListener(v -> Objects.requireNonNull(getDialog()).dismiss());


        return view;
    }

}
