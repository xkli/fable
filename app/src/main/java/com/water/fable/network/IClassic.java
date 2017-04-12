package com.water.fable.network;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lixinke on 2017/4/11.
 */

public interface IClassic {

  @GET("classic.json")
  Flowable<ResponseBody> getClassics(@QueryMap Map<String, String> options);
}
