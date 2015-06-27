package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;
import com.jlmd.android.circularloadinganimation.view.exception.NullStateListenerException;

/**
 * @author jlmd
 */
public abstract class ComponentViewAnimation extends View {

  private static final String DEFAULT_HEX_MAIN_COLOR = "#FF9A00";
  private static final String DEFAULT_HEX_SECONDARY_COLOR = "#BDBDBD";
  protected final int parentWidth;
  protected int mainColor;
  protected int secondaryColor;
  protected float parentCenter;
  protected float circleRadius;
  protected int strokeWidth;
  private StateListener stateListener;

  public ComponentViewAnimation(Context context, int parentWidth) {
    super(context);
    this.parentWidth = parentWidth;
    init();
  }

  private void init() {
    hideView();
    circleRadius = parentWidth / 10;
    parentCenter = parentWidth / 2;
    strokeWidth = (12 * parentWidth) / 700;
    mainColor = Color.parseColor(DEFAULT_HEX_MAIN_COLOR);
    secondaryColor = Color.parseColor(DEFAULT_HEX_SECONDARY_COLOR);
  }

  public void hideView() {
    setVisibility(View.INVISIBLE);
  }

  public void showView() {
    setVisibility(View.VISIBLE);
  }

  public void setMainColor(int mainColor) {
    this.mainColor = mainColor;
  }

  public void setSecondaryColor(int secondaryColor) {
    this.secondaryColor = secondaryColor;
  }

  public void setState(AnimationState state) {
    if (stateListener != null) {
      stateListener.onStateChanged(state);
    } else {
      throw new NullStateListenerException();
    }
  }

  public void setStateListener(StateListener stateListener) {
    this.stateListener = stateListener;
  }

  public interface StateListener {

    void onStateChanged(AnimationState state);
  }
}