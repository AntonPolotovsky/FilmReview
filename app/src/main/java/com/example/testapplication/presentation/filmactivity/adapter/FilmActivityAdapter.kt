package com.example.testapplication.presentation.filmactivity.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.databinding.ItemDescriptionLayoutBinding
import com.example.testapplication.network.remotedatasource.data.Result

class FilmActivityAdapter(
    private val context:Context
) : PagingDataAdapter<Result, FilmActivityAdapter.MovieViewHolder>(MovieDiffCallBack()) {



    class MovieViewHolder(val binding: ItemDescriptionLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        var movieTitle: TextView = binding.title
        var movieDescription: TextView =binding.description
        var movieImage: ImageView = binding.image

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemDescriptionLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.movieTitle.text = current?.headline
        holder.movieDescription.text = current?.summary_short


        val url = Uri.parse(current?.multimedia?.src)
        val image = holder.movieImage
        Glide
            .with(context)
            .load(url)
            .into(image)

    }

    class MovieDiffCallBack : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            //return  oldItem.headline == newItem.headline
            return  oldItem.summary_short == newItem.summary_short
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }


}