package com.water.fable.actions;

import com.water.fable.model.Fable;

import java.util.List;

/**
 * Created by lixinke on 2017/4/11.
 */

public class ClassicAction extends Action<List<Fable>> {

  public static final String ACTION_CLASSIC_NEW_INFO = "classic_new_info";

  public ClassicAction(String type, List<Fable> data) {
    super(type, data);
  }
}
