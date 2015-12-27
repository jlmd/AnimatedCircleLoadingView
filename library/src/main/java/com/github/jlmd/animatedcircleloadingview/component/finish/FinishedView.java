package com.github.jlmd.animatedcircleloadingview.component.finish;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.Log;
import com.github.jlmd.animatedcircleloadingview.animator.AnimationState;
import com.github.jlmd.animatedcircleloadingview.component.ComponentViewAnimation;

/**
 * @author jlmd
 */
public abstract class FinishedView extends ComponentViewAnimation {

  private static final int MIN_IMAGE_SIZE = 1;
  protected final int tintColor;
  private int maxImageSize;
  private int circleMaxRadius;
  private Bitmap originalFinishedBitmap;
  private float currentCircleRadius;
  private int imageSize;

  public FinishedView(Context context, int parentWidth, int mainColor, int secondaryColor,
      int tintColor) {
    super(context, parentWidth, mainColor, secondaryColor);
    this.tintColor = tintColor;
    init();
  }

  private void init() {
    maxImageSize = (140 * parentWidth) / 700;
    circleMaxRadius = (140 * parentWidth) / 700;
    currentCircleRadius = circleRadius;
    imageSize = MIN_IMAGE_SIZE;
    originalFinishedBitmap = BitmapFactory.decodeResource(getResources(), getDrawable());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawCircle(canvas);
    drawCheckedMark(canvas);
  }

  private void drawCheckedMark(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColorFilter(new LightingColorFilter(getDrawableTintColor(), 0));

    Bitmap bitmap = Bitmap.createScaledBitmap(originalFinishedBitmap, imageSize, imageSize, true);
    canvas.drawBitmap(bitmap, parentCenter - bitmap.getWidth() / 2,
        parentCenter - bitmap.getHeight() / 2, paint);
  }

  public void drawCircle(Canvas canvas) {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setColor(getCircleColor());
    paint.setAntiAlias(true);
    canvas.drawCircle(parentCenter, parentCenter, currentCircleRadius, paint);
  }

  public void startScaleAnimation() {
    startScaleCircleAnimation();
    startScaleImageAnimation();
  }

  private void startScaleCircleAnimation() {
    ValueAnimator valueCircleAnimator =
        ValueAnimator.ofFloat(circleRadius + strokeWidth / 2, circleMaxRadius);
    valueCircleAnimator.setDuration(1000);
    valueCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        currentCircleRadius = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueCircleAnimator.start();
  }

  private void startScaleImageAnimation() {
    ValueAnimator valueImageAnimator = ValueAnimator.ofInt(MIN_IMAGE_SIZE, maxImageSize);
    valueImageAnimator.setDuration(1000);
    valueImageAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        imageSize = (int) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueImageAnimator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        // Empty
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        setState(AnimationState.ANIMATION_END);
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
    valueImageAnimator.start();
  }

  protected abstract int getDrawable();

  protected abstract int getDrawableTintColor();

  protected abstract int getCircleColor();
}
