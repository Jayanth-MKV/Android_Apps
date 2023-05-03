package com.vugido.foods.androidfragments.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vugido.foods.androidfragments.R;
import com.vugido.foods.androidfragments.fragments.FragmentOne;
import com.vugido.foods.androidfragments.fragments.FragmentThree;
import com.vugido.foods.androidfragments.fragments.FragmentTwo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button f1,f2,f3;

    // fragment handling variables...
   // Fragment fragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    FragmentOne fragmentOne;
    FragmentThree fragmentThree;
    FragmentTwo fragmentTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting ids
        f1=findViewById(R.id.f1);
        f2=findViewById(R.id.f2);
        f3=findViewById(R.id.f3);

        // setting on click listeners...
        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);


        // creating the fragment manager object..
        fragmentManager=getSupportFragmentManager();

        //adding default fragment ... this default fragment will be the fragment which will be added
        //automatically when the app starts...

        addDefaultFragment();

        // till now we have seen how to add default fragment.. only
        // Now we need to learn the following
        //* Replacing a fragment with another fragment.. // DONE .... replacing method we created
        //* Hiding and showing a fragment..
        //* removing a fragment completely from activity..
        //* Add new fragment

        // for this we create a function named fragment transaction...

        // ook we did replacing

        // everytime when we replace a fragment the old fragment will we destroyed and dettached from the activity
        // it's a problem so instead of replacing everytime.. we can hide the exisitng fragment
        // which is visible and show the new fragment.. so it's simple..
        // let's check..




    }

    private void addDefaultFragment() {
        // In our case our default fragment is F1 like Home in Youtube....

        // ok now all fragments are ready... with their xml layouts..

        // creating and adding fragment one..
        fragmentOne=new FragmentOne();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragmentOne,"FRAGMENT_ONE");
        fragmentTransaction.commit();

    }

    // creating a method to show and hide fragment..
    private void showAndHideFragment(Fragment fragment, String TAG){

        fragmentTransaction=fragmentManager.beginTransaction();

        // there may be multiple fragments.. being created..so we need to know which fragment is visible
        if(fragmentOne!=null && fragmentOne.isVisible())
            fragmentTransaction.hide(fragmentOne);
        if(fragmentTwo!=null && fragmentTwo.isVisible())
            fragmentTransaction.hide(fragmentTwo);
        if(fragmentThree!=null && fragmentThree.isVisible())
            fragmentTransaction.hide(fragmentThree);

        // now have hided all existing fragments...
        // now using the fragment value passed to this method we will show that fragment..

        if(fragment!=null&& fragment.isAdded())
            fragmentTransaction.show(fragment);
        else {
            assert fragment != null;
            fragmentTransaction.add(R.id.fragment_container,fragment,TAG);
        }

        // finally commit..
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.f1:
                // add fragment 1 here
                if(fragmentOne==null)
                fragmentOne=new FragmentOne();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(fragmentOne,"FRAGMENT_ONE");
                break;
            case R.id.f2:
                //fragment 2
                if(fragmentTwo==null)
                fragmentTwo=new FragmentTwo();

                // now we need to ensure that before calling fragment show or hide it must be added first..
                // so small change in this method
                showAndHideFragment(fragmentTwo,"FRAGMENT_TWO");


                // fragmentTransaction(fragmentTwo,"FRAGMENT_TWO");

                break;
            case R.id.f3:
                // fragment 3
                if(fragmentThree==null)
                fragmentThree=new FragmentThree();


                showAndHideFragment(fragmentThree,"FRAGMENT_THREE");


               // fragmentTransaction(fragmentThree,"FRAGMENT_THREE");

                break;
        }

    }

    private void fragmentTransaction(Fragment fragment, String fragment_tag) {

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,fragment_tag);
        fragmentTransaction.commit();

    }
}