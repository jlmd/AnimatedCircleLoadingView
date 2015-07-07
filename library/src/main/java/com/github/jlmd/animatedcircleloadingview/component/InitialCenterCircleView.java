package com.github.jlmd.animatedcircleloadingview.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.github.jlmd.animatedcircleloadingview.animator.AnimationState;

/**
 * @author jlmd
 */
public class InitialCenterCircleView extends ComponentViewAnimation {

  private float minRadius;
  private float currentCircleWidth;
  private float currentCircleHeight;

  public InitialCenterCircleView(Context context, int parentWidth, int mainColor,
      int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
    init();
  }

  private void init() {
    minRadius = (15 * parentWidth) / 700;
    currentCircleWidth = minRadius;
    currentCircleHeight = minRadius;
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
    RectF oval = new RectF();
    oval.set(parentCenter - currentCircleWidth, parentCenter - currentCircleHeight,
        parentCenter + currentCircleWidth, parentCenter + currentCircleHeight);
    canvas.drawOval(oval, paint);
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
        currentCircleWidth = (float) animation.getAnimatedValue();
        currentCircleHeight = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public void startTranslateBottomAnimation() {
    float translationYFrom = -(260 * parentWidth) / 700;
    float translationYTo = (360 * parentWidth) / 700;
    ObjectAnimator translationY =
        ObjectAnimator.ofFloat(this, "translationY", translationYFrom, translationYTo);
    translationY.setDuration(650);
    translationY.start();
  }

  public void startScaleDisappear() {
    float maxScaleSize = (250 * parentWidth) / 700;
    ValueAnimator valueScaleWidthAnimator = ValueAnimator.ofFloat(circleRadius, maxScaleSize);
    valueScaleWidthAnimator.setDuration(260);
    valueScaleWidthAnimator.setStartDelay(430);
    valueScaleWidthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        currentCircleWidth = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueScaleWidthAnimator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        setState(AnimationState.MAIN_CIRCLE_SCALED_DISAPPEAR);
        currentCircleWidth = circleRadius + (strokeWidth / 2);
        currentCircleHeight = circleRadius + (strokeWidth / 2);
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
    valueScaleWidthAnimator.start();

    ValueAnimator valueScaleHeightAnimator = ValueAnimator.ofFloat(circleRadius, circleRadius / 2);
    valueScaleHeightAnimator.setDuration(260);
    valueScaleHeightAnimator.setStartDelay(430);
    valueScaleHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        currentCircleHeight = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueScaleHeightAnimator.start();
  }

  public void startTranslateCenterAnimation() {
    float translationYFrom = -(260 * parentWidth) / 700;
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", translationYFrom, 0);
    translationY.setDuration(650);
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
    translationY.start();
  }
}
