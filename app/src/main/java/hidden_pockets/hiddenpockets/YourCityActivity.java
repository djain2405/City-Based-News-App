package hidden_pockets.hiddenpockets;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import hidden_pockets.hiddenpockets.Adapter.CityListAdapter;
import hidden_pockets.hiddenpockets.constant.City;

/**
 * Created by divya on 11/11/2017.
 */

public class YourCityActivity extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private LinearLayoutManager mLinearLayoutManager;
        private CityListAdapter mAdapter;
        private ArrayList<City> mCityList;
        private DividerItemDecoration divider;


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourcity);

        mCityList = new ArrayList<>();
        City[] cities = City.cities();
        for (int i = 0; i < cities.length; i++) {
            mCityList.add(cities[i]);
            //System.out.println("Divya jain.." + cities[i].toString());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new RetrieveCityId().execute(getApplicationContext());

            mAdapter = new CityListAdapter(mCityList);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
            divider = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
            Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
            divider.setDrawable(verticalDivider);
            mRecyclerView.addItemDecoration(divider);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
          onBackPressed();
          return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
