package com.example.karthik.expresso;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.TimeoutError;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by karthik on 5/4/16.
 */
public class MainRecyclerViewRowHolder extends RecyclerView.ViewHolder {

    protected NetworkImageView thumbnail;
    protected TextView title;
    protected TextView subreddit;
    protected TextView post_time;
    protected TextView upvotes;
    protected TextView comments;
    protected TextView url;
    protected RelativeLayout relativeLayout;

    public MainRecyclerViewRowHolder(View view){
        super(view);
        this.thumbnail = (NetworkImageView) view.findViewById(R.id.networkImage);
        this.title = (TextView) view.findViewById(R.id.article_title);
        this.post_time = (TextView) view.findViewById(R.id.post_time);
        this.subreddit = (TextView) view.findViewById(R.id.subreddit);
        this.upvotes = (TextView) view.findViewById(R.id.upvotes);
        this.url = (TextView) view.findViewById(R.id.link_source);
        this.comments = (TextView) view.findViewById(R.id.comments);
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.rel_layout);
    }
}
