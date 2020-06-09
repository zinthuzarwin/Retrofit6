package com.example.retrofit6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.retrofit6.R
import com.example.retrofit6.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        if(arguments != null) {
            var id = arguments?.let {
                DetailFragmentArgs.fromBundle(it)
            }
            movieId = id?.id ?: 0
        }
        obserView()

        //show home when click return
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) { //ture is enabled by default
            override fun handleOnBackPressed() {
                findNavController().popBackStack() //handle back button event
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        movieDetailViewModel.loadDetail(movieId)
    }


    private fun obserView() {
        movieDetailViewModel.getMovie().observe(viewLifecycleOwner,
            Observer { result ->
                Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}")
                    .placeholder(R.drawable.loading)
                    .into(img_movie)
                txtMovieTitle.text = result.original_title
                txtOverview.text = result.overview
                txtRating.text = result.vote_average.toString()
                txtReleaseDate.text = result.release_date

            })
    }
}