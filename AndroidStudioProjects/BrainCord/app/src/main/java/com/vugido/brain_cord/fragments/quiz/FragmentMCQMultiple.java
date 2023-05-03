package com.vugido.brain_cord.fragments.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;

public class FragmentMCQMultiple  extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private Context context;
    TextView Q;
    CheckBox c1,c2,c3,c4;
    boolean o1=false;
    boolean o2=false;
    boolean o3=false;
    boolean o4=false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mcm,container,false);

        Q=view.findViewById(R.id.textView2);

        c1=view.findViewById(R.id.checkBox2);
        c2=view.findViewById(R.id.checkBox3);
        c3=view.findViewById(R.id.checkBox4);
        c4=view.findViewById(R.id.checkBox5);

        c1.setOnCheckedChangeListener(this);
        c2.setOnCheckedChangeListener(this);
        c3.setOnCheckedChangeListener(this);
        c4.setOnCheckedChangeListener(this);


        Bundle bundle=getArguments();
        Q.setText(bundle.getString("Q"));

        c1.setText(bundle.getString("O1"));
        c2.setText(bundle.getString("O2"));
        c3.setText(bundle.getString("O3"));
        c4.setText(bundle.getString("O4"));

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (buttonView.getId()==R.id.checkBox2 )
                o1=isChecked;
            else if (buttonView.getId()==R.id.checkBox3)
                o2=isChecked;
            else if (buttonView.getId()==R.id.button4)
                o3=isChecked;
            else
                o4=isChecked;

            new MyPreferences(context).setAnswer(o1+","+o2+","+o3+","+o4);


    }
}
