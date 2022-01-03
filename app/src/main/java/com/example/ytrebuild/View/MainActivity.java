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
import com.example.ytrebuild.Adapter.ShortsAdapter;
import com.example.ytrebuild.Parent.ParentSearchResponse;
import com.example.ytrebuild.R;
import com.example.ytrebuild.Response.YTSearchResponse;
import com.example.ytrebuild.Retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.alterac.blurkit.BlurLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<YTSearchResponse> ytRes = new ArrayList<>();
    List<YTSearchResponse> ytResFirst = new ArrayList<>();

    RecyclerView videoIdList;
    SearchListAdapter searchListAdapter;

    RecyclerView shortsList;
    ShortsAdapter shortsListAdapter;

    RecyclerView singleVideoIdList;
    SearchListAdapter singleListAdapter;

    LinearLayoutManager linearLayoutManagerVid;
    LinearLayoutManager linearLayoutManagerShorts;
    LinearLayoutManager singleLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int[] categories = {1, 2, 10, 15, 17, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29};

        getVideoIdYT();
//        for (int i : categories) {
//            getPopVidYT(10);
//        }
        getFirstVidYT(10);
        init();

    }



    private void init(){

        videoIdList = findViewById(R.id.videoIdList);
        shortsList = findViewById(R.id.shortsList);
        singleVideoIdList = findViewById(R.id.firstSingleVid);

        searchListAdapter = new SearchListAdapter(getApplicationContext(), ytRes);
        shortsListAdapter = new ShortsAdapter(getApplicationContext());
        singleListAdapter = new SearchListAdapter(getApplicationContext(), ytResFirst);


        linearLayoutManagerVid = new LinearLayoutManager(getApplicationContext());
        linearLayoutManagerVid.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManagerShorts = new LinearLayoutManager(getApplicationContext());
        linearLayoutManagerShorts.setOrientation(RecyclerView.HORIZONTAL);

        singleLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        singleLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        videoIdList.setLayoutManager(linearLayoutManagerVid);
        videoIdList.setAdapter(searchListAdapter);

        shortsList.setLayoutManager(linearLayoutManagerShorts);
        shortsList.setAdapter(shortsListAdapter);

        singleVideoIdList.setLayoutManager(singleLinearLayoutManager);
        singleVideoIdList.setAdapter(singleListAdapter);

    }


    private void getVideoIdYT() {
        Call<ParentSearchResponse> data = RetrofitInstance.getRetrofitInstance().getVideoDetails();

        data.enqueue(new Callback<ParentSearchResponse>() {
            @Override
            public void onResponse(Call<ParentSearchResponse> call, Response<ParentSearchResponse> response) {
                if (response.isSuccessful()) {

//                    ytRes.addAll(response.body().getItems());
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


    private void getFirstVidYT(int id) {
        Call<ParentSearchResponse> data = RetrofitInstance.getRetrofitInstance().getPopVids(id);

        data.enqueue(new Callback<ParentSearchResponse>() {
            @Override
            public void onResponse(Call<ParentSearchResponse> call, Response<ParentSearchResponse> response) {
                if (response.isSuccessful() && response != null) {

                    ytResFirst.add(response.body().getItems().get(0));
                    singleListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ParentSearchResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_SHORT).show();
            }
        });

    }


}