package com.sample.fdelamora.samplearch.debug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.debug.ui.screens.DebugScreen
import com.sample.fdelamora.samplearch.debug.viewmodels.DebugViewModel
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.MockDebugFlags
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DebugActivity : ComponentActivity() {

    private val debugViewModel: DebugViewModel by viewModels()

    @Inject
    lateinit var mockDebugFlags: MockDebugFlags

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppUserInterface {
                DebugScreen.Default.Screen(
                    debugViewModel
                )
            }
        }

        debugViewModel.closeDebugView.observe(this) { event ->
            event?.getContentIfNotHandled()?.let {
                closeAsModal()
            }
        }

        loadCurrentFlags()
    }

    private fun loadCurrentFlags() {
        debugViewModel.run {
            mockDebugFlags.let {
                delay = it.delayInMillis.toString()
                apiErrorTypeSelectionIndex = it.apiErrorType.toIndex()
                userRepoCount = it.userCount.toString()
                userRepoCount = it.repoCount.toString()
                isIncomplete = it.isIncompleteResponse
            }
        }
    }

    private fun closeAsModal() {
        finish()
        overridePendingTransition(
            com.sample.fdelamora.samplearch.R.anim.stay_in_place,
            com.sample.fdelamora.samplearch.R.anim.slide_out_to_bottom
        )
    }
}
