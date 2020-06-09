package com.example.retrofit6.api

import com.example.retrofit6.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    private val movieInterface: MovieInterface


    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieInterface = retrofit.create(MovieInterface::class.java)
    }

    fun getTopMovies(apiKey: String): Call<Movies> = movieInterface.getTopRatedMovie(apiKey)
    fun detailMovie(movieId : Int , apiKey: String) = movieInterface.detailMovie(movieId,apiKey)
}