package com.example.ytrebuild.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ytrebuild.Adapter.SearchListAdapter;
import com.example.ytrebuild.Parent.ParentSearchResponse;
import com.example.ytrebuild.R;
import com.example.ytrebuild.Response.YTSearchResponse;
import com.example.ytrebuild.Retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<YTSearchResponse> ytRes = new ArrayList<>();

    RecyclerView videoIdList;
    SearchListAdapter searchListAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getVideoIdYT();
        init();


    }

    private void init(){

        videoIdList = findViewById(R.id.videoIdList);
        searchListAdapter = new SearchListAdapter(getApplicationContext(),ytRes);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        videoIdList.setLayoutManager(linearLayoutManager);
        videoIdList.setAdapter(searchListAdapter);

    }


    private void getVideoIdYT() {
        Call<ParentSearchResponse> data = RetrofitInstance.getRetrofitInstance().getVideoDetails();

        data.enqueue(new Callback<ParentSearchResponse>() {
            @Override
            public void onResponse(Call<ParentSearchResponse> call, Response<ParentSearchResponse> response) {
                if (response.isSuccessful() && response != null) {

                    ytRes.addAll(response.body().getItems());
                    searchListAdapter.notifyDataSetChanged();

//                    ytRes.addAll(response.body().getItems());
                    Log.d("TAG", "onResponse: "+response.body().getItems().get(0).getId().getVideoId());
                }
            }

            @Override
            public void onFailure(Call<ParentSearchResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_SHORT).show();
            }
        });

    }

}