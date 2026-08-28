package org.b3.agrios.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.b3.agrios.ui.capability.Drawable
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleContentContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleRootContainerView
import org.b3.agrios.ui.impl.sidenav.sidenav.SideNavContainerView

object AgriOsConsoleRootComposition {
    fun compose(): Drawable = AgriOsConsoleRootContainerView(
        sideNav = SideNavContainerView(
            title = SideNavComposition.composeTitle(),
            itemGroup = SideNavComposition.composeItemGroup(),
            column = SideNavComposition.composeColumn(),
            modifier = Modifier
                .fillMaxHeight()
                .width(220.dp),
        ),
        consoleContent = AgriOsConsoleContentContainerView(
            header = object : ContainerView {
                @Composable
                override fun onReCompose() { }

                @Composable
                override fun onRender() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                    ) { Text("Here is AgriOS Console Content Header.") }
                }
            },
            content = object : ContainerView {
                @Composable
                override fun onReCompose() { }

                @Composable
                override fun onRender() {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        ) { Text("Here is AgriOS Console Content Container.") }
                }
            },
        ),
    )
}
