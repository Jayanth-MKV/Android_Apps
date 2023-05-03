package com.vugido.brain_cord.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.adapters.CategoryRecyclerViewAdapter;
import com.vugido.brain_cord.adapters.HomeVideosRecyclerViewAdapter;
import com.vugido.brain_cord.models.Category;
import com.vugido.brain_cord.models.Videos;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment  {

    private Context context;
    private RecyclerView CrecyclerView,HrecyclerView;
    CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    List<Category> categoryList;
    HomeVideosRecyclerViewAdapter homeVideosRecyclerViewAdapter;
    //YouTubePlayerView youTubePlayerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        CrecyclerView=view.findViewById(R.id.grid_recycler_view);
        HrecyclerView=view.findViewById(R.id.homeRec);

        categoryList=new ArrayList<>();

        categoryList.add(new Category(R.drawable.ml,"Machine Learning",1));
        categoryList.add(new Category(R.drawable.java,"Java",1));
        categoryList.add(new Category(R.drawable.python,"Python",1));
        categoryList.add(new Category(R.drawable.cpp,"C++",1));
        categoryList.add(new Category(R.drawable.r,"R Language",1));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerViewAdapter=new CategoryRecyclerViewAdapter(categoryList,context,"","");

        CrecyclerView.setLayoutManager(linearLayoutManager);
        CrecyclerView.setAdapter(categoryRecyclerViewAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        HrecyclerView.setLayoutManager(layoutManager);

        List<Videos> youtubeVideos=new ArrayList<>();



//        youtubeVideos.add( new Videos("https://www.youtube.com/watch?v=eWEF1Zrmdow") );
        youtubeVideos.add( new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RBUbja6V9Aw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y8Rr39jKFKU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Hg1tqIwIfI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uhQ7mh_o_cM\" frameborder=\"0\" allowfullscreen></iframe>") );

        homeVideosRecyclerViewAdapter=new HomeVideosRecyclerViewAdapter(youtubeVideos,context);
        HrecyclerView.setAdapter(homeVideosRecyclerViewAdapter);



//         youTubePlayerView = view.findViewById(R.id.youtube_player_view);
//        getLifecycle().addObserver(youTubePlayerView);
//
//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String videoId = "PGWZUgzDMYI";
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//        });





        return view;
    }



    //    private void initYouTubePlayerView() {
//        youTubePlayerView.inflateCustomPlayerUi(R.layout.ayp_empty_layout);
//
//        IFramePlayerOptions iFramePlayerOptions = new IFramePlayerOptions.Builder()
//                .controls(0)
//                .rel(0)
//                .ivLoadPolicy(1)
//                .ccLoadPolicy(1)
//                .build();
//
//        getLifecycle().addObserver(youTubePlayerView);
//
//        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                setPlayNextVideoButtonClickListener(youTubePlayer);
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer, getLifecycle(),
//                        VideoIdsProvider.getNextVideoId(),0f
//                );
//            }
//        }, true, iFramePlayerOptions);
//    }

}
