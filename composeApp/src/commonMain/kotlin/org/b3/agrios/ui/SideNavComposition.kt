package org.b3.agrios.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.b3.agrios.ui.content.ContentView
import org.b3.agrios.ui.content.ContentViewContext
import org.b3.agrios.ui.content.ContentViewShell
import org.b3.agrios.ui.impl.sidenav.column.ColumnContainerView
import org.b3.agrios.ui.impl.sidenav.column.ColumnContentGroupContainerView
import org.b3.agrios.ui.impl.sidenav.item.ItemContainerView
import org.b3.agrios.ui.impl.sidenav.item.ItemGroupContainerView
import org.b3.agrios.ui.widget.ExecuteButtonWidgetView

object SideNavComposition {
    fun composeTitle(): ContentView = object : ContentViewShell() {
        override val context: ContentViewContext =
            ContentViewContext("sidenav_title", "SideNav Title")
        @Composable
        override fun onRender() = super.create {
            Text("AgriOS")
        }
    }
    fun composeItemGroup(): ItemGroupContainerView = ItemGroupContainerView(
        items = listOf(
            ItemContainerView(
                icon = object : ContentViewShell() {
                    override val context: ContentViewContext =
                        ContentViewContext("sidenav_item_group", "Item Group")
                    @Composable
                    override fun onRender() = super.create {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                },
                title = object : ContentViewShell() {
                    override val context: ContentViewContext =
                        ContentViewContext("sidenav_item_title", "Item Title")
                    @Composable
                    override fun onRender() = super.create {
                        Text("|Settings")
                    }
                },
                notification = object : ContentViewShell() {
                    override val context: ContentViewContext =
                        ContentViewContext("", "")
                    @Composable
                    override fun onRender() = super.create {
                        Text("|Notifications: 3")
                    }
                }
            ),
            /*
            ItemContainerView(),
            ItemContainerView(),
            ItemContainerView(),
            ItemContainerView(),
            */
        ),
    )
    fun composeColumn(): ColumnContainerView = ColumnContainerView(
        title = object : ContentViewShell() {
            override val context: ContentViewContext =
                ContentViewContext("column_title", "Column Title")

            @Composable
            override fun onRender() = super.create {
                println("system status")
                Text("System Status")
            }
        },
        contentGroup = ColumnContentGroupContainerView(
            content = object : ContentViewShell() {
                override val context: ContentViewContext =
                    ContentViewContext("column_content_group", "Column ContentGroup")

                @Composable
                override fun onRender() = super.create {
                    println("gateway")
                    Text("Gateway")
                }
            },
            status = object : ContentViewShell() {
                override val context: ContentViewContext =
                    ContentViewContext("column_status", "Column Status")

                @Composable
                override fun onRender() = super.create {
                    println("status")
                    Text("|🟢")
                }
            },
            description = object : ContentViewShell() {
                override val context: ContentViewContext =
                    ContentViewContext("column_description", "Column Description")

                @Composable
                override fun onRender() = super.create {
                    println("description")
                    Text("|Online")
                }
            },
        ),
        button = ExecuteButtonWidgetView(),
    )
}
