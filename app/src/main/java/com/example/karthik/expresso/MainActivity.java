package com.example.karthik.expresso;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MyRecyclerList";
    private List<RecycleViewItems> recycleViewItemsList = new ArrayList<RecycleViewItems>();
    private RecyclerView mRecyclerView;
    private EditText editText;
   // private Button goButton;

    private MainRecyclerAdapter adapter;
    private int counter = 0;
    private String count;
    private String jsonSubreddit;
    private String after_id;
    private static final String gaming = "gaming";
    private static final String ps4 = "ps4";
    private static final String all = "all";
   // private static String sub;
    private static final String bicycling = "bicycling";
    private static final String subredditUrl = "http://www.reddit.com/r/";
    private static final String jsonEnd = "/.json";
    private static final String qcount = "?count=";
    private static final String after = "&after=";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Infalte the layout for this recycler view

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button myButton = (Button) findViewById(R.id.go);
        final EditText editText = (EditText)findViewById(R.id.title);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = editText.getText().toString();
                updateList(sub);
                //hello
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        updateList(all);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                int lastFirstVisiblePosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
                loadMore(jsonSubreddit);
            }
        });

    }

    public void updateList(String subreddit){
        counter = 0;
        subreddit = subredditUrl + subreddit +jsonEnd;
        adapter = new MainRecyclerAdapter(MainActivity.this,recycleViewItemsList);
        mRecyclerView.setAdapter(adapter);

        //defining request Queue
        RequestQueue queue = Volley.newRequestQueue(this);

        adapter.ClearAdapter();
        showPd();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, subreddit, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidePd();

                try {
                    JSONObject data = response.getJSONObject("data");
                    after_id = data.getString("after");
                    JSONArray children = data.getJSONArray("children");

                    for (int i = 0; i < children.length(); i++) {
                        JSONObject post = children.getJSONObject(i).getJSONObject("data");
                        RecycleViewItems item = new RecycleViewItems();
                        item.setTitle(post.getString("title"));
                        item.setPermalink(post.getString("permalink"));
                        item.setUpvotes(post.getString("score"));
                        item.setSubreddit(post.getString("subreddit"));
                        item.setPost_time(post.getString("created_utc"));
                        item.setThumbnail(post.getString("thumbnail"));
                        item.setUrl(post.getString("url"));
                        item.setComments(post.getString("num_comments"));
                        jsonSubreddit = post.getString("subreddit");
                        recycleViewItemsList.add(item);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

            }, new Response.ErrorListener(){

                @Override
                        public void onErrorResponse(VolleyError error){
                    VolleyLog.d(TAG,"Error" + error.getMessage());
                    hidePd();
                }

        });
        queue.add(jsonObjectRequest);
    }


    public void loadMore(String subreddit){
        counter += 25;
        count = String.valueOf(counter);
        subreddit = jsonSubreddit;


        subreddit = subredditUrl + subreddit +jsonEnd +qcount+after+after_id;
        adapter = new MainRecyclerAdapter(MainActivity.this,recycleViewItemsList);
        mRecyclerView.setAdapter(adapter);

        //defining request Queue
        RequestQueue queue = Volley.newRequestQueue(this);
        showPd();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, subreddit, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidePd();

                try {
                    JSONObject data = response.getJSONObject("data");
                    after_id = data.getString("after");
                    JSONArray children = data.getJSONArray("children");

                    for (int i = 0; i < children.length(); i++) {
                        JSONObject post = children.getJSONObject(i).getJSONObject("data");
                        RecycleViewItems item = new RecycleViewItems();
                        item.setTitle(post.getString("title"));
                        item.setPermalink(post.getString("permalink"));
                        item.setUpvotes(post.getString("score"));
                        item.setSubreddit(post.getString("subreddit"));
                        item.setPost_time(post.getString("created_utc"));
                        item.setThumbnail(post.getString("thumbnail"));
                        item.setUrl(post.getString("url"));
                        item.setComments(post.getString("num_comments"));
                        jsonSubreddit = post.getString("subreddit");
                        recycleViewItemsList.add(item);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG,"Error" + error.getMessage());
                hidePd();
            }

        });
        queue.add(jsonObjectRequest);
    }



    private void showPd(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }
    private void hidePd(){

        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
