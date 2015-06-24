package com.jlmd.android.circularloadinganimation.view.animator;

import com.jlmd.android.circularloadinganimation.view.component.ComponentViewAnimation;
import com.jlmd.android.circularloadinganimation.view.component.FinishedFailureView;
import com.jlmd.android.circularloadinganimation.view.component.FinishedOkView;
import com.jlmd.android.circularloadinganimation.view.component.InitialCenterCircleView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;
import com.jlmd.android.circularloadinganimation.view.component.SideArcsView;
import com.jlmd.android.circularloadinganimation.view.component.TopCircleView;

/**
 * @author jlmd
 */
public class AnimatorHelper implements ComponentViewAnimation.StateListener {

  // For TESTING
  private static final int MAX_ITERATIONS = 3;
  private int iteration = 0;

  private InitialCenterCircleView initialCenterCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private TopCircleView topCircleView;
  private MainCircleView mainCircleView;
  private FinishedOkView finishedOkView;
  private FinishedFailureView finishedFailureView;
  private AnimationState finishedState;

  public void setComponentViewAnimations(InitialCenterCircleView initialCenterCircleView,
      RightCircleView rightCircleView, SideArcsView sideArcsView, TopCircleView topCircleView,
      MainCircleView mainCircleView, FinishedOkView finishedOkCircleView,
      FinishedFailureView finishedFailureView) {
    this.initialCenterCircleView = initialCenterCircleView;
    this.rightCircleView = rightCircleView;
    this.sideArcsView = sideArcsView;
    this.topCircleView = topCircleView;
    this.mainCircleView = mainCircleView;
    this.finishedOkView = finishedOkCircleView;
    this.finishedFailureView = finishedFailureView;
    initListeners();
  }

  private void initListeners() {
    initialCenterCircleView.setStateListener(this);
    rightCircleView.setStateListener(this);
    sideArcsView.setStateListener(this);
    topCircleView.setStateListener(this);
    mainCircleView.setStateListener(this);
    finishedOkView.setStateListener(this);
    finishedFailureView.setStateListener(this);
  }

  public void startAnimator() {
    initialCenterCircleView.showView();
    initialCenterCircleView.startTranslateTopAnimation();
    initialCenterCircleView.startScaleAnimation();
    rightCircleView.showView();
    rightCircleView.startSecondaryCircleAnimation();
  }

  @Override
  public void onStateChanged(AnimationState state) {
    switch (state) {
      case MAIN_CIRCLE_TRANSLATED_TOP:
        onMainCircleTranslatedTop();
        break;
      case MAIN_CIRCLE_SCALED_DISAPPEAR:
        onMainCircleScaledDisappear();
        break;
      case MAIN_CIRCLE_FILLED_TOP:
        iteration++;
        if (iteration == MAX_ITERATIONS) {
          onStateChanged(AnimationState.FINISHED_OK);
        } else {
          onMainCircleFilledTop();
        }
        break;
      case SIDE_ARCS_RESIZED_TOP:
        onSideArcsResizedTop();
        break;
      case MAIN_CIRCLE_DRAWN_TOP:
        onMainCircleDrawnTop();
        break;
      case FINISHED_OK:
        onFinished(state);
        break;
      case FINISHED_ERROR:
        onFinished(state);
        break;
      case MAIN_CIRCLE_TRANSLATED_CENTER:
        onMainCircleTranslatedCenter();
        break;
      default:
        break;
    }
  }

  private void onMainCircleTranslatedTop() {
    initialCenterCircleView.startTranslateBottomAnimation();
    initialCenterCircleView.startScaleDisappear();
  }

  private void onMainCircleScaledDisappear() {
    sideArcsView.showView();
    sideArcsView.startRotateAnimation();
    sideArcsView.startResizeDownAnimation();
  }

  private void onSideArcsResizedTop() {
    sideArcsView.hideView();
    topCircleView.showView();
    topCircleView.startDrawCircleAnimation();
  }

  private void onMainCircleDrawnTop() {
    mainCircleView.showView();
    mainCircleView.startFillCircleAnimation();
  }

  private void onMainCircleFilledTop() {
    topCircleView.hideView();
    mainCircleView.hideView();
    initialCenterCircleView.startTranslateBottomAnimation();
    initialCenterCircleView.startScaleDisappear();
  }

  private void onFinished(AnimationState state) {
    topCircleView.hideView();
    mainCircleView.hideView();
    finishedState = state;
    initialCenterCircleView.startTranslateCenterAnimation();
  }

  private void onMainCircleTranslatedCenter() {
    if (finishedState == AnimationState.FINISHED_OK) {
      finishedOkView.showView();
      finishedOkView.startScaleAnimation();
    } else {
      finishedFailureView.showView();
      finishedFailureView.startScaleAnimation();
    }
    initialCenterCircleView.hideView();
  }
}