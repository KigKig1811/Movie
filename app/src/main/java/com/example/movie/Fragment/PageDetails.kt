package com.example.movie.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.adapter.PageAdapter
import com.example.movie.databinding.PageDetailsBinding
import com.example.movie.databinding.PageListBinding
import com.example.movie.model.Movie
import com.example.movie.service.MainRepository
import com.example.movie.service.RetrofitService
import com.example.movie.viewmodel.MainViewModel
import com.example.movie.viewmodel.MyViewModelFactory

class PageDetails : Fragment(), PageAdapter.OnMovieItemClickListener {

    var movies = mutableListOf<Movie>()

    private lateinit var binding: PageDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PageDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://image.freepik.com/free-psd/horizontal-banner-healthy-salad-lunch_23-2148715623.jpg")
            .into(binding.imageview)
        Glide.with(this)
            .load("https://image.freepik.com/free-psd/horizontal-banner-healthy-salad-lunch_23-2148715623.jpg")
            .into(binding.imageview)
        Glide.with(this)
            .load("https://image.freepik.com/free-psd/horizontal-banner-healthy-salad-lunch_23-2148715623.jpg")
            .into(binding.imageBannerPageDetails)
        Glide.with(this)
            .load("https://image.freepik.com/free-psd/horizontal-banner-healthy-salad-lunch_23-2148715623.jpg")
            .into(binding.imagePageDatails)
        
    }


    override fun onItemClick(item: Movie, position: Int) {
        binding.tvCate.text = item.category
        binding.tvName.text = item.name
        binding.tvDesc.text = item.desc

        }

    }

