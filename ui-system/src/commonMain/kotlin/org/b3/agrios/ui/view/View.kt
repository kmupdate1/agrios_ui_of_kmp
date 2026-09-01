package org.b3.agrios.ui.view

import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.capability.Renderable

interface View : Renderable {
    val stylesKey: StylesKey
}
