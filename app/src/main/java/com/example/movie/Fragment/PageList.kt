package com.example.movie.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie.adapter.PageAdapter
import com.example.movie.databinding.PageListBinding
import com.example.movie.model.Movie
import com.example.movie.service.MainRepository
import com.example.movie.service.RetrofitService
import com.example.movie.viewmodel.MainViewModel
import com.example.movie.viewmodel.MyViewModelFactory
import androidx.fragment.app.FragmentTransaction

import com.example.movie.R

class PageList() : Fragment(), PageAdapter.OnMovieItemClickListener {
    private val TAG = "PageList"
    private lateinit var binding: PageListBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = PageAdapter(this)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PageListBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerviewPageList.adapter = adapter


        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })


        viewModel.errMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.getAllMovies()

        Glide.with(this)
            .load("https://i.pinimg.com/originals/c2/28/da/c228daefd8ac8896d839bf416c8ea4f6.jpg")
            .into(binding.imagePageList)
    }

    override fun onItemClick(item: Movie, position: Int) {
        val pageDetails: PageDetails = PageDetails()
       // Toast.makeText(activity,item.name,Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pageDetails)
                .addToBackStack(null)
                .commit()
        }










}