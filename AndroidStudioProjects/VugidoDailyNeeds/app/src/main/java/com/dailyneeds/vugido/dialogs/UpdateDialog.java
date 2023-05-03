package com.dailyneeds.vugido.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.dailyneeds.vugido.R;
import java.util.Objects;


public class UpdateDialog extends DialogFragment implements View.OnClickListener {
    private Context context;
   private Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=getContext();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.update_dialog,container,false);
        Button updateApp = view.findViewById(R.id.updateAppButton);
        updateApp.setOnClickListener(this);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    Objects.requireNonNull(getDialog()).dismiss();
                    activity.finishAffinity();
                }
                return true;
            }
        });


        return  view;

    }


    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.updateAppButton){
            // take user to the google play store to update..
            Intent intent;
            final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
            intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(intent);


        }

    }
}
