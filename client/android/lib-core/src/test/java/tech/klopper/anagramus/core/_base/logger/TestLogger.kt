package tech.klopper.anagramus.core._base.logger

class Logger : BaseLogger {
    override fun d(message: String) {
        println(message)
    }

    override fun i(message: String) {
        println(message)
    }

    override fun e(message: String) {
        println(message)
    }

    override fun e(message: String, exception: Throwable) {
        println(message)
    }
}
