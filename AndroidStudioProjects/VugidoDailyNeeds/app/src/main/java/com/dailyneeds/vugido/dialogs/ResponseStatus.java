package com.dailyneeds.vugido.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dailyneeds.vugido.R;

import java.util.Objects;

public class ResponseStatus extends DialogFragment {
    private ImageView imageView;
    private TextView message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.response_status,container,false);
        imageView=view.findViewById(R.id.status_img);
        message=view.findViewById(R.id.status_msg);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        Bundle bundle=getArguments();
        assert bundle!=null;
        if(bundle.getBoolean("error")){
            imageView.setImageResource(R.drawable.wrong);
        }else {
            imageView.setImageResource(R.drawable.right);
        }

        message.setText(bundle.getString("msg"));
        return  view;
    }
}
