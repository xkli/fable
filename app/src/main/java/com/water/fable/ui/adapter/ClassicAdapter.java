package com.water.fable.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.water.fable.R;
import com.water.fable.model.Fable;
import com.water.fable.ui.ClassicFragment.OnListFragmentInteractionListener;

import java.util.List;


public class ClassicAdapter extends RecyclerView.Adapter<ClassicAdapter.ViewHolder> {

  private final Context mContext;
  private final List<Fable> mValues;
  private final OnListFragmentInteractionListener mListener;

  public ClassicAdapter(Context context,List<Fable> items, OnListFragmentInteractionListener listener) {
    mContext=context;
    mValues = items;
    mListener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_classic_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.mItem = mValues.get(position);
    Glide.with(mContext).load(mValues.get(position).img).into(holder.mImageView);
    holder.mIdView.setText(mValues.get(position).title);
    holder.mAuthorView.setText(mValues.get(position).author);
    holder.mContentView.setText(mValues.get(position).content);

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mIdView;
    public final TextView mAuthorView;
    public final TextView mContentView;
    public final ImageView mImageView;
    public Fable mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      mImageView=(ImageView)view.findViewById(R.id.classic_img);
      mIdView = (TextView) view.findViewById(R.id.classic_title);
      mAuthorView=(TextView)view.findViewById(R.id.classic_subtitle);
      mContentView = (TextView) view.findViewById(R.id.classic_brief);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + mContentView.getText() + "'";
    }
  }
}
