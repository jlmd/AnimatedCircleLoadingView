package com.github.jlmd.animatedcircleloadingview.component;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.DecelerateInterpolator;
import com.github.jlmd.animatedcircleloadingview.animator.AnimationState;

/**
 * @author jlmd
 */
public class TopCircleBorderView extends ComponentViewAnimation {

  private static final int MIN_ANGLE = 25;
  private static final int MAX_ANGLE = 180;
  private Paint paint;
  private RectF oval;
  private int arcAngle;

  public TopCircleBorderView(Context context, int parentWidth, int mainColor, int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
    init();
  }

  private void init() {
    initPaint();
    initOval();
    arcAngle = MIN_ANGLE;
  }

  private void initPaint() {
    paint = new Paint();
    paint.setColor(mainColor);
    paint.setStrokeWidth(strokeWidth);
    paint.setStyle(Paint.Style.STROKE);
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
    canvas.drawArc(oval, 270, arcAngle, false, paint);
    canvas.drawArc(oval, 270, -arcAngle, false, paint);
  }

  public void startDrawCircleAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(MIN_ANGLE, MAX_ANGLE);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(400);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        arcAngle = (int) animation.getAnimatedValue();
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
        setState(AnimationState.MAIN_CIRCLE_DRAWN_TOP);
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
