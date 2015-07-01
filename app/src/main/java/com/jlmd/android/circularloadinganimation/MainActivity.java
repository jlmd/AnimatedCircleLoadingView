package com.jlmd.android.circularloadinganimation;

import android.os.Bundle;
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
    startPercentMockThread();
  }

  private void startLoading() {
    circleLoadingView.startDeterminate();
  }

  private void startPercentMockThread() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1500);
          for (int i = 0; i <= 100; i++) {
            Thread.sleep(65);
            changePercent(i);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(runnable).start();
  }

  private void changePercent(final int percent) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        circleLoadingView.setPercent(percent);
      }
    });
  }
}