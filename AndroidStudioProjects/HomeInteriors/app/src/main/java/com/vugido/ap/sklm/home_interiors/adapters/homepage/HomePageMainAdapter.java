package com.vugido.ap.sklm.home_interiors.adapters.homepage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.vugido.ap.sklm.home_interiors.R;
import com.vugido.ap.sklm.home_interiors.designs.Space;
import com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.HomePageMainModel;

import java.util.List;

import static com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.HomePageMainModel.SQUARE_MEDIUM_VIEW;


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
                MySquareMedium mySquareMedium=new MySquareMedium(view);
                mySquareMedium.SquareMediumRecyclerView.addItemDecoration(new Space(2,5,false,0));
                return mySquareMedium;
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

//            case CIRCULAR_VIEW:
//               GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                   @Override
//                   public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                       ((MyCircularView)holder).BG.setBackground(resource);
//
//                   }
//
//                   @Override
//                   public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                   }
//               });
//
//               ((MyCircularView)holder).TITLE.setText(homePageMainModel.getTITLE());
//                ((MyCircularView)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
//                ((MyCircularView)holder).recyclerView.setRecycledViewPool(CircularRecycledViewPool);
//                ((MyCircularView)holder).recyclerView.setHasFixedSize(true);
//                List<CircularViewModel> circularViewModelList=homePageMainModel.getCircularViewModelList();
//                ((MyCircularView)holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//                CircularViewRecyclerAdapter circularViewRecyclerAdapter=new CircularViewRecyclerAdapter(context,circularViewModelList);
//                ((MyCircularView)holder).recyclerView.setAdapter(circularViewRecyclerAdapter);
//                break;
//            case CHIP_RECTANGULAR_VIEW:
//                GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((MyChipRectangularView)holder).BG.setBackground(resource);
//
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                } );
//                ((MyChipRectangularView)holder).TITLE.setText(homePageMainModel.getTITLE());
//                ((MyChipRectangularView)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
//                ((MyChipRectangularView)holder).ChipRectangularRecyclerView.setRecycledViewPool(ChipPool);
//                ((MyChipRectangularView)holder).ChipRectangularRecyclerView.setHasFixedSize(true);
//                ((MyChipRectangularView)holder).ChipRectangularRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//                ChipRecatangularRecyclerViewAdapter chipRecatangularRecyclerViewAdapter=new ChipRecatangularRecyclerViewAdapter(homePageMainModel.getChipsRectangluarModelList(),context);
//                ((MyChipRectangularView)holder).ChipRectangularRecyclerView.setAdapter(chipRecatangularRecyclerViewAdapter);
//                break;
//            case LIST_VIEW:
//                ((MyListView)holder).ListRecyclerView.setRecycledViewPool(ListPool);
//                ((MyListView)holder).ListRecyclerView.setHasFixedSize(true);
//                ((MyListView)holder).ListRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
//                ListProductsRecyclerViewAdapter listProductsRecyclerViewAdapter=new ListProductsRecyclerViewAdapter(context,homePageMainModel.getListProductsViewModelList());
//                ((MyListView)holder).ListRecyclerView.setAdapter(listProductsRecyclerViewAdapter);
//                break;
//            case MEDIUM_RECTANGULAR_VIEW:
//                GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((MyMediumRectangularView)holder).BG.setBackground(resource);
//
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
//                ((MyMediumRectangularView)holder).TITLE.setText(homePageMainModel.getTITLE());
//                ((MyMediumRectangularView)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
//                ((MyMediumRectangularView)holder).RectangularMediumRecyclerView.setRecycledViewPool(MediumRectPool);
//                ((MyMediumRectangularView)holder).RectangularMediumRecyclerView.setHasFixedSize(true);
//                ((MyMediumRectangularView)holder).RectangularMediumRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//                RectangularMediumRecyclerViewAdapter rectangularMediumRecyclerViewAdapter=new RectangularMediumRecyclerViewAdapter(homePageMainModel.getRectangularMediumViewModelList(),context);
//                ((MyMediumRectangularView)holder).RectangularMediumRecyclerView.setAdapter(rectangularMediumRecyclerViewAdapter);
//                break;
//            case SLIDER_VIEW:
//                GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((MySliderView)holder).BG.setBackground(resource);
//
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
//                ((MySliderView)holder).setSliderAdapter(homePageMainModel.getSliderViewModelList(),homePageMainModel.getHID());
//                break;
            case SQUARE_MEDIUM_VIEW:
//                GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((MySquareMedium)holder).BG.setBackground(resource);
//
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
                 ((MySquareMedium)holder).TITLE.setText(homePageMainModel.getTITLE());
                //((MySquareMedium)holder).SquareMediumRecyclerView.setNestedScrollingEnabled(false);
                 ((MySquareMedium)holder).SquareMediumRecyclerView.setRecycledViewPool(SquareMediumPool);
                 ((MySquareMedium)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
                 //((MySquareMedium)holder).SquareMediumRecyclerView.setHasFixedSize(true);
               // ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));

                GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);

                gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(gridLayoutManager);
                ((MySquareMedium)holder).SquareMediumRecyclerView.addItemDecoration(new Space(2,10,true,0));
                SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(homePageMainModel.getSquareMediumModelList(),context,homePageMainModel.getCategoriesItemList());
                ((MySquareMedium)holder).SquareMediumRecyclerView.setAdapter(squareMediumRecyclerViewAdapter);
                break;
//            case SQUARE_MINI:
//                GlideApp.with(context).load(homePageMainModel.getBG_COLOR()).into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((MySquareMiniView)holder).BG.setBackground(resource);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                } );
//                ((MySquareMiniView)holder).TITLE.setText(homePageMainModel.getTITLE());
//                ((MySquareMiniView)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
//                ((MySquareMiniView)holder).SquareMiniRecyclerView.setRecycledViewPool(SquareMiniPool);
//                ((MySquareMiniView)holder).SquareMiniRecyclerView.setHasFixedSize(true);
//                ((MySquareMiniView)holder).SquareMiniRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
//                SquareMiniRecyclerViewAdapter squareMiniRecyclerViewAdapter=new SquareMiniRecyclerViewAdapter(homePageMainModel.getSquareMiniModelList(),context);
//                ((MySquareMiniView)holder).SquareMiniRecyclerView.setAdapter(squareMiniRecyclerViewAdapter);
//                break;
            default:
                return ;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageMainModelList.get(position).getTYPE()){
//            case 1:
//                return CIRCULAR_VIEW;
//            case 2:
//                return CHIP_RECTANGULAR_VIEW;
//            case 3:
//                return LIST_VIEW;
//            case 4:
//                return MEDIUM_RECTANGULAR_VIEW;
//            case 5:
//                return SLIDER_VIEW;
            case 6:
                return SQUARE_MEDIUM_VIEW;
//            case 7:
//                return SQUARE_MINI;
            default:
                return -1;

        }
    }

    @Override
    public int getItemCount() {
        return homePageMainModelList.size();
    }

    ////////////// ALL MODEL CLASSES ...


//    // CIRCULAR VIEW TYPE....
//     static class MyCircularView extends RecyclerView.ViewHolder{
//
//
//        RecyclerView recyclerView;
//        ConstraintLayout BG;
//        TextView TITLE;
//        public MyCircularView(@NonNull View itemView) {
//            super(itemView);
//            recyclerView=itemView.findViewById(R.id.horizontal_recycler_view);
//            BG=itemView.findViewById(R.id.circular_bg);
//            TITLE=itemView.findViewById(R.id.title);
//
//
//        }
//    }
//
//    // CHIP RECTANGULAR VIEW....
//    static class MyChipRectangularView extends RecyclerView.ViewHolder{
//
//        RecyclerView ChipRectangularRecyclerView;
//        ConstraintLayout BG;
//        TextView TITLE;
//        public MyChipRectangularView(@NonNull View itemView) {
//            super(itemView);
//            ChipRectangularRecyclerView=itemView.findViewById(R.id.rect_chip_horizontal_recycler_view);
//            BG=itemView.findViewById(R.id.bg_rect_chips);
//            TITLE=itemView.findViewById(R.id.title);
//
//        }
//    }
//    // LIST VIEW
//    static class MyListView extends RecyclerView.ViewHolder{
//
//
//        RecyclerView ListRecyclerView;
//
//        public MyListView(@NonNull View itemView) {
//            super(itemView);
//            ListRecyclerView =itemView.findViewById(R.id.list_recycler_view);
//        }
//
//    }
//
//    // MEDIUM RECTANGULAR VIEW
//     static class MyMediumRectangularView extends RecyclerView.ViewHolder{
//
//
//        RecyclerView RectangularMediumRecyclerView;
//        ConstraintLayout BG;
//        TextView TITLE;
//        public MyMediumRectangularView(@NonNull View itemView) {
//            super(itemView);
//            TITLE=itemView.findViewById(R.id.title);
//            BG=itemView.findViewById(R.id.bg_med_rect);
//            RectangularMediumRecyclerView=itemView.findViewById(R.id.horizontal_recycler_view_rectangular_medium);
//        }
//    }

    // SQUARE MEDIUM VIEW
     static class MySquareMedium extends RecyclerView.ViewHolder{


    RecyclerView SquareMediumRecyclerView;
    ConstraintLayout BG;
    TextView TITLE;
        public MySquareMedium(@NonNull View itemView) {
            super(itemView);
            BG=itemView.findViewById(R.id.bg_med_sq);
            TITLE=itemView.findViewById(R.id.title);
            SquareMediumRecyclerView=itemView.findViewById(R.id.horizontal_recycler_view_square_medium);


        }
    }
//
//    // SQUARE MINI VIEW
//    static class MySquareMiniView extends RecyclerView.ViewHolder{
//
//
//        RecyclerView SquareMiniRecyclerView;
//        ConstraintLayout BG;
//        TextView TITLE;
//        public MySquareMiniView(@NonNull View itemView) {
//            super(itemView);
//            BG=itemView.findViewById(R.id.bg_mini_sq);
//            TITLE=itemView.findViewById(R.id.title);
//            SquareMiniRecyclerView=itemView.findViewById(R.id.mini_square_grid_recycler_view);
//
//        }
//
//
//    }
//
//    public class MySliderView extends RecyclerView.ViewHolder{
//
//        private ViewPager viewPager;
//        ConstraintLayout BG;
//        public MySliderView(@NonNull View view) {
//            super(view);
//            viewPager=view.findViewById(R.id.view_pager_banner);
//            BG=view.findViewById(R.id.bg_slider);
//
//            //setSliderAdapter();
//        }
//
//        private void setSliderAdapter(List<SliderViewModel> sliderViewModelList,int hid){
//            SliderViewAdapter sliderViewAdapter=new SliderViewAdapter(sliderViewModelList,context,hid);
//            viewPager.setClipToPadding(false);
//            viewPager.setPageMargin(10);
//            viewPager.setAdapter(sliderViewAdapter);
//
//        }
//    }
}
