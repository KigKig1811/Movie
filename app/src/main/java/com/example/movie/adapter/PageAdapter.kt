package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.Fragment.PageList
import com.example.movie.databinding.PagelistAdapterBinding
import com.example.movie.model.Movie

class PageAdapter(var clickListener: OnMovieItemClickListener) : RecyclerView.Adapter<PageAdapter.PageViewHolder>() {

    var movies = mutableListOf<Movie>()

    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    class PageViewHolder(val binding: PagelistAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun initalize(item: Movie, action: PageAdapter.OnMovieItemClickListener) {
            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PagelistAdapterBinding.inflate(inflater, parent, false)

        return PageAdapter.PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.tvName.text = movie.name
        holder.binding.tvCate.text = movie.category
        holder.binding.tvDesc.text = movie.desc
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imagePageList)
        holder.initalize(movies.get(position),clickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    interface OnMovieItemClickListener {
        fun onItemClick(item: Movie, position: Int){

        }
    }

}