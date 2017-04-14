package com.water.fable.stores;

import android.text.TextUtils;

import com.water.fable.actions.Action;
import com.water.fable.actions.DetailAction;
import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.model.Fable;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by lixinke on 2017/4/12.
 */

public class DetailStore extends Store {

  private Fable mFable;

  public DetailStore(Dispatcher dispatcher) {
    super(dispatcher);
  }

  public Fable getFable() {
    return mFable;
  }

  @Override
  public StoreChangeEvent changeEvent(String eventName) {
    return new StoreChangeEvent(eventName);
  }

  @Subscribe
  @Override
  public void onAction(Action action) {
    if (TextUtils.equals(DetailAction.ACTION_DETAIL_MSG, action.getType())) {
      mFable = ((DetailAction) action).getData();
      changeEvent(DetailAction.ACTION_DETAIL_MSG);
    }
  }
}
