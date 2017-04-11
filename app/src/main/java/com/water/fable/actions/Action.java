package com.water.fable.actions;

/**
 * Created by lixinke on 2017/4/11.
 */

public class Action<T> {

  private final String mType;
  private final T mData;

  public Action(String type, T data) {
    mType = type;
    mData = data;
  }

  public String getType() {
    return mType;
  }

  public T getData() {
    return mData;
  }
}
