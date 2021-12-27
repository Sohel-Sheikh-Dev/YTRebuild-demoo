package com.example.ytrebuild.Retrofit;

import static com.example.ytrebuild.Credentials.YTCredentials.BASE_API;

import com.example.ytrebuild.Parent.ParentSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //videos?part=snippet&chart=mostPopular&order=date&maxResults=1000&regionCode=IN&key=AIzaSyB4Gt0F2v2Zt9wLBFIU9nxvRIzR7L9OZAs
    //videos?part=snippet&chart=mostPopular&regionCode=IN&videoCategoryId=20&key=AIzaSyB4Gt0F2v2Zt9wLBFIU9nxvRIzR7L9OZAs
    @GET("videos?part=snippet&chart=mostPopular&order=date&maxResults=1000&regionCode=US&key=AIzaSyB4Gt0F2v2Zt9wLBFIU9nxvRIzR7L9OZAs")
    Call<ParentSearchResponse> getVideoDetails();

    @GET("videos?part=snippet&chart=mostPopular&order=date&maxResults=1000&regionCode=US&key=AIzaSyB4Gt0F2v2Zt9wLBFIU9nxvRIzR7L9OZAs")
    Call<ParentSearchResponse> getPopVids(@Query("videoCategoryId") int id);

}
