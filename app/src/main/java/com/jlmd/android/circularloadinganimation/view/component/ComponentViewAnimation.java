package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.jlmd.android.circularloadinganimation.view.animator.AnimationState;
import com.jlmd.android.circularloadinganimation.view.exception.NullStateListenerException;

/**
 * @author jlmd
 */
public abstract class ComponentViewAnimation extends View {

  private StateListener stateListener;

  public ComponentViewAnimation(Context context) {
    super(context);
    init();
  }

  public ComponentViewAnimation(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ComponentViewAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    hideView();
  }

  public void hideView() {
    setVisibility(View.INVISIBLE);
  }

  public void showView() {
    setVisibility(View.VISIBLE);
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