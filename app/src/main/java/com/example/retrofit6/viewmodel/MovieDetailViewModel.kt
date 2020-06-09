package com.example.retrofit6.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit6.api.MovieApi
import com.example.retrofit6.model.MovieID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    var movieApi: MovieApi = MovieApi()

    var result: MutableLiveData<MovieID> = MutableLiveData()

    fun getMovie() = result

    fun loadDetail(movieId: Int) {
        val apiCall = movieApi.detailMovie(movieId, "85cd01088c47c4b5e700ab0ee81b6d69")
        apiCall.enqueue(object : Callback<MovieID> {
            override fun onFailure(call: Call<MovieID>, t: Throwable) {
                Log.i("Error >>>>>>", "Loading Fail")
            }

            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {
                Log.i("Success >>>>>>", "Loading success")
                response.isSuccessful.let {
                    var getMovie = response.body()
                    result.value = getMovie
                }
            }

        })
    }

}