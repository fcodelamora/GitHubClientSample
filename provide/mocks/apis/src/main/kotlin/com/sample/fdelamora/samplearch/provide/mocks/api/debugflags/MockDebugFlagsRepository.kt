package com.sample.fdelamora.samplearch.provide.mocks.api.debugflags

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockDebugFlagsRepository @Inject constructor(var mockDebugFlags: MockDebugFlags) :
    IMockDebugFlagsRepository {

    override fun saveMockDebugFlags(mockDebugFlags: MockDebugFlags) {
        this.mockDebugFlags = mockDebugFlags
    }

    override fun loadMockDebugFlags(): MockDebugFlags = mockDebugFlags

    override suspend fun handleError() {
        mockDebugFlags.run {
            delay(delayInMillis)
            apiErrorType.handleError()
        }
    }
}
