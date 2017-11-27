package org.buildbat.core.future

open class Future<out RESULT>(
        private val functionToRunInFuture: () -> RESULT
) {

    fun resolve(functionToRunAfterResolve: (RESULT) -> Unit) {
        Thread(Runnable {
            val result = functionToRunInFuture()
            functionToRunAfterResolve.invoke(result)
        }).start()
    }

    fun resolve() {
        Thread(Runnable { functionToRunInFuture() }).start()
    }

    fun <MAPPED_RESULT> then(function: (RESULT) -> MAPPED_RESULT): Future<MAPPED_RESULT> {
        return Future({ function.invoke(functionToRunInFuture()) })
    }

    fun <MAPPED_RESULT> then(future: Future<MAPPED_RESULT>): Future<MAPPED_RESULT> {
        return Future({
            functionToRunInFuture()
            future.functionToRunInFuture.invoke()
        })
    }
}