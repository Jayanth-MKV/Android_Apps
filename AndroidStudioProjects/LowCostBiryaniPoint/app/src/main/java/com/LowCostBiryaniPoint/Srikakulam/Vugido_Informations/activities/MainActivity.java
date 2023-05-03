package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.R;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments.AccountFragment;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments.ActiveOrderFragment;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments.HomeFragment;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments.ProcessedOrdersFragment;
import com.google.android.material.navigation.NavigationView;
import java.util.Objects;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TextView toolbar_title;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Fragment fragment;
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);



        drawerLayout=findViewById(R.id.navbar);
        actionBarDrawerToggle=
                new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView=findViewById(R.id.nav_items_action);

        navigationView.setNavigationItemSelectedListener(this);

        loadDefaultFragment();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        item.setChecked(true);

        drawerLayout.closeDrawers();

        switch (item.getItemId()){
            case R.id.nav_home:
                fragment=new HomeFragment();
                fragmentTransaction(fragment,"Home");
                toolbar_title.setText("Home");
                break;
            case R.id.nav_active_orders:
                fragment=new ActiveOrderFragment();
                fragmentTransaction(fragment,"Active Orders");
                toolbar_title.setText("Active Orders");
                break;
            case R.id.nav_processed_orders:
                fragment=new ProcessedOrdersFragment();
                fragmentTransaction(fragment,"ProcessedOrders");
                toolbar_title.setText("Processed orders");
                break;
            case R.id.nav_account:
                fragment=new AccountFragment();
                fragmentTransaction(fragment,"AccountFragment");
                toolbar_title.setText("Account Settings");
                break;
            case R.id.about_us:
                break;
            case R.id.share:
                break;
            case R.id.rate:
                break;
            case R.id.call_helpline:
                break;
            case R.id.tc:
                break;
            case R.id.pp:
                break;
            case R.id.logout:
                break;

        }

        return  true;
    }


    private void fragmentTransaction(Fragment fragment,String TAG){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();

    }

    private void loadDefaultFragment() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new HomeFragment();
        fragmentTransaction.add(R.id.fragment_container,fragment,"Home");
        fragmentTransaction.commit();
        toolbar_title.setText("Home");
    }
}
