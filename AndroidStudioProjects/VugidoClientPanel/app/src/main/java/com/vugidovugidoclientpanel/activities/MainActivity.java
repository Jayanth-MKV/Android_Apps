package com.vugidovugidoclientpanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.vugidovugidoclientpanel.R;
import com.vugidovugidoclientpanel.Retrofit.RetrofitBuilder;
import com.vugidovugidoclientpanel.app_congif.MyPreferences;
import com.vugidovugidoclientpanel.models.Categories.addCategory.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    FloatingActionButton AddButton;
    Toolbar toolbar;
    TextView toolbar_title;
    private SweetAlertDialog s;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private View Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Progress=findViewById(R.id.my_progress);

        Progress.setVisibility(View.INVISIBLE);


        AddButton=findViewById(R.id.floatingActionButton);
        toolbar=findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);

        toolbar_title.setText("Dashboard");

        drawerLayout=findViewById(R.id.navbar);
        actionBarDrawerToggle=
                new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView=findViewById(R.id.nav_items_action);

        navigationView.setNavigationItemSelectedListener(this);
        AddButton.setOnClickListener(this);


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
                // dashboard
              break;
            case R.id.add_new_product:

                startActivity(new Intent(MainActivity.this,NewProductActivity.class));

                break;
            case R.id.add_new_category:
                // show dialog box...
                myDialogBox();

                break;
            case R.id.my_inventory:
                startActivity(new Intent(MainActivity.this,AllProducts.class));
                break;
            case R.id.log_out:
                showAlertDialog();
                break;

        }

        return  true;
    }

    private void showAlertDialog() {

        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new MyPreferences(getApplicationContext()).setVerified(false);
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        alertDialog.show();

    }

    private void restartApplication() {

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void myDialogBox() {


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
                    addSubCategory(CategoryName);
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
    private void addSubCategory(String categoryName) {

        Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());
        map.put("SUB_NAME",categoryName);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().addNewSubCategory(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    Progress.setVisibility(View.GONE);
                    assert response.body() != null;
                    if(!response.body().isError()){
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

                Progress.setVisibility(View.GONE);
                // error
                showDialogStatus(false);
            }
        });
    }


    private void showDialogStatus(boolean status){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Added Successfully")
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error.. Try again!!")
            ;
            s.show();

        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.floatingActionButton:


                /// start new product activity...

                break;
        }
    }
}
