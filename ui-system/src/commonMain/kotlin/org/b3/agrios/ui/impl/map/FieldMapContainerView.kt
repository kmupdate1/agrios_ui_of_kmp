package org.b3.agrios.ui.impl.map

import org.b3.agrios.ui.container.OverlayContainerView
import org.b3.agrios.ui.content.FieldMapContentView

class FieldMapContainerView(
    private val map: FieldMapContentView,
) : OverlayContainerView(
    listOf(map),
) {
    override fun onAttach() {
        TODO("Not yet implemented")
    }

    override fun onDetach() {
        TODO("Not yet implemented")
    }

    override fun onResize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onMove(x: Int, y: Int) {
        TODO("Not yet implemented")
    }

    override fun onEnter() {
        TODO("Not yet implemented")
    }

    override fun onExit() {
        TODO("Not yet implemented")
    }
}
