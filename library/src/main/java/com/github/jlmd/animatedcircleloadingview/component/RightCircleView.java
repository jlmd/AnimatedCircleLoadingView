package com.github.jlmd.animatedcircleloadingview.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.github.jlmd.animatedcircleloadingview.animator.AnimationState;

/**
 * @author jlmd
 */
public class RightCircleView extends ComponentViewAnimation {

  private int rightMargin;
  private int bottomMargin;
  private Paint paint;

  public RightCircleView(Context context, int parentWidth, int mainColor, int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
    init();
  }

  private void init() {
    rightMargin = (150 * parentWidth / 700);
    bottomMargin = (50 * parentWidth / 700);
    initPaint();
  }

  private void initPaint() {
    paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(secondaryColor);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawCircle(canvas);
  }

  public void drawCircle(Canvas canvas) {
    canvas.drawCircle(getWidth() - rightMargin, parentCenter - bottomMargin, circleRadius, paint);
  }

  public void startSecondaryCircleAnimation() {
    int bottomMovementAddition = (260 * parentWidth) / 700;
    TranslateAnimation translateAnimation =
        new TranslateAnimation(getX(), getX(), getY(), getY() + bottomMovementAddition);
    translateAnimation.setStartOffset(200);
    translateAnimation.setDuration(1000);

    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
    alphaAnimation.setStartOffset(1300);
    alphaAnimation.setDuration(200);

    AnimationSet animationSet = new AnimationSet(true);
    animationSet.addAnimation(translateAnimation);
    animationSet.addAnimation(alphaAnimation);
    animationSet.setFillAfter(true);
    animationSet.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        setState(AnimationState.SECONDARY_CIRCLE_FINISHED);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
        // Empty
      }
    });

    startAnimation(animationSet);
  }
}