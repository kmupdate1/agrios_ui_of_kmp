package org.b3.agrios

import android.app.Activity
import android.os.Bundle
import org.b3.agrios.lifecycle.Lifecycle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.onCreate()
        application.onPrepare()
    }

    override fun onStart() {
        super.onStart()
        application.onStart()
    }

    override fun onStop() {
        application.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        application.onDestroy()
        super.onDestroy()
    }

    private val application: Lifecycle = Application
}
