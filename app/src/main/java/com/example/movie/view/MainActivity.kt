package com.example.movie.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.bumptech.glide.Glide

import com.example.movie.Fragment.PageList
import com.example.movie.R
import com.example.movie.adapter.MainAdapter
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.model.Movie
import com.example.movie.service.MainRepository
import com.example.movie.service.RetrofitService
import com.example.movie.viewmodel.MainViewModel
import com.example.movie.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() ,MainAdapter.OnMovieItemClickListener {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)


            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewModel =
                ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                    MainViewModel::class.java
                )

            binding.recyclerview.adapter = adapter


            viewModel.movieList.observe(this, Observer {
                Log.d(TAG, "onCreate: $it")
                adapter.setMovieList(it)
            })


            viewModel.errMessage.observe(this, Observer {
        })
        viewModel.getAllMovies()
        Glide.with(this)
            .load("https://image.freepik.com/free-psd/horizontal-banner-healthy-salad-lunch_23-2148715623.jpg")
            .into(binding.imageBanner)


    }

    override fun onItemClick(item: Movie, position: Int) {


        if (position == 1) {

            val pageList: PageList = PageList()


            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pageList).addToBackStack(null).commit()


        }else if(position == 0){
            Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
        }



    }
}


