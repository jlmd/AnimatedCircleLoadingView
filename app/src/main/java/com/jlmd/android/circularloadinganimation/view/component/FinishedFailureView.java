package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class FinishedFailureView extends FinishedView {

  public FinishedFailureView(Context context, int parentWidth) {
    super(context, parentWidth);
  }

  public FinishedFailureView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FinishedFailureView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected int getDrawable() {
    return R.drawable.ic_failure_mark;
  }

  @Override
  protected int getCircleColor() {
    return R.color.secondary_circle;
  }
}