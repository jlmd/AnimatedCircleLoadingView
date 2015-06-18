package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author jlmd
 */
public abstract class ComponentViewAnimation extends View {

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
    setAlpha(0);
  }

  public void showView() {
    setAlpha(100);
  }

  public abstract void startAnimation(Callback callback);

  public interface Callback {

    void onAnimationFinished();
  }
}