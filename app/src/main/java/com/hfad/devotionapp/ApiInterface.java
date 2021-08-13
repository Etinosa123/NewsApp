package com.hfad.devotionapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //the url of the news api
    String BASE_URL = "https://newsapi.org/v2/";

    //getting all news from the site using the news api
    //the Query is a required parameter according the documentation
    @GET("top-headlines")
    Call<mainNews> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );

    //getting categorised news from various sites using the news api
    @GET("top-headlines")
    Call<mainNews> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );
}
