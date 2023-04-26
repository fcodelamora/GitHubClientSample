package com.sample.fdelamora.samplearch.core.usecases

import com.sample.fdelamora.samplearch.core.entities.ErrorViewData

interface IErrorView {
    fun showErrorView(errorViewData: ErrorViewData)
    fun handleException(exception: Exception)
}
