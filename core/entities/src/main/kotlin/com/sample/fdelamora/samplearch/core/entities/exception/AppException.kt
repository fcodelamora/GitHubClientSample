@file:Suppress("SpellCheckingInspection")

package com.sample.fdelamora.samplearch.core.entities.exception

sealed class AppException(val messageForApplication: String) : Exception(messageForApplication) {
    object UnknownException : AppException("Network error or another unknown issue")

    data class GeneralApiException(
        override val messageFromApi: String,
        override val errorCode: Int
    ) : AppException(messageFromApi), IGeneralApiException

    interface IGeneralApiException {
        val messageFromApi: String
        val errorCode: Int
    }
}
