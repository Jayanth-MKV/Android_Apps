package com.imgideongo.vfp.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.imgideongo.vfp.fragments.DashBoard;

public class RecyclerviewItemClickListner implements RecyclerView.OnItemTouchListener {
    private DashBoard.RecyclerViewClickListener recyclerViewClickListener;
    private GestureDetector gestureDetector;


    public RecyclerviewItemClickListner(Context context, final RecyclerView recyclerView,
                                        final DashBoard.RecyclerViewClickListener
                                                recyclerViewClickListener){

        this.recyclerViewClickListener=recyclerViewClickListener;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && recyclerViewClickListener!=null){
                    recyclerViewClickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                }
            }
        });

    }




    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {


        View child=recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
        if(child!=null && recyclerViewClickListener!=null && gestureDetector.onTouchEvent(motionEvent)){
            recyclerViewClickListener.onClick(child,recyclerView.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
