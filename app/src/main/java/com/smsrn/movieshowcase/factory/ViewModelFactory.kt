package com.smsrn.movieshowcase.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smsrn.movieshowcase.viewmodels.MainActivityViewModel
import com.smsrn.movieshowcase.viewmodels.MoviesDetailActivityViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel()
                isAssignableFrom(MoviesDetailActivityViewModel::class.java) -> MoviesDetailActivityViewModel()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() =
            instance
                ?: synchronized(ViewModelFactory::class.java) {
                    instance
                        ?: ViewModelFactory().also { instance = it }
                }
    }
}