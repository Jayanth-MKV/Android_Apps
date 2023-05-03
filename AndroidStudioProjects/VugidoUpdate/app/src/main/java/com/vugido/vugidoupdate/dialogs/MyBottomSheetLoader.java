package com.vugido.vugidoupdate.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.vugido.vugidoupdate.R;

import net.kyouko.lockablebottomsheet.LockableBottomSheetDialogFragment;

import java.util.Objects;

public class MyBottomSheetLoader extends LockableBottomSheetDialogFragment {

    LottieAnimationView lottieAnimationView;
    TextView Msg;
    //CoordinatorLayout coordinatorLayout;
    ConstraintLayout c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet_dialog_loading,container,false);
        Msg=view.findViewById(R.id.textView9);
        lottieAnimationView=view.findViewById(R.id.lottie_progress_center);
       // c=view.findViewById(R.id.bottom_sheet_loader);
        assert getArguments() != null;
        String msg=getArguments().getString("MSG");
        Msg.setText(msg);
        lottieAnimationView.playAnimation();


        return view;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);

    }


/* @SuppressLint("RestrictedApi")
     @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.bottom_sheet_dialog_loading, null);
        dialog.setContentView(view);

         Msg=view.findViewById(R.id.textView9);
         lottieAnimationView=view.findViewById(R.id.lottie_jump_loader);
         // c=view.findViewById(R.id.bottom_sheet_loader);
         assert getArguments() != null;
         String msg=getArguments().getString("MSG");
         Msg.setText(msg);
         lottieAnimationView.playAnimation();

         setCancelable(false);
    }*/
}
