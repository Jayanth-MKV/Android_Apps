package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Bottom_Model_Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.foodhub.vugido.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class CartDiscountBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {


    private Context context;
    private Button Apply,Cancel;
    private LottieAnimationView lottieAnimationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cart_discount_dialog,container,false);
        Apply=view.findViewById(R.id.button6);
        Cancel=view.findViewById(R.id.button7);
        assert getArguments() != null;
        int d=getArguments().getInt("D");
        TextView dis=view.findViewById(R.id.discount);
        dis.setText("Rs."+d+"/-");
        lottieAnimationView=view.findViewById(R.id.lottie_no_network);

        Apply.setOnClickListener(this);
        Cancel.setOnClickListener(this);

       Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);

       playMusic();
       lottieAnimationView.setAnimation(R.raw.discount);
       lottieAnimationView.playAnimation();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button7:
                Objects.requireNonNull(getDialog()).dismiss();
                break;
        }
    }


    private void playMusic() {


        MediaPlayer ring= MediaPlayer.create(context,R.raw.reveal);
        ring.start();

    }

}
