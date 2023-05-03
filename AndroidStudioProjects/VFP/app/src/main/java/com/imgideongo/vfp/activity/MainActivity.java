package com.imgideongo.vfp.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.ConfigVariables.MyApplication;
import com.imgideongo.vfp.ConfigVariables.NotificationConstants;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.adapters.AbstractItemRecyclerViewAdapter;
import com.imgideongo.vfp.dialogs.OrderSuccessfulDialog;
import com.imgideongo.vfp.fragments.Account;
import com.imgideongo.vfp.fragments.ActiveOrders;
import com.imgideongo.vfp.fragments.DashBoard;
import com.imgideongo.vfp.helper.MyPreferences;
import com.imgideongo.vfp.receiver.ConnectivityChecker;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DashBoard.RefreshDashBoard,
        DashBoard.ActionMdeInterface,
        AbstractItemRecyclerViewAdapter.Dialogbox ,
        ConnectivityChecker.ConnectivityListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    TextView toolbar_title;
    Fragment fragment;
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    public ActionMode actionMode;
    private boolean isMultiSelect = false;
    Bundle bundle;
    ImageView ClientHeaderImage;
    private AlertDialog alertDialog;
    private boolean INITILIZATION_WORK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        checkNetworkConnection();

    }

    private void showNotification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int importance=NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel=new NotificationChannel(NotificationConstants.CHANNEL_ID,NotificationConstants.CHANNEL_NAME,importance);
            notificationChannel.setDescription(NotificationConstants.CHANNEL_DESCRIPTION);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    private void sendTokenToServer() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.URL_CID_FIRE_BASE_TOKEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> param=new HashMap<>();
            //    Log.e("token",new MyPreferences(getApplicationContext()).getFireBaseToken());
                param.put("CID",new MyPreferences(getApplicationContext()).getUID());
                param.put("T",new MyPreferences(getApplicationContext()).getFireBaseToken());
                return param;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void showAlertDialog() {

        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new MyPreferences(getApplicationContext()).setLoginInState(false);
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });

        alertDialog.show();




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }
    private void loadDashBoard() {
      //  bundle=new Bundle();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new DashBoard();
      //  bundle.putInt("CID",getIntent().getIntExtra("CID",0));
      //  fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container,fragment,"DashBoard");
        fragmentTransaction.commit();
        toolbar_title.setText(R.string.dashboard);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void refreshDashBoard() {
        bundle=new Bundle();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new DashBoard();
        bundle.putInt("CID",getIntent().getIntExtra("CID",0));
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container,fragment,"DashBoard");
        fragmentTransaction.commit();

    }

    private ActionMode.Callback callback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getMenuInflater().inflate(R.menu.dashboard_item_selection,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {


            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            actionMode=null;
            isMultiSelect=false;
            toolbar.setVisibility(View.VISIBLE);
           refreshDashBoard();


            //refresh adapter


        }
    };


    @Override
    public void actionMode(boolean longClick,boolean justClick) {


                if (actionMode == null) {
                    isMultiSelect=true;
                    toolbar.setVisibility(View.GONE);
                    actionMode = startSupportActionMode(callback);

                }



    }

    @Override
    public boolean getActionModeState() {
        return isMultiSelect;
    }

    @Override
    public void setActionModeTitleInfo(int count) {
        actionMode.setTitle(count +" Items");
    }

    @Override
    public void dialogBoxVisibility(int State) {
        final OrderSuccessfulDialog orderSuccessfulDialog=new OrderSuccessfulDialog();
        Bundle bundle=new Bundle();
        bundle.putInt("STATE",State);
        orderSuccessfulDialog.setArguments(bundle);
        orderSuccessfulDialog.show((getSupportFragmentManager()),"DialogBox");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                orderSuccessfulDialog.dismiss();
            }
        },2000);

    }

    private void fragmentTransaction(Fragment fragment,String TAG){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();

    }
    private String topOfBackStack() {
        String TAG = "";
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(count - 1);
            TAG = backStackEntry.getName();
        }
        return TAG;
    }

    private void restartApplication() {

        //resetUserPreferencesToNull();
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void checkNetworkConnection(){
        boolean connected=ConnectivityChecker.isConnected();

        if(connected){

            INITILIZATION_WORK=true;

            basicWORK();
            showNotification();
          //  sendTokenToServer();





        }else {

            INITILIZATION_WORK=false;
        }

    }

    private void basicWORK() {
      //  String clientLogoUrl = Config.URL_CLIENT_LOGO + new MyPreferences(this).getLogoString();
        drawerLayout=findViewById(R.id.navbar);
        actionBarDrawerToggle=
                new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        loadDashBoard();
        NavigationView navigationView=findViewById(R.id.nav_items_action);
       // View view=navigationView.getHeaderView(0);
       // ClientHeaderImage=view.findViewById(R.id.ClientLogoNavHeader);
       // Log.e("logoUrl", clientLogoUrl);
      //  Glide.with(this).load(clientLogoUrl).into(ClientHeaderImage);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        menuItem.setChecked(true);

                        drawerLayout.closeDrawers();

                        switch (menuItem.getItemId()){
                            case R.id.activeOrders:
                                fragment=new ActiveOrders();
                                fragmentTransaction(fragment,"ActiveOrders");
                                toolbar_title.setText(R.string.active_orders);
                                break;
                            case R.id.dashboard:
                                Log.e("executed","ok");
                                bundle=new Bundle();
                                bundle.putInt("CID",getIntent().getIntExtra("CID",0));
                                fragment=new DashBoard();
                                fragment.setArguments(bundle);
                                fragmentTransaction(fragment,"DashBoard");
                                toolbar_title.setText(R.string.dashboard);
                                break;
                            case  R.id.account:
                                fragment=new Account();
                                fragmentTransaction(fragment,"MyAccount");
                                toolbar_title.setText(R.string.my_account);
                                break;
                            case R.id.logout:
                                showAlertDialog();
                                break;
                        }
                        return true;


                    }

                }

        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().unSetConnectivityListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    public void connectivityListener(boolean connection) {

        if(!connection){
            if(alertDialog==null){
                showNetworkError();
            }
        }else {
            // all ok.. hide the network dialog error
            hideDialog();
             // u can now resume remaining activity
            if(!INITILIZATION_WORK){
                basicWORK();
                INITILIZATION_WORK=true;
            }

        }

    }

    private void hideDialog() {

        if(alertDialog!=null){
            alertDialog.dismiss();
            alertDialog=null;
        }
    }

    private void showNetworkError() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Check Your Internet Connection !");
        alertDialog= alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }
}
