package com.water.fable.stores;

import android.text.TextUtils;

import com.water.fable.actions.Action;
import com.water.fable.dispatchs.Dispatcher;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by lixinke on 2017/4/11.
 */

public class SplashStore extends Store {

  public static final String ACTION_TYPE_SPLASH_FINISH = "action_splash_finish";
  public static final String EVENT_SPLASH_FINISH = "event_splash_finish";

  public SplashStore(Dispatcher dispatcher) {
    super(dispatcher);
  }

  @Override
  public IStoreChangeEvent changeEvent() {
    return new IStoreChangeEvent() {
      @Override
      public String getEventName() {
        return EVENT_SPLASH_FINISH;
      }
    };
  }

  @Subscribe
  @Override
  public void onAction(Action action) {
    if (TextUtils.equals(ACTION_TYPE_SPLASH_FINISH, action.getType())) {
      emitStoreChange();
    }
  }
}
