package com.jlmd.android.circularloadinganimation.view.animator;

import com.jlmd.android.circularloadinganimation.view.component.ComponentViewAnimation;
import com.jlmd.android.circularloadinganimation.view.component.InitialCenterCircleView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;
import com.jlmd.android.circularloadinganimation.view.component.SideArcsView;
import com.jlmd.android.circularloadinganimation.view.component.TopCircleView;

/**
 * @author jlmd
 */
public class AnimatorHelper {

  private InitialCenterCircleView initialCenterCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private TopCircleView topCircleView;
  private MainCircleView mainCircleView;

  public void setComponentViewAnimations(InitialCenterCircleView initialCenterCircleView,
      RightCircleView rightCircleView, SideArcsView sideArcsView, TopCircleView topCircleView,
      MainCircleView mainCircleView) {
    this.initialCenterCircleView = initialCenterCircleView;
    this.rightCircleView = rightCircleView;
    this.sideArcsView = sideArcsView;
    this.topCircleView = topCircleView;
    this.mainCircleView = mainCircleView;
  }

  public void startAnimator() {
    initialCenterCircleView.showView();
    initialCenterCircleView.startAnimation(new ComponentViewAnimation.Callback() {
      @Override
      public void onAnimationFinished() {
        sideArcsView.showView();
        sideArcsView.startAnimation(new ComponentViewAnimation.Callback() {
          @Override
          public void onAnimationFinished() {
            sideArcsView.hideView();
            topCircleView.showView();
            topCircleView.startAnimation(new ComponentViewAnimation.Callback() {
              @Override
              public void onAnimationFinished() {
                mainCircleView.showView();
                mainCircleView.startAnimation(new ComponentViewAnimation.Callback() {
                  @Override
                  public void onAnimationFinished() {

                  }
                });
              }
            });
          }
        });
      }
    });
    rightCircleView.showView();
    rightCircleView.startAnimation(new ComponentViewAnimation.Callback() {
      @Override
      public void onAnimationFinished() {
        // Empty
      }
    });
  }
}
