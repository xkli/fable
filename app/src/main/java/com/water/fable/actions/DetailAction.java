package com.water.fable.actions;

import com.water.fable.model.Fable;

/**
 * Created by lixinke on 2017/4/12.
 */

public class DetailAction extends Action<Fable> {
  public static final String ACTION_DETAIL_MSG="detail_msg";

  public DetailAction(String type, Fable data) {
    super(type, data);
  }
}
