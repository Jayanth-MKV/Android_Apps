package com.dailyneeds.vugido.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dailyneeds.vugido.R;
import com.trncic.library.DottedProgressBar;

import java.util.Objects;

public class ProgressDialog extends DialogFragment {
    private Context context;
    private Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.progress_bar_dialog,container,false);
        TextView message=view.findViewById(R.id.progress_message);
        assert getArguments() != null;
        message.setText(getArguments().getString("MSG"));
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        DottedProgressBar progressBar = view.findViewById(R.id.progress);
        progressBar.startProgress();



        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Objects.requireNonNull(getDialog()).dismiss();
                 activity.finish();
                }
                return true;
            }
        });
      //  progressBar.stopProgress();
        return view;
    }


}
