package hidden_pockets.hiddenpockets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hidden_pockets.hiddenpockets.Adapter.FeedAdapter;
import hidden_pockets.hiddenpockets.constant.FeedContent;

/**
 * Created by divya on 11/10/2017.
 */

public class FeedActivity extends AppCompatActivity {

    private RecyclerView feedRecyclerview;
    private Intent intent;
    private List<FeedContent> feedList;
    FeedAdapter feedAdapter;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        feedRecyclerview = (RecyclerView) findViewById(R.id.rvFeed);

        intent = getIntent();
        feedList = new ArrayList<FeedContent>();
        feedList = (ArrayList<FeedContent>) intent.getExtras().getSerializable("feedlist");
        cityName = intent.getStringExtra("cityname");
        feedAdapter = new FeedAdapter(getApplicationContext(), feedList);
        getSupportActionBar().setTitle(cityName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        feedRecyclerview.setAdapter(feedAdapter);
        feedRecyclerview.setLayoutManager(layoutManager);

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
