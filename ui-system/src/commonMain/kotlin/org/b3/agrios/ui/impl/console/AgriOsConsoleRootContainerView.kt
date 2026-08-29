package org.b3.agrios.ui.impl.console

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.container.HorizontalListContainerView
import org.b3.agrios.ui.impl.sidenav.sidenav.SideNavContainerView

class AgriOsConsoleRootContainerView(
    private val sideNav: SideNavContainerView,
    private val consoleContent: AgriOsConsoleContentContainerView,
    modifier: Modifier = Modifier,
) : HorizontalListContainerView(
    children = listOf(sideNav, consoleContent),
    modifier = modifier.fillMaxSize(),
)
