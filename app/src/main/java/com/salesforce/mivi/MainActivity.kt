package com.salesforce.mivi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.salesforce.mivi.network.MovieService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}