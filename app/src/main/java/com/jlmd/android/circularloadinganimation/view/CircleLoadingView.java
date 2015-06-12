package com.jlmd.android.circularloadinganimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jlmd.android.circularloadinganimation.view.component.LeftOutlineView;
import com.jlmd.android.circularloadinganimation.view.component.MainCircleView;
import com.jlmd.android.circularloadinganimation.view.component.RightCircleView;

/**
 * @author jlmd
 */
public class CircleLoadingView extends FrameLayout {

  private final Context context;
  private MainCircleView mainCircle;
  private RightCircleView rightCircle;
  private LeftOutlineView leftOutlineView;

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
    startAnimation();
  }

  private void initComponents() {
    mainCircle = new MainCircleView(context);
    rightCircle = new RightCircleView(context);
    leftOutlineView = new LeftOutlineView(context);
  }

  private void addComponentsViews() {
    addView(mainCircle);
    addView(rightCircle);
  }

  private void startAnimation() {
    mainCircle.startAnimation();
    rightCircle.startAnimation();
  }
}
