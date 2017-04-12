package com.water.fable.stores;

import com.water.fable.actions.Action;
import com.water.fable.actions.ClassicAction;
import com.water.fable.actions.NetworkAction;
import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.model.Fable;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixinke on 2017/4/11.
 */

public class ClassicStore extends Store {

  public static final String EVENT_CLASSIC_INFO_CHANGE = "classic_info_change";
  private List<Fable> mFables;

  public ClassicStore(Dispatcher dispatcher) {
    super(dispatcher);
    mFables = new ArrayList<>();
  }

  @Override
  public StoreChangeEvent changeEvent(String eventName) {
    return new StoreChangeEvent(eventName);
  }

  @Subscribe
  @Override
  public void onAction(Action action) {

    String eventName = null;
    switch (action.getType()) {
      case ClassicAction.ACTION_CLASSIC_NEW_INFO:
        ClassicAction classicAction = (ClassicAction) action;
        mFables.addAll(classicAction.getData());
        eventName = EVENT_CLASSIC_INFO_CHANGE;
        break;
      case NetworkAction.ACTION_NETWORK_COMPLETE:
        eventName = action.getType();
        break;
      case NetworkAction.ACTION_NETWORK_ERROR:
        eventName = action.getType();
        break;
    }
    emitStoreChange(eventName);
  }

  public List<Fable> getClassicInfos() {
    return mFables;
  }
}
