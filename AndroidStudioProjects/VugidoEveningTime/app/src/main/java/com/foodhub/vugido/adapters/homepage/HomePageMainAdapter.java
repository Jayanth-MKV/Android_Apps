package com.foodhub.vugido.adapters.homepage;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.design.Space;
import com.foodhub.vugido.models.homepage.CategoryModel;
import com.foodhub.vugido.models.homepage.HomePageMainModel;
import com.foodhub.vugido.models.homepage.HomePostsModel;
import com.foodhub.vugido.models.homepage.SliderModel;
import com.foodhub.vugido.models.product.ProductModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import static com.foodhub.vugido.models.homepage.HomePageMainModel.CATEGORY_VIEW;
import static com.foodhub.vugido.models.homepage.HomePageMainModel.GRID_VIEW;
import static com.foodhub.vugido.models.homepage.HomePageMainModel.HOME_POSTS;
import static com.foodhub.vugido.models.homepage.HomePageMainModel.SLIDER_VIEW;


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
  //      SquareMediumPool=             new RecyclerView.RecycledViewPool();
//        ListPool=                     new RecyclerView.RecycledViewPool();

        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType){

            case SLIDER_VIEW:
                view=LayoutInflater.from(context).inflate(R.layout.offers_slider,parent,false);
                MySliderView mySliderView=new MySliderView(view);
                mySliderView.viewPager.setClipToPadding(false);
                mySliderView.viewPager.setPageMargin(10);
                mySliderView.autoSlider();
                return mySliderView;

            case CATEGORY_VIEW:
                view= LayoutInflater.from(context).inflate(R.layout.categories_layout,parent,false);
                MyCategoryView mySquareMiniView=new MyCategoryView(view);
                GridLayoutManager gridLayoutManager =new GridLayoutManager(context,4);
                mySquareMiniView.recyclerView.setLayoutManager(gridLayoutManager);
                mySquareMiniView.recyclerView.addItemDecoration(new Space(4,10,false,0));
                return mySquareMiniView;
            case HOME_POSTS:
                view= LayoutInflater.from(context).inflate(R.layout.home_posts_design,parent,false);
                return new HomePostsViewHolder(view);
            case GRID_VIEW:
                view=LayoutInflater.from(context).inflate(R.layout.sectioned_products_layout,parent,false);
                MyGridSectionView myGridSectionView=new MyGridSectionView(view);
                GridLayoutManager gridLayoutManager1=new GridLayoutManager(context,2);
                myGridSectionView.recyclerView.setLayoutManager(gridLayoutManager1);
                myGridSectionView.recyclerView.addItemDecoration(new Space(2,10,true,0));
                return myGridSectionView;
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        HomePageMainModel homePageMainModel=homePageMainModelList.get(position);

        switch (homePageMainModel.getTYPE()){

            case HOME_POSTS:
                HomePostsModel squareMediumModel=homePageMainModelList.get(position).getHomePostsModel();
                ((HomePostsViewHolder)holder).setPostsAdapter(squareMediumModel,context);
                break;
            case SLIDER_VIEW:
                ((MySliderView)holder).setSliderAdapter(homePageMainModel.getSliderItemList(),homePageMainModel.getHID(),context);
                break;
            case CATEGORY_VIEW:
                ((MyCategoryView)holder).setCategoriesAdapter(context,homePageMainModel.getCategoriesItemList());
                break;
            case GRID_VIEW:
                ((MyGridSectionView)holder).setGridProductsAdapter(context,homePageMainModel.getProductModelList());
            default:
                return ;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageMainModelList.get(position).getTYPE()){


            case SLIDER_VIEW:
                return SLIDER_VIEW;
            case CATEGORY_VIEW:
                return CATEGORY_VIEW;
            case HOME_POSTS:
                return  HOME_POSTS;
            case GRID_VIEW:
                return GRID_VIEW;

            default:
                return -1;

        }
    }

    @Override
    public int getItemCount() {
        return homePageMainModelList.size();
    }


    static class MyGridSectionView extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public MyGridSectionView(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.grid_recycler_view);

        }

        private void setGridProductsAdapter(Context context, List<ProductModel> productModelList){

//            ProductsAdapter productsAdapter=new ProductsAdapter(productModelList,context,1);
//            recyclerView.setAdapter(productsAdapter);
//            recyclerView.setNestedScrollingEnabled(false);
        }
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

    //CATEGORY VIEW
    static class MyCategoryView extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;




        public MyCategoryView(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.grid_recycler_view);
        }

        private void setCategoriesAdapter(Context context, List<CategoryModel> categoryModelList){

            CategoryRecyclerViewAdapter categoryRecyclerViewAdapter=new CategoryRecyclerViewAdapter(categoryModelList,context,"","");
            recyclerView.setAdapter(categoryRecyclerViewAdapter);
            recyclerView.setNestedScrollingEnabled(false);

        }


    }



    //SLIDER VIEW..
    static class MySliderView extends RecyclerView.ViewHolder{
        int current_position = 0;
        int tv_size = 0;

        ViewPager viewPager;
        TabLayout tabLayout;
        public MySliderView(@NonNull View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.offer_viewpager);
            tabLayout=itemView.findViewById(R.id.top_tab_indicator);
        }

        private void setSliderAdapter(List<SliderModel> sliderViewModelList, int hid,Context context){
//            SliderAdapter sliderViewAdapter=new SliderAdapter(sliderViewModelList,context,hid);
//            tabLayout.setupWithViewPager(viewPager);
//            viewPager.setAdapter(sliderViewAdapter);
//
//            tv_size=sliderViewModelList.size();
        }

        private void autoSlider () {
            final Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    int numPages = tv_size;
                    current_position = (current_position + 1) % numPages;
                    viewPager.setCurrentItem(current_position, true);
                    handler.postDelayed(this, 3000);
                }
            };

            handler.post(runnable);
        }


    }

    static class HomePostsViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView imageView;


        public HomePostsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.grid_category_image);
          //  constraintLayout=itemView.findViewById(R.id.category_item);



        }

        public void setPostsAdapter(HomePostsModel squareMediumModel, Context context) {
            Glide.with(context).load(squareMediumModel.getImage()).into(imageView);
        }
    }

}
