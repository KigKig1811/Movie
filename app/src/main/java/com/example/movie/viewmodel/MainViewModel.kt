package com.example.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.model.Movie
import com.example.movie.service.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()

    val errMessage = MutableLiveData<String>()


    fun getAllMovies(){

        val repository = repository.getAllMovies()
        repository.enqueue(object : Callback<List<Movie>>{
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                errMessage.postValue(t.message)
            }

        })
    }

}