package com.sample.fdelamora.samplearch.common.resources.ui

import androidx.compose.runtime.Composable
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.ErrorDialog
import com.sample.fdelamora.samplearch.common.resources.viewmodels.ErrorViewModel

@Composable
fun SharedErrorsHandler(
    viewModel: ErrorViewModel,
) {
    viewModel.currentError?.let { errorViewData ->
        ErrorDialog(
            errorViewData = errorViewData,
            onOkPressed = { viewModel.currentError = null }
        )
    }
}
