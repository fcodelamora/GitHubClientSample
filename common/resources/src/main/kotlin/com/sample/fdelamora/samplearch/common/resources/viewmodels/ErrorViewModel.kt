package com.sample.fdelamora.samplearch.common.resources.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.sample.fdelamora.samplearch.common.resources.BuildConfig
import com.sample.fdelamora.samplearch.common.resources.R
import com.sample.fdelamora.samplearch.common.resources.utils.mutableStateOf
import com.sample.fdelamora.samplearch.core.entities.ErrorViewData
import com.sample.fdelamora.samplearch.core.entities.exception.AppException
import com.sample.fdelamora.samplearch.core.usecases.IErrorView
import timber.log.Timber
import timber.log.debug

open class ErrorViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application),
    IErrorView {

    var currentError by savedStateHandle.mutableStateOf<ErrorViewData?>(null)

    override fun showErrorView(errorViewData: ErrorViewData) {
        Timber.debug { "showErrorView" }
        currentError = errorViewData
    }

    override fun handleException(exception: Exception) {
        Timber.debug { "handleException: $exception" }
        val error = when (exception) {
            is AppException.GeneralApiException -> {
                ErrorViewData(
                    getString(R.string.error_title_server_error),
                    exception.messageForApplication,
                    exception.errorCode.toString()
                )
            }

            is AppException -> {
                ErrorViewData(
                    getString(R.string.error_title_application_error),
                    exception.messageForApplication
                )
            }

            else -> {
                ErrorViewData(
                    getString(R.string.error_title_unknown_error),
                    getString(R.string.error_message_unknown_error)
                )
            }
        }

        currentError = if (BuildConfig.DEBUG) {
            error.copy(message = "${error.errorMessage}\n\n[Debug Mode] Full Exception:\n$exception")
        } else {
            error
        }
    }

    fun onErrorHandled() {
        currentError = null
    }

    protected fun getString(resId: Int) =
        getApplication<Application>().getString(resId)
}
