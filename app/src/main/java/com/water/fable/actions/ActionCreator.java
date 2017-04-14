package com.water.fable.actions;

import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.model.Fable;
import com.water.fable.stores.SplashStore;

import java.util.List;

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

  public void onNetworkError() {
    mDispatcher.dispatch(new Action(NetworkAction.ACTION_NETWORK_ERROR, null));
  }

  public void onNetworkComplete() {
    mDispatcher.dispatch(new Action(NetworkAction.ACTION_NETWORK_COMPLETE, null));
  }

  public void onNetworkSuccess(List<Fable> fables) {
    ClassicAction action = new ClassicAction(ClassicAction.ACTION_CLASSIC_NEW_INFO, fables);
    mDispatcher.dispatch(action);
  }

  public void gotoDetail(Fable item) {
    DetailAction action = new DetailAction(DetailAction.ACTION_DETAIL_MSG, item);
    mDispatcher.dispatchSticky(action);
  }
}
