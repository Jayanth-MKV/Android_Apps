package com.vugido.info.homeservicesadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.dialogs.MyStatusDialog;
import com.vugido.info.homeservicesadmin.fragments.HomeFragment;

public class HomeCServicesEdit extends AppCompatActivity {


    MyStatusDialog myStatusDialog;
    private HomeFragment homePageFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    int v;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_edi);

        v=getIntent().getIntExtra("V",0);

        fragmentManager=getSupportFragmentManager();

        defaultFragment();

    }







    private void defaultFragment() {
        homePageFragment=new HomeFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("V",v);
        homePageFragment.setArguments(bundle);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,homePageFragment,"HOME");
        fragmentTransaction.commit();

    }

//
//    // creating a method to show and hide fragment..
//    private void showAndHideFragment(Fragment fragment, String TAG){
//
//        fragmentTransaction=fragmentManager.beginTransaction();
//
//        // there may be multiple fragments.. being created..so we need to know which fragment is visible
//        if(homePageFragment!=null && homePageFragment.isVisible())
//            fragmentTransaction.hide(homePageFragment);
//        if(myOrdersFragment!=null && myOrdersFragment.isVisible())
//            fragmentTransaction.hide(myOrdersFragment);
//        if(myAccountFragment!=null && myAccountFragment.isVisible())
//            fragmentTransaction.hide(myAccountFragment);
//        if(mySearchFragment!=null&& mySearchFragment.isVisible())
//            fragmentTransaction.hide(mySearchFragment);
//        // now have hided all existing fragments...
//        // now using the fragment value passed to this method we will show that fragment..
//
//        if(fragment!=null&& fragment.isAdded())
//            fragmentTransaction.show(fragment);
//        else {
//            assert fragment != null;
//            fragmentTransaction.add(R.id.fragment_container,fragment,TAG);
//        }
//
//        // finally commit..
//        fragmentTransaction.commit();
//
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//
//        switch (menuItem.getItemId()){
//
//            case R.id.home:
//               // Cart.setVisible(true);
//                // Notifications.setVisible(true);
//                // PocketCart.setVisible(false);
//                if(homePageFragment==null)
//                    homePageFragment=new HomeFragment();
//                // now instead replacing fragments we can call hide and show method..
//
//                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");
//
//
//                showAndHideFragment(homePageFragment,"HOME");
//                break;
//            case R.id.bottom_orders:
//               // Cart.setVisible(true);
//
//                if(myOrdersFragment==null)
//                    myOrdersFragment=new MyOrdersFragment();
//
//                showAndHideFragment(myOrdersFragment,"ORDERS");
//                break;
////            case R.id.search:
////               // Cart.setVisible(true);
////
////                if(mySearchFragment==null)
////                    mySearchFragment=new SearchFragment();
////
////                showAndHideFragment(mySearchFragment,"OFFERS");
////                // invoke earn coupons fragment
////                break;
//            case R.id.account:
//                //Cart.setVisible(false);
//                // PocketCart.setVisible(false);
//                // Notifications.setVisible(false);
//                if(myAccountFragment==null)
//                    myAccountFragment=new MyAccountFragment();
//                // now instead replacing fragments we can call hide and show method..
//
//                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");
//
//
//                showAndHideFragment(myAccountFragment,"PROFILE");
//                break;
//
//
//        }
//
//
//
//        return true;
//
//
//
//    }
//

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }

}