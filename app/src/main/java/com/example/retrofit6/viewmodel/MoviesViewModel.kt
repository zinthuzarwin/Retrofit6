package com.example.retrofit6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit6.api.MovieApi
import com.example.retrofit6.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    val movieApi: MovieApi = MovieApi()
    var movieList: MutableLiveData<Movies> = MutableLiveData()

    fun loadResults() {
        val apiCall = movieApi.getTopMovies("85cd01088c47c4b5e700ab0ee81b6d69")

        apiCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {

                response.isSuccessful.let {
                    val resultList = Movies(response.body()?.results ?: emptyList())
                    movieList.value = resultList
                }
            }
        })

    }

}