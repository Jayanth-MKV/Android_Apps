package vugido.foodhub.ap.sklm.hungrybirds.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.category.SquareMediumRecyclerViewAdapter;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.ConfigVariables;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;
import vugido.foodhub.ap.sklm.hungrybirds.models.category.DATAItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.category.Response;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.PRODUCTSMENUItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.SECItem;
import vugido.foodhub.ap.sklm.hungrybirds.network.Retrofit.RetrofitBuilder;

import static vugido.foodhub.ap.sklm.hungrybirds.activities.MapsActivity.ORDER;

public class AllProductsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SquareMediumRecyclerViewAdapter.AddToCart {

    Toolbar toolbar;
    View view1;
    private TextView CartCount;

    RecyclerView recyclerView;
    int CID;
    private SearchView searchView;
    private SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter;

    ImageView imageView;


    View eats;
    private MenuItem Cart;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_cart:
                startActivityForResult(new Intent(AllProductsActivity.this,CartProductsActivity.class),ORDER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(CartCount!=null)
            ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

        if (requestCode==ORDER && resultCode==RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        recyclerView=findViewById(R.id.all_products_recycler_view);



        view1=findViewById(R.id.hidden);
        imageView=findViewById(R.id.product_image);

        // get the title from the intent ... also the category intent..

        CID=getIntent().getIntExtra("CID",0);
        String title=getIntent().getStringExtra("TITLE");

        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.gradient_end_color));
        setSupportActionBar(toolbar);
        fetchAllCategoryProducts(CID);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_blue);
        toolbar.setNavigationOnClickListener(v -> finish());





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        eats=  cart.getActionView();
        CartCount=eats.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
        eats.setOnClickListener(view1 -> onOptionsItemSelected(cart));
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null && searchManager!=null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
           /* searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);*/
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }




    private void fetchAllCategoryProducts(int cid) {

        Map<String, Object> map=new HashMap<>();
        map.put("CID", String.valueOf(cid));



        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getCategoryProducts(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){


                    assert response.body() != null;
                    if (response.body().isSTATUS()){

                        //ok success


                        bindDataToAdapter(response.body().getDATA());

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });




    }

    private void bindDataToAdapter(List<DATAItem> data) {










        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(data,this);
        recyclerView.setAdapter(squareMediumRecyclerViewAdapter);

    }

//    private void setRecyclerViewItems(List<DATAItem> dataItemList) {
//
//      // List<AllProductsItem> productsItemList= dataItemList.get(0).getAllProducts();
//       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//       gridRecyclerViewAdapter=new GridRecyclerViewAdapter(dataItemList,this,CID);
//        recyclerView.setAdapter(gridRecyclerViewAdapter);
//
//
//    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

        if(squareMediumRecyclerViewAdapter!=null)
            squareMediumRecyclerViewAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void eat(int pid, ImageView loc, String url, int s) {

        if(s==1){
            new MyPreferences(this).setVerified(true);
            if(new MyPreferences(this).getVerified()){
                view1.setVisibility(View.VISIBLE);
                Glide.with(this).load(url).into(imageView);
                new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);

                if(CartCount!=null)
                    ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);
                addToCartAnimation(loc,eats);
                setCart(pid);
            }else {

                //startActivity(new Intent(MenuActivity.this,LoginActivity.class));
            }
        }else{

            removePID(pid);
            new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()-1);

            if(CartCount!=null)
                ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

        }


    }

    private void addToCartAnimation(View view,View cart){
        // get location of the clicked view
        int[] fromLoc=new int[2];
        view.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

// get location of cart icon
        int[] toLoc = new int[2];
        cart.getLocationOnScreen(toLoc);
        float destX = toLoc[0];
        float destY = toLoc[1];

        AnimationSet animationToLeft = new AnimationSet(true);

        ScaleAnimation scaleToLeftAnimation = new ScaleAnimation(1, (float) 0.8, 1, (float) 0.8);
        scaleToLeftAnimation.setInterpolator(new BounceInterpolator());
        scaleToLeftAnimation.setDuration(200);
        scaleToLeftAnimation.setFillAfter(true);
        animationToLeft.addAnimation(scaleToLeftAnimation);

        TranslateAnimation translateToLeftAnimation = new TranslateAnimation(startX, destX, startY, startY);
        translateToLeftAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateToLeftAnimation.setDuration(200);
        animationToLeft.addAnimation(translateToLeftAnimation);

        AlphaAnimation alpha = new AlphaAnimation(1, (float) 0.8);
        alpha.setDuration(200);
        animationToLeft.addAnimation(alpha);

        final AnimationSet animationToBottom = new AnimationSet(true);
        // animationToBottom.setInterpolator(new BounceInterpolator());

        ScaleAnimation scaleToBottomAnimation = new ScaleAnimation((float) 0.8, (float) 0.1, (float) 0.8, (float) 0.1);
        scaleToBottomAnimation.setDuration(400);
        scaleToBottomAnimation.setFillAfter(true);
        animationToBottom.addAnimation(scaleToBottomAnimation);

        TranslateAnimation translateToBottomAnimation = new TranslateAnimation(destX, destX, startY, destY);
        translateToBottomAnimation.setDuration(800);
        animationToBottom.addAnimation(translateToBottomAnimation);

        AlphaAnimation alphaToBottom = new AlphaAnimation((float) 0.8, (float) 0.6);
        alphaToBottom.setDuration(800);
        animationToBottom.addAnimation(alphaToBottom);

//        animationToLeft.start();
//        animationToBottom.start();


        view1.setAnimation(animationToLeft);
        animationToLeft.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                view1.setAnimation(animationToBottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                view1.setVisibility(View.VISIBLE);
            }
        });



        animationToBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                view1.setVisibility(View.GONE);

//                new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);
//
//                if(CartCount!=null)
//                    ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
    private void setCart(int pid){

        MyPreferences myPreferences=new MyPreferences(this);

        if(myPreferences.getCartProducts()==null){
            myPreferences.setCartProducts(String.valueOf(pid));
        }else {
            myPreferences.setCartProducts(myPreferences.getCartProducts()+","+ pid);
        }
    }

    private void removePID(int pid) {
        MyPreferences myPreferences=new MyPreferences(this);
        String pids=myPreferences.getCartProducts();
        String[] a=pids.split(",");

        for (int i=0;i<a.length;i++){

            if(a[i].equals(String.valueOf(pid)))
            {
                a[i]="0";
                break;
            }

        }
        myPreferences.setCartProducts(null);
        for (int i=0;i<a.length;i++){

            if(!a[i].equals("0"))
            {
                setCart(Integer.parseInt(a[i]));
            }
        }
    }


}
