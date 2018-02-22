package hidden_pockets.hiddenpockets.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import hidden_pockets.hiddenpockets.FeedActivity;
import hidden_pockets.hiddenpockets.R;
import hidden_pockets.hiddenpockets.RetrieveFeedTask;
import hidden_pockets.hiddenpockets.constant.City;

/**
 * Created by Divya Jain on 8/3/2017.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityHolder> {

    public ArrayList<City> mCities;
    RetrieveFeedTask retrieveFeedTask;

    public class CityHolder extends RecyclerView.ViewHolder  {

        private TextView mItemDescription;
        private City mCity;


        public CityHolder(View v) {
            super(v);

            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = v.getContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE);
                    HashMap<String, Integer> map= (HashMap<String, Integer>) pref.getAll();
                    String city = mCities.get(getAdapterPosition()).toString();
                    Integer value=map.get(city);

                    retrieveFeedTask = new RetrieveFeedTask(v.getContext());
                    retrieveFeedTask.execute(value, city);

                }

            });
        }

        public void bindCity(City city) {
            mCity = city;
            mItemDescription.setText(city.toString());
        }
    }



    public CityListAdapter(ArrayList<City> cities) {
        mCities = cities;
    }
    @Override
    public CityListAdapter.CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cityrecyclerview_item_row, parent, false);
        return new CityHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CityListAdapter.CityHolder holder, int position) {

        City itemPhoto = mCities.get(position);
        holder.bindCity(itemPhoto);

    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }


}

