package com.vugido.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyJava extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button b1, b2;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_java_xml);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        b1.setOnClickListener(this);

        b2.setOnClickListener(this);


        fragmentManager=getSupportFragmentManager();

        defaultFragment();


    }

    private void defaultFragment() {

        fragmentOne=new FragmentOne();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragmentOne,"F1");
        fragmentTransaction.commit();
    }

    private void myFragmentTransaction(Fragment fragment,String frag_name){
        fragmentTransaction=fragmentManager.beginTransaction();
        //fragment
        //string...

        fragmentTransaction.replace(R.id.fragment_container,fragment,frag_name);
        fragmentTransaction.commit();



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button){
            if (fragmentOne==null) {
                fragmentOne = new FragmentOne();
            }
            if(!fragmentOne.isVisible())
                myFragmentTransaction(fragmentOne, "F1");


        }else if(v.getId()==R.id.button2){
            if(fragmentTwo==null) {
                fragmentTwo = new FragmentTwo();
            }
            if(!fragmentTwo.isVisible())
                myFragmentTransaction(fragmentTwo, "F2");


        }
    }
}
