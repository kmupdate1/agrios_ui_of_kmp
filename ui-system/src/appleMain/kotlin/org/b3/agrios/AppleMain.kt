package org.b3.agrios

import androidx.compose.ui.window.ComposeUIViewController
import org.b3.agrios.lifecycle.Lifecycle
import platform.UIKit.UIViewController

object AppleMain : Bootable {
    lateinit var viewController: UIViewController
        private set

    override fun bootstrap() = application.run {
        onCreate()
        onPrepare()

        viewController = ComposeUIViewController(content = ::Content)
    }

    private val application: Lifecycle = Application
}
