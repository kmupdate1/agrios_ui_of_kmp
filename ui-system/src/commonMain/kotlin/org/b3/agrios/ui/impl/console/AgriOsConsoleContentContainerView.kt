package org.b3.agrios.ui.impl.console

import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.container.VerticalListContainerView

class AgriOsConsoleContentContainerView(
    private val header: ContainerView,
    private val content: ContainerView,
) : VerticalListContainerView(
    listOf(header, content),
) {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")
}
