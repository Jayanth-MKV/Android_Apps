package com.vugido.brain_cord.fragments.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;

public class FragmentParagraph extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private Context context;
    RadioGroup rg1,rg2,rg3;
    RadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12;
    TextView paragraph,q1,q2,q3;
    String a1="",a2="",a3="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_paragraph,container,false);


        paragraph=view.findViewById(R.id.textView2);

        q1=view.findViewById(R.id.textView5);
        q2=view.findViewById(R.id.textView7);
        q3=view.findViewById(R.id.textView8);

        rg1=view.findViewById(R.id.radiogroup);
        rg2=view.findViewById(R.id.radioGroup2);
        rg3=view.findViewById(R.id.radiogroup3);

         r1 =view.findViewById(R.id.radioButton);
         r2 =view.findViewById(R.id.radioButton2);
         r3 =view.findViewById(R.id.radioButton3);
         r4 =view.findViewById(R.id.radioButton4);
         r5 =view.findViewById(R.id.radioButton5);
         r6 =view.findViewById(R.id.radioButton6);
         r7 =view.findViewById(R.id.radioButton7);
         r8 =view.findViewById(R.id.radioButton8);
         r9 =view.findViewById(R.id.radioButton9);
         r10=view.findViewById(R.id.radioButton10);
         r11=view.findViewById(R.id.radioButton11);
         r12=view.findViewById(R.id.radioButton12);

         Bundle bundle=getArguments();

         r1 .setText(bundle.getString("Q1_O1"));
         r2 .setText(bundle.getString("Q1_O2"));
         r3 .setText(bundle.getString("Q1_O3"));
         r4 .setText(bundle.getString("Q1_O4"));
         r5 .setText(bundle.getString("Q2_O1"));
         r6 .setText(bundle.getString("Q2_O2"));
         r7 .setText(bundle.getString("Q2_O3"));
         r8 .setText(bundle.getString("Q2_O4"));
         r9 .setText(bundle.getString("Q3_O1"));
         r10.setText(bundle.getString("Q3_O2"));
         r11.setText(bundle.getString("Q3_O3"));
         r12.setText(bundle.getString("Q3_O4"));

          q1.setText(bundle.getString("Q1"));
          q2.setText(bundle.getString("Q2"));
          q3.setText(bundle.getString("Q3"));


          paragraph.setText(bundle.getString("Q"));

           rg1.setOnCheckedChangeListener(this);
           rg2.setOnCheckedChangeListener(this);
           rg3.setOnCheckedChangeListener(this);
        //b2.putInt("ID",qpara.getID());



        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        if (group.getId()==R.id.radiogroup)
        {
            if (checkedId == R.id.radioButton) {
                // r1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a1="a";

            } else if (checkedId == R.id.radioButton2) {
                //r2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a1="b";

            } else if (checkedId == R.id.radioButton3) {
                //r3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a1="c";

            } else {
                // r4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a1="d";

            }

        }else if (group.getId()==R.id.radioGroup2)
        {
            if (checkedId == R.id.radioButton5) {
                // r1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a2="a";

            } else if (checkedId == R.id.radioButton6) {
                //r2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a2="b";

            } else if (checkedId == R.id.radioButton7) {
                //r3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a2="c";

            } else {
                // r4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a2="d";

            }

        }else {
            if (checkedId == R.id.radioButton9) {
                // r1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a3="a";

            } else if (checkedId == R.id.radioButton10) {
                //r2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a3="b";

            } else if (checkedId == R.id.radioButton11) {
                //r3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a3="c";

            } else {
                // r4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                a3="d";

            }

        }

        new MyPreferences(context).setAnswer(a1+","+a2+","+a3);

    }
}
