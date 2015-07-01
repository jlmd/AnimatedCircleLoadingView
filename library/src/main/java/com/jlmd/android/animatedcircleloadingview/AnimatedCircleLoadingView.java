package com.jlmd.android.animatedcircleloadingview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jlmd.android.animatedcircleloadingview.animator.AnimatorHelper;
import com.jlmd.android.animatedcircleloadingview.component.*;
import com.jlmd.android.animatedcircleloadingview.component.finish.*;

/**
 * @author jlmd
 */
public class AnimatedCircleLoadingView extends FrameLayout {

  private final Context context;
  private InitialCenterCircleView initialCenterCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private MainCircleView mainCircleView;
  private TopCircleBorderView topCircleBorderView;
  private FinishedOkView finishedOkView;
  private FinishedFailureView finishedFailureView;
  private PercentIndicatorView percentIndicatorView;
  private AnimatorHelper animatorHelper;
  private boolean startAnimationIndeterminate;
  private boolean startAnimationDeterminate;

  public AnimatedCircleLoadingView(Context context) {
    super(context);
    this.context = context;
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    init();
    if (startAnimationIndeterminate) {
      animatorHelper.startAnimator();
      startAnimationIndeterminate = false;
    }
    if (startAnimationDeterminate) {
      addView(percentIndicatorView);
      animatorHelper.startAnimator();
      startAnimationDeterminate = false;
    }
  }

  private void init() {
    initComponents();
    addComponentsViews();
    initAnimatorHelper();
  }

  private void initComponents() {
    int width = getWidth();
    initialCenterCircleView = new InitialCenterCircleView(context, width);
    rightCircleView = new RightCircleView(context, width);
    sideArcsView = new SideArcsView(context, width);
    topCircleBorderView = new TopCircleBorderView(context, width);
    mainCircleView = new MainCircleView(context, width);
    finishedOkView = new FinishedOkView(context, width);
    finishedFailureView = new FinishedFailureView(context, width);
    percentIndicatorView = new PercentIndicatorView(context, width);
  }

  private void addComponentsViews() {
    addView(initialCenterCircleView);
    addView(rightCircleView);
    addView(sideArcsView);
    addView(topCircleBorderView);
    addView(mainCircleView);
    addView(finishedOkView);
    addView(finishedFailureView);
  }

  private void initAnimatorHelper() {
    animatorHelper = new AnimatorHelper();
    animatorHelper.setComponentViewAnimations(initialCenterCircleView, rightCircleView,
        sideArcsView, topCircleBorderView, mainCircleView, finishedOkView, finishedFailureView,
        percentIndicatorView);
  }

  public void startIndeterminate() {
    startAnimationIndeterminate = true;
  }

  public void startDeterminate() {
    startAnimationDeterminate = true;
  }

  public void setPercent(int percent) {
    if (percentIndicatorView != null) {
      percentIndicatorView.setPercent(percent);
      if (percent == 100) {
        animatorHelper.finishOk();
      }
    }
  }

  public void stopOk() {
    animatorHelper.finishOk();
  }

  public void stopFailure() {
    animatorHelper.finishFailure();
  }
}
