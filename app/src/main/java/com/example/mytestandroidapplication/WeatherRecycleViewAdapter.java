package com.example.mytestandroidapplication;


import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mytestandroidapplication.dummy.DummyContent;

import java.util.List;


public class WeatherRecycleViewAdapter extends RecyclerView.Adapter<WeatherRecycleViewAdapter.ViewHolder> {

    private final List<DummyContent.WeatherDateItem> mValues;
    //private final OnListFragmentInteractionListener mListener;

    public WeatherRecycleViewAdapter(List<DummyContent.WeatherDateItem> items) {//, OnListFragmentInteractionListener listener) {
        mValues = items;
        //mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_weather_item, parent, false);
        return new ViewHolder(view);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).date);
        holder.mContentView.setText("City: " + mValues.get(position).city + " Temp: " + mValues.get(position).temp + "°С Wind: " + mValues.get(position).wind + " м\\c");
        holder.mDescriptionView.setText("Description: " + mValues.get(position).description);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mDescriptionView;
        public DummyContent.WeatherDateItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);

            mDescriptionView = (TextView) view.findViewById(R.id.desc);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
