package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import com.jlmd.android.circularloadinganimation.R;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;

/**
 * @author jlmd
 */
public class TopCircleView extends ComponentViewAnimation {

  private static final int MIN_ANGLE = 0;
  private static final int MAX_ANGLE = 180;
  private Paint paint;
  private RectF oval;
  private int arcAngle;

  public TopCircleView(Context context, int parentWidth) {
    super(context, parentWidth);
    init();
  }

  public TopCircleView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public TopCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    initPaint();
    arcAngle = MIN_ANGLE;
  }

  private void initPaint() {
    paint = new Paint();
    paint.setColor(getResources().getColor(R.color.main_circle));
    paint.setStrokeWidth(12);
    paint.setStyle(Paint.Style.STROKE);
  }

  private void initOval() {
    float padding = paint.getStrokeWidth() / 2;
    oval = new RectF();
    oval.set(parentCenter - circleRadius, padding, (getWidth() / 2) + circleRadius,
        circleRadius * 2);
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

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initOval();
  }

  public void startDrawCircleAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(MIN_ANGLE, MAX_ANGLE);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(500);
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
