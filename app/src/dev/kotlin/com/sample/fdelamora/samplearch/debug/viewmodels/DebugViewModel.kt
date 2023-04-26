package com.sample.fdelamora.samplearch.debug.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sample.fdelamora.samplearch.common.resources.Event
import com.sample.fdelamora.samplearch.common.resources.utils.mutableStateOf
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.ApiErrorType
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.MockDebugFlags
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import timber.log.debug
import javax.inject.Inject

@HiltViewModel
class DebugViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {

    companion object {
        const val DEFAULT_DELAY_MILLIS = "250"
        const val DEFAULT_API_ERROR_TYPE_INDEX = 0
        const val DEFAULT_USER_COUNT = "10"
        const val DEFAULT_REPO_COUNT = "5"
        const val DEFAULT_RESPONSE_INCOMPLETENESS = false
    }

    @Inject
    lateinit var mockDebugFlags: MockDebugFlags

    val availableErrorList = ApiErrorType.values().map { it.displayName }

    var delay by savedStateHandle.mutableStateOf(DEFAULT_DELAY_MILLIS)

    var apiErrorTypeSelectionIndex by savedStateHandle.mutableStateOf(DEFAULT_API_ERROR_TYPE_INDEX)

    var searchUserCount by savedStateHandle.mutableStateOf(DEFAULT_USER_COUNT)
    var userRepoCount by savedStateHandle.mutableStateOf(DEFAULT_REPO_COUNT)
    var isIncomplete by savedStateHandle.mutableStateOf(DEFAULT_RESPONSE_INCOMPLETENESS)

    private val _closeDebugView: MutableLiveData<Event<Unit>?> = MutableLiveData(null)
    val closeDebugView: LiveData<Event<Unit>?> = _closeDebugView

    fun updateFlags() {
        mockDebugFlags.apply {
            delayInMillis = delay.toLongOrNull() ?: DEFAULT_DELAY_MILLIS.toLong()
            apiErrorType = ApiErrorType.fromIndex(apiErrorTypeSelectionIndex)
            userCount = searchUserCount.toIntOrNull() ?: DEFAULT_USER_COUNT.toInt()
            repoCount = userRepoCount.toIntOrNull() ?: DEFAULT_REPO_COUNT.toInt()
            isIncompleteResponse = isIncomplete
        }

        Timber.debug { "New Flags: $mockDebugFlags" }

        _closeDebugView.value = Event(Unit)
    }
}
