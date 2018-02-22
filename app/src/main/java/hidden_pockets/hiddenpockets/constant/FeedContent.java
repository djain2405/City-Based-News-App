package hidden_pockets.hiddenpockets.constant;

import java.io.Serializable;

/**
 * Created by divya on 11/10/2017.
 */

public class FeedContent implements Serializable{

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    String title;
    String excerpt;
    String URL;
}
