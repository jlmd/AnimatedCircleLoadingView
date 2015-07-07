package com.github.jlmd.animatedcircleloadingview.component;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.github.jlmd.animatedcircleloadingview.animator.AnimationState;

/**
 * @author jlmd
 */
public class MainCircleView extends ComponentViewAnimation {

  private Paint paint;
  private RectF oval;
  private int arcFillAngle = 0;
  private int arcStartAngle = 0;

  public MainCircleView(Context context, int parentWidth, int mainColor, int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
    init();
  }

  private void init() {
    initPaint();
    initOval();
  }

  private void initPaint() {
    paint = new Paint();
    paint.setColor(mainColor);
    paint.setStrokeWidth(strokeWidth);
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
  }

  private void initOval() {
    float padding = paint.getStrokeWidth() / 2;
    oval = new RectF();
    oval.set(parentCenter - circleRadius, padding, parentCenter + circleRadius, circleRadius * 2);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawArcs(canvas);
  }

  private void drawArcs(Canvas canvas) {
    canvas.drawArc(oval, arcStartAngle, arcFillAngle, false, paint);
  }

  public void startFillCircleAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(90, 360);
    valueAnimator.setDuration(800);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        arcStartAngle = (int) animation.getAnimatedValue();
        arcFillAngle = (90 - arcStartAngle) * 2;
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
        setState(AnimationState.MAIN_CIRCLE_FILLED_TOP);
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
