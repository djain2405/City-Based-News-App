package hidden_pockets.hiddenpockets;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by divya on 11/11/2017.
 */

public class AskUsActivity extends AppCompatActivity {

//    private TextView title;
//    private EditText senderemail;
//    private EditText sendercity;
//    private EditText subject;
//    private EditText message;
//    private Button submit;

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askus);
        getSupportActionBar().setTitle("Ask Queries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webview = (WebView) findViewById(R.id.askusview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLScXk1t6hi3HCQM8f27iBb709bYJekdoV10p8gIAZxzdEBZpfg/viewform?usp=sf_link");

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
