package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.fragments.ProductAddOnDialog;
import com.dailyneeds.vugido.fragments.categories_fragment.CategoryFragment;

import java.util.Objects;



public class CategoryActivity extends AppCompatActivity implements ProductAddOnDialog.UpdateCartCount {
    private Toolbar toolbar;
    private  int CID;
    public static final int ORDER_PLACED_CODE = 1;
    private TextView CartCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        toolbar=findViewById(R.id.toolbar);
        // set here all values of toolbar

        String name= Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getString("N");
        CID= Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getInt("ID");
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(view -> finish());




       // fetchData();



        CategoryFragment categoryFragment=new CategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("CID",CID);
        categoryFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.category_fragment, categoryFragment);
                fragmentTransaction.commit();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("okkkkkk","ok");
        if(requestCode==MainActivity.ORDER_PLACED_CODE && resultCode==RESULT_CANCELED){

            Log.e("from category","all ok");

            assert CartCount!=null;
            ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);

        }else if(requestCode==ORDER_PLACED_CODE){


            if(resultCode==1){

                // show the order info activity.. order placed
                // also fetch the cart details
                Log.e("onAres","ok");


               // setResult(ConfigVariables.ORDER_PLACED_RESPONSE_CODE);
                setResult(999);
                finish();

            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hometoolbarmenu,menu);
        // Associate searchable configuration with the SearchView
        final MenuItem cart = menu.findItem(R.id.cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        //ConfigVariables.setupBadge(0,CartCount);
       // view.setOnClickListener(view1 -> onOptionsItemSelected(cart));


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


    @Override
    public void updateCartCount(int Count) {
        assert CartCount!=null;
        new MyPreferences(this).setCartCount(Count);
        ConfigVariables.setupBadge(Count,CartCount);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
