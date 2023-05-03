package com.vugido.vugidoupdate.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MotionEventCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vugido.vugidoupdate.R;

import java.util.Objects;

public class BottomSheetDialog extends BottomSheetDialogFragment {

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
        c=view.findViewById(R.id.bottom_sheet_loader);
        assert getArguments() != null;
        String msg=getArguments().getString("MSG");
        Msg.setText(msg);
        lottieAnimationView.playAnimation();

        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);



        BottomSheetBehavior.from(c).setDraggable(false);
        BottomSheetBehavior.from(c).setHideable(false);


      BottomSheetBehavior.from(c).addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
           @Override
           public void onStateChanged(@NonNull View bottomSheet, int newState) {

           }

           @Override
           public void onSlide(@NonNull View bottomSheet, float slideOffset) {

               Log.e("Slide","ok");

               bottomSheet.setOnTouchListener(new View.OnTouchListener() {
                   @Override
                   public boolean onTouch(View v, MotionEvent event) {

                       Log.e("Event Is", String.valueOf(event.getActionMasked()));

                      /* switch (event.getActionMasked()){

                           case MotionEvent.ACTION_DOWN:

                               Log.i("OnTouchListener", "DOWN :"+MotionEvent.actionToString(event.getActionMasked()));
                               return true;
                           case 1:
                               Log.i("OnTouchListener", "1 :"+MotionEvent.actionToString(event.getActionMasked()));
                               return true;
                           case 2:
                              // BottomSheetBehavior.from(c).setDraggable(false);
                               Log.i("OnTouchListener", "2 :"+MotionEvent.actionToString(event.getActionMasked()));
                               return true;
                           case 3:

                               //BottomSheetBehavior.from(c).setDraggable(false);
                               Log.i("OnTouchListener", "3 :"+MotionEvent.actionToString(event.getActionMasked()));
                               return true;



                       }*/


                       return false;

                   }
               });

           }
       });

        return view;
    }





}
