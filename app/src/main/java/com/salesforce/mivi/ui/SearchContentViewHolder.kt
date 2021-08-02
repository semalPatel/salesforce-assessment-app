package com.salesforce.mivi.ui

import androidx.recyclerview.widget.RecyclerView
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentViewHolder(
    private val contentSearchResult: ContentSearchResultBinding
) : RecyclerView.ViewHolder(contentSearchResult.root) {

    fun update(mediaEntity: MediaEntity) {
        contentSearchResult.title.text = mediaEntity.title
    }
}