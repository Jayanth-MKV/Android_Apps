package com.vugido.online_groceries.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.product.ProductListRecyclerViewAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.clients_menu.DATA;
import com.vugido.online_groceries.models.clients_menu.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;

public class ClientActivity  extends AppCompatActivity implements ProductListRecyclerViewAdapter.CLICKED_ADD, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private Toolbar toolbar;
    RecyclerView recyclerView;
//    private boolean isOpen=false;
//    FloatingActionButton close;
//    ExtendedFloatingActionButton seeCategories;
//    private CardView menu;
    //ConstraintLayout yourView;
    TextView cc,cp;





    View goCart;

    MyDialogLoader myDialogLoader;
    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity);

        toolbar=findViewById(R.id.include2);
        cc=findViewById(R.id.textView16);
        cp=findViewById(R.id.textView17);
        recyclerView=findViewById(R.id.rcc);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
        goCart=findViewById(R.id.include3);
        goCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClientActivity.this,MyCartActivity.class);
                startActivityForResult(intent,ORDER_PLACED_CODE);
            }
        });
//        seeCategories=findViewById(R.id.ex);
//        crec=findViewById(R.id.cat_recycler_view);
//        close=findViewById(R.id.floatingActionButton);
        toolbar.setTitle(getIntent().getStringExtra("NAME"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());

//        menu=findViewById(R.id.seecatrec);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchProducts);

//        close.setOnClickListener(v -> {
//            circularRevealCard(menu);
//            seeCategories.setVisibility(View.VISIBLE);
//            close.setVisibility(View.GONE);
//        });
//        seeCategories.setOnClickListener(v -> {
//            circularRevealCard(menu);
//            seeCategories.setVisibility(View.GONE);
//            close.setVisibility(View.VISIBLE);
//        });


        fetchProducts();



//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        crec.setLayoutManager(layoutManager);
//        MenuCategoryAdapter menuCategoryAdapter=new MenuCategoryAdapter(menuModelList,this);
//        crec.setAdapter(menuCategoryAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));

        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }

    }

    private void fetchProducts() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("CID",String.valueOf(getIntent().getIntExtra("CID",0)));
       // Log.e("cid value",String.valueOf(getIntent().getIntExtra("CID",0)));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().cid_menu(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isSTATUS()){


                        bindData(response.body().getDATA());


                    }else
                    {

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });




    }

    private void bindData(DATA data) {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProductListRecyclerViewAdapter productListRecyclerViewAdapter=new ProductListRecyclerViewAdapter(data.getFOODS(),this,getIntent().getIntExtra("CID",0));
        recyclerView.setAdapter(productListRecyclerViewAdapter);


    }

//    private void circularRevealCard(View view){
//        float finalRadius = Math.max(view.getWidth(), view.getHeight());
//
//
//        if(!isOpen) {
//
//            int x = view.getRight();
//            int y = view.getBottom();
//
//            int startRadius = 0;
//            int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
//            // create the animator for this view (the start radius is zero)
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
//            //circularReveal.setDuration(300);
//
//            // make the view visible and start the animation
//            view.setVisibility(View.VISIBLE);
//
//            circularReveal.start();
//            circularReveal.addListener(this);
//
//            isOpen=true;
//            seeCategories.setText("Close");
//            seeCategories.setBackgroundColor(Color.WHITE);
//            seeCategories.setTextColor(getResources().getColor(R.color.gradient_start_color));
//        }else {
//            int x = view.getRight();
//            int y = view.getBottom();
//
//            int startRadius =(int) Math.hypot(view.getWidth(), view.getHeight()) ;
//            int endRadius = 0;
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
//
//            circularReveal.start();
//            // view.setVisibility(View.INVISIBLE);
//            isOpen=false;
//
//            circularReveal.addListener(this);
//            seeCategories.setBackgroundColor(getResources().getColor(R.color.mred));
//            seeCategories.setText("Browse Food Menu");
//            seeCategories.setTextColor(Color.WHITE);
//        }
//    }
//
//
//    @Override
//    public void onAnimationStart(Animator animation) {
//
//    }
//
//    @Override
//    public void onAnimationEnd(Animator animation) {
//
//        if(!isOpen){
//            menu.setVisibility(View.INVISIBLE);
//        }else {
//            menu.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void onAnimationCancel(Animator animation) {
//
//    }

//
//    @Override
//    public void menuClicked(int id) {
//
//
//        circularRevealCard(menu);
//        seeCategories.setVisibility(View.VISIBLE);
//        close.setVisibility(View.GONE);
//
//        //now using id navigate to that menu...
//
//        //homePageMainAdapter
//        int p=0;
//        for(int i =0 ;i<homePageMainModelList.size();i++){
//            if(homePageMainModelList.get(i).getHID()==id){
//                p=i;
//                break;
//            }
//        }
//
//        float y = HomeMainRecyclerView.getChildAt(p).getY();
//
//        nestedScrollView.fling(0);
//        nestedScrollView.setSmoothScrollingEnabled(true);
//        nestedScrollView.smoothScrollTo(0, (int) y);
//        //  layoutManager.smoothScrollToPosition(HomeMainRecyclerView,null,3);
//        //HomeMainRecyclerView.smoothScrollToPosition(3);
//    }
//    @Override
//    public void onAnimationRepeat(Animator animation) {
//
//    }




    @Override
    public void onRefresh() {
        fetchProducts();
    }

    @Override
    public void clicked_add(int cid,int pid,int price) {

        String x=new MyPreferences(this).getCartProducts();

        if (x==null)
            x=cid+","+pid;
        else
            x=x+";"+cid+","+pid;

        new MyPreferences(this).setCartProducts(x);

        new MyPreferences(this).setCartCount(x.split(";").length);

        new MyPreferences(this).setCartPrice(new MyPreferences(this).getCartPrice()+price);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));

    }
}
