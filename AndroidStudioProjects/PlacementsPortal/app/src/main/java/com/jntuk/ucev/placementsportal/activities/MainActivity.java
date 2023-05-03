package com.jntuk.ucev.placementsportal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.adapters.home.HomePageMainAdapter;
import com.jntuk.ucev.placementsportal.designs.Space;
import com.jntuk.ucev.placementsportal.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.jntuk.ucev.placementsportal.models.home.HomePageMainModel;
import com.jntuk.ucev.placementsportal.models.home.HomePostsModel;
import com.jntuk.ucev.placementsportal.models.home.HomeProjectsPostModel;
import com.jntuk.ucev.placementsportal.models.home.StudentsPlaced;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener {


    Toolbar toolbar;
    BottomAppBar bottomAppBar;
    FloatingActionButton floatingActionButton;

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    HomePageMainAdapter homePageMainAdapter;
    List<HomePageMainModel> homePageMainModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=findViewById(R.id.activity_toolbar);
        recyclerView=findViewById(R.id.home_recyclerView);

        toolbar.setTitle("App Name");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));

        setSupportActionBar(toolbar);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBS");
            // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        });


        bottomAppBar.setOnMenuItemClickListener(this);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ActivityChats.class));
                //startActivityForResult(new Intent(MainActivity.this,SearchActivity.class),1);
            }
        });



        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..




        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(false);

                // get the data here
                //fetchHomePageData();
                //fetchMenu();

            }
        });

        loadData();

    }

    private void loadData() {
        homePageMainModelList=new ArrayList<>();

        HomePostsModel h1=new HomePostsModel(1,R.drawable.quizz,"Python Coding Challenge","Department Of  Computer Science JNTUK-UCEV is going to conduct the coding challenge on Python, those who are interested can join now and win prizes.","","Participate");

        homePageMainModelList.add(HomePageMainModel.createHomePostsModelPost(HomePageMainModel.HOME_POSTS,h1,"Coding Challenge",4,"","",""));



        List<StudentsPlaced> inCse=new ArrayList<>();
        inCse.add(new StudentsPlaced(1,R.drawable.govind,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));
        inCse.add(new StudentsPlaced(1,R.drawable.prathyusha,"Prathyusha","Btech CSE","20lpa","Google"));
        inCse.add(new StudentsPlaced(1,R.drawable.siddarth,"K.Siddhartha","Btech CSE","20lpa","Microsoft"));
        inCse.add(new StudentsPlaced(1,R.drawable.swathi,"T.Swathi","Btech CSE","20lpa","Amazon"));
//        inCse.add(new StudentsPlaced(1,R.drawable.me,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));
//        inCse.add(new StudentsPlaced(1,R.drawable.me,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));


        HomeProjectsPostModel homeProjectsPostModel=new HomeProjectsPostModel(1,R.drawable.prathyusha,R.drawable.cnn_image,"Prathyusha","B-tech Cse 3rd year","Image Classification Using CNN","In this project I did classification of flowers image dataset using Artificial Neural Networks");

        homePageMainModelList.add(HomePageMainModel.createHomeProjectsModelPost(HomePageMainModel.HOME_PROJECT_POST,homeProjectsPostModel,6));

        HomePostsModel h2=new HomePostsModel(2,R.drawable.legac_geek,"Legacy Geeks Quiz","Department Of  Computer Science JNTUK-UCEV is going to conduct the coding challenge on Python, those who are interested can join now and win prizes.","","Register Now");


        List<StudentsPlaced> top=new ArrayList<>();
        top.add(new StudentsPlaced(1,R.drawable.swathi,"T.Swathi","Btech CSE","20lpa","Google"));
        top.add(new StudentsPlaced(1,R.drawable.sameer,"M.Sameer","Btech CSE","20lpa","Amazon"));
        top.add(new StudentsPlaced(1,R.drawable.nandini,"Nandini","Btech CSE","20lpa","Facebook"));
        top.add(new StudentsPlaced(1,R.drawable.prathyusha,"Prathyusha","Btech CSE","20lpa","Apple"));
//        top.add(new StudentsPlaced(1,R.drawable.me,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));
//        top.add(new StudentsPlaced(1,R.drawable.me,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));


        List<StudentsPlaced> t3=new ArrayList<>();
        t3.add(new StudentsPlaced(1,R.drawable.me,"Vurjana Gideon","Btech CSE","20lpa","Amazon"));
        t3.add(new StudentsPlaced(1,R.drawable.swathi,"T.Swathi","Btech CSE","20lpa","Google"));
        t3.add(new StudentsPlaced(1,R.drawable.sameer,"M.Sameer","Btech CSE","20lpa","Amazon"));
        t3.add(new StudentsPlaced(1,R.drawable.nandini,"Nandini","Btech CSE","20lpa","Facebook"));
        t3.add(new StudentsPlaced(1,R.drawable.prathyusha,"Prathyusha","Btech CSE","20lpa","Apple"));



        homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(HomePageMainModel.SQUARE_MEDIUM_VIEW,top,"Hired In Top Companies",1,"","",""));

        HomeProjectsPostModel homeProjectsPostModel1=new HomeProjectsPostModel(1,R.drawable.govind,R.drawable.fp,"Gideon Vurjana","B-tech Cse 2rd year","Gender Classification Using CNN","In this project I did classification of genders using fingerprints image dataset using Artificial Neural Networks");

        homePageMainModelList.add(HomePageMainModel.createHomeProjectsModelPost(HomePageMainModel.HOME_PROJECT_POST,homeProjectsPostModel1,7));

        homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(HomePageMainModel.SQUARE_MEDIUM_VIEW,inCse,"Placements In Cse",2,"","",""));
        homePageMainModelList.add(HomePageMainModel.createHomePostsModelPost(HomePageMainModel.HOME_POSTS,h2,"Quiz",5,"","",""));

        homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(HomePageMainModel.SQUARE_MEDIUM_VIEW,t3,"Placements In Cse",2,"","",""));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (homePageMainAdapter==null)
            homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homePageMainAdapter);
        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.addItemDecoration(new Space(homePageMainModelList.size(),10,false,0));



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
         swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId()==R.id.jobs)
        {
            startActivity(new Intent(MainActivity.this,JobsActivity.class));
        }else if (item.getItemId()==R.id.training){
            startActivity(new Intent(MainActivity.this,TrainingActivity.class));
        }

        return true;
    }
}