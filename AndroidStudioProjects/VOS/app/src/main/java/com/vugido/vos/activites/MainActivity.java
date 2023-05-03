package com.vugido.vos.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import com.vugido.vos.R;
import com.vugido.vos.fragments.FragmentAnalytics;
import com.vugido.vos.fragments.FragmentHome;
import com.vugido.vos.fragments.FragmentInventory;

import java.util.Objects;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    Toolbar toolbar;
    TextView toolbar_title;
    private SweetAlertDialog s;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_title.setText("Dashboard");

        fragmentManager=getSupportFragmentManager();
        drawerLayout = findViewById(R.id.navbar);
        actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = findViewById(R.id.nav_items_action);

        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager.addOnBackStackChangedListener(this);
        defaultFragment();
    }

    private void defaultFragment() {
        fragment=new FragmentHome();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragment,"HOME");
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

        switch (item.getItemId()) {
            case R.id.nav_home:
                // dashboard
                fragment=new FragmentHome();
                fragmentTransaction(fragment,"HOME");
                toolbar_title.setText("Dashboard");

                break;
            case R.id.nav_analytics:
                // analytics
                fragment=new FragmentAnalytics();
                fragmentTransaction(fragment,"ANALYTICS");
                toolbar_title.setText("Analytics");

                break;
            case R.id.nav_inventory:
                // inventory..
                fragment=new FragmentInventory();
                fragmentTransaction(fragment,"INVENTORY");
                toolbar_title.setText("Inventory");

                break;


        }
        return true;
    }

    private void fragmentTransaction(Fragment fragment,String TAG){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackStackChanged() {

    }
}