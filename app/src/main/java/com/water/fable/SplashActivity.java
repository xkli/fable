package com.water.fable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.water.fable.actions.ActionCreator;
import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.stores.SplashStore;
import com.water.fable.stores.Store;
import com.water.fable.ui.MainActivity;

import org.greenrobot.eventbus.Subscribe;


public class SplashActivity extends AppCompatActivity {

  private SplashStore mSplashStore;
  private Dispatcher mDispatcher;
  private ActionCreator mActionCreator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    mDispatcher = Dispatcher.get();
    mSplashStore = new SplashStore(mDispatcher);
    mActionCreator = ActionCreator.get(mDispatcher);

    mDispatcher.register(mSplashStore);
    mSplashStore.bind(this);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mActionCreator.onSplashFinish();
      }
    }, 1000);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mDispatcher.unregister(mSplashStore);
    mSplashStore.unbind(this);
  }

  @Subscribe
  public void onSplashFinish(Store.StoreChangeEvent event) {
    if (TextUtils.equals(event.getEventName(), SplashStore.EVENT_SPLASH_FINISH)){
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }
  }
}
