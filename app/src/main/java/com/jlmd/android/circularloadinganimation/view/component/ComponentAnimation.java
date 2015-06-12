package com.jlmd.android.circularloadinganimation.view.component;

/**
 * @author jlmd
 */
public interface ComponentAnimation {

  void startAnimation(Callback callback);

  interface Callback {

    void onAnimationFinished();
  }
}