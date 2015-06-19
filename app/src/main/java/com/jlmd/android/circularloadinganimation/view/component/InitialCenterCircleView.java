package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.jlmd.android.circularloadinganimation.R;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;

/**
 * @author jlmd
 */
public class InitialCenterCircleView extends ComponentViewAnimation {

  private static final float MIN_RADIUS = 15;
  private static final float MAX_RADIUS = 70;

  private float circleRadius = MIN_RADIUS;

  public InitialCenterCircleView(Context context) {
    super(context);
    init();
  }

  public InitialCenterCircleView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public InitialCenterCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawCircle(canvas);
  }

  public void drawCircle(Canvas canvas) {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(getResources().getColor(R.color.main_circle));
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, paint);
  }

  public void startTranslateTopAnimation() {
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", 0, -255);
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
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(MIN_RADIUS, MAX_RADIUS);
    valueAnimator.setDuration(1400);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        circleRadius = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public void startTranslateBottomAnimation() {
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", -255, 600);
    translationY.setDuration(700);
    translationY.start();
  }

  public void startScaleDisappear() {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(MAX_RADIUS, 200);
    valueAnimator.setDuration(75);
    valueAnimator.setStartDelay(460);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        circleRadius = (float) animation.getAnimatedValue();
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
        circleRadius = MAX_RADIUS;
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
}
