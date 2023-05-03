package com.imgideongo.vfp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.imgideongo.vfp.R;

public class OrderSuccessfulDialog extends DialogFragment {
    private Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        assert getArguments() != null;
        int status=getArguments().getInt("STATE");
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.order_successful_dialog,null);
        TextView textView=view.findViewById(R.id.successfully_text);
        if(status==1){
            //de-activated
            textView.setText(R.string.de_activated);
        }else {
            //activated
            textView.setText(R.string.successfully_activated);
        }
        builder.setView(view);
        return builder.create();

    }
}
