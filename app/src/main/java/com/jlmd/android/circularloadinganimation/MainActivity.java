package com.jlmd.android.circularloadinganimation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import com.jlmd.android.circularloadinganimation.view.CircleLoadingView;

public class MainActivity extends ActionBarActivity {

  private CircleLoadingView circleLoadingView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    circleLoadingView = (CircleLoadingView) findViewById(R.id.circle_loading_view);
    startLoading();
    stopLoadingDelayed();
  }

  private void startLoading() {
    circleLoadingView.startIndeterminate();
  }

  private void stopLoadingDelayed() {
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        stopLoading();
      }
    }, 7000);
  }

  private void stopLoading() {
    circleLoadingView.stopOk();
  }
}
