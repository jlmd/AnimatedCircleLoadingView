package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public class LeftOutlineView extends View {

  public LeftOutlineView(Context context) {
    super(context);
  }

  public LeftOutlineView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LeftOutlineView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawArc(canvas);
  }

  private void drawArc(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(getResources().getColor(R.color.main_circle));
    paint.setStrokeWidth(1);
    paint.setStyle(Paint.Style.STROKE);
    final RectF oval = new RectF();
    oval.set(200, 400, 450, 600);
    canvas.drawArc(oval, 0, 270, false, paint);
  }

}
