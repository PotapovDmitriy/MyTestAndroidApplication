package com.example.mytestandroidapplication;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestandroidapplication.dummy.DummyContent;


public class WeatherListActivity extends FragmentActivity implements WeatherItemFragment.OnListFragmentInteractionListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_list);

        recyclerView = (RecyclerView) findViewById(R.id.weather_fragment);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new WeatherRecycleViewAdapter(DummyContent.ITEMS);
        recyclerView.setAdapter(mAdapter);

    }



    @Override
    public void onListFragmentInteraction(DummyContent.WeatherDateItem item) {

    }
}
