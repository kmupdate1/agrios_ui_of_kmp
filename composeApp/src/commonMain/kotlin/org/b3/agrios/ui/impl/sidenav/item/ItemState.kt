package org.b3.agrios.ui.impl.sidenav.item

sealed interface ItemState {
    data object Normal : ItemState
    data object Selected : ItemState
    data object Hovered : ItemState
    data object Movable : ItemState
    data object Dragging : ItemState
}
