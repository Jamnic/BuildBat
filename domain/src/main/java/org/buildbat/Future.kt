package org.buildbat

class Future<out RESULT>(
        private val futureFunction: () -> RESULT
) {
    fun doWhenResolved(function: (RESULT) -> Unit) {
        Thread(Runnable { function.invoke(futureFunction()) }).start()
    }

    fun resolve() {
        Thread(Runnable { futureFunction() }).start()
    }

    fun <MAPPED_RESULT> then(function: (RESULT) -> MAPPED_RESULT): Future<MAPPED_RESULT> {
        return Future({ function.invoke(futureFunction()) })
    }

    fun <MAPPED_RESULT> then(future: Future<MAPPED_RESULT>): Future<MAPPED_RESULT> {
        return Future({
            futureFunction()
            future.futureFunction.invoke()
        })
    }
}