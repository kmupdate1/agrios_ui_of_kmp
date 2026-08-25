package org.b3.agrios.feature

interface Attachable {
    fun onAttach()
    fun onDetach()
}

interface Resizable {
    fun onResize(width: Int, height: Int)
}

interface Movable {
    fun onMove(x: Int, y: Int)
}

interface Selectable {
    fun onSelect()
    fun onDeselect()
}

interface Editable {
    fun onEnter()
    fun onExit()
}

interface Renderable {
    fun onRender()
}

interface DashboardContainer :
    Attachable,
    Resizable,
    Movable,
    Renderable

interface UiContainer :
    DashboardContainer,
    Selectable

interface InteractiveContainer :
    UiContainer,
    Editable
