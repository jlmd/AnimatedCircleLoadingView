package com.github.jlmd.animatedcircleloadingview.component.finish;

import android.content.Context;
import com.github.jlmd.animatedcircleloadingview.R;
/**
 * @author jlmd
 */
public class FinishedFailureView extends FinishedView {

  public FinishedFailureView(Context context, int parentWidth, int mainColor, int secondaryColor) {
    super(context, parentWidth, mainColor, secondaryColor);
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