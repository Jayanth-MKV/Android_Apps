package com.vugido.vidb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vugido.vidb.adapters.MyRecyclerViewAdapter;
import com.vugido.vidb.models.MyImageModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MyImageModel> myImageModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView=findViewById(R.id.recyclerview);
//
//        //we need to create data source...
//        myImageModelList=new ArrayList<>();
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,1));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,2));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,3));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,4));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,5));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,6));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,7));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,8));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,9));
//        myImageModelList.add(new MyImageModel(R.drawable.veg_hakka_noodles,10));
//
//
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        MyRecyclerViewAdapter myRecyclerViewAdapter=new MyRecyclerViewAdapter(this,myImageModelList);
//        recyclerView.setAdapter(myRecyclerViewAdapter);
//





    }
}