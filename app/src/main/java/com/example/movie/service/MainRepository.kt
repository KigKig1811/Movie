package com.example.movie.service

class MainRepository constructor(private val  retrofitService: RetrofitService){

    fun getAllMovies() = retrofitService.getAllMovies()

    /*class MainRepository constructor(private val retrofitService: RetrofitService) {

        fun getAllMovies() = retrofitService.getAllMovies()
    }*/
}