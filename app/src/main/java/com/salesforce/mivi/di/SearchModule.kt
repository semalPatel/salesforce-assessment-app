package com.salesforce.mivi.di

import com.salesforce.mivi.network.ContentService
import com.salesforce.mivi.viewmodel.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class SearchModule {

    @Provides
    fun provideContentRepository(
        contentService: ContentService
    ) = ContentRepository(contentService)
}