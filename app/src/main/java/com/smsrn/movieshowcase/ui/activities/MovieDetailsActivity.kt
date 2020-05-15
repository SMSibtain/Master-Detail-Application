package com.smsrn.movieshowcase.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.smsrn.movieshowcase.R
import com.smsrn.movieshowcase.adapter.LastAdapter
import com.smsrn.movieshowcase.databinding.ActivityMovieDetailsBinding
import com.smsrn.movieshowcase.extensions.gotoActivity
import com.smsrn.movieshowcase.extensions.obtainViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.PhotoDetails
import com.smsrn.movieshowcase.ui.BaseActivity
import com.smsrn.movieshowcase.util.AppToast
import com.smsrn.movieshowcase.util.Constants.DIGIT_ONE
import com.smsrn.movieshowcase.util.Constants.DIGIT_TWO
import com.smsrn.movieshowcase.util.Constants.IntentKeys.MOVIE_DETAIL_OBJECT
import com.smsrn.movieshowcase.util.NullAwayUtils
import com.smsrn.movieshowcase.viewmodels.MoviesDetailActivityViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.apache.commons.lang3.ObjectUtils

class MovieDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    var movieDetails: MovieDetails? = null
    lateinit var lastAdapter: LastAdapter<PhotoDetails>
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = obtainViewModel(MoviesDetailActivityViewModel::class.java).apply {
            items.observe(this@MovieDetailsActivity, Observer {
                if (!it.isNullOrEmpty()) {
                    linLayoutPhotosCollection.visibility = View.VISIBLE
                }
            })
        }

        binding.lifecycleOwner = this

        if (ObjectUtils.anyNotNull(intent) &&
            ObjectUtils.anyNotNull(intent.extras) &&
            intent.extras!!.containsKey(MOVIE_DETAIL_OBJECT)
        ) {
            movieDetails = intent.extras!!.getParcelable(MOVIE_DETAIL_OBJECT) as MovieDetails?
            binding.viewModel?.movieDetails?.value = movieDetails
        }

        setAdapter()
        showProgressDialog()
        binding.viewModel?.requestMovieImages()
    }

    /**
     * Set Adapter For Movie Photos Collection
     */
    private fun setAdapter() {
        lastAdapter = LastAdapter(R.layout.item_photo)
        recyclerViewPhotosCollection.setHasFixedSize(true)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(DIGIT_TWO, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewPhotosCollection.layoutManager = staggeredGridLayoutManager
        recyclerViewPhotosCollection.adapter = lastAdapter
        recyclerViewPhotosCollection.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(DIGIT_ONE)) {
                    NullAwayUtils.safeLet(
                        binding.viewModel?.pageCount,
                        binding.viewModel?.totalPageCount,
                        binding.viewModel?.isFetchingPhotos,
                        binding.viewModel?.isAllImagesFetched
                    ) { pageCount, totalPageCount, isFetchingPhotos, isAllImagesFetched ->
                        if (isAllImagesFetched) {
                            AppToast.showToast("All images are fetched.")
                        } else if (!isFetchingPhotos) {
                            if (pageCount <= totalPageCount) {
                                binding.viewModel?.requestMovieImages()
                            } else if (pageCount > totalPageCount) {
                                AppToast.showToast("No more images.")
                            }
                        }
                    }
                }
            }
        })
    }
}
