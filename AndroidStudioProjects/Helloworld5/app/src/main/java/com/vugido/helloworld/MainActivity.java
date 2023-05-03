package com.vugido.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LearnFragment learnFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        fragmentManager=getSupportFragmentManager();
//        toolbar=findViewById(R.id.toolbar);
//
//        toolbar.setTitle("AZBotics");
//
//        setSupportActionBar(toolbar);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);






    }




    private void myFragmentTransaction(Fragment fragment, String frag_name){
        fragmentTransaction=fragmentManager.beginTransaction();
        //fragment
        //string...

        fragmentTransaction.replace(R.id.frameLayout,fragment,frag_name);
        fragmentTransaction.commit();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.store:

               Toast.makeText(this,"store",Toast.LENGTH_LONG).show();
               break;
           case R.id.learn:
               Toast.makeText(this,"Learn",Toast.LENGTH_LONG).show();

               if(learnFragment==null)
                   learnFragment=new LearnFragment();
                   myFragmentTransaction(learnFragment,"LF");
               break;
           case R.id.events:
               Toast.makeText(this,"Events",Toast.LENGTH_LONG).show();

               break;
           case R.id.profile:
               Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();

               break;



       }

       return true;
    }
}