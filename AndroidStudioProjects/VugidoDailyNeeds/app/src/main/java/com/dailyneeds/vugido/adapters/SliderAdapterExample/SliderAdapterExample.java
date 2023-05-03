package com.dailyneeds.vugido.adapters.SliderAdapterExample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.models.ComingRes;
import com.dailyneeds.vugido.models.ResponseComingSoon;
import com.dailyneeds.vugido.models.ResultsItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {
    private Context context;
    private List<ResultsItem> resultsItemList;

    public SliderAdapterExample(Context context, List<ResultsItem> responseComingSoonList) {
        this.context = context;
        this.resultsItemList=responseComingSoonList;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        ResultsItem resultsItem=resultsItemList.get(position);
        viewHolder.textViewDescription.setText(resultsItem.getNAME());

        Glide.with(context)
                .load(resultsItem.getICON())
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return resultsItemList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription,cs;

        SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            cs=itemView.findViewById(R.id.cs);
            this.itemView = itemView;

            fadeInOut();
        }

        private void fadeInOut(){

            final AnimatorSet mAnimationSet;
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(cs, "alpha", .5f, .1f);
            fadeOut.setDuration(200);
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(cs, "alpha", .1f, .5f);
            fadeIn.setDuration(200);

            mAnimationSet = new AnimatorSet();

            mAnimationSet.play(fadeIn).after(fadeOut);

            mAnimationSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mAnimationSet.start();
                }
            });

            mAnimationSet.start();
        }
    }
}
