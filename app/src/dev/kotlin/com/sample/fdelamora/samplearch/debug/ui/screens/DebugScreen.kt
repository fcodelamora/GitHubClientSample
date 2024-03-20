package com.sample.fdelamora.samplearch.debug.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.DefaultButton
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.Information1Text
import com.sample.fdelamora.samplearch.debug.ui.catalogs.SectionSubtitleText
import com.sample.fdelamora.samplearch.debug.ui.catalogs.SectionTitleText
import com.sample.fdelamora.samplearch.debug.ui.catalogs.views.DebugCheckBox
import com.sample.fdelamora.samplearch.debug.ui.catalogs.views.NumericTextField
import com.sample.fdelamora.samplearch.debug.ui.catalogs.views.SimpleSpinner
import com.sample.fdelamora.samplearch.debug.viewmodels.DebugViewModel

object DebugScreen {

    object Default {

        @Composable
        fun Screen(viewmodel: DebugViewModel) {
            Content(
                delayInMillis = viewmodel.delay,
                availableErrorList = viewmodel.availableErrorList,
                searchUserCount = viewmodel.searchUserCount,
                userReposCount = viewmodel.userRepoCount,
                isIncompleteResponse = viewmodel.isIncomplete,
                apiErrorTypeSelectedIndex = viewmodel.apiErrorTypeSelectionIndex,
                onApiErrorSelected = { index -> viewmodel.apiErrorTypeSelectionIndex = index },
                onDelayInMillisUpdated = { newValue -> viewmodel.delay = newValue },
                onSearchUserCountUpdated = { newValue -> viewmodel.searchUserCount = newValue },
                onUserReposCountUpdated = { newValue -> viewmodel.userRepoCount = newValue },
                onIsIncompleteResponse = { newValue -> viewmodel.isIncomplete = newValue },
                onUpdateFlags = { viewmodel.updateFlags() }
            )
        }

        @Composable
        fun Content(
            delayInMillis: String = "250",
            availableErrorList: List<String> = listOf("No Error"),
            apiErrorTypeSelectedIndex: Int = 0,
            searchUserCount: String = "10",
            userReposCount: String = "5",
            isIncompleteResponse: Boolean = false,
            onApiErrorSelected: (Int) -> Unit = {},
            onDelayInMillisUpdated: (String) -> Unit = {},
            onSearchUserCountUpdated: (String) -> Unit = {},
            onUserReposCountUpdated: (String) -> Unit = {},
            onIsIncompleteResponse: (Boolean) -> Unit = {},
            onUpdateFlags: () -> Unit = {}
        ) {
            val scrollState = rememberScrollState()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Information1Text(text = "PRESS BACK TO CANCEL")

                SectionTitleText(text = "API - General")
                SectionSubtitleText(text = "Errors")
                SimpleSpinner(
                    availableErrorList,
                    apiErrorTypeSelectedIndex,
                    onApiErrorSelected,
                )

                SectionSubtitleText(text = "API Response Delay (ms)")
                NumericTextField(
                    value = delayInMillis,
                    onValueChange = onDelayInMillisUpdated
                )

                Spacer(Modifier.size(16.dp))

                SectionTitleText(text = "API - GitHub Client")

                DebugCheckBox(
                    text = "Do Pagination?\n[IsIncompleteResponse]",
                    isChecked = isIncompleteResponse,
                    onCheckedChange = onIsIncompleteResponse,
                )

                SectionSubtitleText(text = "Search Users - User Count")
                NumericTextField(
                    value = searchUserCount,
                    onValueChange = onSearchUserCountUpdated,
                )

                SectionSubtitleText(text = "User Repos - Repos Count")
                NumericTextField(
                    value = userReposCount,
                    onValueChange = onUserReposCountUpdated,
                )

                Spacer(Modifier.size(24.dp))

                DefaultButton(buttonText = "SET FLAGS") { onUpdateFlags.invoke() }

                Spacer(Modifier.size(24.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DebugScreenPreview() {
    AppUserInterface {
        DebugScreen.Default.Content()
    }
}
