package com.tobiasstrom.stairs.common.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemHolder(
    val navAction: NavigationAction,
    val icon: ImageVector,
    val label: String
)
