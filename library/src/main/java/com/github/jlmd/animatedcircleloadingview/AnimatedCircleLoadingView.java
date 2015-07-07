package com.github.jlmd.animatedcircleloadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.github.jlmd.animatedcircleloadingview.animator.ViewAnimator;
import com.github.jlmd.animatedcircleloadingview.component.InitialCenterCircleView;
import com.github.jlmd.animatedcircleloadingview.component.MainCircleView;
import com.github.jlmd.animatedcircleloadingview.component.PercentIndicatorView;
import com.github.jlmd.animatedcircleloadingview.component.RightCircleView;
import com.github.jlmd.animatedcircleloadingview.component.SideArcsView;
import com.github.jlmd.animatedcircleloadingview.component.TopCircleBorderView;
import com.github.jlmd.animatedcircleloadingview.component.finish.FinishedFailureView;
import com.github.jlmd.animatedcircleloadingview.component.finish.FinishedOkView;

/**
 * @author jlmd
 */
public class AnimatedCircleLoadingView extends FrameLayout {

  private static final String DEFAULT_HEX_MAIN_COLOR = "#FF9A00";
  private static final String DEFAULT_HEX_SECONDARY_COLOR = "#BDBDBD";
  private final Context context;
  private InitialCenterCircleView initialCenterCircleView;
  private MainCircleView mainCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private TopCircleBorderView topCircleBorderView;
  private FinishedOkView finishedOkView;
  private FinishedFailureView finishedFailureView;
  private PercentIndicatorView percentIndicatorView;
  private ViewAnimator viewAnimator;
  private boolean startAnimationIndeterminate;
  private boolean startAnimationDeterminate;
  private int mainColor;
  private int secondaryColor;

  public AnimatedCircleLoadingView(Context context) {
    super(context);
    this.context = context;
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    initAttributes(attrs);
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    initAttributes(attrs);
  }

  private void initAttributes(AttributeSet attrs) {
    TypedArray attributes =
        getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedCircleLoadingView);
    mainColor = attributes.getColor(R.styleable.AnimatedCircleLoadingView_mainColor,
        Color.parseColor(DEFAULT_HEX_MAIN_COLOR));
    secondaryColor = attributes.getColor(R.styleable.AnimatedCircleLoadingView_secondaryColor,
        Color.parseColor(DEFAULT_HEX_SECONDARY_COLOR));
    attributes.recycle();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    init();
    if (startAnimationIndeterminate) {
      viewAnimator.startAnimator();
      startAnimationIndeterminate = false;
    }
    if (startAnimationDeterminate) {
      addView(percentIndicatorView);
      viewAnimator.startAnimator();
      startAnimationDeterminate = false;
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // Force view to be a square
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }

  private void init() {
    initComponents();
    addComponentsViews();
    initAnimatorHelper();
  }

  private void initComponents() {
    int width = getWidth();
    initialCenterCircleView =
        new InitialCenterCircleView(context, width, mainColor, secondaryColor);
    rightCircleView = new RightCircleView(context, width, mainColor, secondaryColor);
    sideArcsView = new SideArcsView(context, width, mainColor, secondaryColor);
    topCircleBorderView = new TopCircleBorderView(context, width, mainColor, secondaryColor);
    mainCircleView = new MainCircleView(context, width, mainColor, secondaryColor);
    finishedOkView = new FinishedOkView(context, width, mainColor, secondaryColor);
    finishedFailureView = new FinishedFailureView(context, width, mainColor, secondaryColor);
    percentIndicatorView = new PercentIndicatorView(context, width);
  }

  private void addComponentsViews() {
    addView(initialCenterCircleView);
    addView(rightCircleView);
    addView(sideArcsView);
    addView(topCircleBorderView);
    addView(mainCircleView);
    addView(finishedOkView);
    addView(finishedFailureView);
  }

  private void initAnimatorHelper() {
    viewAnimator = new ViewAnimator();
    viewAnimator.setComponentViewAnimations(initialCenterCircleView, rightCircleView, sideArcsView,
        topCircleBorderView, mainCircleView, finishedOkView, finishedFailureView,
        percentIndicatorView);
  }

  public void startIndeterminate() {
    startAnimationIndeterminate = true;
  }

  public void startDeterminate() {
    startAnimationDeterminate = true;
  }

  public void setPercent(int percent) {
    if (percentIndicatorView != null) {
      percentIndicatorView.setPercent(percent);
      if (percent == 100) {
        viewAnimator.finishOk();
      }
    }
  }

  public void stopOk() {
    viewAnimator.finishOk();
  }

  public void stopFailure() {
    viewAnimator.finishFailure();
  }
}
