package com.vugido.vugidoinventorymanagement.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.fragments.admin_action_fragments.AddNewImageFragment;
import com.vugido.vugidoinventorymanagement.fragments.admin_action_fragments.AddNewMainCategoryFragment;

import java.util.Objects;

public class AdminActionActivity extends AppCompatActivity {

    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_action_activity);

        toolbar=findViewById(R.id.admin_action_toolbar);

        // get admin action string
        toolbar.setTitle(Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getString("ACTION"));
        setSupportActionBar(toolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fragmentManager=getSupportFragmentManager();
        callRelationFragment(Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")));


    }

    private void callRelationFragment(Bundle bundle) {

        int AID=bundle.getInt("AID");
        String ACTION=bundle.getString("ACTION");
        switch (AID){
            case 1:
                //new product
                break;
            case 2:
                //new image
                AddNewImageFragment adminActionFragment=new AddNewImageFragment();
                adminActionFragment.setArguments(bundle);
                addFragment(adminActionFragment);

                break;
            case 3:
                // new category
                AddNewMainCategoryFragment addNewMainCategoryFragment=new AddNewMainCategoryFragment();
                addNewMainCategoryFragment.setArguments(bundle);
                addFragment(addNewMainCategoryFragment);
                break;

        }



    }

    private void addFragment(Fragment fragment){

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.admin_action_frame_layout,fragment,"ADMIN_ACTION_FRAGMENT");
        fragmentTransaction.commit();


    }
}
