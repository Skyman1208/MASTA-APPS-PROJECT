package com.example.up;

import android.graphics.drawable.AnimationDrawable;
import android.text.Layout;
import android.widget.RelativeLayout;

public class colourAnimation {
    AnimationDrawable animDrawable;

    public colourAnimation(RelativeLayout mLayout) {
        animDrawable = (AnimationDrawable) mLayout.getBackground();
        animDrawable.setEnterFadeDuration(8);
        animDrawable.setExitFadeDuration(2000);
        animDrawable.start();
    }
}
