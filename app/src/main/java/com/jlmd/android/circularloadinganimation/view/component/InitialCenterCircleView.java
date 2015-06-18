package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class InitialCenterCircleView extends ComponentViewAnimation {

  public InitialCenterCircleView(Context context) {
    super(context);
  }

  public InitialCenterCircleView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public InitialCenterCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
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
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, 30, paint);
  }

  @Override
  public void startAnimation(Callback callback) {
    startFirstAnimation(callback);
  }

  private void startFirstAnimation(final Callback callback) {
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", 0, -255);
    // TODO Add alpha animation
    ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", 2.3f);
    ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", 2.3f);

    AnimatorSet scaleUp = new AnimatorSet();
    scaleUp.play(translationY).with(scaleDownX).with(scaleDownY);
    scaleUp.setDuration(1500);
    scaleUp.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        startSecondStepAnimation(callback);
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
    scaleUp.start();
  }

  private void startSecondStepAnimation(final Callback callback) {
    ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", -255, 600);
    translationY.setDuration(700);
    translationY.start();
    startThirdStepAnimation(callback);
  }

  private void startThirdStepAnimation(final Callback callback) {
    ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", 7.5f);
    ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 7.5f);

    AnimatorSet scaleUp = new AnimatorSet();
    scaleUp.play(scaleUpX).with(scaleUpY);
    scaleUp.setDuration(75);
    scaleUp.setStartDelay(460);
    scaleUp.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        callback.onAnimationFinished();
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
    scaleUp.start();
  }
}
