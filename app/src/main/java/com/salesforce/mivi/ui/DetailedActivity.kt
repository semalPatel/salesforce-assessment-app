package com.salesforce.mivi.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.salesforce.mivi.Constants
import com.salesforce.mivi.R
import com.salesforce.mivi.data.DetailedMediaEntity
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.databinding.ActivityDetailsBinding
import com.salesforce.mivi.viewmodel.DetailedContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var contentId: String

    private val mediaEntityViewModel: DetailedContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        handleIntent()
    }

    private fun setupBinding() {
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
    }

    private fun handleIntent() {
        contentId = intent
            .extras
            ?.get(Constants.CONTENT_IMDB_ID) as? String
            ?: SearchMediaEntity.getDefaultInstance().imdbId
        observeContent()
        mediaEntityViewModel.searchContentById(contentId)
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
                        Snackbar.make(
                            this,
                            activityDetailsBinding.root,
                            "Unable to complete the request",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun updateUi(mediaEntity: DetailedMediaEntity) {
        activityDetailsBinding.collapsingLayout.title = mediaEntity.title
        activityDetailsBinding.detailedPlot.text = mediaEntity.plot
        loadGlideImage(imageView = activityDetailsBinding.collapsingImage, url = mediaEntity.posterUrl)
        loadGlideImage(imageView = activityDetailsBinding.mainPoster, url = mediaEntity.posterUrl)
    }

    private fun loadGlideImage(imageView: ImageView, url: String) {
        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}