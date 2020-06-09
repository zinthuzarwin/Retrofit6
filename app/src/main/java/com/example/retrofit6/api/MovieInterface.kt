package com.example.retrofit6.api

import com.example.retrofit6.model.Movies
import com.example.retrofit6.model.MovieID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("top_rated")  //Query String parameter
    fun getTopRatedMovie(@Query("api_key") apiKey: String): Call<Movies>

    @GET("{movie_id}")
    fun detailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieID>
}