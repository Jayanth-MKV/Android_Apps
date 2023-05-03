package com.vugido.foods.vugido_delivery_manager.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.vugido.foods.vugido_delivery_manager.R;

import java.util.Objects;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

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
        //defaultFragment();




       /* if(Build.VERSION.SDK_INT >= 23){

            //check for permissions...
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PERMISSION_GRANTED){

                // ok permissions granted
                startService();
            }else {
                // no permissions so ask
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }

        }else {
            // we can start services no required run time permissions
            startService();
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
                if(grantResults[0]==PERMISSION_GRANTED){
                    startService();
                }else {
                    Toast.makeText(this,"Give the location access",Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

    private void startService(){
       // Intent intent=new Intent(this, LocationService.class);
       // ContextCompat.startForegroundService(this,intent);
    }
    private void stopService(){
        //Intent intent=new Intent(this,LocationService.class);
        //stopService(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {

            case R.id.log_out:

                break;
            /*case R.id.exit:
                /// send server that bboy exit app..
                stopService();
                break;*/

        }
        return true;
    }
}