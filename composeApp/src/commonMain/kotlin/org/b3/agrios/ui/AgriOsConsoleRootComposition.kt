package org.b3.agrios.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleContentContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleRootContainerView
import org.b3.agrios.ui.impl.sidenav.sidenav.SideNavContainerView

object AgriOsConsoleRootComposition {
    fun compose(): Renderable = AgriOsConsoleRootContainerView(
        sideNav = SideNavContainerView(
            title = SideNavComposition.composeTitle(),
            itemGroup = SideNavComposition.composeItemGroup(),
            column = SideNavComposition.composeColumn(),
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp),
        ),
        consoleContent = AgriOsConsoleContentContainerView(
            header = object : ContainerView {
                @Composable
                override fun onRender() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(Color.Yellow)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .background(Color.Red),
                    ) { Text("Here is AgriOS Console Content Header.") }
                }
            },
            content = object : ContainerView {
                @Composable
                override fun onRender() {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .background(Color.Blue),
                        ) { Text("Here is AgriOS Console Content Container.") }
                }
            },
        ),
    )
}
