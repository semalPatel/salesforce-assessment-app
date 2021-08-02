package com.salesforce.mivi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.salesforce.mivi.Constants
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.databinding.ActivityDetailsBinding
import com.salesforce.mivi.viewmodel.DetailedContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {

    private lateinit var activitySearchBinding: ActivityDetailsBinding
    private lateinit var contentId: String

    private val mediaEntityViewModel: DetailedContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        contentId = intent
            .extras
            ?.get(Constants.CONTENT_IMDB_ID) as? String
            ?: MediaEntity.getDefaultInstance().imdbId
        mediaEntityViewModel.searchContentById(contentId)
        setSupportActionBar(activitySearchBinding.toolbarTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observeContent() {
        mediaEntityViewModel
            .singleContentResult
            .observe(this) { content ->
                when (content) {
                    is Result.Success -> {
                        updateUi(content.data)
                    }
                    is Result.Failure -> {

                    }
                }
            }
    }

    private fun updateUi(mediaEntity: MediaEntity) {
        activitySearchBinding.toolbarTitle.title = mediaEntity.title
        //activitySearchBinding.detailedPlot.text = mediaEntity.plot
        Glide
            .with(this)
            .load(mediaEntity.posterUrl)
            .centerCrop()
            .into(activitySearchBinding.collapsingImage)
    }
}