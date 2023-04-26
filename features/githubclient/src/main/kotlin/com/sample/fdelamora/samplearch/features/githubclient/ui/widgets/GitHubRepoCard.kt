package com.sample.fdelamora.samplearch.features.githubclient.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.theme.Typography
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo

@Composable
fun GitHubRepoCard(
    repo: GitHubRepo,
    onCardTap: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(percent = 10),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onCardTap() }
                .padding(16.dp)
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = repo.name,
                color = MaterialTheme.colors.secondary,
                style = Typography.h6
            )
            if (!repo.language.isNullOrBlank()) {
                Text(
                    text = repo.language ?: "",
                    color = MaterialTheme.colors.primary.copy(alpha = 0.7f),
                    style = Typography.body1
                )
            }

            if (!repo.language.isNullOrBlank()) {
                Text(
                    text = repo.description ?: "",
                    color = MaterialTheme.colors.secondary,
                    style = Typography.body1
                )
            }

            Text(
                text = "★ ${repo.starredCount}",
                color = MaterialTheme.colors.secondary.copy(alpha = 0.7f),
                style = Typography.body2,
                modifier = Modifier.align(End)
            )
        }
    }
}

@Preview
@Composable
private fun GitHubRepoCardPreview() = CatalogView {
    GitHubRepoCard(
        GitHubRepo(
            id = 0,
            name = "Repo",
            fullName = "user/Repo",
            language = "Language",
            starredCount = 1,
            followersCount = 2,
            description = "Description",
            private = false,
            fork = false,
            htmlUrl = ""
        )
    )
}
