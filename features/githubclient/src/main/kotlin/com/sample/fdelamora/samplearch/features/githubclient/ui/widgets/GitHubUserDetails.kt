package com.sample.fdelamora.samplearch.features.githubclient.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.sample.fdelamora.samplearch.common.resources.AppDrawable
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.theme.Typography
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.features.githubclient.R

@Composable
fun GitHubUserDetails(
    gitHubUser: GitHubUser,
    modifier: Modifier = Modifier
) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(gitHubUser.avatarUrl)
            .placeholder(AppDrawable.ic_cloud)
            .error(AppDrawable.ic_error)
            .crossfade(true)
            .scale(Scale.FILL)
            .build()
    )

    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            colorFilter = if (painter.state !is AsyncImagePainter.State.Success) {
                tint(MaterialTheme.colors.secondary)
            } else {
                null
            },
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.background)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = CircleShape
                )
        )

        Spacer(Modifier.size(10.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = gitHubUser.name ?: "-",
                color = MaterialTheme.colors.secondary,
                style = Typography.h6
            )
            Text(
                text = gitHubUser.login,
                color = MaterialTheme.colors.secondary.copy(alpha = 0.75f),
                style = Typography.subtitle1
            )

            Column(Modifier.align(End)) {
                Text(
                    text = stringResource(R.string.user_details_card_followers, gitHubUser.followers ?: 0),
                    color = MaterialTheme.colors.primary.copy(alpha = 0.75f),
                    style = Typography.body2
                )
                Text(
                    text = stringResource(R.string.user_details_card_following, gitHubUser.following ?: 0),
                    color = MaterialTheme.colors.primary.copy(alpha = 0.75f),
                    style = Typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
private fun GitHubUserCardPreview() = CatalogView {
    GitHubUserDetails(
        GitHubUser(
            login = "login",
            id = 0,
            avatarUrl = "",
            gravatarId = null,
            url = "",
            htmlUrl = "",
            followersUrl = "",
            reposUrl = "",
            publicRepos = null,
            publicGists = null,
            followers = 1,
            following = null,
            name = "Name"
        )
    )
}
