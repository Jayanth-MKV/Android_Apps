package com.foodhub.vugido.adapters.homepage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.foodhub.vugido.R;
import com.foodhub.vugido.design.Space;
import com.foodhub.vugido.models.homepage.HomePageMainModel;

import java.util.List;

import static com.foodhub.vugido.models.homepage.HomePageMainModel.SQUARE_MEDIUM_VIEW;


public class HomePageMainAdapter extends Adapter {


    private List<HomePageMainModel> homePageMainModelList;
    private Context context;
//    private RecyclerView.RecycledViewPool
//            CircularRecycledViewPool,ChipPool,MediumRectPool,SquareMiniPool,ListPool;
    //recycledViewPool,
    private RecyclerView.RecycledViewPool SquareMediumPool;

    public HomePageMainAdapter(List<HomePageMainModel> homePageMainModelList, Context context) {
        this.homePageMainModelList = homePageMainModelList;
        //recycledViewPool=new RecyclerView.RecycledViewPool();
//        CircularRecycledViewPool=     new RecyclerView.RecycledViewPool();
//        ChipPool=                     new RecyclerView.RecycledViewPool();
//        MediumRectPool=               new RecyclerView.RecycledViewPool();
//        SquareMiniPool=               new RecyclerView.RecycledViewPool();
        SquareMediumPool=             new RecyclerView.RecycledViewPool();
//        ListPool=                     new RecyclerView.RecycledViewPool();

        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType){
//            case CIRCULAR_VIEW:
//                view= LayoutInflater.from(context).inflate(R.layout.circular_horizontal_recycler_view,parent,false);
//                return new MyCircularView(view);
//            case CHIP_RECTANGULAR_VIEW:
//                view= LayoutInflater.from(context).inflate(R.layout.rectangular_chips_horizontal_recycler_view,parent,false);
//                return new MyChipRectangularView(view);
//            case LIST_VIEW:
//                view= LayoutInflater.from(context).inflate(R.layout.list_products_recycler_view,parent,false);
//                MyListView myListView=new MyListView(view);
//                myListView.ListRecyclerView.addItemDecoration(new Space(1,10,true,0));
//                return myListView;
//            case MEDIUM_RECTANGULAR_VIEW:
//                view= LayoutInflater.from(context).inflate(R.layout.rectangular_medium_recycler_view,parent,false);
//                return new MyMediumRectangularView(view);
//            case SLIDER_VIEW:
//                view=LayoutInflater.from(context).inflate(R.layout.viewpager_slider,parent,false);
//                return new MySliderView(view);
            case SQUARE_MEDIUM_VIEW:
                view= LayoutInflater.from(context).inflate(R.layout.square_medium_recycler_view,parent,false);
                //mySquareMedium.SquareMediumRecyclerView.addItemDecoration(new Space(2,10,true,0));
                return new MySquareMedium(view);
//            case SQUARE_MINI:
//                view= LayoutInflater.from(context).inflate(R.layout.mini_square_grid_view,parent,false);
//                MySquareMiniView mySquareMiniView=new MySquareMiniView(view);
//                mySquareMiniView.SquareMiniRecyclerView.addItemDecoration(new Space(3,10,true,0));
//                return mySquareMiniView;
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        HomePageMainModel homePageMainModel=homePageMainModelList.get(position);

        switch (homePageMainModel.getTYPE()){


            case SQUARE_MEDIUM_VIEW:
                 ((MySquareMedium)holder).TITLE.setText(homePageMainModel.getTITLE());
                //((MySquareMedium)holder).SquareMediumRecyclerView.setNestedScrollingEnabled(false);
                 ((MySquareMedium)holder).SquareMediumRecyclerView.setRecycledViewPool(SquareMediumPool);
                 ((MySquareMedium)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
                 //((MySquareMedium)holder).SquareMediumRecyclerView.setHasFixedSize(true);
               // ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));

                LinearLayoutManager gridLayoutManager=new LinearLayoutManager(context);
                gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                ((MySquareMedium)holder).des.setText(homePageMainModel.getDes());
                ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(gridLayoutManager);
               // ((MySquareMedium)holder).SquareMediumRecyclerView.addItemDecoration(new Space(1,10,true,0));
                SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(homePageMainModel.getSquareMediumModelList(),context,homePageMainModel.getCategoriesItemList());
                ((MySquareMedium)holder).SquareMediumRecyclerView.setAdapter(squareMediumRecyclerViewAdapter);
                break;
            default:
                return ;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageMainModelList.get(position).getTYPE()){

            case 6:
                return SQUARE_MEDIUM_VIEW;

            default:
                return -1;

        }
    }

    @Override
    public int getItemCount() {
        return homePageMainModelList.size();
    }


    // SQUARE MEDIUM VIEW
     static class MySquareMedium extends RecyclerView.ViewHolder{


    RecyclerView SquareMediumRecyclerView;
    ConstraintLayout BG;
    TextView TITLE,des;
        public MySquareMedium(@NonNull View itemView) {
            super(itemView);
            BG=itemView.findViewById(R.id.bg_med_sq);
            TITLE=itemView.findViewById(R.id.title);
            des=itemView.findViewById(R.id.textView12);
            SquareMediumRecyclerView=itemView.findViewById(R.id.horizontal_recycler_view_square_medium);


        }
    }
}
