package com.sample.fdelamora.samplearch.debug.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import co.touchlab.kermit.Logger
import com.sample.fdelamora.samplearch.common.resources.Event
import com.sample.fdelamora.samplearch.common.resources.utils.mutableStateOf
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.ApiErrorType
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.MockDebugFlags
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DebugViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    companion object {
        const val DefaultDelayMillis = "250"
        const val DefaultApiErrorTypeIndex = 0
        const val DefaultUserCount = "10"
        const val DefaultRepoCount = "5"
        const val DefaultResponseIncompleteness = false
    }

    @Inject
    lateinit var mockDebugFlags: MockDebugFlags

    val availableErrorList = ApiErrorType.values().map { it.displayName }

    var delay by savedStateHandle.mutableStateOf(DefaultDelayMillis)

    var apiErrorTypeSelectionIndex by savedStateHandle.mutableStateOf(DefaultApiErrorTypeIndex)

    var searchUserCount by savedStateHandle.mutableStateOf(DefaultUserCount)
    var userRepoCount by savedStateHandle.mutableStateOf(DefaultRepoCount)
    var isIncomplete by savedStateHandle.mutableStateOf(DefaultResponseIncompleteness)

    private val _closeDebugView: MutableLiveData<Event<Unit>?> = MutableLiveData(null)
    val closeDebugView: LiveData<Event<Unit>?> = _closeDebugView

    fun updateFlags() {
        mockDebugFlags.apply {
            delayInMillis = delay.toLongOrNull() ?: DefaultDelayMillis.toLong()
            apiErrorType = ApiErrorType.fromIndex(apiErrorTypeSelectionIndex)
            userCount = searchUserCount.toIntOrNull() ?: DefaultUserCount.toInt()
            repoCount = userRepoCount.toIntOrNull() ?: DefaultRepoCount.toInt()
            isIncompleteResponse = isIncomplete
        }

        Logger.d { "New Flags: $mockDebugFlags" }

        _closeDebugView.value = Event(Unit)
    }
}
