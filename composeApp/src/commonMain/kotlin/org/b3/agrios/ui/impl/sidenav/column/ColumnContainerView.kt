package org.b3.agrios.ui.impl.sidenav.column

import org.b3.agrios.ui.container.VerticalListContainerView
import org.b3.agrios.ui.content.ContentView
import org.b3.agrios.ui.widget.ExecuteButtonWidgetView

class ColumnContainerView(
    private val title: ContentView,
    private val contentGroup: ColumnContentGroupContainerView,
    private val button: ExecuteButtonWidgetView,
) : VerticalListContainerView(
    listOf(title, contentGroup, button),
)
