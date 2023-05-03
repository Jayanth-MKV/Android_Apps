package com.vugido.info.homeservicesadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.app_config_variables.MyPreferences;
import com.vugido.info.homeservicesadmin.dialogs.MyDialogLoader;
import com.vugido.info.homeservicesadmin.fragments.Dashboard;
import com.vugido.info.homeservicesadmin.fragments.orders.ActiveServices;
import com.vugido.info.homeservicesadmin.models.Response;
import com.vugido.info.homeservicesadmin.network.Retrofit.RetrofitBuilder;
import com.vugido.info.homeservicesadmin.services.RingToneService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MyDialogLoader myDialogLoader;

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

//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setApplicationId("1:214368475157:android:6901e91b4df6f4cb47561c") // Required for Analytics.
//                .setProjectId("home-services-app-d72f9") // Required for Firebase Installations.
//                .setApiKey("AIzaSyA8rqnaRQP1amq8AqnvG2oyeK_EERZB-aY") // Required for Auth.
//                .build();
//        FirebaseApp.initializeApp(this, options, "Home Services Admin App");

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


    private void sendFCMToken(final int check) {

        Map<String,Object> map=new HashMap<>();
        map.put("DID","1");
//        Log.e("fcm",new MyPreferences(this).getFireBaseToken());
        map.put("TOKEN",new MyPreferences(this).getFireBaseToken());
        map.put("IS_CHECK",String.valueOf(check));




        Call<com.vugido.info.homeservicesadmin.models.status.Response> call= RetrofitBuilder.getInstance().getRetrofit().sendUserFCM(map);

        call.enqueue(new Callback<com.vugido.info.homeservicesadmin.models.status.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.info.homeservicesadmin.models.status.Response> call, @NonNull retrofit2.Response<com.vugido.info.homeservicesadmin.models.status.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // updated


                        new MyPreferences(getApplicationContext()).tokenChanged(false);


                    }else {

                        if(check==1){

                            // show re-verify dialog..

                           // showReVerifyDialogBox();
                        }
                        // not updated

                    }
                }else {

                    // error
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.info.homeservicesadmin.models.status.Response> call, @NonNull Throwable t) {

            }
        });
    }

    private void defaultFragment() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new ActiveServices();
        fragmentTransaction.add(R.id.fragment_container,fragment,"AS");
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
//            case R.id.nav_home:
//                // dashboard
//                break;
            case R.id.acd:

                Intent i=new Intent(MainActivity.this,AddNewServiceInfoActivity.class);
                i.putExtra("V",0);
                startActivity(i);

                break;
            case R.id.ancd:

                startActivity(new Intent(MainActivity.this,AddNewNearInfoActivity.class));

                break;
            case R.id.ans:
                // show dialog box...
                myDialogBox(1);

                break;
            case R.id.ann:
                myDialogBox(2);
                break;
//            case R.id.log_out:
//                showAlertDialog();
//                break;
            case R.id.sac:
                Intent intent1=new Intent(MainActivity.this,HomeCServicesEdit.class);
                intent1.putExtra("V",0);
                startActivity(intent1);
                break;
            case R.id.ani:
                Intent i2=new Intent(MainActivity.this,NearC.class);
                i2.putExtra("V",0);
                startActivity(i2);
                break;
            case R.id.esac:
                Intent intent=new Intent(MainActivity.this,HomeCServicesEdit.class);
                intent.putExtra("V",1);
                startActivity(intent);
                break;
            case R.id.eancd:
                Intent i3=new Intent(MainActivity.this,NearC.class);
                i3.putExtra("V",1);
                startActivity(i3);
                break;

        }

        return  true;
    }



    private void myDialogBox(int k) {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout_add_sub_cat, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS

                // check first for valid name...


                // get the text and send to server...
                String CategoryName= editText.getText().toString();



                if(checkName(CategoryName)){
                    addHomePageSubCategory(CategoryName,k);
                    dialogBuilder.dismiss();

                }else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Category Name(all letters)",Toast.LENGTH_LONG).show();
                }


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    private boolean checkName(String username) {
        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher= pattern.matcher(username);

        if(matcher.find() && matcher.group().equals(username)){
            return  true;
        }
        else
            return false;



    }

    private void addHomePageSubCategory(String categoryName,int k) {

      //  Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("SUB_NAME",categoryName);
        map.put("K",String.valueOf(k));

        if (myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Adding...");
        myDialogLoader.setArguments(bundle);

        myDialogLoader.show(getSupportFragmentManager(),"D");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().addNewCategory(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                myDialogLoader.dismiss();
                if(response.isSuccessful()){

                   // Progress.setVisibility(View.GONE);
                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok
                        showDialogStatus(true);

                    }else {

                        // error
                        showDialogStatus(false);
                    }

                }else {

                    // some error
                    showDialogStatus(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {

               // Progress.setVisibility(View.GONE);
                // error
                showDialogStatus(false);
                myDialogLoader.dismiss();
            }
        });
    }

    private void showDialogStatus(boolean status){
        if(status){
            Toast.makeText(this,"Added Successfully",Toast.LENGTH_LONG).show();
//            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
//                    .setTitleText("Added Successfully")
//            ;
//            s.show();
        }else{
            Toast.makeText(this,"Error.. Try again!!",Toast.LENGTH_LONG).show();

//            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("Error.. Try again!!")
//            ;
//            s.show();

        }

    }


}