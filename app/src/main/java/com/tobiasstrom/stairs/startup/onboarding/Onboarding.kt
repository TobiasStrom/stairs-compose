package com.tobiasstrom.stairs.startup.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.composable.ShortcutButton

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(
    viewModel: OnboardingViewModel
) {
    val pages = viewModel.pages
    val pagerState = rememberPagerState(pageCount = pages.size)
    val scope = rememberCoroutineScope()

    BackHandler(
        enabled = pagerState.currentPage > 0 && pagerState.currentPage < pagerState.pageCount
    ) {
        scope.launch(Dispatchers.IO) {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dimen_2x))
    ) {
        val (
            topImage,
            pager,
            button,
            pagerIndicator
        ) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(topImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(pager.top)
                    centerHorizontallyTo(parent)
                }
                .padding(top = dimensionResource(R.dimen.dimen_6x)),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = ""
        )

        HorizontalPager(
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(topImage.bottom)
                    bottom.linkTo(button.top)
                    centerVerticallyTo(parent)
                    width = Dimension.fillToConstraints
                }
                .fillMaxHeight(0.65f),
            state = pagerState
        ) { index ->
            Page(pages[index])
        }

        ShortcutButton(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(pager.bottom)
                    bottom.linkTo(pagerIndicator.top)
                    centerHorizontallyTo(parent)
                }
                .padding(dimensionResource(R.dimen.dimen_2x)),
            onClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    scope.launch(Dispatchers.IO) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.finish()
                }
            }
        ) {
            Text(text = pages[pagerState.currentPage].buttonText)
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .constrainAs(pagerIndicator) {
                    top.linkTo(button.bottom)
                    centerHorizontallyTo(parent)
                }
                .padding(dimensionResource(R.dimen.dimen_2x)),
            pagerState = pagerState
        )
    }
}

@Composable
private fun Page(p: OnboardingPage) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_2x))
    ) {
        val (title, text, image) = createRefs()
        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            },
            text = p.title,
            style = MaterialTheme.typography.h2
        )
        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(title.bottom)
                centerHorizontallyTo(parent)
            },
            text = p.text,
            style = MaterialTheme.typography.subtitle1
        )
        Image(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(text.bottom)
                    centerHorizontallyTo(parent)
                }
                .padding(top = dimensionResource(R.dimen.dimen_4x)),
            painter = painterResource(p.image),
            contentDescription = ""
        )
    }
}
