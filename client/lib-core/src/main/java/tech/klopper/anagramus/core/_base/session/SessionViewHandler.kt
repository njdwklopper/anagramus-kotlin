package tech.klopper.anagramus.core._base.session

import tech.klopper.anagramus.core._base.logger.BaseLogger

class InitSessionHandle(
    val view: SessionViewHandler,
    val log: BaseLogger
) : InitSessionHandler {
    private val firebaseHandler = FirebaseUserHandler(log)

    override fun handleAppInitSession() {
        if (firebaseHandler.isUserNotNull()) {
            view.handleGotoHomeView()
        } else {
            view.handleGotoLoginView()
        }
    }
}

interface InitSessionHandler {
    fun handleAppInitSession()
}

interface SessionViewHandler {
    fun handleGotoLoginView()
    fun handleGotoHomeView()
}
