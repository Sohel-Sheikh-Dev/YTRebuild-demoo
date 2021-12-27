package com.example.ytrebuild.View;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytrebuild.Adapter.SearchListAdapter;
import com.example.ytrebuild.Parent.ParentSearchResponse;
import com.example.ytrebuild.R;
import com.example.ytrebuild.Response.YTSearchResponse;
import com.example.ytrebuild.Retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.Collections;
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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        int[] categories = {1, 2, 10, 15, 17, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29};

        getVideoIdYT();
        for (int i : categories) {
            getPopVidYT(i);
        }
        init();


    }

    private void init(){

        videoIdList = findViewById(R.id.videoIdList);
        searchListAdapter = new SearchListAdapter(getApplicationContext(), ytRes);
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
//                    Collections.shuffle(ytRes);
                    searchListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ParentSearchResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getPopVidYT(int id) {
        Call<ParentSearchResponse> data = RetrofitInstance.getRetrofitInstance().getPopVids(id);

        data.enqueue(new Callback<ParentSearchResponse>() {
            @Override
            public void onResponse(Call<ParentSearchResponse> call, Response<ParentSearchResponse> response) {
                if (response.isSuccessful() && response != null) {

                    ytRes.addAll(response.body().getItems());
                    Collections.shuffle(ytRes);
                    searchListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ParentSearchResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_SHORT).show();
            }
        });

    }

}