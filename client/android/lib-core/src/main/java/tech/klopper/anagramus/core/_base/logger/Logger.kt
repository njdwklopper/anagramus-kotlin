package tech.klopper.anagramus.core._base.logger

import android.content.Context

class Logger(private val context: Context) : BaseLogger {
    override fun d(message: String) {
        android.util.Log.d(context.packageName, message)
    }

    override fun i(message: String) {
        android.util.Log.i(context.packageName, message)
    }

    override fun e(message: String) {
        android.util.Log.e(context.packageName, message)
    }

    override fun e(message: String, exception: Throwable) {
        android.util.Log.e(context.packageName, message, exception)
    }
}

interface BaseLogger {
    fun d(message: String)
    fun i(message: String)
    fun e(message: String)
    fun e(message: String, exception: Throwable)
}
