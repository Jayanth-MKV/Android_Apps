package com.vugido.vugidoupdate.designs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SquareBoxDesign extends FrameLayout {
    public SquareBoxDesign(Context context) {
        super(context);
    }

    public SquareBoxDesign(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareBoxDesign(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareBoxDesign(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set the height to be the same as the width (a square!)
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}