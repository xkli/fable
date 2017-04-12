package com.water.fable.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.water.fable.R;
import com.water.fable.actions.ActionCreator;
import com.water.fable.actions.NetworkAction;
import com.water.fable.constants.Constants;
import com.water.fable.dispatchs.Dispatcher;
import com.water.fable.model.Fable;
import com.water.fable.network.IClassic;
import com.water.fable.stores.ClassicStore;
import com.water.fable.stores.Store;
import com.water.fable.ui.adapter.ClassicAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ClassicFragment extends Fragment {


  private ProgressBar mProgressBar;
  private TextView mTipView;
  private ClassicAdapter mClassicAdapter;

  private ActionCreator mActionCreator;
  private Dispatcher mDispatcher;
  private ClassicStore mClassicStore;

  public static ClassicFragment newInstance() {
    ClassicFragment fragment = new ClassicFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }


  public ClassicFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_classic_item_list, container, false);

    mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    mTipView = (TextView) view.findViewById(R.id.tip_view);

    sendRequest();

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    mClassicAdapter=new ClassicAdapter(mClassicStore.getClassicInfos(),null);
    recyclerView.setAdapter(mClassicAdapter);
    return view;
  }

  private void sendRequest() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(Constants.BASIC_HOST)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    IClassic classic = retrofit.create(IClassic.class);
    Map<String, String> params = new HashMap<>();
    Flowable<ResponseBody> flowable = classic.getClassics(params);
    flowable.subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<ResponseBody>() {
      @Override
      public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(ResponseBody responseBody) {
        try {
          String value = responseBody.string();
          parseData(value);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable t) {
        mActionCreator.onNetworkError();
      }

      @Override
      public void onComplete() {
        mActionCreator.onNetworkComplete();
      }
    });
  }

  private void parseData(String value) {
    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(value);
    final List<Fable> fables = new ArrayList<>();
    if (jsonObject != null) {
      Set<String> keySet = jsonObject.keySet();
      for (String string : keySet) {
        Fable fable = JSON.toJavaObject(jsonObject.getJSONObject(string), Fable.class);
        fables.add(fable);
      }
    }
    mActionCreator.onNetworkSuccess(fables);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    setHasOptionsMenu(true);
    getActivity().setTitle("经典");
    mDispatcher = Dispatcher.get();
    mActionCreator = ActionCreator.get(mDispatcher);
    mClassicStore = new ClassicStore(mDispatcher);

    mDispatcher.register(mClassicStore);
    mClassicStore.bind(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onHttpSuccess(Store.StoreChangeEvent event) {
    if (ClassicStore.EVENT_CLASSIC_INFO_CHANGE.equals(event.getEventName())) {
      mClassicAdapter.notifyDataSetChanged();
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onHttpError(Store.StoreChangeEvent event) {
    if(NetworkAction.ACTION_NETWORK_ERROR.equals(event.getEventName())){
      mProgressBar.setVisibility(View.GONE);
      mTipView.setVisibility(View.VISIBLE);
      mTipView.setText("网络错误!");
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onHttpComplete(Store.StoreChangeEvent event) {
    if(NetworkAction.ACTION_NETWORK_COMPLETE.equals(event.getEventName())){
      mProgressBar.setVisibility(View.GONE);
      mTipView.setVisibility(View.GONE);
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mDispatcher.unregister(mClassicStore);
    mClassicStore.unbind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public interface OnListFragmentInteractionListener {

    void onListFragmentInteraction(Fable item);
  }
}
