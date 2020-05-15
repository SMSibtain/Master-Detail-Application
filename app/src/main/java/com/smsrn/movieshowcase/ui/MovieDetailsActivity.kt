package com.smsrn.movieshowcase.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smsrn.movieshowcase.R
import com.smsrn.movieshowcase.adapter.LastAdapter
import com.smsrn.movieshowcase.databinding.ActivityMovieDetailsBinding
import com.smsrn.movieshowcase.extensions.gotoActivity
import com.smsrn.movieshowcase.extensions.obtainViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.PhotoDetails
import com.smsrn.movieshowcase.util.Constants.IntentKeys.MOVIE_DETAIL_OBJECT
import com.smsrn.movieshowcase.viewmodels.MoviesDetailActivityViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.apache.commons.lang3.ObjectUtils

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    var movieDetails: MovieDetails? = null
    lateinit var lastAdapter: LastAdapter<PhotoDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = obtainViewModel(MoviesDetailActivityViewModel::class.java)
        binding.lifecycleOwner = this

        if (ObjectUtils.anyNotNull(intent) &&
            ObjectUtils.anyNotNull(intent.extras) &&
            intent.extras!!.containsKey(MOVIE_DETAIL_OBJECT)
        ) {
            movieDetails = intent.extras!!.getParcelable(MOVIE_DETAIL_OBJECT) as MovieDetails?
            binding.viewModel?.movieDetails?.value = movieDetails
        }

        setAdapter()
    }

    /**
     * Set Adapter For Movie Photos Collection
     */
    private fun setAdapter() {
        lastAdapter = LastAdapter(R.layout.item_movie_summary, object : LastAdapter.OnItemClickListener<PhotoDetails> {
            override fun onItemClick(item: PhotoDetails) {
                gotoActivity(MovieDetailsActivity::class.java, MOVIE_DETAIL_OBJECT, item)
            }
        })
        recyclerViewPhotosCollection.adapter = lastAdapter
    }
}
