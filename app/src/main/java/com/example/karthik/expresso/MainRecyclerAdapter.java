package com.example.karthik.expresso;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by karthik on 5/4/16.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewRowHolder> {
    private List<RecycleViewItems> recycleViewItemsLists;
    private Context mContext;
    private RecycleViewItems holla = new RecycleViewItems();
    private ImageLoader mImageLoader;
    private int focusedItem = 0;

    public MainRecyclerAdapter(Context context, List<RecycleViewItems> recycleViewItemsLists){
        this.recycleViewItemsLists = recycleViewItemsLists;
        this.mContext = context;

    }

    @Override
    public MainRecyclerViewRowHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,null);
        MainRecyclerViewRowHolder holder = new MainRecyclerViewRowHolder(v);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // This is code to take to url
                TextView redditUrl = (TextView) v.findViewById(R.id.link_source);
                String postUrl = redditUrl.getText().toString();
                Intent intent = new Intent(mContext,WebActivity.class);
                intent.putExtra("url",postUrl);
                mContext.startActivity(intent);



            }

        });
        return holder;

    }

    @Override
    public void onBindViewHolder(final MainRecyclerViewRowHolder mainRecyclerViewRowHolder, int position) {
        RecycleViewItems recycleViewItems = recycleViewItemsLists.get(position);
        mainRecyclerViewRowHolder.itemView.setSelected(focusedItem == position);
        mainRecyclerViewRowHolder.getLayoutPosition();
        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
        mainRecyclerViewRowHolder.thumbnail.setImageUrl(recycleViewItems.getThumbnail(), mImageLoader);
        mainRecyclerViewRowHolder.thumbnail.setDefaultImageResId(R.drawable.reddit_placeholder);
        mainRecyclerViewRowHolder.title.setText(Html.fromHtml(recycleViewItems.getTitle()));
        mainRecyclerViewRowHolder.subreddit.setText(Html.fromHtml(recycleViewItems.getSubreddit()));
        mainRecyclerViewRowHolder.post_time.setText(Html.fromHtml(recycleViewItems.getPost_time()));
        mainRecyclerViewRowHolder.comments.setText(Html.fromHtml(recycleViewItems.getComments()));
        mainRecyclerViewRowHolder.url.setText(Html.fromHtml(recycleViewItems.getUrl()));
        mainRecyclerViewRowHolder.upvotes.setText(Html.fromHtml(recycleViewItems.getUpvotes()));


    }
    public void ClearAdapter(){

        recycleViewItemsLists.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return(null != recycleViewItemsLists ? recycleViewItemsLists.size(): 0);
    }




}
