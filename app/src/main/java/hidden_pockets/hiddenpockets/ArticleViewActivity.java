package hidden_pockets.hiddenpockets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ShareActionProvider;

/**
 * Created by divya on 11/10/2017.
 */

public class ArticleViewActivity extends AppCompatActivity {

    private WebView webview;
    Intent i;
    String url;
    String title;
    private ShareActionProvider mShareActionProvider;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        webview = (WebView) findViewById(R.id.articleview);
        webview.getSettings().setJavaScriptEnabled(true);
        i = getIntent();
        url = i.getStringExtra("Url");
        title = i.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview.loadUrl(url);
    }





}
