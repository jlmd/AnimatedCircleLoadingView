package com.jlmd.android.circularloadinganimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jlmd.android.circularloadinganimation.view.animator.AnimatorHelper;
import com.jlmd.android.circularloadinganimation.view.component.finish.FinishedFailureView;
import com.jlmd.android.circularloadinganimation.view.component.finish.FinishedOkView;
import com.jlmd.android.circularloadinganimation.view.component.InitialCenterCircleView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;
import com.jlmd.android.circularloadinganimation.view.component.SideArcsView;
import com.jlmd.android.circularloadinganimation.view.component.TopCircleView;

/**
 * @author jlmd
 */
public class CircleLoadingView extends FrameLayout {

  private final Context context;
  private InitialCenterCircleView initialCenterCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private MainCircleView mainCircleView;
  private TopCircleView topCircleView;
  private FinishedOkView finishedOkView;
  private FinishedFailureView finishedFailureView;
  private AnimatorHelper animatorHelper;
  private boolean startAnimationIndeterminate;

  public CircleLoadingView(Context context) {
    super(context);
    this.context = context;
  }

  public CircleLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
  }

  public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    topCircleView = new TopCircleView(context, width);
    mainCircleView = new MainCircleView(context, width);
    finishedOkView = new FinishedOkView(context, width);
    finishedFailureView = new FinishedFailureView(context, width);
  }

  private void addComponentsViews() {
    addView(initialCenterCircleView);
    addView(rightCircleView);
    addView(sideArcsView);
    addView(topCircleView);
    addView(mainCircleView);
    addView(finishedOkView);
    addView(finishedFailureView);
  }

  private void initAnimatorHelper() {
    animatorHelper = new AnimatorHelper();
    animatorHelper.setComponentViewAnimations(initialCenterCircleView, rightCircleView,
        sideArcsView, topCircleView, mainCircleView, finishedOkView, finishedFailureView);
  }

  public void startIndeterminate() {
    startAnimationIndeterminate = true;
  }

  public void stopOk() {
    animatorHelper.finishOk();
  }

  public void stopFailure() {
    animatorHelper.finishFailure();
  }
}
