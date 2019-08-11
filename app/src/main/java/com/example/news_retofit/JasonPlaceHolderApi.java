package com.example.news_retofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface JasonPlaceHolderApi {
    @GET("v2/top-headlines?country=EG&apiKey=ccd2b108c53c498385135bad0b83694d")
    Call<Root> getNews();

    @GET("v2/top-headlines")
    Call<Root> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
