package com.imgideongo.vfp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imgideongo.vfp.R;

public class ErrorDialogBottomSheet extends BottomSheetDialogFragment {

    TextView message;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.error_dialog,container,false);
        message=view.findViewById(R.id.message);
        message.setText(R.string.error);
        return view;

    }
}
