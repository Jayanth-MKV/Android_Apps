package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.models.GridCategoryModel;
import com.foodhub.vugido.models.HomePageModel;
import com.foodhub.vugido.models.HorizontalCategoryModel;

import java.util.List;
/*
import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.BANNER_SLIDER;
import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.GRID_LAYOUT_MODEL;
import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.HORIZONTAL_LAYOUT_MODEL;
import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.STRIP_AD_BANNER;

*/
public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;
    private InvokeCategoriesfragment invokeCategoriesfragment;
    private Context context;

    public HomePageRecyclerViewAdapter(List<HomePageModel> homePageModelList,Context context) {
        this.homePageModelList = homePageModelList;
        this.context=context;
        invokeCategoriesfragment= (InvokeCategoriesfragment) context;
    }


    @Override
    public int getItemViewType(int position) {

        switch (homePageModelList.get(position).getTYPE()){

         /*   case 0:
                return BANNER_SLIDER;
            case 1:
                return STRIP_AD_BANNER;
            case 2:
                return GRID_LAYOUT_MODEL;
            case 3:
                return HORIZONTAL_LAYOUT_MODEL;
            default:
                return -1;
*/

        }
        return -1;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // the view type will be returned from the getItemViewTypeMethod
       /* switch (viewType){

            case BANNER_SLIDER:
               View SliderBannerView=LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_banner, parent, false);


                //return  new BannerSliderViewHolder(SliderBannerView);
            case STRIP_AD_BANNER:
               View StripAdView=LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);

               return new StripAdViewHolder(StripAdView);
            case GRID_LAYOUT_MODEL:
                View GridView=LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);

                return new MyGridViewHolder(GridView);
            case HORIZONTAL_LAYOUT_MODEL:
                View HorizontalView=LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

                return new HorizontalViewHolder(HorizontalView);
        }*/
        return null;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homePageModelList.get(position).getTYPE()){
         /*   case BANNER_SLIDER:
                List<SliderModel> sliderModelList=homePageModelList.get(position).getSliderModelList();
              //  ((BannerSliderViewHolder)holder).setBannerSliderViewPager(sliderModelList);
                break;
            case STRIP_AD_BANNER:
                ((StripAdViewHolder)holder).StripAdContainer.setBackgroundColor(context.getResources().getColor(R.color.gradient_start_color));

               Glide.with(context)
                        .load(homePageModelList.get(position).getResource())
                        .into(((StripAdViewHolder)holder).StripAdImage);
               // ((StripAdViewHolder)holder).StripAdImage.setImageResource(homePageModelList.get(position).getResource());
                break;
            case GRID_LAYOUT_MODEL:
                List<GridCategoryModel> gridCategoryModelList=homePageModelList.get(position).getGridCategoryModelList();

                ((MyGridViewHolder)holder).GridCategoryTitle.setText(homePageModelList.get(position).getGridTitle());
                ((MyGridViewHolder)holder).setGridLayoutData(gridCategoryModelList);

                break;
            case HORIZONTAL_LAYOUT_MODEL:
                List<HorizontalCategoryModel> horizontalCategoryModelList=homePageModelList.get(position).getHorizontalCategoryModelList();
                ((HorizontalViewHolder)holder).setHorizontalRecyclerViewAdapter(horizontalCategoryModelList);
                break;
            default:
                return;*/

        }




    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }









    public class HorizontalViewHolder extends RecyclerView.ViewHolder{


        RecyclerView recyclerView;
       // Button ViewAllProducts;
        TextView ProductCategoryTitle;

        HorizontalViewHolder(@NonNull final View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.grid_recycler_view);
           // ViewAllProducts=itemView.findViewById(R.id.grid_button);
            ProductCategoryTitle=itemView.findViewById(R.id.grid_category_title);


            /*ViewAllProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    invokeCategoriesfragment.invokeCategoriesFragment();

                }
            });*/

        }

        private void setHorizontalRecyclerViewAdapter(List<HorizontalCategoryModel> horizontalCategoryModelList){

           /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter=new HorizontalRecyclerViewAdapter(horizontalCategoryModelList,context);
            recyclerView.setAdapter(horizontalRecyclerViewAdapter);*/
        }
    }


    public class StripAdViewHolder extends RecyclerView.ViewHolder{


        private ConstraintLayout StripAdContainer;
        private ImageView StripAdImage;


        StripAdViewHolder(@NonNull View itemView) {
            super(itemView);

            StripAdContainer=itemView.findViewById(R.id.strip_ad_container);
            StripAdImage=itemView.findViewById(R.id.strip_ad_image);

        }
    }


   /* public  class  BannerSliderViewHolder extends RecyclerView.ViewHolder{


        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        private final long DELAY_TIME = 2000;
        private final long PERIOD_TIME = 2000;



        BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager=itemView.findViewById(R.id.view_pager_banner);


        }


        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){


            SliderAdapter sliderAdapter=new SliderAdapter(sliderModelList,context);

            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);
            bannerSliderViewPager.setCurrentItem(currentPage);


            bannerSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    currentPage=position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                    if(state==ViewPager.SCROLL_STATE_IDLE){
                        pageLooper(sliderModelList);
                    }
                }
            });


            startBannerSlideShow(sliderModelList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(sliderModelList);
                    stopBannerSlideShow();
                    if(event.getAction()== MotionEvent.ACTION_UP){

                        startBannerSlideShow(sliderModelList);

                    }
                    return false;
                }
            });

            bannerSliderViewPager.setAdapter(sliderAdapter);
        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList){

            final Handler handler=new Handler();
            final Runnable update=new Runnable() {
                @Override
                public void run() {
                    if(currentPage >= sliderModelList.size()){
                        currentPage=1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++);

                }
            };

            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            },DELAY_TIME,PERIOD_TIME);


        }

        private void stopBannerSlideShow(){

            timer.cancel();

        }

        private void pageLooper(List<SliderModel> sliderModelList){

            if(currentPage== sliderModelList.size()-2){

                currentPage=2;
                bannerSliderViewPager.setCurrentItem(currentPage,false);


            }
            if(currentPage== 1){

                currentPage=sliderModelList.size()-3;
                bannerSliderViewPager.setCurrentItem(currentPage,false);

            }


        }
    }*/


    public class MyGridViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        Button GridViewAllButton;
        TextView GridCategoryTitle;



        MyGridViewHolder(@NonNull final View itemView) {
            super(itemView);

            recyclerView=itemView.findViewById(R.id.products_grid_recycler_view);
            GridViewAllButton=itemView.findViewById(R.id.product_category_view_all);
            GridCategoryTitle=itemView.findViewById(R.id.product_category_title);





        }



        private void setGridLayoutData(List<GridCategoryModel> gridCategoryModelList){


           /* GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            GridRecyclerViewAdapter gridRecyclerViewAdapter=new GridRecyclerViewAdapter(gridCategoryModelList,context);
            recyclerView.setAdapter(gridRecyclerViewAdapter);*/






        }
    }


    public  interface InvokeCategoriesfragment{

        void  invokeCategoriesFragment();
    }

}
