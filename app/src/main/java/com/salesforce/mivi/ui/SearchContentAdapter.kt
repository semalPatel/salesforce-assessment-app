package com.salesforce.mivi.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salesforce.mivi.Constants
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentAdapter(
    private val context: Context,
    private val contentResult: List<MediaEntity>,
) : RecyclerView.Adapter<SearchContentViewHolder>(), OnContentClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentViewHolder {
        return SearchContentViewHolder(
            contentSearchResult = ContentSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onContentClickListener = this
        )
    }

    override fun onBindViewHolder(holder: SearchContentViewHolder, position: Int) {
        holder.update(contentResult[position])
    }

    override fun getItemCount() = contentResult.size

    override fun onClicked(position: Int) {
        val intent = Intent(
            context,
            DetailedActivity::class.java
        ).apply {
            putExtra(Constants.CONTENT_IMDB_ID, contentResult[position].imdbId)
        }
        context.startActivity(intent)
    }
}

interface OnContentClickListener {
    fun onClicked(position: Int)
}


