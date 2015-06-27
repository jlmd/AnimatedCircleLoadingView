package com.jlmd.android.circularloadinganimation.view.component.finish;

import android.content.Context;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class FinishedFailureView extends FinishedView {

  public FinishedFailureView(Context context, int parentWidth) {
    super(context, parentWidth);
  }

  @Override
  protected int getDrawable() {
    return R.drawable.ic_failure_mark;
  }

  @Override
  protected int getCircleColor() {
    return secondaryColor;
  }
}