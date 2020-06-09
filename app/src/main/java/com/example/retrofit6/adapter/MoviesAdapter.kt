package com.example.retrofit6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit6.R
import com.example.retrofit6.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(var movieList: List<Result> = ArrayList()) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var mClickListener: ClickListener? = null
    fun setClick(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private lateinit var result : Result

        fun moviesBind(result: Result) {
            this.result = result
            itemView.txtTitle.text = result.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${result.poster_path}")
                .placeholder(R.drawable.loading)
                .into(itemView.imgPoster)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        var myView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(myView)
    }

    override fun getItemCount(): Int {

        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.moviesBind(movieList[position])
    }


    fun updateResultList(resultList: List<Result>) {
        this.movieList = resultList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}