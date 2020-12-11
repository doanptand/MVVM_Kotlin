package com.ddona.mvvm.util

import timber.log.Timber

class AppDebugTree: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        val className = super.createStackElementTag(element)?.split("$")?.get(0)
        return "($className.kt:${element.lineNumber})#${element.methodName}"
    }
}