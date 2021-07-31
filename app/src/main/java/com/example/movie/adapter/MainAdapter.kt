package com.example.movie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.movie.R
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.databinding.AdapterMovieBinding
import com.example.movie.model.Movie
import com.example.movie.view.MainActivity
import com.example.movie.viewmodel.MainViewModel

class MainAdapter(var clickListener: OnMovieItemClickListener): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var movies = mutableListOf<Movie>()

    fun setMovieList(movies : List<Movie>){
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater,parent,false)

        return MainViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int){
        val movie = movies[position]
        holder.binding.name.text=movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        holder.initalize(movies.get(position),clickListener)

    }
    override fun getItemCount(): Int {
        return movies.size
    }
    class MainViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun initalize(item:Movie,action: OnMovieItemClickListener){
            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }
        }
    }
    interface OnMovieItemClickListener{
        fun onItemClick(item: Movie,position: Int)
    }


}