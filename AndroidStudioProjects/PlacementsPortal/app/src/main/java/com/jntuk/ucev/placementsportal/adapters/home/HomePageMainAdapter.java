package com.jntuk.ucev.placementsportal.adapters.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.designs.Space;
import com.jntuk.ucev.placementsportal.models.home.HomePageMainModel;
import com.jntuk.ucev.placementsportal.models.home.HomePostsModel;
import com.jntuk.ucev.placementsportal.models.home.HomeProjectsPostModel;

import java.util.List;

import static com.jntuk.ucev.placementsportal.models.home.HomePageMainModel.HOME_POSTS;
import static com.jntuk.ucev.placementsportal.models.home.HomePageMainModel.HOME_PROJECT_POST;
import static com.jntuk.ucev.placementsportal.models.home.HomePageMainModel.SQUARE_MEDIUM_VIEW;

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
                view= LayoutInflater.from(context).inflate(R.layout.students_placements,parent,false);
                //mySquareMedium.SquareMediumRecyclerView.addItemDecoration(new Space(2,10,true,0));
                return new MySquareMedium(view);
            case HOME_POSTS:
                view= LayoutInflater.from(context).inflate(R.layout.home_posts_design,parent,false);
                return new HomePostsViewHolder(view);
            case HOME_PROJECT_POST:
                view= LayoutInflater.from(context).inflate(R.layout.projects_post_design,parent,false);
                return new HomeProjectPost(view);
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
                ((MySquareMedium)holder).SquareMediumRecyclerView.setNestedScrollingEnabled(false);
                 ((MySquareMedium)holder).SquareMediumRecyclerView.setRecycledViewPool(SquareMediumPool);
               //  ((MySquareMedium)holder).TITLE.setTextColor(Color.parseColor(homePageMainModel.getTITLE_COLOR()));
                 //((MySquareMedium)holder).SquareMediumRecyclerView.setHasFixedSize(true);
               // ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));

                LinearLayoutManager gridLayoutManager=new LinearLayoutManager(context);
                gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                //((MySquareMedium)holder).des.setText(homePageMainModel.getDes());
                ((MySquareMedium)holder).SquareMediumRecyclerView.setLayoutManager(gridLayoutManager);
                ((MySquareMedium)holder).SquareMediumRecyclerView.addItemDecoration(new Space(homePageMainModel.getSquareMediumModelList().size(),10,true,0));
                SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(homePageMainModel.getSquareMediumModelList(),context);
                ((MySquareMedium)holder).SquareMediumRecyclerView.setAdapter(squareMediumRecyclerViewAdapter);
                break;
            case HOME_POSTS:
                HomePostsModel squareMediumModel=homePageMainModelList.get(position).getHomePostsModel();

                Glide.with(context).load(squareMediumModel.getImage()).into(((HomePostsViewHolder)holder).imageView);
                ((HomePostsViewHolder)holder).name.setText(squareMediumModel.getTitle());
                ((HomePostsViewHolder)holder).des.setText(squareMediumModel.getDescription());
                ((HomePostsViewHolder)holder).tag.setText(squareMediumModel.getTag());
                break;
            case HOME_PROJECT_POST:
                HomeProjectsPostModel homeProjectsPostModel=homePageMainModelList.get(position).getHomeProjectsPostModel();

                Glide.with(context).load(homeProjectsPostModel.getSimage()).into(((HomeProjectPost)holder).pic);
                Glide.with(context).load(homeProjectsPostModel.getPimage()).into(((HomeProjectPost)holder).imageView);

                ((HomeProjectPost)holder).title.setText(homeProjectsPostModel.getTitle());
                ((HomeProjectPost)holder).des.setText(homeProjectsPostModel.getDescription());
                ((HomeProjectPost)holder).sn.setText(homeProjectsPostModel.getS_name());
                ((HomeProjectPost)holder).sinfo.setText(homeProjectsPostModel.getS_info());

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
            case 1:
                return HOME_POSTS;

            case 2:
                return HOME_PROJECT_POST;

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
    TextView TITLE;
        public MySquareMedium(@NonNull View itemView) {
            super(itemView);
            TITLE=itemView.findViewById(R.id.title);
            SquareMediumRecyclerView=itemView.findViewById(R.id.horizontal_recycler_view_square_medium);


        }
    }


    static class HomePostsViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView name,des,tag;

        public HomePostsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.grid_category_image);
            constraintLayout=itemView.findViewById(R.id.category_item);
            tag=itemView.findViewById(R.id.grid_category_title);
            des=itemView.findViewById(R.id.textView6);
            name=itemView.findViewById(R.id.textView5);


        }
    }

    static class HomeProjectPost extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView imageView,pic;
        TextView des,sn,sinfo,title;

        public HomeProjectPost(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView2);
            pic=itemView.findViewById(R.id.grid_category_image);
            constraintLayout=itemView.findViewById(R.id.category_item);
            sn=itemView.findViewById(R.id.textView10);
            sinfo=itemView.findViewById(R.id.textView9);
            title=itemView.findViewById(R.id.textView5);
            des=itemView.findViewById(R.id.textView6);


        }
    }
}
