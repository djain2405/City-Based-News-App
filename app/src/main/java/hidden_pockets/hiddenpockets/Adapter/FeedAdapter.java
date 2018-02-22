package hidden_pockets.hiddenpockets.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hidden_pockets.hiddenpockets.ArticleViewActivity;
import hidden_pockets.hiddenpockets.FeedActivity;
import hidden_pockets.hiddenpockets.R;
import hidden_pockets.hiddenpockets.constant.FeedContent;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by divya on 11/10/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {

    private static List<FeedContent> feeds;
    private static Context mContext;

    public class FeedHolder extends RecyclerView.ViewHolder  {
        private TextView title;
        private TextView excerpt;
        private FeedContent feed;

        public FeedHolder(View v)
        {
            super(v);
            title = (TextView) v.findViewById(R.id.feedTitle);
            excerpt = (TextView) v.findViewById(R.id.feedexcerpt);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, ArticleViewActivity.class);
                    i.putExtra("title", feeds.get(getAdapterPosition()).getTitle());
                    i.putExtra("Url", feeds.get(getAdapterPosition()).getURL());
                    startActivity(mContext, i, null);
                }
            });
        }

        public void bindFeed(FeedContent feed) {
            this.feed = feed;
            title.setText(Html.fromHtml(feed.getTitle()));
            excerpt.setText(Html.fromHtml(feed.getExcerpt()));
        }
    }

    public FeedAdapter(Context context, List<FeedContent> feeds)
    {
        mContext = context;
        this.feeds = feeds;
    }
    @Override
    public FeedAdapter.FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.feed_content_layout,
                parent, false);
        return new FeedHolder(v);

    }

    @Override
    public void onBindViewHolder(FeedAdapter.FeedHolder holder, int position) {
            FeedContent feed = feeds.get(position);
            holder.bindFeed(feed);
    }

    @Override
    public int getItemCount() {
        return feeds != null ? feeds.size() : 0;
    }
}
