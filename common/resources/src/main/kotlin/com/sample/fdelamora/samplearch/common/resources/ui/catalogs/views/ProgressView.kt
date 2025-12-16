package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

// ProgressViews

@Composable
fun SimpleLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background.copy(alpha = 0.7f))
            .clickable(enabled = false) {}
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(75.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun LoadingFromNetworkView(isLoading: Boolean = true) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("animations/40251-network-activity-icon.json")
    )
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.STROKE_COLOR,
            value = MaterialTheme.colors.secondary.toArgb(),
            keyPath = arrayOf("**")
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = MaterialTheme.colors.secondary.toArgb(),
            keyPath = arrayOf("**")
        )
    )
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background.copy(alpha = 0.7f))
                .clickable(enabled = false) {}
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                dynamicProperties = dynamicProperties,
                speed = 0.8f,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ProgressViewCatalogPreview() {
    CatalogView {
        Box(Modifier.size(50.dp)) {
            SimpleLoadingView()
        }
        Box(Modifier.size(50.dp)) {
            LoadingFromNetworkView()
        }
    }
}
