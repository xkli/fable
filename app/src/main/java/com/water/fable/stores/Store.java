package com.water.fable.stores;

import com.water.fable.actions.Action;
import com.water.fable.dispatchs.Dispatcher;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lixinke on 2017/4/11.
 */

public abstract class Store {

  private final Dispatcher mDispatcher;

  public Store(Dispatcher dispatcher) {
    mDispatcher = dispatcher;
  }

  public void bind(Object view) {
    EventBus.getDefault().register(view);
  }

  public void unbind(Object view) {
    EventBus.getDefault().unregister(view);
  }

  public void emitStoreChange(String eventName) {
    EventBus.getDefault().post(changeEvent(eventName));
  }

  public abstract StoreChangeEvent changeEvent(String eventName);

  public abstract void onAction(Action action);

  public class StoreChangeEvent {

    private String mEventName;

    public StoreChangeEvent(String eventName) {
      mEventName = eventName;
    }

    public String getEventName() {
      return mEventName;
    }
  }
}
