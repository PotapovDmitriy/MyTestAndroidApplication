package com.example.mytestandroidapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestandroidapplication.R;
import com.example.mytestandroidapplication.WeatherItemFragment;
import com.example.mytestandroidapplication.WeatherRecycleViewAdapter;
import com.example.mytestandroidapplication.dummy.DummyContent;

public class WeatherListFragment extends Fragment implements WeatherItemFragment.OnListFragmentInteractionListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.temp_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.weather_fragment);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new WeatherRecycleViewAdapter(DummyContent.ITEMS);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.WeatherDateItem item) {

    }
}
