package com.jlmd.android.circularloadinganimation.view.animator;

import com.jlmd.android.circularloadinganimation.view.component.ComponentAnimation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jlmd
 */
public class AnimatorHelper {

  private List<ComponentAnimation> componentAnimations = Collections.emptyList();

  public void setComponentAnimations(ComponentAnimation... componentAnimations) {
    this.componentAnimations = Arrays.asList(componentAnimations);
  }

  public void startAnimator() {
    componentAnimations.get(0).startAnimation(new ComponentAnimation.Callback() {
      @Override
      public void onAnimationFinished() {
        componentAnimations.get(2).startAnimation(new ComponentAnimation.Callback() {
          @Override
          public void onAnimationFinished() {
            // Empty
          }
        });
        componentAnimations.get(3).startAnimation(new ComponentAnimation.Callback() {
          @Override
          public void onAnimationFinished() {
            // Empty
          }
        });
      }
    });
    componentAnimations.get(1).startAnimation(new ComponentAnimation.Callback() {
      @Override
      public void onAnimationFinished() {
        // Empty
      }
    });
  }
}
