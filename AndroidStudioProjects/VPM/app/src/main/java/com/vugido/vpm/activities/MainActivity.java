package com.vugido.vpm.activities;

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

import com.google.android.material.navigation.NavigationView;
import com.vugido.vpm.R;
import com.vugido.vpm.fragments.Dashboard;
import com.vugido.vpm.fragments.FailedOrders;
import com.vugido.vpm.fragments.PackedOrders;
import com.vugido.vpm.fragments.PendingOrders;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TextView toolbar_title;
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
        setSupportActionBar(toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        toolbar_title.setText("Dashboard");
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
        fragment=new Dashboard();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragment,"DASHBOARD");
        fragmentTransaction.commit();
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
            case R.id.dashboard:
                fragment=new Dashboard();
                changeFragment(fragment,"DASHBOARD");
                break;
            case R.id.pending_orders:
                fragment=new PendingOrders();
                changeFragment(fragment,"PENDING");
                break;
            case R.id.packed_orders:
                fragment=new PackedOrders();
                changeFragment(fragment,"PACKED");
                break;
            case R.id.cancelled_orders:
                fragment=new FailedOrders();
                changeFragment(fragment,"FAILED");
                break;
            case R.id.log_out:

                break;

        }

        return true;
    }

    private void changeFragment(Fragment fragment, String TAG) {

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();

    }
}