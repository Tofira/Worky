package com.bringg.worky.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.bringg.worky.R;

/**
 * Created by Mickael on 08/03/2018.
 */

public class AnimationsUtils {

    public static void startSplashAnimation(View logoFirst, View logoSecond, View cvAddress)
    {
        ObjectAnimator firstTextAnimator = ObjectAnimator.ofFloat(
                logoFirst,
                "alpha",
                0f,
                1f
        );

        ObjectAnimator secondTextAnimator = ObjectAnimator.ofFloat(
                logoSecond,
                "alpha",
                0f,
                1f
        );
        secondTextAnimator.setStartDelay(Constants.ANIMATIONS.SPLASH_ANIMATION_ITEM_DELAY);

        ObjectAnimator cardViewAnimator = ObjectAnimator.ofFloat(
                cvAddress,
                "alpha",
                0f,
                1f
        );
        cardViewAnimator.setStartDelay(Constants.ANIMATIONS.SPLASH_ANIMATION_ITEM_DELAY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(
                firstTextAnimator,
                secondTextAnimator,
                cardViewAnimator
        );
        animatorSet.setDuration(Constants.ANIMATIONS.SPLASH_ANIMATION_ITEM_DURATION);
        animatorSet.setStartDelay(Constants.ANIMATIONS.SPLASH_ANIMATION_INITIAL_DELAY);

        animatorSet.start();
    }

}
