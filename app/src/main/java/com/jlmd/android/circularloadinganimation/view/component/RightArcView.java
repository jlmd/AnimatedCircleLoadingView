package com.jlmd.android.circularloadinganimation.view.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class RightArcView extends View implements ComponentAnimation {

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
    setInvisible();
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
    Paint paint = new Paint();
    paint.setColor(getResources().getColor(R.color.main_circle));
    paint.setStrokeWidth(15);
    paint.setStyle(Paint.Style.STROKE);
    final RectF oval = new RectF();
    oval.set(0, 0, getWidth(), getHeight());
    canvas.drawArc(oval, 35, 45, false, paint);
  }

  @Override
  public void startAnimation(final Callback callback) {
    setVisible();
    ObjectAnimator rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, -120);
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
  }

  private void setVisible() {
    setAlpha(100);
  }
}
