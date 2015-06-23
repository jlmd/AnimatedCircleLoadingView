package com.jlmd.android.circularloadinganimation.view.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.jlmd.android.circularloadinganimation.R;

/**
 * @author jlmd
 */
public abstract class FinishedView extends ComponentViewAnimation {

  private static final int CIRCLE_MAX_RADIUS = 140;
  private Bitmap finishedBitmap;

  public FinishedView(Context context) {
    super(context);
    init();
  }

  public FinishedView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FinishedView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    finishedBitmap = BitmapFactory.decodeResource(getResources(), getDrawable());
    finishedBitmap =
        Bitmap.createScaledBitmap(finishedBitmap, CIRCLE_MAX_RADIUS, CIRCLE_MAX_RADIUS, true);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawCircle(canvas);
    drawCheckedMark(canvas);
  }

  private void drawCheckedMark(Canvas canvas) {
    float centerY = getHeight() / 2;
    float centerX = getWidth() / 2;
    canvas.drawBitmap(finishedBitmap, centerX - finishedBitmap.getWidth() / 2,
        centerY - finishedBitmap.getHeight() / 2, new Paint());
  }

  public void drawCircle(Canvas canvas) {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setColor(getResources().getColor(R.color.main_circle));
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, CIRCLE_MAX_RADIUS, paint);
  }

  protected abstract int getDrawable();
}
