package com.salesforce.mivi.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentViewHolder(
    private val contentSearchResult: ContentSearchResultBinding,
    private val onContentClickListener: OnContentClickListener
) : RecyclerView.ViewHolder(contentSearchResult.root) {

    init {
        contentSearchResult.root.setOnClickListener { onContentClickListener.onClicked(layoutPosition) }
    }

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