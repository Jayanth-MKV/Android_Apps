package com.jntuk.ucev.placementsportal.fragments.Reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.jntuk.ucev.placementsportal.R;

import java.util.Calendar;

public class PersonalDetailsFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button Proceed,setImage;
    PI pi;
    ConstraintLayout constraintLayout;
    DatePicker datePicker;
    ImageView pic;
    TextInputEditText name,email,mobile;
    RadioButton male,female;
    String imageString;
    boolean gender=false;
    int MF;
    private boolean allSet() {

        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty()||mobile.getText().toString().isEmpty()
        ||imageString==null|| !gender){

            return false;
        }else
            return  true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pi= (PI) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal_details,container,false);
        Proceed=view.findViewById(R.id.button4);
        datePicker=view.findViewById(R.id.datePicker1);
        pic=view.findViewById(R.id.pic);
        constraintLayout=view.findViewById(R.id.pcons);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        male=view.findViewById(R.id.male);
        female=view.findViewById(R.id.female);
        mobile=view.findViewById(R.id.mobile);
        setImage=view.findViewById(R.id.button2);







        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -27); // subtract 2 years from now
        datePicker.setMinDate(c.getTimeInMillis());

        c.add(Calendar.YEAR, 8); // add 4 years to min date to have 2 years after now
        datePicker.setMaxDate(c.getTimeInMillis());
        Proceed.setOnClickListener(this);

        male.setOnCheckedChangeListener(this);
        female.setOnCheckedChangeListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {

        if(allSet()){

            Bundle bundle=new Bundle();

            pi.pi(bundle);
        }else {

            Snackbar snackbar = Snackbar
                    .make(constraintLayout, "Please Fill All Fields", Snackbar.LENGTH_LONG);
            snackbar.show();

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        gender=true;
        if(buttonView.getId()==R.id.male){
            MF=1;
        }else{
            MF=0;
        }

    }


    public interface PI{

        void pi(Bundle bundle);
    }
}
