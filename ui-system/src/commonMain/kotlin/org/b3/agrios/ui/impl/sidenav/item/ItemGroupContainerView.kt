package org.b3.agrios.ui.impl.sidenav.item

import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.container.VerticalListContainerView

class ItemGroupContainerView(
    private val items: List<ItemContainerView>,
) : VerticalListContainerView(items) {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")
}
