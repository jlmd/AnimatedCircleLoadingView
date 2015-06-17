package com.jlmd.android.circularloadinganimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jlmd.android.circularloadinganimation.view.animator.AnimatorHelper;
import com.jlmd.android.circularloadinganimation.view.component.SideArcsView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightArcView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;

/**
 * @author jlmd
 */
public class CircleLoadingView extends FrameLayout {

  private final Context context;
  private MainCircleView mainCircle;
  private RightCircleView rightCircle;
  private SideArcsView sideArcsView;
  private RightArcView rightArcView;
  private AnimatorHelper animatorHelper;

  public CircleLoadingView(Context context) {
    super(context);
    this.context = context;
    init();
  }

  public CircleLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    init();
  }

  public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    init();
  }

  private void init() {
    initComponents();
    addComponentsViews();
    initAnimatorHelper();
    startAnimation();
  }

  private void initComponents() {
    mainCircle = new MainCircleView(context);
    rightCircle = new RightCircleView(context);
    sideArcsView = new SideArcsView(context);
  }

  private void addComponentsViews() {
    addView(mainCircle);
    addView(rightCircle);
    addView(sideArcsView);
  }

  private void initAnimatorHelper() {
    animatorHelper = new AnimatorHelper();
    animatorHelper.setComponentAnimations(mainCircle, rightCircle, sideArcsView);
  }

  private void startAnimation() {
    animatorHelper.startAnimator();
  }
}
