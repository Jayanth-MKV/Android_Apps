package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.Objects;

public class ReVerifyAccount extends DialogFragment {

    private Activity activity;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reverify_account, container, false);
        Button reVerify = view.findViewById(R.id.button5);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);

        new MyPreferences(context).setVerified(false);
        new MyPreferences(context).tokenChanged(true);

        reVerify.setOnClickListener(view1 -> {


            new MyPreferences(context).setVerified(false);
            restartApplication();
        });
        getDialog().setOnKeyListener((dialogInterface, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {

                Objects.requireNonNull(getDialog()).dismiss();
               activity.finishAffinity();
            }
            return true;
        });



        return view;
    }
    private void restartApplication() {

        Intent i = activity.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(activity.getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
