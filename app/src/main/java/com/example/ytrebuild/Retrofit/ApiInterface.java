package com.example.ytrebuild.Retrofit;

import static com.example.ytrebuild.Credentials.YTCredentials.BASE_API;

import com.example.ytrebuild.Parent.ParentSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search?key=AIzaSyB4Gt0F2v2Zt9wLBFIU9nxvRIzR7L9OZAs&part=snippet,id&order=date&maxResults=1000&regionCode=IN")
    Call<ParentSearchResponse> getVideoDetails();

}
