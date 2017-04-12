package com.water.fable.actions;

/**
 * Created by lixinke on 2017/4/11.
 */

public class NetworkAction extends Action {

  public static final String ACTION_NETWORK_ERROR = "network_error";
  public static final String ACTION_NETWORK_COMPLETE = "network_complete";

  public NetworkAction(String type, Object data) {
    super(type, data);
  }
}
