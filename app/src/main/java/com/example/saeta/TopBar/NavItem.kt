package com.example.saeta.TopBar

import androidx.compose.ui.graphics.painter.Painter
data class NavigationItem(
        val title: String,
        val selectedIcon: Int,
        val screen: Screens
)