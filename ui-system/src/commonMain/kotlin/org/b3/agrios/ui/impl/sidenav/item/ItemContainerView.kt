package org.b3.agrios.ui.impl.sidenav.item

import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.capability.Draggable
import org.b3.agrios.ui.capability.Movable
import org.b3.agrios.ui.capability.Selectable
import org.b3.agrios.ui.container.HorizontalListContainerView
import org.b3.agrios.ui.content.ContentView

class ItemContainerView(
    private val icon: ContentView,
    private val title: ContentView,
    private val notification: ContentView,
) : Selectable, Movable, Draggable,
    HorizontalListContainerView(
        children = listOf(icon, title, notification),
    ) {
    override fun onSelect() { }

    override fun onDeselect() { }

    override fun onMove(x: Int, y: Int) { }

    override fun onDrag() { }

    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")
}
