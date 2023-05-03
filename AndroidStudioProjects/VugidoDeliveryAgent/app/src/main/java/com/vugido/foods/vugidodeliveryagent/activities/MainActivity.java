package com.vugido.foods.vugidodeliveryagent.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.vugido.foods.vugidodeliveryagent.R;
import com.vugido.foods.vugidodeliveryagent.app_config_variables.MyPreferences;
import com.vugido.foods.vugidodeliveryagent.dialogs.ReVerifyAccount;
import com.vugido.foods.vugidodeliveryagent.fragments.PendingOrders;
import com.vugido.foods.vugidodeliveryagent.models.status.Response;
import com.vugido.foods.vugidodeliveryagent.network.Retrofit.RetrofitBuilder;
import com.vugido.foods.vugidodeliveryagent.services.LocationService;
import com.vugido.foods.vugidodeliveryagent.services.RingToneService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

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
        defaultFragment();


        stopSService();

        if(Build.VERSION.SDK_INT >= 23){

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
        }

        if(new MyPreferences(this).isTokenChanged()){
            //  To store...
            sendFCMToken(0);
        }else {

            //check user authentication..
            sendFCMToken(1);

        }
    }

    public void stopSService() {
        Intent serviceIntent = new Intent(this, RingToneService.class);
        stopService(serviceIntent);
    }

    private void defaultFragment() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new PendingOrders();
        fragmentTransaction.add(R.id.fragment_container,fragment,"PENDING");
        fragmentTransaction.commit();



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
        Intent intent=new Intent(this, LocationService.class);
        ContextCompat.startForegroundService(this,intent);
    }
    private void stopService(){
        Intent intent=new Intent(this,LocationService.class);
        stopService(intent);
    }

    private void sendFCMToken(final int check) {

        Map<String,Object> map=new HashMap<>();
        map.put("DID",new MyPreferences(this).getDID());
        map.put("TOKEN",new MyPreferences(this).getFireBaseToken());
        map.put("IS_CHECK",String.valueOf(check));




        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().sendUserFCM(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // updated


                        new MyPreferences(getApplicationContext()).tokenChanged(false);


                    }else {

                        if(check==1){

                            // show re-verify dialog..

                            showReVerifyDialogBox();
                        }
                        // not updated

                    }
                }else {

                    // error
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });
    }

    private void showReVerifyDialogBox() {

        ReVerifyAccount reVerifyAccount=new ReVerifyAccount();
        reVerifyAccount.show(getSupportFragmentManager(),"REVERIFY");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {

            case R.id.log_out:

                break;
            case R.id.exit:
                /// send server that bboy exit app..
                stopService();
                break;
            case R.id.pending_orders:
                defaultFragment();
                break;
            case R.id.packed_orders:
                break;
            case R.id.cancelled_orders:
                break;
            case R.id.dashboard:
                break;

        }
        return true;
    }
}