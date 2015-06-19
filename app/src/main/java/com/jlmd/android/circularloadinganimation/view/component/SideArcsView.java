package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.jlmd.android.circularloadinganimation.R;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;

/**
 * @author jlmd
 */
public class SideArcsView extends ComponentViewAnimation {

  private static final int MIN_RESIZE_ANGLE = 8;
  private static final int MAX_RESIZE_ANGLE = 45;
  private static final int INITIAL_LEFT_ARC_START_ANGLE = 100;
  private static final int INITIAL_RIGHT_ARC_START_ANGLE = 80;
  private static final int MIN_START_ANGLE = 0;
  private static final int MAX_START_ANGLE = 165;
  private int startLeftArcAngle = INITIAL_LEFT_ARC_START_ANGLE;
  private int startRightArcAngle = INITIAL_RIGHT_ARC_START_ANGLE;
  private Paint paint;
  private RectF oval;
  private int arcAngle;

  public SideArcsView(Context context) {
    super(context);
    init();
  }

  public SideArcsView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SideArcsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    initPaint();
    arcAngle = MAX_RESIZE_ANGLE;
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
    oval.set(padding, padding, getWidth() - padding, getHeight() - padding);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initOval();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawArcs(canvas);
  }

  private void drawArcs(Canvas canvas) {
    canvas.drawArc(oval, startLeftArcAngle, arcAngle, false, paint);
    canvas.drawArc(oval, startRightArcAngle, -arcAngle, false, paint);
  }

  public void startRotateAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(MIN_START_ANGLE, MAX_START_ANGLE);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(700);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        startLeftArcAngle = INITIAL_LEFT_ARC_START_ANGLE + (int) animation.getAnimatedValue();
        startRightArcAngle = INITIAL_RIGHT_ARC_START_ANGLE - ((int) animation.getAnimatedValue());
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public void startResizeDownAnimation() {
    ValueAnimator valueAnimator = ValueAnimator.ofInt(MAX_RESIZE_ANGLE, MIN_RESIZE_ANGLE);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(700);
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
        setState(AnimationState.SIDE_ARCS_RESIZED_TOP);
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
