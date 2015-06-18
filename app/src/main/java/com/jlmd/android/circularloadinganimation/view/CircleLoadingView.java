package com.jlmd.android.circularloadinganimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jlmd.android.circularloadinganimation.view.animator.AnimatorHelper;
import com.jlmd.android.circularloadinganimation.view.component.InitialCenterCircleView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.TopCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;
import com.jlmd.android.circularloadinganimation.view.component.SideArcsView;

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
    initialCenterCircleView = new InitialCenterCircleView(context);
    rightCircleView = new RightCircleView(context);
    sideArcsView = new SideArcsView(context);
    topCircleView = new TopCircleView(context);
    mainCircleView = new MainCircleView(context);
  }

  private void addComponentsViews() {
    addView(initialCenterCircleView);
    addView(rightCircleView);
    addView(sideArcsView);
    addView(topCircleView);
    addView(mainCircleView);
  }

  private void initAnimatorHelper() {
    animatorHelper = new AnimatorHelper();
    animatorHelper.setComponentViewAnimations(initialCenterCircleView, rightCircleView,
        sideArcsView, topCircleView, mainCircleView);
  }

  private void startAnimation() {
    animatorHelper.startAnimator();
  }
}
