package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;

/**
 * @author jlmd
 */
public class InitialCenterCircleView extends ComponentViewAnimation {

  private float minRadius;
  private float currentCircleRadius;

  public InitialCenterCircleView(Context context, int parentWidth) {
    super(context, parentWidth);
    init();
  }

  private void init() {
    minRadius = (15 * parentWidth) / 700;
    currentCircleRadius = minRadius;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawCircle(canvas);
  }

  public void drawCircle(Canvas canvas) {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setColor(mainColor);
    canvas.drawCircle(parentCenter, parentCenter, currentCircleRadius, paint);
  }

  public void startTranslateTopAnimation() {
    float translationYTo = -(255 * parentWidth) / 700;

    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", 0, translationYTo);
    translationY.setDuration(1100);
    translationY.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        setState(AnimationState.MAIN_CIRCLE_TRANSLATED_TOP);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        // Empty
      }
    });
    translationY.start();
  }

  public void startScaleAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(minRadius, circleRadius);
    valueAnimator.setDuration(1400);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        currentCircleRadius = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public void startTranslateBottomAnimation() {
    float translationYFrom = -(260 * parentWidth) / 700;
    float translationYTo = (600 * parentWidth) / 700;

    ObjectAnimator translationY =
        ObjectAnimator.ofFloat(this, "translationY", translationYFrom, translationYTo);
    translationY.setDuration(700);
    translationY.start();
  }

  public void startScaleDisappear() {
    float maxScaleSize = (200 * parentWidth) / 700;
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(circleRadius, maxScaleSize);
    valueAnimator.setDuration(75);
    valueAnimator.setStartDelay(460);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        currentCircleRadius = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        currentCircleRadius = circleRadius;
        setState(AnimationState.MAIN_CIRCLE_SCALED_DISAPPEAR);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        // Empty
      }
    });
    valueAnimator.start();
  }

  public void startTranslateCenterAnimation() {
    float translationYFrom = -(260 * parentWidth) / 700;
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", translationYFrom, 0);
    translationY.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        setState(AnimationState.MAIN_CIRCLE_TRANSLATED_CENTER);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        // Empty
      }
    });
    translationY.setDuration(700);
    translationY.start();
  }
}
