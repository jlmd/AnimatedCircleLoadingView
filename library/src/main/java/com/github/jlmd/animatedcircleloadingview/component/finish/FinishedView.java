package com.github.jlmd.animatedcircleloadingview.component.finish;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.jlmd.animatedcircleloadingview.component.ComponentViewAnimation;

/**
 * @author jlmd
 */
public abstract class FinishedView extends ComponentViewAnimation {

  private static final int MIN_IMAGE_SIZE = 1;
  private int maxImageSize;
  private int circleMaxRadius;
  private Bitmap originalFinishedBitmap;
  private float currentCircleRadius;
  private int imageSize;

  public FinishedView(Context context, int parentWidth, int mainColor, int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
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
    Bitmap bitmap = Bitmap.createScaledBitmap(originalFinishedBitmap, imageSize, imageSize, true);
    canvas.drawBitmap(bitmap, parentCenter - bitmap.getWidth() / 2,
        parentCenter - bitmap.getHeight() / 2, new Paint());
  }

  public void drawCircle(Canvas canvas) {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setColor(getCircleColor());
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
    valueImageAnimator.start();
  }

  protected abstract int getDrawable();

  protected abstract int getCircleColor();
}
