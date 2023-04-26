package com.sample.fdelamora.samplearch.common.resources.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.theme.SampleArchitectureTheme
import com.sample.fdelamora.samplearch.core.entities.AppTheme

@Composable
fun AppUserInterface(
    selectedTheme: AppTheme = AppTheme.RED,
    content: @Composable () -> Unit
) = SampleArchitectureTheme(theme = selectedTheme) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}
