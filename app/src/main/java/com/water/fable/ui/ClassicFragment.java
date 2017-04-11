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

import com.water.fable.R;
import com.water.fable.model.Fable;
import com.water.fable.ui.adapter.ClassicItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassicFragment extends Fragment {


  private ProgressBar mProgressBar;
  private TextView mTipView;

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
    mProgressBar.setVisibility(View.GONE);
    mTipView = (TextView) view.findViewById(R.id.tip_view);
    mTipView.setVisibility(View.GONE);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    List<Fable> fables = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      Fable fable = new Fable();
      fable.id = String.valueOf(Math.random());
      fable.content = "守株待兔";
      fables.add(fable);
    }
    recyclerView.setAdapter(new ClassicItemAdapter(fables, null));
    return view;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  public interface OnListFragmentInteractionListener {

    void onListFragmentInteraction(Fable item);
  }
}
