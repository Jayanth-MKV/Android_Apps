package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.R;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.HomePageRecyclerViewAdapter;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.app_config_variables.ConfigVariables;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.app_config_variables.MyPreferences;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.dialogs.MyStatusDialog;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.dialogs.ReVerifyAccount;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.dialogs.UpdateDialog;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments.HomePageFragment;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments.MyOrders;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments.MyPocket;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments.MyProfile;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.network.Retrofit.RetrofitBuilder;
import com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.receivers.NetworkCallBack;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements
        HomePageRecyclerViewAdapter.InvokeCategoriesfragment, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int NETWORK_BACK_CODE = 1;
    private boolean NetworkFlag=false;
    public static final int ORDER_PLACED_CODE = 10000;
    private boolean ok=false;
    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private MenuItem PocketCart,Cart,Notifications;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Toolbar toolbar;
    private HomePageFragment homePageFragment;
    private MyOrders myOrders;
    private MyProfile myProfile;
    private MyPocket myPocket;// offers fragment
    //private
    private TextView CartCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.cancelAll();
        //startActivity(new Intent(MainActivity.this,ReferralActivity.class));

        toolbar=findViewById(R.id.activity_toolbar);
        toolbar.setTitle("Vugido Food-Hub");
        setSupportActionBar(toolbar);

        bottomNavigationView=findViewById(R.id.bottom_navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentManager=getSupportFragmentManager();

        //createDynamicLink();

        //demo
        //showReVerifyDialogBox();
        //showUpdateDialogBox();

        if(NetworkCallBack.NetworkAvailability){
            // network is available..
            if(new MyPreferences(this).isTokenChanged()){
                //  To store...
                sendFCMToken(0);
            }else {

                //check user authentication..
                sendFCMToken(1);

            }
            defaultFragment();
            sendNotificationClickedData();
            checkAppUpdate();
            AffiliatedProduct();

            // referral activity...
        }else {

            NetworkFlag=true;

        }













        // load the default fragment here..














      /*  AppSignatureHelper appSignatureHelper=new AppSignatureHelper(this);
        ArrayList<String> stringArrayList=appSignatureHelper.getAppSignatures();


        Log.e("HashString",stringArrayList.toString());*/



    }





    ////////////////////delete later...................








    ///////////////////////////////

    private void AffiliatedProduct(){
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(this, pendingDynamicLinkData -> {

           // Log.e("Link got","Success");
            Uri deepLink=null;

            if(pendingDynamicLinkData!=null){
                deepLink=pendingDynamicLinkData.getLink();


                assert deepLink != null;
                String AffiliateLink=deepLink.toString();
                try {
                    String AffiliateLinkContent = AffiliateLink.substring(AffiliateLink.lastIndexOf("=") + 1);

                    String[] values = AffiliateLinkContent.split("--");

                    String TYPE = values[0];

                    if (TYPE.equals("2")) {
                        // product affiliated...
                        String AUID = values[1];
                        String APID = values[2];
                        String COINS = values[3];
                        String TimeMillis = values[4];

                        //add one more parameter.. RID...
                        //check if not available show dialog..

                    Intent intent = new Intent(MainActivity.this, ProductAffiliatedActivity.class);
                    intent.putExtra("AUID", AUID);
                    intent.putExtra("APID", APID);
                    intent.putExtra("COINS", COINS);
                    intent.putExtra("TIME", TimeMillis);
                    startActivity(intent);
                }else if(TYPE.equals("1")){

                        //refer and earn..
                        String AUID=values[1];
                        String Timemillis=values[2];

                       // if(!new MyPreferences(this).getReferralStatus())
                        if(new MyPreferences(this).getIsSignUp())
                            sendRC(AUID,Timemillis);

                        /// send the request to tht server that this user UID has used AUID referral code to install app...
                        //okay...
                    }
                }catch (Exception e){

                }
            }

            if(deepLink!=null){
              //  Log.e("DeepLink content Uri",deepLink.toString());
            }
        }).addOnFailureListener(this, e -> {
          //  Log.e("Deeplink","Failed not got");
        });
    }

    private void sendRC(String auid,String al) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("AUID",auid);
        map.put("AL_TIME",al);
       // map.put("RC",toString);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").sendRC(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok successfully verified..
                        // show dialog....
                        new MyPreferences(getApplicationContext()).setReferralCodeStatus(true);
                        new MyPreferences(getApplicationContext()).isSignUp(false);
                        if(myPocket==null)
                            myPocket=new MyPocket();
                        MenuItem menuItem=bottomNavigationView.getMenu().findItem(R.id.bottom_offers);
                        menuItem.setChecked(true);
                        showAndHideFragment(myPocket,"OFFERS");
                        // show dialog....


                    }else {
                        // not verified...

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

    @Override
    public void onBackPressed() {
        new MyPreferences(this).isSignUp(false);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        new MyPreferences(this).isSignUp(false);
        super.onDestroy();
    }

    private void checkAppUpdate(){
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<com.foodhub.vugido.models.AppUpdate.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getAppUpdateStatus(map);
        call.enqueue(new Callback<com.foodhub.vugido.models.AppUpdate.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.AppUpdate.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.AppUpdate.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok
                        if(!response.body().isUPDATED()){
                            showUpdateDialogBox();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.AppUpdate.Response> call, @NonNull Throwable t) {

            }
        });
    }

    private void showUpdateDialogBox() {
        UpdateDialog updateDialog=new UpdateDialog();
        updateDialog.show(getSupportFragmentManager(),"UPDATE");
    }

    private void sendNotificationClickedData(){
        boolean ok=getIntent().hasExtra("CLICKED");
        if(ok && (getIntent().getIntExtra("CLICKED",0)==1)){
            int NID=getIntent().getIntExtra("NID",0);

            Map<String,Object> map=new HashMap<>();
            map.put("NID",String.valueOf(NID));
            map.put("UID",new MyPreferences(this).getUID());

            Call<Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).sendUserNotificationAnalytics(map);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                }

                @Override
                public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(() -> {

            if(!NetworkCallBack.NetworkAvailability){

                Intent intent=new Intent(MainActivity.this,NetworkErrorActivity.class);
                startActivityForResult(intent,NETWORK_BACK_CODE);

            }
        }, TIME);
    }

    private void sendFCMToken(int check) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("TOKEN",new MyPreferences(this).getFireBaseToken());
        map.put("IS_CHECK",String.valueOf(check));
       /* FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("Stored",new MyPreferences(this).getFireBaseToken());
            Log.e("newToken",newToken);

        });*/

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").sendUserFCM(map);

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.my_cart:
                // start activity..
                Intent intent=new Intent(this,MyCartActivity.class);
                startActivityForResult(intent,ORDER_PLACED_CODE);
                return true;

        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

      //  PocketCart = menu.findItem(R.id.pocket_cart);
      //  PocketCart.setVisible(false);
        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));

        //  Notifications=menu.findItem(R.id.notification);
       // menuItem.setVisible(false);

        return true;
    }

    private void defaultFragment() {
        homePageFragment=new HomePageFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,homePageFragment,"HOME");
        fragmentTransaction.commit();

    }


    // creating a method to show and hide fragment..
    private void showAndHideFragment(Fragment fragment, String TAG){

        fragmentTransaction=fragmentManager.beginTransaction();

        // there may be multiple fragments.. being created..so we need to know which fragment is visible
        if(homePageFragment!=null && homePageFragment.isVisible())
            fragmentTransaction.hide(homePageFragment);
        if(myOrders!=null && myOrders.isVisible())
            fragmentTransaction.hide(myOrders);
        if(myProfile!=null && myProfile.isVisible())
            fragmentTransaction.hide(myProfile);
        if(myPocket!=null&& myPocket.isVisible())
            fragmentTransaction.hide(myPocket);
        // now have hided all existing fragments...
        // now using the fragment value passed to this method we will show that fragment..

        if(fragment!=null&& fragment.isAdded())
            fragmentTransaction.show(fragment);
        else {
            assert fragment != null;
            fragmentTransaction.add(R.id.fragment_container,fragment,TAG);
        }

        // finally commit..
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){

            case R.id.home:
                Cart.setVisible(true);
               // Notifications.setVisible(true);
               // PocketCart.setVisible(false);
                if(homePageFragment==null)
                    homePageFragment=new HomePageFragment();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(homePageFragment,"HOME");
                break;
            case R.id.bottom_orders:
                Cart.setVisible(true);

                if(myOrders==null)
                    myOrders=new MyOrders();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(myOrders,"ORDERS");
                break;
            case R.id.bottom_offers:
                Cart.setVisible(true);

                if(myPocket==null)
                    myPocket=new MyPocket();

                showAndHideFragment(myPocket,"OFFERS");
                // invoke earn coupons fragment
                break;
//            case R.id.my_wallet:
//
//                break;
           /* case R.id.plans:
                fragment=new MyCategories();
                fragmentTransaction(fragment,"CATEGORIES");
                break;
            case R.id.bottom_pocket:
                Notifications.setVisible(true);
                PocketCart.setVisible(true);
                Cart.setVisible(false);
                fragment=new MyPocket();
                fragmentTransaction(fragment,"POCKET");
                break;*/
            case R.id.profile:
                Cart.setVisible(false);
               // PocketCart.setVisible(false);
               // Notifications.setVisible(false);
                if(myProfile==null)
                    myProfile=new MyProfile();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(myProfile,"PROFILE");
                break;


        }



        return true;



    }

    /*private void fragmentTransaction(Fragment fragment, String TAG){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();
    }*/

    @Override
    public void invokeCategoriesFragment() {

      //  fragment=new MyCategories();
       // fragmentTransaction(fragment,"CATEGORIES");
       // bottomNavigationView.getMenu().findItem(R.id.plans).setChecked(true);


    }
    private void showDialog(boolean status,int id){



        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        if(status){
            bundle.putString("MSG","Order Placed Successfully");
            bundle.putBoolean("STATUS",true);

            // refresh the data set of..adapter..

        }else {
            bundle.putString("MSG","Order Not placed !!");
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(CartCount!=null)
            ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);

        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                // show the order placed successfully..fragment
                Toast.makeText(this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();

                //show dialog...
                showDialog(true,0);
                // change cart count..

            }

        }else if(requestCode==NETWORK_BACK_CODE){

            if(NetworkFlag && NetworkCallBack.NetworkAvailability)
            {
                retryAllInitialNetworkOperations();
            }
        }
    }

    private void retryAllInitialNetworkOperations() {

        // network is available..
        if(new MyPreferences(this).isTokenChanged()){
            //  To store...
            sendFCMToken(0);
        }else {

            //check user authentication..
            sendFCMToken(1);

        }
        defaultFragment();
        sendNotificationClickedData();
        checkAppUpdate();
        AffiliatedProduct();

        NetworkFlag=false;
    }
}
