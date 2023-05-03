package com.dailyneeds.vugido.activities;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dailyneeds.vugido.adapters.categories_adapter.Categories_Adapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.dialogs.AppWeeklyServiveTime;
import com.dailyneeds.vugido.dialogs.StartDateDialog;
import com.dailyneeds.vugido.dialogs.UpdateDialog;
import com.dailyneeds.vugido.fragments.Home;
import com.dailyneeds.vugido.fragments.MyOrders;
import com.dailyneeds.vugido.fragments.MyPurchases;
import com.dailyneeds.vugido.fragments.ProductAddOnDialog;
import com.dailyneeds.vugido.fragments.bottom_navigation_fragments.Categories_Bottom_Fragment;
import com.dailyneeds.vugido.fragments.bottom_navigation_fragments.HomeFragment;
import com.dailyneeds.vugido.fragments.bottom_navigation_fragments.Todays_Deal_Fragment;
import com.dailyneeds.vugido.fragments.bottom_navigation_fragments.Whish_List_Fragment;
import com.dailyneeds.vugido.receivers.NetworkCallBack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dailyneeds.vugido.R;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener ,
         NetworkQueries.NetworkQueryInterface, NetworkQueries.NetworkQueryInterfaceWithCode, HomeFragment.CountUpdateer
{
    private static final int NETWORK_BACK_CODE = 0;
    public static final int ORDER_PLACED_CODE = 1;
    private TextView CartCount;

    private static final int REQ_CODE_SPEECH_INPUT = 10;
    private boolean FAB_CENTER=true;
    FloatingActionButton floatingActionButton;

    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private ImageView Logo;// BottomHome,BottomTodayDeal,BottomWish,Bottomcategories;
    TextView toolbar_title;
    private boolean NetworkFlag=false;
   // private CoordinatorLayout coordinatorLayout;
//    private BottomAppBar bottomAppBar;
    public  NavigationView navigationView;
  //  private FloatingActionButton VoiceButton;
    private Translate translate;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.activity_toolbar);
       // bottomAppBar=findViewById(R.id.bottomAppBar);
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();
      //  coordinatorLayout=findViewById(R.id.main_coordinator);


        // Bottom Nav buttons...

       /* BottomHome=findViewById(R.id.bottom_home);
        Bottomcategories=findViewById(R.id.bottom_categories);
        BottomTodayDeal=findViewById(R.id.bottom_TodayDeal);
       // BottomWish=findViewById(R.id.bottom_wish);
       // VoiceButton=findViewById(R.id.voiceSearch);


        // adding on click listener to all bottom buttons

        BottomHome.setOnClickListener(this);
        BottomWish.setOnClickListener(this);
        BottomTodayDeal.setOnClickListener(this);
        Bottomcategories.setOnClickListener(this);
        VoiceButton.setOnClickListener(this);*/



        //loading default fragment
        toolbar_title=findViewById(R.id.toolbar_title);
        Logo=findViewById(R.id.app_logo);
        drawerLayout=findViewById(R.id.navbar);
       // drawerLayout.setScrimColor(Color.TRANSPARENT);
       // drawerLayout.setDrawerElevation(0f);
        actionBarDrawerToggle=
                new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){

          /*  private float scaleFactor=5f;
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        super.onDrawerSlide(drawerView, slideOffset);

                        float slideX = drawerView.getWidth() * slideOffset;
                        coordinatorLayout.setTranslationX(slideX);
                        coordinatorLayout.setScaleX(1 - (slideOffset));
                        coordinatorLayout.setScaleY(1 - (slideOffset));
                    }*/
                };

       // Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);







        // fabAnimation();
       // bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu);
        //navigation view clicks..

        navigationView=findViewById(R.id.nav_items_action);
        navigationView.setNavigationItemSelectedListener(this);

        if(!ConfigVariables.appWeeklyServiceTimings()){
            AppWeeklyServiveTime appWeeklyServiveTime=new AppWeeklyServiveTime();
            appWeeklyServiveTime.show(getSupportFragmentManager(),"WEEKLY");
            // show user service not available between

        }
        // check for the update..

        if(NetworkCallBack.NetworkAvailability){
            // network is available..

            NetworkQueries networkQueries=new NetworkQueries(this,ConfigVariables.UPDATE_URL,null);
            networkQueries.sendNormalRequest();
            GetTokenFromoServer();
            defaultFragment();

           // defaultHomeFragment();

            fetchCartDetails();
            if(new MyPreferences(this).isTokenChanged())
                sendToken();


        }else {

            NetworkFlag=true;

        }









    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NETWORK_BACK_CODE && resultCode== RESULT_CANCELED){

            if(NetworkCallBack.NetworkAvailability && NetworkFlag ){

                NetworkQueries networkQueries=new NetworkQueries(this,ConfigVariables.UPDATE_URL,null);
                networkQueries.sendNormalRequest();
                GetTokenFromoServer();
                defaultFragment();
                fetchCartDetails();
                if(new MyPreferences(this).isTokenChanged())
                    sendToken();

            }

        }else if(requestCode==MainActivity.ORDER_PLACED_CODE && resultCode==RESULT_CANCELED){

            Log.e("from category","all ok");

            assert CartCount!=null;
            ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);

        }else if(requestCode==ORDER_PLACED_CODE){


            if(resultCode==1){

                // show the order info activity.. order placed
                // also fetch the cart details
                Log.e("onAres","ok");

                fetchCartDetails();


            }


        }else if(requestCode== REQ_CODE_SPEECH_INPUT){


                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    assert result != null;

                    getTranslateService();
                    String string=translate(result.get(0));
                    Toast.makeText(this,string + ","+result.get(0),Toast.LENGTH_LONG).show();



                    // now send this result query to web and get those products info..




                }


        }
    }

    private void fetchCartDetails() {


        NetworkQueries networkQueries=new NetworkQueries(this,ConfigVariables.FETCH_CART_COUNT,buildParamsUID(),2);
        networkQueries.sendNormalRequestCode();



    }

    private Map<String, String> buildParamsUID() {
        Map<String , String> params=new HashMap<>();
        params.put("UID",new MyPreferences(this).getUID());
        return params;
    }

    private void defaultFragment() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment=new HomeFragment();
        fragmentTransaction.add(R.id.fragment_container,fragment,"HOME");
        Logo.setVisibility(View.VISIBLE);
        toolbar_title.setVisibility(View.GONE);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return  true;
        }else if(item.getItemId()==R.id.cart){


           // showStartDate();
             startActivityForResult(new Intent(MainActivity.this,CartActivity.class),ORDER_PLACED_CODE);
            return true;

        }/*else if(item.getItemId()== R.id.search){

            startActivity(new Intent(MainActivity.this,SearchActivity.class));
            return  true;
        }*/
        return false;


    }

    private void showStartDate() {


        StartDateDialog startDateDialog=new StartDateDialog();
        startDateDialog.show(getSupportFragmentManager(),"START");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hometoolbarmenu,menu);
        // Associate searchable configuration with the SearchView
        final MenuItem cart = menu.findItem(R.id.cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(0,CartCount);
        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));


       /* MenuItem s = menu.findItem(R.id.search);
        SearchView search = (SearchView) s.getActionView();*/

        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if(searchManager==null){
            Toast.makeText(this,"Search null",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Search available",Toast.LENGTH_LONG).show();
        }
        assert searchManager != null;
        search.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchActivity.class)));*/
        return true;
    }



    private void GetTokenFromoServer() {

        NetworkQueries networkQueries=new NetworkQueries(this,ConfigVariables.GET_FIREBASE_TOKEN,buildParams(),1);
        networkQueries.sendNormalRequestCode();



    }

    private Map<String,String> buildParams(){

        Map<String,String> param=new HashMap<>();
        param.put("UID",new MyPreferences(getApplicationContext()).getUID());
        param.put("token",new MyPreferences(getApplicationContext()).getFireBaseToken());
        return param;
    }




    // method to handle all drawer clicks..
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        drawerLayout.closeDrawers();

        switch(menuItem.getItemId()){

            case R.id.rateUs:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

            case R.id.home:
                menuItem.setChecked(true);

                fragment=new HomeFragment();
                fragmentTransaction(fragment,"HOME");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);
              //  toolbar_title.setText(R.string.active_order);
                break;
            case R.id.orders:
                //showStartDate();
                menuItem.setChecked(true);

                fragment=new MyOrders();
                fragmentTransaction(fragment,"ORDERS");
                Logo.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(R.string.my_orders);
                break;
          /*  case R.id.offers:
               // showStartDate();


                fragment=new Offers();
                fragmentTransaction(fragment,"OFFERS");
                Logo.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(R.string.offers);
                break;*/
           /* case R.id.history:
               // showStartDate();
                menuItem.setChecked(true);

                fragment=new MyPurchases();
                fragmentTransaction(fragment,"PURCHASES");
                Logo.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(R.string.purchases);
                break;*/
            case R.id.about:
               // showStartDate();
                menuItem.setChecked(false);
                startActivity(new Intent(MainActivity.this, AboutUs.class));
             /*   fragment=new AboutUs();
                fragmentTransaction(fragment,"ABOUT_US");
                Logo.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(R.string.about_us);*/
             break;
            case R.id.share:
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "App Download Link :"+System.lineSeparator()+ ConfigVariables.MY_APP_URL);
                startActivity(intent);
                // set share link...

                break;
            case R.id.helpline:

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:7447337566"));
                startActivity(callIntent);
               // showStartDate();
                break;
            case R.id.login:
                showAlertDialog();
                break;

          /*  case R.id.drawer_categories:
                fragment=new Categories_Bottom_Fragment();
                fragmentTransaction(fragment,"CATEGORY");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);

                break;
            case R.id.drawer_deal:
                fragment=new Todays_Deal_Fragment();
                fragmentTransaction(fragment,"TODAY_DEAL");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);


                break;
            case R.id.drawer_wish:
                fragment=new Whish_List_Fragment();
                fragmentTransaction(fragment,"WISH");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);
                break;*/


        }


        return true;
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

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void fragmentTransaction(Fragment fragment, String TAG){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,TAG);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login:
                showAlertDialog();
                break;
          /*  case R.id.bottom_home:

                // perform fragment transaction


                fragment=new HomeFragment();
                fragmentTransaction(fragment,"HOME");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);
                // check weather the current fragment is visible



                break;
            case R.id.bottom_TodayDeal:
                // perform fragment transaction

                fragment=new Todays_Deal_Fragment();
                fragmentTransaction(fragment,"TODAY_DEAL");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);


                break;
            case R.id.bottom_wish:
                // perform fragment transaction
                fragment=new Whish_List_Fragment();
                fragmentTransaction(fragment,"WISH");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);

                break;
            case R.id.bottom_categories:
                // perform fragment transaction


                fragment=new Categories_Bottom_Fragment();
                fragmentTransaction(fragment,"CATEGORY");
                Logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);


                break;
            case R.id.voiceSearch:

                getVoiceInput();

                break;
*/

        }

    }

    private void getVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,new String[]{"te-IN","en-IN"});
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"te-IN");
       // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-IN");


        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.ask_vugi));



        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.voice_not_supp),
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = getResources().openRawResource(R.raw.gtranslate_credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }


    public String translate(String originalText) {

        String translatedText;
        //Get input text to be translated:
        assert translate!=null;
        Log.e("textFromVugi",originalText);
        Translation translation = translate.translate(originalText, Translate.TranslateOption.sourceLanguage("te"),Translate.TranslateOption.targetLanguage("en"));
        translatedText = translation.getTranslatedText();

        //Translated text and original text are set to TextViews:


        return translatedText;
       // translatedTv.setText(translatedText);

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


   /* @Override
    public void updateCartCount(int Count) {


        Log.e("update","called");
       if(CartCount != null){

            Log.e("Count",String.valueOf(Count));

           ConfigVariables.setupBadge(Count,CartCount);

        }

    }*/



    @Override
    public void networkQueryInterface(String Response) {


        try {
            JSONObject jsonObject=new JSONObject(Response);
            boolean A=jsonObject.getBoolean("A");
            if(A){



                int currentCode= jsonObject.getInt("VC");
                int deviceCode= getVersionCode();

                if(currentCode!=deviceCode){

                    UpdateDialog updateDialog=new UpdateDialog();
                    updateDialog.show(getSupportFragmentManager(),"UPDATE");
                    updateDialog.setCancelable(false);


                }





            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void networkQueryError(String error) {



    }

    @Override
    public void networkQueryInterface(String Response, int code) {


        if(code==1){

            // get token.. and verify...
            try {
                JSONObject jsonObject=new JSONObject(Response);
                boolean error = jsonObject.getBoolean("error");

                if(!error){

                    String FCM= jsonObject.getString("FCM");

                    if (new MyPreferences(this).getFireBaseToken().equals(FCM)){

                        // no problem...
                    }else {

                        // tell  user to re verify the account...

                      //  ReVerifyAccount reVerifyAccount=new ReVerifyAccount();
                      //  reVerifyAccount.show(getSupportFragmentManager(),"RE_VERIFY");
                        Log.e("Re-Verify","FCM changed");
                    }

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else
            if(code==2){


                try {
                    JSONObject jsonObject=new JSONObject(Response);
                    boolean error=jsonObject.getBoolean("error");


                    if(!error){

                        Log.e("fetched","ok");
                        int count=jsonObject.getInt("COUNT");
                        new MyPreferences(this).setCartCount(count);
                       // updateCartCount(count);
                        assert CartCount!=null;
                        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);





                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }



    }

    @Override
    public void networkQueryError(String error, int code) {

    }

    private int getVersionCode(){
        PackageInfo packageInfo = null;
        int versionCode=-1;

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;

    }

    private void sendToken(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConfigVariables.SEND_FIREBASE_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean error=jsonObject.getBoolean("error");



                    if(!error){
                        new MyPreferences(getApplicationContext()).tokenChanged(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){


            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("UID",new MyPreferences(getApplicationContext()).getUID());
                params.put("TOKEN",new MyPreferences(getApplicationContext()).getFireBaseToken());
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void countUpdater() {


        fetchCartDetails();
    }
}

