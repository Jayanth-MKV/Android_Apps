package com.vugido.ap.sklm.hungrybirdsadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.vugido.ap.sklm.hungrybirdsadmin.R;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.AccountSettings;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.ActiveOrdersFragment;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.CancelledOrders;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.DashBoardFragment;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.InventoryManager;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.ManageDeliveryBoys;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.ManageNotifications;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.ProcessedOrders;
import com.vugido.ap.sklm.hungrybirdsadmin.fragments.UserAppSettings;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.navbar);

        toolbar=findViewById(R.id.activity_toolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.nav_items_action);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();
        defaultFragment();



    }


    private void defaultFragment() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new DashBoardFragment();
        fragmentTransaction.add(R.id.fragment_container,fragment,"Dashboard");
        fragmentTransaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }


    private void restartApplication() {

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    private void showAlertDialog() {


        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // new MyPreferences(getApplicationContext()).setVerified(false);
                restartApplication();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog alertDialog=alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.gradient_start_color));
            }
        });

        alertDialog.show();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {

            case R.id.log_out:
                showAlertDialog();
                break;
            case R.id.processed_orders:
                if(!(fragment instanceof ProcessedOrders) ){
                    manageFragments(new ProcessedOrders(),"Processed Orders","Processed Orders");
                }
                break;
            case R.id.active_orders:
                if(!(fragment instanceof ActiveOrdersFragment) ){
                    manageFragments(new ActiveOrdersFragment(),"Active Orders","Active Orders");
                }
                break;
            case R.id.cancelled_orders:
                if(!(fragment instanceof CancelledOrders) ){
                    manageFragments(new CancelledOrders(),"Cancelled Orders","Failed Orders");
                }
                break;
            case R.id.dashboard:
                if(!(fragment instanceof DashBoardFragment) ){
                    manageFragments(new DashBoardFragment(),"Dashboard","Dashboard");
                }
                break;
            case R.id.account:
                if(!(fragment instanceof AccountSettings) ){
                    manageFragments(new AccountSettings(),"Account Settings" ,"Account Settings");
                }
                break;
            case R.id.notifications:
                if(!(fragment instanceof ManageNotifications) ){
                    manageFragments(new ManageNotifications(),"Notifications","Notifications");
                }
                break;
            case R.id.inventory_manager:
                if(!(fragment instanceof InventoryManager) ){
                    manageFragments(new InventoryManager(),"Inventory","Inventory Manager");
                }
                break;
            case R.id.manage_delivery_boys:
                if(!(fragment instanceof ManageDeliveryBoys) ){
                    manageFragments(new ManageDeliveryBoys(),"Delivery Boys","Delivery Boys");
                }
                break;
            case R.id.user_app_settings:
                if(!(fragment instanceof UserAppSettings) ){
                    manageFragments(new UserAppSettings(),"App Settings","App Settings");
                }
                break;

        }
        return true;
    }

    private void manageFragments(Fragment fragmentHere,String Tag,String title){
        toolbar.setTitle(title);
        fragment=fragmentHere;
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,Tag);
        fragmentTransaction.commit();
    }
}