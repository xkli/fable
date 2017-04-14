package com.water.fable.dispatchs;

import com.water.fable.actions.Action;
import com.water.fable.stores.Store;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lixinke on 2017/4/11.
 */

public class Dispatcher {

  private static final Dispatcher sDispatcher = new Dispatcher();

  public static Dispatcher get() {
    return sDispatcher;
  }

  public void register(Store store) {
    EventBus.getDefault().register(store);
  }

  public void unregister(Store store) {
    EventBus.getDefault().unregister(store);
  }

  public void dispatch(Action action) {
    EventBus.getDefault().post(action);
  }

  public void dispatchSticky(Action action) {
    EventBus.getDefault().postSticky(action);
  }
}
