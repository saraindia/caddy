package com.app.pagination;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.pagination.Adapter.MyAdapter;
import com.app.pagination.model.List_Data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://www.reddit.com/r/health/hot.json?limit=10&after=t3_t3rfqv";
    RecyclerView recyclerView;
    private List<List_Data> dataList;
    private MyAdapter adapter;
    ProgressBar progressBar;
    ImageView backarrow;

    //pagination adding
    int page = 0, limit = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar=findViewById(R.id.progress_bar);
        backarrow=findViewById(R.id.backarrow);
        getSupportActionBar().hide();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();

        //method to call API
        loadJson();

        //pagination
        getDataFromAPI(page, limit);

        //method to finishing app on backpress arrow
        backpress();
    }


    private void backpress() {
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
    }

    private void loadJson() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Kind=jsonObject.getString("kind");
                            Log.e("the_res","==>"+response);
                            Log.e("the_kind","==>"+Kind);
                            JSONObject data=jsonObject.getJSONObject("data");
                            Log.e("the_data","==>"+data);
                            JSONArray jsonArray=data.getJSONArray("children");
                            for(int i=0;i<=jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                JSONObject datajsonobject=jsonObject1.getJSONObject("data");

                                String ups=datajsonobject.getString("ups");
                                Log.e("ups","==>"+ups);

                                String selftext=datajsonobject.getString("selftext");
                                Log.e("selftext","==>"+selftext);

                                String title=datajsonobject.getString("title");
                                Log.e("title","==>"+title);

                                String created=datajsonobject.getString("created");
                                Log.e("created","==>"+created);

                                String thumbnail=datajsonobject.getString("thumbnail");
                                Log.e("thumbnail","==>"+thumbnail);

                                List_Data list_data=new List_Data(selftext,title,Integer.parseInt(ups),thumbnail,true,500.0);
                                dataList.add(list_data);

                               // MyAdapter adapter = new MyAdapter(list_data, getApplicationContext());
                                adapter = new MyAdapter(dataList, MainActivity.this);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage());
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
        requestQueue.getCache().clear();
    }
}