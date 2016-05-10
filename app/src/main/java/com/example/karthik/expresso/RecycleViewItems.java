package com.example.karthik.expresso;


/**
 * Created by karthik on 5/4/16.
 */
public class RecycleViewItems {
    private String thumbnail;
    private String url;
    private String comments;
    private String id;
    private String title;
    private String post_time;
    private String upvotes;
    private String subreddit;


    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getThumbnail(){
        return thumbnail;
    }
    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getComments(){
        return comments;
    }
    public void setComments(String comments){
        this.comments = comments;
    }
    public String getPermalink(){
        return id;
    }
    public void setPermalink(String id){
        this.id = id;
    }
    public String getPost_time(){
        return post_time;
    }
    public void setPost_time(String post_time){
        String date = post_time;
        java.util.Date time = new java.util.Date(Double.valueOf(date).longValue()*1000);
        this.post_time = time.toString();

    }
    public String getUpvotes(){
        return upvotes;
    }
    public void setUpvotes(String upvotes){
        this.upvotes = upvotes;
    }
    public String getSubreddit(){
        return subreddit;
    }
    public void setSubreddit(String subreddit){
        this.subreddit = subreddit;
    }
}
