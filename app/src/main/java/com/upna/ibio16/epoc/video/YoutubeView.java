package com.upna.ibio16.epoc.video;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.upna.ibio16.epoc.R;

/**
 * Created by hkfuertes on 31/03/16.
 */
public class YoutubeView extends WebView{
    private static final String percent = "%";
    private static final String template = "<html><body style='background-color:black;'><iframe width='100"+percent+"' src='https://www.youtube.com/embed/ID' frameborder='0' allowfullscreen></iframe></body></html>";
    private String videoId = null;

    public YoutubeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.YoutubeView, 0, 0);
        try {
            videoId = ta.getString(R.styleable.YoutubeView_video_id);
            if(videoId != null){
                setVideoId(videoId);
            }
        } finally {
            ta.recycle();
        }
    }

    public void setVideoId(String videoId){
        this.videoId = videoId;
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        loadData(data(this.videoId), "text/html", "utf-8");
        //loadData(this.videoId, "text/text", "utf-8");
    }

    private String data(String url){
        String data = template;
        data = data.replace("ID",url);
        return data;
    }
}
