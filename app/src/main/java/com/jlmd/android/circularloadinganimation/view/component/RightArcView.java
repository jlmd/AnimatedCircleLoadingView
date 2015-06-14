package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class RightArcView extends View implements ComponentAnimation {

  private static final int MIN_ANGLE = 8;
  private static final int MAX_ANGLE = 45;

  private Paint paint;
  private RectF oval;
  private int drawAngle;

  public RightArcView(Context context) {
    super(context);
    init();
  }

  public RightArcView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public RightArcView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    initPaint();
    setInvisible();
    drawAngle = MAX_ANGLE;
  }

  private void initPaint() {
    paint = new Paint();
    paint.setColor(getResources().getColor(R.color.main_circle));
    paint.setStrokeWidth(15);
    paint.setStyle(Paint.Style.STROKE);
  }

  private void initOval() {
    oval = new RectF();
    oval.set(0, 0, getWidth(), getHeight());
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initOval();
  }

  private void setInvisible() {
    setAlpha(0);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawArc(canvas);
  }

  private void drawArc(Canvas canvas) {
    canvas.drawArc(oval, 35, drawAngle, false, paint);
  }

  @Override
  public void startAnimation(final Callback callback) {
    setVisible();
    ObjectAnimator rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, -128);
    rotation.setDuration(700);
    rotation.addListener(new Animator.AnimatorListener() {
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
    rotation.start();
    startResizeDownAnimation();
  }

  private void startResizeDownAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(MAX_ANGLE, MIN_ANGLE);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(700);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        drawAngle = (int) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.start();
  }

  private void setVisible() {
    setAlpha(100);
  }
}
