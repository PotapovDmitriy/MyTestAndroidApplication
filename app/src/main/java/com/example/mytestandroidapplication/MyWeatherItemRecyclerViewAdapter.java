package com.example.mytestandroidapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mytestandroidapplication.WeatherItemFragment.OnListFragmentInteractionListener;
import com.example.mytestandroidapplication.dummy.DummyContent;
import com.example.mytestandroidapplication.dummy.DummyContent.WeatherDateItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link WeatherDateItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyWeatherItemRecyclerViewAdapter extends RecyclerView.Adapter<MyWeatherItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.WeatherDateItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyWeatherItemRecyclerViewAdapter(List<DummyContent.WeatherDateItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).date);
        holder.mContentView.setText(mValues.get(position).city + " " + mValues.get(position).temp );

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
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
        public final TextView mContentView;
        public DummyContent.WeatherDateItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
