package com.vugido.vpm.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.vpm.R;

import java.util.HashMap;

public class OrderedItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);
        recyclerView=findViewById(R.id.ordered_items_recycler_view);
        toolbar=findViewById(R.id.activity_toolbar);

        toolbar.setTitle("Ordered Items");


        setSupportActionBar(toolbar);


       /* toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });*/



        int OID= getIntent().getIntExtra("OID",0);


        fetchOrderedItems(OID);



    }

    private void fetchOrderedItems(int oid) {


        Log.e("OID",String.valueOf(oid));
        HashMap<String, Object> request = new HashMap<>();
        request.put("OID", String.valueOf(oid));

        Call<OrderedItems> response= RetrofitBuilder.getInstance().getRetrofit().getOrderedItems(request);

        response.enqueue(new Callback<OrderedItems>() {
            @Override
            public void onResponse(@NonNull  Call<OrderedItems> call, @NonNull Response<OrderedItems> response) {


                Log.e("res",response.toString());

                if(response.isSuccessful()){


                    assert response.body() != null;
                    if(response.body().isStatus()){

                        //ok data got


                        bindDataToAdapter(response.body().getData());

                    }else {
                        // no data



                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<OrderedItems> call, @NonNull Throwable t) {

            }
        });




    }

    private void bindDataToAdapter(List<DataItem> data) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        OrderedItemsAdapter orderedItemsAdapter=new OrderedItemsAdapter(this,data);
        recyclerView.setAdapter(orderedItemsAdapter);


    }


}
