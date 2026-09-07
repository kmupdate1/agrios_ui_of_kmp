package org.b3.agrios.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.content.ContentView
import org.b3.agrios.ui.content.ContentViewContext
import org.b3.agrios.ui.content.ContentViewShell
import org.b3.agrios.ui.impl.sidenav.column.ColumnContainerView
import org.b3.agrios.ui.impl.sidenav.column.ColumnContentGroupContainerView
import org.b3.agrios.ui.impl.sidenav.item.ItemContainerView
import org.b3.agrios.ui.impl.sidenav.item.ItemGroupContainerView
import org.b3.agrios.ui.widget.button.ButtonVariant
import org.b3.agrios.ui.widget.button.ExecuteButtonWidgetView

object SideNavComposition {
    fun composeTitle(): ContentView = object : ContentViewShell() {
        override val stylesKey: StylesKey
            get() = TODO("Not yet implemented")

        override val context: ContentViewContext =
            ContentViewContext("sidenav_title", "SideNav Title")

        @Composable
        override fun onRender() = super.create(modifier = Modifier, content = {
            Row {
                Icon(
                    Icons.Default.EnergySavingsLeaf,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Agri ONE",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        })
    }
    fun composeItemGroup(): ItemGroupContainerView = ItemGroupContainerView(
        items = listOf(
            ItemContainerView(
                icon = object : ContentViewShell() {
                    override val stylesKey: StylesKey
                        get() = TODO("Not yet implemented")

                    override val context: ContentViewContext =
                        ContentViewContext("sidenav_item_group", "Item Group")

                    @Composable
                    override fun onRender() = super.create(modifier = Modifier, content = {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                    })
                },
                title = object : ContentViewShell() {
                    override val stylesKey: StylesKey
                        get() = TODO("Not yet implemented")

                    override val context: ContentViewContext =
                        ContentViewContext("sidenav_item_title", "Item Title")

                    @Composable
                    override fun onRender() = super.create(modifier = Modifier, content = {
                        Text("|Settings")
                    })
                },
                notification = object : ContentViewShell() {
                    override val stylesKey: StylesKey
                        get() = TODO("Not yet implemented")

                    override val context: ContentViewContext =
                        ContentViewContext("", "")

                    @Composable
                    override fun onRender() = super.create(modifier = Modifier, content = {})
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
            override val stylesKey: StylesKey
                get() = TODO("Not yet implemented")

            override val context: ContentViewContext =
                ContentViewContext("column_title", "Column Title")

            @Composable
            override fun onRender() = super.create(modifier = Modifier, content = {
                Text("System Status")
            })
        },
        contentGroup = ColumnContentGroupContainerView(
            content = object : ContentViewShell() {
                override val stylesKey: StylesKey
                    get() = TODO("Not yet implemented")

                override val context: ContentViewContext =
                    ContentViewContext("column_content_group", "Column ContentGroup")

                @Composable
                override fun onRender() = super.create(modifier = Modifier, content = {
                    Text("Gateway")
                })
            },
            status = object : ContentViewShell() {
                override val stylesKey: StylesKey
                    get() = TODO("Not yet implemented")

                override val context: ContentViewContext =
                    ContentViewContext("column_status", "Column Status")

                @Composable
                override fun onRender() = super.create(modifier = Modifier, content = {
                    Text("|🟢")
                })
            },
            description = object : ContentViewShell() {
                override val stylesKey: StylesKey
                    get() = TODO("Not yet implemented")

                override val context: ContentViewContext =
                    ContentViewContext("column_description", "Column Description")

                @Composable
                override fun onRender() = super.create(modifier = Modifier, content = {
                    Text("|Online")
                })
            },
        ),
        button = object : ExecuteButtonWidgetView(modifier = Modifier.fillMaxWidth()) {
            override val stylesKey: StylesKey
                get() = TODO("Not yet implemented")

            override val context: ContentViewContext =
                ContentViewContext("", "")

            override val variant: ButtonVariant = ButtonVariant.Outlined

            override fun execute() {
                println("Column 'Show Details' Button clicked.")
            }

            @Composable
            override fun onContent() {
                Text("Show Details")
            }
        },
    )
}
