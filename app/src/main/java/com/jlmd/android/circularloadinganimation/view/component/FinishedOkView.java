package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class FinishedOkView extends FinishedView {

  public FinishedOkView(Context context) {
    super(context);
  }

  public FinishedOkView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FinishedOkView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected int getDrawable() {
    return R.drawable.ic_checked_mark;
  }
}
