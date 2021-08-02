package com.salesforce.mivi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    val singleContentResult: MutableLiveData<Result<MediaEntity>> = MutableLiveData()
    val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun searchContentById(id: String) {
        viewModelScope.launch {
            updateUiState(UiState.LOADING)
            when(val result = contentRepository.getContentById(id)) {
                is Result.Success -> {
                    updateUiState(UiState.SUCCESSFUL)
                    singleContentResult.postValue(result)
                }
                is Result.Failure -> {
                    updateUiState(UiState.FAILED)
                    singleContentResult.postValue(result)
                }
            }
        }
    }

    private fun updateUiState(newState: UiState) = uiState.postValue(newState)
}