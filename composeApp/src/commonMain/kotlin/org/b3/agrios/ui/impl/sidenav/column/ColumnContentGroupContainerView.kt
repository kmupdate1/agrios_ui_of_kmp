package org.b3.agrios.ui.impl.sidenav.column

import org.b3.agrios.ui.container.HorizontalListContainerView
import org.b3.agrios.ui.content.ContentView

class ColumnContentGroupContainerView(
    private val content: ContentView,
    private val status: ContentView,
    private val description: ContentView,
) : HorizontalListContainerView(
    listOf(content, status, description),
)
