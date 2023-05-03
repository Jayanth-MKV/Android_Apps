package com.vugido.brain_cord.fragments.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;

public class FragmentImageBased extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private Context context;
    ImageView imageView;
    TextView Q;
    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_based,container,false);

        imageView=view.findViewById(R.id.imageView);
        Q=view.findViewById(R.id.textView2);

        radioGroup=view.findViewById(R.id.radiogroup);
        r1=view.findViewById(R.id.radioButton);
        r2=view.findViewById(R.id.radioButton2);
        r3=view.findViewById(R.id.radioButton3);
        r4=view.findViewById(R.id.radioButton4);

        radioGroup.setOnCheckedChangeListener(this);

        Bundle bundle=getArguments();
        Glide.with(context).load("http://braincord.vugido.com/media/"+bundle.getString("IMG")).into(imageView);
        Q.setText(bundle.getString("Q"));

        r1.setText(bundle.getString("O1"));
        r2.setText(bundle.getString("O2"));
        r3.setText(bundle.getString("O3"));
        r4.setText(bundle.getString("O4"));

//        b.putString("IMG",qimg.getQIMG());
//        b.putString("Q",qimg.getQ());
//        b.putString("O1",qimg.getO1());
//        b.putString("O2",qimg.getO2());
//        b.putString("O3",qimg.getO3());
//        b.putString("O4",qimg.getO4());
//        b.putInt("ID",qimg.getID());
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        if (checkedId==R.id.radioButton) {
            //r1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            new MyPreferences(context).setAnswer("a");
        }
        else if (checkedId==R.id.radioButton2) {
            new MyPreferences(context).setAnswer("b");
        }
        else if (checkedId==R.id.radioButton3) {
            new MyPreferences(context).setAnswer("c");
        }
        else {
            new MyPreferences(context).setAnswer("d");
        }





    }
}
