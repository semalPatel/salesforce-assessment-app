package com.salesforce.mivi.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salesforce.mivi.Util
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentViewHolder(
    private val contentSearchResult: ContentSearchResultBinding
) : RecyclerView.ViewHolder(contentSearchResult.root) {

    fun update(mediaEntity: MediaEntity) {
        contentSearchResult.title.text = mediaEntity.title
        contentSearchResult.typeAndYear.text = "${mediaEntity.type} (${mediaEntity.year})"
        /*val overrideDimens = Util.dpToPixel(
            dip = 96F,
            context = contentSearchResult.root.context
        )*/
        Glide
            .with(contentSearchResult.root.context)
            .load(mediaEntity.posterUrl)
            .centerCrop()
            //.override
            .into(contentSearchResult.poster)
    }
}