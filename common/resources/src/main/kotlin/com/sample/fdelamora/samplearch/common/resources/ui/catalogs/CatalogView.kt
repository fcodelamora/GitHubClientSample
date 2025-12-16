package com.sample.fdelamora.samplearch.common.resources.ui.catalogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.core.entities.AppTheme

@Composable
fun CatalogView(content: @Composable () -> Unit) = AppUserInterface(AppTheme.RED) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            content()
        }
    }
}
