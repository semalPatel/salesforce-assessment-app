package com.salesforce.mivi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.salesforce.mivi.Constants
import com.salesforce.mivi.databinding.ActivityDetailsBinding
import com.salesforce.mivi.viewmodel.DetailedContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {

    private lateinit var activitySearchBinding: ActivityDetailsBinding

    private val mediaEntityViewModel: DetailedContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        Toast.makeText(this, intent.extras?.get(Constants.CONTENT_IMDB_ID).toString() ?: "Not found", Toast.LENGTH_SHORT).show()
    }
}