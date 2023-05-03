package vugido.foodhub.ap.sklm.hungrybirds.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

import vugido.foodhub.ap.sklm.hungrybirds.R;

public class MyStatusDialog extends DialogFragment {
    LottieAnimationView lottieAnimationView;
    TextView Msg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.status_dialog,container,false);
        Msg=view.findViewById(R.id.textView9);
        lottieAnimationView=view.findViewById(R.id.lottie_progress_center);
        assert getArguments() != null;
        String msg=getArguments().getString("MSG");
        boolean status=getArguments().getBoolean("STATUS");

        Msg.setText(msg);
        if(status)
            lottieAnimationView.setAnimation(R.raw.check);
        else
            lottieAnimationView.setAnimation(R.raw.cross);

        lottieAnimationView.playAnimation();
       // Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);

       // setCancelable(false);
        return view;
    }


}
