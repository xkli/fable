package com.water.fable.actions;

import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.stores.SplashStore;

/**
 * Created by lixinke on 2017/4/11.
 */

public class ActionCreator {

  private static ActionCreator sActionCreator;
  private Dispatcher mDispatcher;

  private ActionCreator(Dispatcher dispatcher) {
    mDispatcher = dispatcher;
  }

  public static synchronized ActionCreator get(Dispatcher dispatcher) {
    if (sActionCreator == null) {
      sActionCreator = new ActionCreator(dispatcher);
    }
    return sActionCreator;
  }

  public void onSplashFinish() {
    mDispatcher.dispatch(new Action(SplashStore.ACTION_TYPE_SPLASH_FINISH, null));
  }
}
